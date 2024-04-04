package com.defusername.bookworm.api.service;

import com.defusername.bookworm.api.dao.BookResponse;
import com.defusername.bookworm.api.dto.BookRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface BookService {

	List<BookResponse> getAllBooks();

	Optional<BookResponse> getBooksById(Long id);

	Optional<BookResponse> getBookByIdAndDownload(Long id, String downloadLocation);

	BookResponse addNewOrUpdateExistingBook(BookRequest book, MultipartFile attachedFile);

	Optional<BookResponse> deleteBook(Long id);
	
	List<BookResponse> searchBooksByTitle(String title);

	List<BookResponse> searchBooksByAuthorName(String authorName);

	List<BookResponse> searchBookByGenreName(String genreName);

	List<BookResponse> searchBooksByPublisherName(String publisherName);

}
