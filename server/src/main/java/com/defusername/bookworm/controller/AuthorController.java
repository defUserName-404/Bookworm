package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Author;
import com.defusername.bookworm.entity.Book;
import com.defusername.bookworm.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/authors")
public class AuthorController {

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Author>> getAllAuthors() {
		final List<Author> allAuthors = authorService.getAllAuthors();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allAuthors);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
		final Optional<Author> author = authorService.getAuthorById(id);
		final HttpStatus status = author.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(author.orElse(null));
	}

	@PostMapping(path = "")
	public ResponseEntity<Author> addNewAuthor(@RequestBody Author author) {
		final Author createdAuthor = authorService.addNewOrUpdateExistingAuthor(author);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdAuthor);
	}

	@PutMapping(path = "")
	public ResponseEntity<Author> updateExistingAuthor(@RequestBody Author updatedAuthor) {
		final Author createdAuthor = authorService.addNewOrUpdateExistingAuthor(updatedAuthor);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdAuthor);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
		final boolean deleted = authorService.deleteAuthor(id);
		final HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .build();
	}

}
