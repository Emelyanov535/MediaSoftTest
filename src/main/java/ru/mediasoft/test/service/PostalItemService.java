package ru.mediasoft.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mediasoft.test.controller.Dto.PostalItemDto;
import ru.mediasoft.test.controller.Dto.RegisterPostalItemDto;
import ru.mediasoft.test.model.PostOffice;
import ru.mediasoft.test.model.PostalItem;
import ru.mediasoft.test.model.StatusType;
import ru.mediasoft.test.model.TrackingEvent;
import ru.mediasoft.test.repository.PostalItemRepository;
import ru.mediasoft.test.repository.TrackingEventRepository;
import ru.mediasoft.test.utils.EntityUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class PostalItemService {

    private final PostalItemRepository postalItemRepository;
    private final TrackingEventRepository trackingEventRepository;
    private final EntityUtils entityUtils;
    public PostalItemDto registerPostalItem(RegisterPostalItemDto data) {
        PostOffice postOffice = entityUtils.getPostOffice(data.getPostOfficeId());

        PostalItem item = PostalItem.builder()
                .type(data.getType())
                .recipientAddress(data.getRecipientAddress())
                .recipientIndex(data.getRecipientIndex())
                .recipientName(data.getRecipientName())
                .trackingEvents(new ArrayList<>())
                .build();

        TrackingEvent trackingEvent = TrackingEvent.builder()
                .status(StatusType.REGISTER)
                .postOffice(postOffice)
                .timestamp(LocalDateTime.now())
                .postalItem(item)
                .build();

        item.addToList(trackingEvent);
        postalItemRepository.save(item);
        trackingEventRepository.save(trackingEvent);

        return new PostalItemDto(item);
    }
}
