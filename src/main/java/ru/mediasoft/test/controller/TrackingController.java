package ru.mediasoft.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mediasoft.test.controller.Dto.StatusAndHistoryDto;
import ru.mediasoft.test.service.TrackingService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/tracking")
public class TrackingController {
    private final TrackingService trackingService;

    @GetMapping("/{id}")
    public ResponseEntity<StatusAndHistoryDto> getStatusAndHistory(@PathVariable Long id){
        return ResponseEntity.ok(trackingService.getStatusAndHistory(id));
    }
}
