package ru.mediasoft.test.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_post_item")
public class PostalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ItemType type;
    private Integer recipientIndex;
    private String recipientAddress;
    private String recipientName;

    @OneToMany(mappedBy = "postalItem", cascade = CascadeType.ALL)
    private List<TrackingEvent> trackingEvents;

    public void addToList(TrackingEvent trackingEvent){
        trackingEvents.add(trackingEvent);
    }
}
