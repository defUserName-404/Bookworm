package com.defusername.bookworm.api.service.implementation;

import com.defusername.bookworm.api.dao.EntityToDaoMapper;
import com.defusername.bookworm.api.dao.PublisherResponse;
import com.defusername.bookworm.api.dto.DtoToEntityMapper;
import com.defusername.bookworm.api.dto.PublisherRequest;
import com.defusername.bookworm.api.entity.Publisher;
import com.defusername.bookworm.api.repository.PublisherRepository;
import com.defusername.bookworm.api.service.PublisherService;
import com.defusername.bookworm.util.exception.IdNotFoundException;
import com.defusername.bookworm.util.logging.LoggerManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PublisherServiceImplementation implements PublisherService {

	private final PublisherRepository publisherRepository;
	private final Logger logger;

	@Autowired
	public PublisherServiceImplementation(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
		logger = LoggerManager.getInstance()
							  .getLogger(this.getClass());
	}

	@Override
	public List<PublisherResponse> getAllPublishers() {
		return publisherRepository.findAll()
								  .stream()
								  .map(EntityToDaoMapper::mapPublisherEntityToResponse)
								  .collect(Collectors.toList());
	}

	@Override
	public Optional<PublisherResponse> getPublisherById(Long id) {
		final Optional<Publisher> publisher = publisherRepository.findById(id);
		if (publisher.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		return publisher.map(EntityToDaoMapper::mapPublisherEntityToResponse);
	}

	@Override
	public PublisherResponse addNewOrUpdateExistingPublisher(PublisherRequest publisherRequest) {
		final Publisher publisher = publisherRepository.save(Optional.of(publisherRequest)
																	 .map(DtoToEntityMapper::mapPublisherRequestToEntity)
																	 .orElse(null));
		return Optional.of(publisher)
					   .map(EntityToDaoMapper::mapPublisherEntityToResponse)
					   .orElse(null);
	}

	@Override
	public Optional<PublisherResponse> deletePublisher(Long id) {
		final Optional<Publisher> publisher = publisherRepository.findById(id);
		if (publisher.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		publisherRepository.deleteById(id);
		return publisher.map(EntityToDaoMapper::mapPublisherEntityToResponse);
	}

}
