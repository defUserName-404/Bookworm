package com.defusername.bookworm.api.service.implementation;

import com.defusername.bookworm.api.dao.BookResponse;
import com.defusername.bookworm.api.dao.EntityToDaoMapper;
import com.defusername.bookworm.api.dto.BookRequest;
import com.defusername.bookworm.api.dto.DtoToEntityMapper;
import com.defusername.bookworm.api.entity.Book;
import com.defusername.bookworm.api.entity.constant.BookCategory;
import com.defusername.bookworm.api.repository.BookRepository;
import com.defusername.bookworm.api.service.BookService;
import com.defusername.bookworm.service.FileStorageService;
import com.defusername.bookworm.util.exception.IdNotFoundException;
import com.defusername.bookworm.util.logging.LoggerManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class BookServiceImplementation implements BookService {

	private final BookRepository bookRepository;
	private final Logger logger;
	private final FileStorageService fileStorageService;
	private final String BASE_PATH;

	@Autowired
	public BookServiceImplementation(BookRepository bookRepository, Environment environment) {
		this.bookRepository = bookRepository;
		logger = LoggerManager.getInstance()
							  .getLogger(this.getClass());
		fileStorageService = FileStorageService.getInstance();
		BASE_PATH = environment.getProperty("spring.servlet.multipart.location");
	}

	@Override
	public List<BookResponse> getAllBooks() {
		return bookRepository.findAll()
							 .stream()
							 .map(EntityToDaoMapper::mapBookEntityToResponse)
							 .collect(Collectors.toList());
	}

	@Override
	public Optional<BookResponse> getBooksById(Long id) {
		final Optional<Book> book = bookRepository.findById(id);
		if (book.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		return book.map(EntityToDaoMapper::mapBookEntityToResponse);
	}

	@Override
	public Optional<BookResponse> getBookByIdAndDownload(Long id, String downloadLocation) {
		final Optional<Book> book = bookRepository.findById(id);
		if (book.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		final String filePath = BASE_PATH + "/" + book.get()
													  .getId();
		fileStorageService.download(Path.of(filePath), downloadLocation, book.get()
																			 .getTitle());
		return book.map(EntityToDaoMapper::mapBookEntityToResponse);
	}

	@Override
	public BookResponse addNewOrUpdateExistingBook(BookRequest bookRequest, MultipartFile attachedFile) {
		final Book book = bookRepository.save(Optional.of(bookRequest)
													  .map(DtoToEntityMapper::mapBookRequestToEntity)
													  .orElse(null));
		final BookResponse bookResponse = Optional.of(book)
												  .map(EntityToDaoMapper::mapBookEntityToResponse)
												  .orElse(null);
		if (attachedFile != null) {
			final String filePath = BASE_PATH + "/" + book.getId();
			fileStorageService.save(attachedFile, Path.of(filePath));
		}
		return bookResponse;
	}

	@Override
	public Optional<BookResponse> deleteBook(Long id) {
		final Optional<Book> book = bookRepository.findById(id);
		if (book.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		bookRepository.deleteById(id);
		return book.map(EntityToDaoMapper::mapBookEntityToResponse);
	}

	@Override
	public List<BookResponse> searchBooksByTitle(String title) {
		return bookRepository.findAllByTitleContainingIgnoreCase(title)
							 .stream()
							 .map(EntityToDaoMapper::mapBookEntityToResponse)
							 .collect(Collectors.toList());
	}

	@Override
	public List<BookResponse> searchBooksByAuthorName(String authorName) {
		return bookRepository.findAllByAuthors_NameContainingIgnoreCase(authorName)
							 .stream()
							 .map(EntityToDaoMapper::mapBookEntityToResponse)
							 .collect(Collectors.toList());
	}

	@Override
	public List<BookResponse> searchBookByGenreName(String genreName) {
		final BookCategory bookCategory = BookCategory.valueOf(genreName.toUpperCase());
		return bookRepository.findAllByGenres_NameIgnoreCase(bookCategory)
							 .stream()
							 .map(EntityToDaoMapper::mapBookEntityToResponse)
							 .collect(Collectors.toList());
	}

	@Override
	public List<BookResponse> searchBooksByPublisherName(String publisherName) {
		return bookRepository.findAllByPublishers_NameContainingIgnoreCase(publisherName)
							 .stream()
							 .map(EntityToDaoMapper::mapBookEntityToResponse)
							 .collect(Collectors.toList());
	}

}
