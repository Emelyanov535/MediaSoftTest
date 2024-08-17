package ru.mediasoft.test.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mediasoft.test.model.PostOffice;
import ru.mediasoft.test.model.PostalItem;
import ru.mediasoft.test.model.StatusType;
import ru.mediasoft.test.model.TrackingEvent;
import ru.mediasoft.test.repository.TrackingEventRepository;
import ru.mediasoft.test.utils.EntityUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StatusChangeService {

    private final TrackingEventRepository trackingEventRepository;
    private final EntityUtils entityUtils;

    public void arrivalAtIntermediatePoint(Long itemId, Long postId) {
        PostalItem item = entityUtils.getPostalItem(itemId);
        PostOffice postOffice = entityUtils.getPostOffice(postId);

        createTrackingEvent(item, postOffice, StatusType.ARRIVED_AT_THE_POST_OFFICE);

        if (Objects.equals(postOffice.getIndex(), item.getRecipientIndex())) {
            createTrackingEvent(item, postOffice, StatusType.READY_TO_RECEIVE);
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
    }

    public ResponseEntity<String> departureFromIntermediatePoint(Long itemId){
        PostalItem item = entityUtils.getPostalItem(itemId);

        TrackingEvent trackingEvent = TrackingEvent.builder()
                .postalItem(item)
                .postOffice(item.getTrackingEvents().get(0).getPostOffice())
                .timestamp(LocalDateTime.now())
                .status(StatusType.LEFT_FROM_THE_POST_OFFICE)
                .build();

        trackingEventRepository.save(trackingEvent);
        item.addToList(trackingEvent);
        return ResponseEntity.ok("Посылка покинула пункт");
    }

    public ResponseEntity<?> receiptByAddressee(Long itemId){
        PostalItem item = entityUtils.getPostalItem(itemId);

        TrackingEvent trackingEvent = TrackingEvent.builder()
                .timestamp(LocalDateTime.now())
                .status(StatusType.RECEIVED)
                .postOffice(item.getTrackingEvents().get(0).getPostOffice())
                .postalItem(item)
                .build();

        trackingEventRepository.save(trackingEvent);
        return ResponseEntity.ok("Посылка выдана получателю");
    }
}
