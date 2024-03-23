package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Publisher;
import com.defusername.bookworm.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/publishers")
public class PublisherController {

	private final PublisherService publisherService;

	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Publisher>> getAllPublishers() {
		final List<Publisher> allPublishers = publisherService.getAllPublishers();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allPublishers);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
		final Optional<Publisher> bookPublisher = publisherService.getPublisherById(id);
		final HttpStatus status = bookPublisher.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(bookPublisher.orElse(null));
	}

	@PostMapping(path = "")
	public ResponseEntity<Publisher> addNewPublisher(@RequestBody Publisher publisher) {
		final Publisher createdPublisher = publisherService.addNewOrUpdateExistingPublisher(publisher);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdPublisher);
	}

	@PutMapping(path = "")
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher updatedpublisher) {
		final Publisher createdPublisher = publisherService.addNewOrUpdateExistingPublisher(updatedpublisher);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdPublisher);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable Long id) {
		final boolean deleted = publisherService.deletePublisher(id);
		final HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .build();
	}

}
