package com.defusername.bookworm.api.book;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface BookService {

	List<Book> getAllBooks();

	Optional<Book> getBooksById(Long id);

	Optional<Book> getBookByIdAndDownload(Long id, String downloadLocation);

	Book addNewOrUpdateExistingBook(Book book, MultipartFile attachedFile);

	boolean deleteBook(Long id);

	List<Book> searchBooksByAuthorName(String authorName);

	List<Book> searchBookByGenreName(String genreName);

	List<Book> searchBooksByPublisherName(String publisherName);

}
