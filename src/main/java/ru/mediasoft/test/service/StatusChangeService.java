package ru.mediasoft.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mediasoft.test.model.PostOffice;
import ru.mediasoft.test.model.PostalItem;
import ru.mediasoft.test.model.StatusType;
import ru.mediasoft.test.model.TrackingEvent;
import ru.mediasoft.test.repository.PostalItemRepository;
import ru.mediasoft.test.repository.TrackingEventRepository;
import ru.mediasoft.test.utils.EntityUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StatusChangeService {

    private final TrackingEventRepository trackingEventRepository;
    private final PostalItemRepository postalItemRepository;
    private final EntityUtils entityUtils;

    public String arrivalAtIntermediatePoint(Long itemId, Long postId) {
        PostalItem item = entityUtils.getPostalItem(itemId);
        PostOffice postOffice = entityUtils.getPostOffice(postId);
        List<TrackingEvent> listEvents = item.getTrackingEvents();
        StatusType lastStatus = StatusType.valueOf(listEvents.get(listEvents.size() - 1).getStatus().toString());
        PostOffice currentPostOffice = listEvents.get(listEvents.size() - 1).getPostOffice();

        if (lastStatus == StatusType.LEFT_FROM_THE_POST_OFFICE && currentPostOffice != postOffice){
            createTrackingEvent(item, postOffice, StatusType.ARRIVED_AT_THE_POST_OFFICE);

            if (Objects.equals(postOffice.getIndex(), item.getRecipientIndex())) {
                createTrackingEvent(item, postOffice, StatusType.READY_TO_RECEIVE);
                return "Post item was arrival to destination post";
            }
            return "Post item was arrival to new post";
        }else {
            return "Can't arrival post item to new post";
        }
    }

    public String departureFromIntermediatePoint(Long itemId){
        PostalItem item = entityUtils.getPostalItem(itemId);
        List<TrackingEvent> listEvents = item.getTrackingEvents();
        StatusType lastStatus = StatusType.valueOf(listEvents.get(listEvents.size() - 1).getStatus().toString());
        if (lastStatus == StatusType.REGISTER ||  lastStatus == StatusType.ARRIVED_AT_THE_POST_OFFICE){
            createTrackingEvent(item, listEvents.get(listEvents.size() - 1).getPostOffice(), StatusType.LEFT_FROM_THE_POST_OFFICE);
            return "Post item was departure from post office";
        }
        else {
            return "Post item already was departure from post office";
        }
    }

    public String receiptByAddressee(Long itemId){
        PostalItem item = entityUtils.getPostalItem(itemId);
        List<TrackingEvent> listEvents = item.getTrackingEvents();

        if(Objects.equals(item.getRecipientIndex(), listEvents.get(listEvents.size() - 1).getPostOffice().getIndex())
                && listEvents.get(listEvents.size() - 1).getStatus() != StatusType.RECEIVED){
            TrackingEvent trackingEvent = TrackingEvent.builder()
                    .timestamp(LocalDateTime.now())
                    .status(StatusType.RECEIVED)
                    .postOffice(listEvents.get(listEvents.size() - 1).getPostOffice())
                    .postalItem(item)
                    .build();

            trackingEventRepository.save(trackingEvent);
            return "Post item was receipt by addressee";
        }else {
            return "Wait post item in your post office or Post item was receipt by addressee";
        }
    }

    private void createTrackingEvent(PostalItem item, PostOffice postOffice, StatusType status) {
        TrackingEvent trackingEvent = TrackingEvent.builder()
                .postalItem(item)
                .postOffice(postOffice)
                .timestamp(LocalDateTime.now())
                .status(status)
                .build();

        trackingEventRepository.save(trackingEvent);
        item.addToList(trackingEvent);
        postalItemRepository.save(item);
    }
}
