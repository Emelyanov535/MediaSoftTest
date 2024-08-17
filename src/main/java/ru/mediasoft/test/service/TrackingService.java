package ru.mediasoft.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mediasoft.test.controller.Dto.StatusAndHistoryDto;
import ru.mediasoft.test.controller.Dto.TrackingEventDto;
import ru.mediasoft.test.model.PostalItem;
import ru.mediasoft.test.model.TrackingEvent;
import ru.mediasoft.test.utils.EntityUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TrackingService {

    private final EntityUtils entityUtils;
    public StatusAndHistoryDto getStatusAndHistory(Long itemId){
        PostalItem item = entityUtils.getPostalItem(itemId);
        List<TrackingEvent> history = item.getTrackingEvents();
        List<TrackingEvent> copyHistory = new ArrayList<>(history);
        Collections.reverse(copyHistory);
        String status = copyHistory.get(0).getStatus().toString();
        List<TrackingEventDto> historyDto = new ArrayList<>(copyHistory.stream().map(TrackingEventDto :: new).toList());
        return new StatusAndHistoryDto(status, historyDto);
    }
}
