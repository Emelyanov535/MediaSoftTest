package ru.mediasoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mediasoft.test.model.PostalItem;

public interface PostalItemRepository extends JpaRepository<PostalItem, Long> {
}
