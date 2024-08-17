package ru.mediasoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mediasoft.test.model.TrackingEvent;

public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
}
