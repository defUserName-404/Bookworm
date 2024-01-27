package com.defusername.bookworm.service;

import com.defusername.bookworm.entity.Publisher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface PublisherService {

	List<Publisher> getAllPublishers();

	Optional<Publisher> getPublisherById(Long id);

	Publisher addNewPublisher(Publisher publisher);

	Publisher updatePublisher(Publisher updatedpublisher, Long id);

	boolean deletePublisher(Long id);

}
