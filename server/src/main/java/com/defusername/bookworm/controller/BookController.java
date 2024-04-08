package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Book;
import com.defusername.bookworm.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Book>> getAllBooks() {
		final List<Book> allBooks = bookService.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		final Optional<Book> book = bookService.getBooksById(id);
		final HttpStatus status = book.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(book.orElse(null));
	}

	@GetMapping(path = "/{id}/download")
	public ResponseEntity<Void> getBookByIdAndDownload(@PathVariable Long id, @RequestParam String downloadLocation) {
		final Optional<Book> book = bookService.getBookByIdAndDownload(id, downloadLocation);
		final HttpStatus status = book.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .contentType(MediaType.APPLICATION_OCTET_STREAM)
							 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
									 (book.isEmpty() ? "None" : book.get()
																	.getTitle()
									 ))
							 .build();
	}

	@PostMapping(path = "", consumes = {"multipart/form-data", "application/json"})
	public ResponseEntity<Book> addNewBook(@RequestPart Book book, @RequestPart MultipartFile file) {
		final Book createdBook = bookService.addNewOrUpdateExistingBook(book, file);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdBook);
	}

	@PutMapping(path = "", consumes = {"multipart/form-data", "application/json"})
	public ResponseEntity<Book> updateExistingBook(@RequestPart Book updatedBook, @RequestPart MultipartFile file) {
		final Book createdBook = bookService.addNewOrUpdateExistingBook(updatedBook, file);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdBook);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {
		final boolean deleted = bookService.deleteBook(id);
		final HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .build();
	}

	// ? searching
	@GetMapping(path = "/author")
	public ResponseEntity<List<Book>> getBooksByAuthorName(@RequestParam String authorName) {
		final List<Book> allBooks = bookService.searchBooksByAuthorName(authorName);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

	@GetMapping(path = "/genre")
	public ResponseEntity<List<Book>> getBooksByGenreName(@RequestParam String genreName) {
		final List<Book> allBooks = bookService.searchBookByGenreName(genreName);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

	@GetMapping(path = "/publisher")
	public ResponseEntity<List<Book>> getBooksByPublisherName(@RequestParam String publisherName) {
		final List<Book> allBooks = bookService.searchBooksByPublisherName(publisherName);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

}
