package ru.mediasoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mediasoft.test.model.PostOffice;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
}
