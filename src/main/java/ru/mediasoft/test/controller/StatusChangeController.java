package ru.mediasoft.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mediasoft.test.controller.Dto.DataForChangeItemStatusDto;
import ru.mediasoft.test.service.StatusChangeService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/status-change")
public class StatusChangeController {
    private final StatusChangeService statusChangeService;
    @PostMapping("/arrivalAtIntermediatePoint")
    public ResponseEntity<String> arrivalAtIntermediatePoint(@RequestBody DataForChangeItemStatusDto data) {
        statusChangeService.arrivalAtIntermediatePoint(data.getPostItemId(), data.getPostOfficeId());
        return ResponseEntity.ok("Посылка прибыла в пункт");
    }

    @PostMapping("/departureFromIntermediatePoint")
    public ResponseEntity<?> departureFromIntermediatePoint(@RequestBody Long itemId){
        return ResponseEntity.ok(statusChangeService.departureFromIntermediatePoint(itemId));
    }

    @PostMapping("/receiptByAddressee")
    public ResponseEntity<?> receiptByAddressee(@RequestBody Long itemId){
        return ResponseEntity.ok(statusChangeService.receiptByAddressee(itemId));
    }
}
