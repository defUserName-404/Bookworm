package com.defusername.bookworm.api.controller;

import com.defusername.bookworm.api.dao.BookResponse;
import com.defusername.bookworm.api.dto.BookRequest;
import com.defusername.bookworm.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/books")
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<BookResponse>> getAllBooks() {
		final List<BookResponse> allBooks = bookService.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
		final Optional<BookResponse> book = bookService.getBooksById(id);
		final HttpStatus status = book.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(book.orElse(null));
	}

	@GetMapping(path = "/{id}/download")
	public ResponseEntity<BookResponse> getBookByIdAndDownload(@PathVariable Long id, @RequestParam String downloadLocation) {
		final Optional<BookResponse> book = bookService.getBookByIdAndDownload(id, downloadLocation);
		final HttpStatus status = book.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .contentType(MediaType.APPLICATION_OCTET_STREAM)
							 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
									 (book.isEmpty() ? "None" : book.get()
																	.title()
									 ))
							 .body(book.orElse(null));
	}

	@PostMapping(path = "/admin", consumes = {"multipart/form-data", "application/json"})
	public ResponseEntity<BookResponse> addNewBook(@RequestPart BookRequest book, @RequestPart MultipartFile file) {
		final BookResponse createdBook = bookService.addNewOrUpdateExistingBook(book, file);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdBook);
	}

	@PutMapping(path = "/admin", consumes = {"multipart/form-data", "application/json"})
	public ResponseEntity<BookResponse> updateExistingBook(@RequestPart BookRequest updatedBook, @RequestPart MultipartFile file) {
		final BookResponse createdBook = bookService.addNewOrUpdateExistingBook(updatedBook, file);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdBook);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id) {
		final Optional<BookResponse> deletedBook = bookService.deleteBook(id);
		final HttpStatus status = deletedBook.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return ResponseEntity.status(status)
							 .body(deletedBook.orElse(null));
	}

	// ? searching
	@GetMapping(path = "/search")
	public ResponseEntity<List<BookResponse>> searchBookByTitle(@RequestParam String title) {
		final List<BookResponse> allBooks = bookService.searchBooksByTitle(title);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}
	
	@GetMapping(path = "/search/author")
	public ResponseEntity<List<BookResponse>> getBooksByAuthorName(@RequestParam String authorName) {
		final List<BookResponse> allBooks = bookService.searchBooksByAuthorName(authorName);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

	@GetMapping(path = "/search/genre")
	public ResponseEntity<List<BookResponse>> getBooksByGenreName(@RequestParam String genreName) {
		final List<BookResponse> allBooks = bookService.searchBookByGenreName(genreName);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

	@GetMapping(path = "/search/publisher")
	public ResponseEntity<List<BookResponse>> getBooksByPublisherName(@RequestParam String publisherName) {
		final List<BookResponse> allBooks = bookService.searchBooksByPublisherName(publisherName);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allBooks);
	}

}
