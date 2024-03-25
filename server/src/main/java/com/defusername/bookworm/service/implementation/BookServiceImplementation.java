package com.defusername.bookworm.service.implementation;

import com.defusername.bookworm.entity.Book;
import com.defusername.bookworm.entity.constants.BookCategory;
import com.defusername.bookworm.repository.BookRepository;
import com.defusername.bookworm.service.BookService;
import com.defusername.bookworm.util.exception.IdNotFoundException;
import com.defusername.bookworm.util.logging.LoggerManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImplementation implements BookService {

	private final BookRepository bookRepository;
	private final Logger logger;

	public BookServiceImplementation(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		logger = LoggerManager.getInstance()
							  .getLogger(BookServiceImplementation.class);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> getBooksById(Long id) {
		if (!bookRepository.existsById(id)) {
			logger.info(new IdNotFoundException().getMessage());
		}
		return bookRepository.findById(id);
	}

	@Override
	public Book addNewOrUpdateExistingBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public boolean deleteBook(Long id) {
		if (!bookRepository.existsById(id)) {
			logger.info(new IdNotFoundException().getMessage());
			return false;
		}
		bookRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Book> searchBooksByAuthorName(String authorName) {
		return bookRepository.findByAuthors_NameContainingIgnoreCase(authorName);
	}

	@Override
	public List<Book> searchBookByGenreName(String genreName) {
		final BookCategory bookCategory = BookCategory.valueOf(genreName.toUpperCase());
		return bookRepository.findByGenres_Name(bookCategory);
	}

	@Override
	public List<Book> searchBooksByPublisherName(String publisherName) {
		return bookRepository.findByPublishers_NameContainingIgnoreCase(publisherName);
	}

}
