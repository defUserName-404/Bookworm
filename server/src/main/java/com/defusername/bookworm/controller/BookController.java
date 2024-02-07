package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Book;
import com.defusername.bookworm.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(path = "/all")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Optional<Book> book = bookService.getBooksById(id);
		return book.map(ResponseEntity::ok)
				   .orElseGet(() -> ResponseEntity.notFound()
												  .build());
	}

	@PostMapping
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.addNewBook(book));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Book> updateExistingBook(@RequestBody Book updatedBook, @PathVariable Long id) {
		return ResponseEntity.ok(bookService.updateExistingBook(updatedBook, id));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {
		boolean deleted = bookService.deleteBook(id);
		return deleted ? ResponseEntity.ok("Book deleted successfully") : ResponseEntity.notFound()
																						.build();
	}

}
