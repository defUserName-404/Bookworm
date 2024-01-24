package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Author;
import com.defusername.bookworm.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return author.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
					 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Author> addNewAuthor(@RequestBody Author author) {
		Author newAuthor = authorService.addNewAuthor(author);
		return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@RequestBody Author updatedAuthor, @PathVariable Long id) {
		Author author = authorService.updateAuthor(updatedAuthor, id);
		return new ResponseEntity<>(author, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
		if (authorService.deleteAuthor(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
