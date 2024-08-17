package ru.mediasoft.test.controller.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mediasoft.test.model.StatusType;
import ru.mediasoft.test.model.TrackingEvent;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TrackingEventDto {
    private LocalDateTime timestamp;
    private StatusType status;
    private String postOffice;

    public TrackingEventDto(TrackingEvent trackingEvent){
        this.timestamp = trackingEvent.getTimestamp();
        this.status = trackingEvent.getStatus();
        this.postOffice = "Адрес пункта: " + trackingEvent.getPostOffice().getAddress() + " - " +
                "Индекс пункта: " + trackingEvent.getPostOffice().getIndex();
    }
}
