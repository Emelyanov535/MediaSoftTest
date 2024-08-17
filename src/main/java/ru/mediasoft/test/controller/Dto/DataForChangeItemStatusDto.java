package ru.mediasoft.test.controller.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataForChangeItemStatusDto {
    private Long postItemId;
    private Long postOfficeId;

    public DataForChangeItemStatusDto(Long postItemId, Long postOfficeId){
        this.postItemId = postItemId;
        this.postOfficeId = postOfficeId;
    }
}
