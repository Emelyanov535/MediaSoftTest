package ru.mediasoft.test.controller.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mediasoft.test.model.ItemType;
import ru.mediasoft.test.model.PostalItem;

@Data
@NoArgsConstructor
public class RegisterPostalItemDto {
    @NotNull(message = "Type cannot be null")
    private ItemType type;

    @NotNull(message = "Recipient index cannot be null")
    private Integer recipientIndex;

    @NotBlank(message = "Recipient address cannot be blank")
    private String recipientAddress;

    @NotBlank(message = "Recipient name cannot be blank")
    private String recipientName;

    @NotNull(message = "Post office ID cannot be null")
    private Long postOfficeId;

    public RegisterPostalItemDto(PostalItem postalItem, Long postOfficeId){
        this.type = postalItem.getType();
        this.recipientAddress = postalItem.getRecipientAddress();
        this.recipientIndex = postalItem.getRecipientIndex();
        this.recipientName = postalItem.getRecipientName();
        this.postOfficeId = postOfficeId;
    }
}
