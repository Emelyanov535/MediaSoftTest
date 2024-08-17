package ru.mediasoft.test.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_tracking_event")
public class TrackingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    private StatusType status;

    @ManyToOne
    @JoinColumn(name = "post_office_id")
    private PostOffice postOffice;

    @ManyToOne
    @JoinColumn(name = "postal_item_id")
    private PostalItem postalItem;
}
