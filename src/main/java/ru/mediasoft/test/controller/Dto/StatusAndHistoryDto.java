package ru.mediasoft.test.controller.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StatusAndHistoryDto {
    private String status;
    private List<TrackingEventDto> historyTracking;

    public StatusAndHistoryDto(String status, List<TrackingEventDto> historyTracking){
        this.status = status;
        this.historyTracking = historyTracking;
    }
}
