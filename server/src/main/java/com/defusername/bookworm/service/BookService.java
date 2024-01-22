package com.defusername.bookworm.service;

import com.defusername.bookworm.entity.Book;
import com.defusername.bookworm.repository.GenreRepository;
import com.defusername.bookworm.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private GenreRepository genreRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBooksById(Long id) {
		return bookRepository.findById(id);
	}

	public Book addNewBook(Book book) {
		return bookRepository.save(book);
	}

	public Book updateExistingBook(Book updatedBook, Long id) {
		Book existingBook = bookRepository.findById(id)
										  .orElseThrow();
		existingBook.setTitle(updatedBook.getTitle());
		existingBook.setPagesRead(updatedBook.getPagesRead());
		existingBook.setStatus(updatedBook.getStatus());
		if (updatedBook.getDescription() != null) {
			existingBook.setDescription(updatedBook.getDescription());
		}

		return bookRepository.save(existingBook);
	}

	public boolean deleteBook(Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
