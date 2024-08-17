package ru.mediasoft.test.controller.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataForChangeItemStatusDto {
    @NotNull
    private Long postItemId;
    @NotNull
    private Long postOfficeId;

    public DataForChangeItemStatusDto(Long postItemId, Long postOfficeId){
        this.postItemId = postItemId;
        this.postOfficeId = postOfficeId;
    }
}
