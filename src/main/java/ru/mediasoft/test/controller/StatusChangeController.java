package ru.mediasoft.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mediasoft.test.controller.Dto.DataForChangeItemStatusDto;
import ru.mediasoft.test.controller.Dto.PostItemIdDto;
import ru.mediasoft.test.service.StatusChangeService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/status-change")
public class StatusChangeController {
    private final StatusChangeService statusChangeService;
    @PostMapping("/arrivalAtIntermediatePoint")
    public ResponseEntity<String> arrivalAtIntermediatePoint(@RequestBody @Validated DataForChangeItemStatusDto data) {
        return ResponseEntity.ok(statusChangeService.arrivalAtIntermediatePoint(data.getPostItemId(), data.getPostOfficeId()));
    }

    @PostMapping("/departureFromIntermediatePoint")
    public ResponseEntity<String> departureFromIntermediatePoint(@RequestBody @Validated PostItemIdDto data){
        return ResponseEntity.ok(statusChangeService.departureFromIntermediatePoint(data.getId()));
    }

    @PostMapping("/receiptByAddressee")
    public ResponseEntity<String> receiptByAddressee(@RequestBody @Validated PostItemIdDto data){
        return ResponseEntity.ok(statusChangeService.receiptByAddressee(data.getId()));
    }
}
