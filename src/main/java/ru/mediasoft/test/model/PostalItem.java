package ru.mediasoft.test.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_post_item")
public class PostalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ItemType type;
    private Integer recipientIndex;
    private String recipientAddress;
    private String recipientName;
}
