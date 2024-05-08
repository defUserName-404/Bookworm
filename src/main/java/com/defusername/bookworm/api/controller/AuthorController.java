package com.defusername.bookworm.api.controller;

import com.defusername.bookworm.api.dao.AuthorResponse;
import com.defusername.bookworm.api.dto.AuthorRequest;
import com.defusername.bookworm.api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/authors")
public class AuthorController {

	private final AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
		final List<AuthorResponse> allAuthors = authorService.getAllAuthors();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allAuthors);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
		final Optional<AuthorResponse> author = authorService.getAuthorById(id);
		final HttpStatus status = author.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(author.orElse(null));
	}

	@PostMapping(path = "/admin")
	public ResponseEntity<AuthorResponse> addNewAuthor(@RequestBody AuthorRequest author) {
		final AuthorResponse createdAuthor = authorService.addNewOrUpdateExistingAuthor(author);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdAuthor);
	}

	@PutMapping(path = "/admin")
	public ResponseEntity<AuthorResponse> updateExistingAuthor(@RequestBody AuthorRequest updatedAuthor) {
		final AuthorResponse createdAuthor = authorService.addNewOrUpdateExistingAuthor(updatedAuthor);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdAuthor);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<AuthorResponse> deleteAuthor(@PathVariable Long id) {
		final Optional<AuthorResponse> deletedAuthor = authorService.deleteAuthor(id);
		final HttpStatus status = deletedAuthor.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return ResponseEntity.status(status)
							 .body(deletedAuthor.orElse(null));
	}

}
