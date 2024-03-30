package com.defusername.bookworm.api.service.implementation;

import com.defusername.bookworm.api.entity.Publisher;
import com.defusername.bookworm.api.repository.PublisherRepository;
import com.defusername.bookworm.api.service.PublisherService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublisherServiceImplementation implements PublisherService {

	private final PublisherRepository publisherRepository;

	@Autowired
	public PublisherServiceImplementation(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}

	@Override
	public List<Publisher> getAllPublishers() {
		return publisherRepository.findAll();
	}

	@Override
	public Optional<Publisher> getPublisherById(Long id) {
		return publisherRepository.findById(id);
	}

	@Override
	public Publisher addNewOrUpdateExistingPublisher(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@Override
	public boolean deletePublisher(Long id) {
		if (publisherRepository.existsById(id)) {
			publisherRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
