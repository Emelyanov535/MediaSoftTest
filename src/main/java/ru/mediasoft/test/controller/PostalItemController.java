package ru.mediasoft.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mediasoft.test.controller.Dto.PostalItemDto;
import ru.mediasoft.test.controller.Dto.RegisterPostalItemDto;
import ru.mediasoft.test.service.PostalItemService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/postal-items")
public class PostalItemController {

    private final PostalItemService postalItemService;
    @PostMapping()
    public ResponseEntity<PostalItemDto> registerPostalItem(@RequestBody @Validated RegisterPostalItemDto data) {
        PostalItemDto createdItem = postalItemService.registerPostalItem(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }
}
