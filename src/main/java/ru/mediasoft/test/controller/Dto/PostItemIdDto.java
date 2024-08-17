package ru.mediasoft.test.controller.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostItemIdDto {
    @NotNull(message = "Id cannot be null")
    private Long Id;
}
