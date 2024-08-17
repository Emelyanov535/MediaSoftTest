package ru.mediasoft.test.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mediasoft.test.model.PostOffice;
import ru.mediasoft.test.model.PostalItem;
import ru.mediasoft.test.repository.PostOfficeRepository;
import ru.mediasoft.test.repository.PostalItemRepository;
import ru.mediasoft.test.service.NotFoundException;

@Component
public class EntityUtils {
    private PostalItemRepository postalItemRepository;
    private PostOfficeRepository postOfficeRepository;

    @Autowired
    public EntityUtils(PostalItemRepository postalItemRepository, PostOfficeRepository postOfficeRepository) {
        this.postalItemRepository = postalItemRepository;
        this.postOfficeRepository = postOfficeRepository;
    }
    public PostalItem getPostalItem(Long id) {
        return postalItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Postal item not found with id: " + id));
    }

    public PostOffice getPostOffice(Long id) {
        return postOfficeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post office not found with id: " + id));
    }
}
