package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Author;
import com.defusername.bookworm.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping(path = "/all")
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
		Optional<Author> author = authorService.getAuthorById(id);
		return author.map(ResponseEntity::ok)
					 .orElseGet(() -> ResponseEntity.notFound()
													.build());
	}

	@PostMapping
	public ResponseEntity<Author> addNewAuthor(@RequestBody Author author) {
		return ResponseEntity.ok(authorService.addNewAuthor(author));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Author> updateExistingAuthor(@RequestBody Author updatedAuthor, @PathVariable Long id) {
		return ResponseEntity.ok(authorService.updateExistingAuthor(updatedAuthor, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
		boolean deleted = authorService.deleteAuthor(id);
		return deleted ? ResponseEntity.ok("Author deleted successfully") : ResponseEntity.notFound()
																						  .build();
	}

}
