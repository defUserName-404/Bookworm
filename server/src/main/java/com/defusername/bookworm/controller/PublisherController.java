package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Publisher;
import com.defusername.bookworm.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/publishers")
@CrossOrigin(origins = "http://localhost:3000")
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	@GetMapping(path = "/all")
	public List<Publisher> getAllPublishers() {
		return publisherService.getAllPublishers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
		Optional<Publisher> publisher = publisherService.getPublisherById(id);
		return publisher.map(ResponseEntity::ok)
						.orElseGet(() -> ResponseEntity.notFound()
													   .build());
	}

	@PostMapping
	public ResponseEntity<Publisher> addNewPublisher(@RequestBody Publisher publisher) {
		return ResponseEntity.ok(publisherService.addNewPublisher(publisher));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher updatedpublisher, @PathVariable Long id) {
		return ResponseEntity.ok(publisherService.updatePublisher(updatedpublisher, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable Long id) {
		boolean deleted = publisherService.deletePublisher(id);
		return deleted ? ResponseEntity.ok("Publisher deleted successfully") : ResponseEntity.notFound()
																							 .build();
	}

}
