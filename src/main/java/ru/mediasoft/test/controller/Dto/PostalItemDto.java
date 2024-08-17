package ru.mediasoft.test.controller.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mediasoft.test.model.ItemType;
import ru.mediasoft.test.model.PostalItem;
@NoArgsConstructor
@Getter
@Setter
public class PostalItemDto {
    private Long id;
    private ItemType type;
    private Integer recipientIndex;
    private String recipientAddress;
    private String recipientName;

    public PostalItemDto(PostalItem postalItem){
        this.id = postalItem.getId();
        this.type = postalItem.getType();
        this.recipientIndex = postalItem.getRecipientIndex();
        this.recipientAddress = postalItem.getRecipientAddress();
        this.recipientName = postalItem.getRecipientName();
    }
}
