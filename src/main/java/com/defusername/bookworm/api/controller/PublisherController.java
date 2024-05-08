package com.defusername.bookworm.api.controller;

import com.defusername.bookworm.api.dao.GenreResponse;
import com.defusername.bookworm.api.dao.PublisherResponse;
import com.defusername.bookworm.api.dto.PublisherRequest;
import com.defusername.bookworm.api.entity.Publisher;
import com.defusername.bookworm.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/publishers")
public class PublisherController {

	private final PublisherService publisherService;

	@Autowired
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<PublisherResponse>> getAllPublishers() {
		final List<PublisherResponse> allPublishers = publisherService.getAllPublishers();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allPublishers);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<PublisherResponse> getPublisherById(@PathVariable Long id) {
		final Optional<PublisherResponse> bookPublisher = publisherService.getPublisherById(id);
		final HttpStatus status = bookPublisher.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(bookPublisher.orElse(null));
	}

	@PostMapping(path = "/admin")
	public ResponseEntity<PublisherResponse> addNewPublisher(@RequestBody PublisherRequest publisher) {
		final PublisherResponse createdPublisher = publisherService.addNewOrUpdateExistingPublisher(publisher);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdPublisher);
	}

	@PutMapping(path = "/admin")
	public ResponseEntity<PublisherResponse> updatePublisher(@RequestBody PublisherRequest updatedpublisher) {
		final PublisherResponse createdPublisher = publisherService.addNewOrUpdateExistingPublisher(updatedpublisher);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdPublisher);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<PublisherResponse> deletePublisher(@PathVariable Long id) {
		final Optional<PublisherResponse> deletedPublisher = publisherService.deletePublisher(id);
		final HttpStatus status = deletedPublisher.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return ResponseEntity.status(status)
							 .body(deletedPublisher.orElse(null));
	}

}
