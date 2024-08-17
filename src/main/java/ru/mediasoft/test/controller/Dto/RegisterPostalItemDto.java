package ru.mediasoft.test.controller.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mediasoft.test.model.ItemType;
import ru.mediasoft.test.model.PostalItem;

@Data
@NoArgsConstructor
public class RegisterPostalItemDto {
    private ItemType type;
    private Integer recipientIndex;
    private String recipientAddress;
    private String recipientName;
    private Long postOfficeId;

    public RegisterPostalItemDto(PostalItem postalItem, Long postOfficeId){
        this.type = postalItem.getType();
        this.recipientAddress = postalItem.getRecipientAddress();
        this.recipientIndex = postalItem.getRecipientIndex();
        this.recipientName = postalItem.getRecipientName();
        this.postOfficeId = postOfficeId;
    }
}
