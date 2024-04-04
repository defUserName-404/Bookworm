package com.defusername.bookworm.api.service;

import com.defusername.bookworm.api.dao.PublisherResponse;
import com.defusername.bookworm.api.dto.PublisherRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface PublisherService {

	List<PublisherResponse> getAllPublishers();

	Optional<PublisherResponse> getPublisherById(Long id);

	PublisherResponse addNewOrUpdateExistingPublisher(PublisherRequest publisher);
	
	Optional<PublisherResponse> deletePublisher(Long id);

}
