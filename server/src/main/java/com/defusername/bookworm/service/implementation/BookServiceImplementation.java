package com.defusername.bookworm.service.implementation;

import com.defusername.bookworm.entity.Book;
import com.defusername.bookworm.entity.constants.BookCategory;
import com.defusername.bookworm.repository.BookRepository;
import com.defusername.bookworm.service.BookService;
import com.defusername.bookworm.service.FileStorageService;
import com.defusername.bookworm.util.exception.IdNotFoundException;
import com.defusername.bookworm.util.logging.LoggerManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImplementation implements BookService {

	private final BookRepository bookRepository;
	private final Logger logger;
	private final FileStorageService fileStorageService;
	private final Environment environment;

	public BookServiceImplementation(BookRepository bookRepository, Environment environment) {
		this.bookRepository = bookRepository;
		this.environment = environment;
		logger = LoggerManager.getInstance()
							  .getLogger(BookServiceImplementation.class);
		fileStorageService = FileStorageService.getInstance();
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
	public Book addNewOrUpdateExistingBook(Book book, MultipartFile attachedFile) {
		final Book addedBook = bookRepository.save(book);
		final String basePath
				= environment.getProperty("spring.servlet.multipart.location");
		fileStorageService.save(attachedFile, Path.of(basePath + "/" + book.getId()));
		return addedBook;
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
