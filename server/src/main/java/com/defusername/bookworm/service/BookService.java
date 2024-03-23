package com.defusername.bookworm.service;

import com.defusername.bookworm.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface BookService {

	List<Book> getAllBooks();

	Optional<Book> getBooksById(Long id);

	Book addNewOrUpdateExistingBook(Book book);

	boolean deleteBook(Long id);

	List<Book> searchBooksByAuthorName(String authorName);

	List<Book> searchBookByGenreName(String genreName);

	List<Book> searchBooksByPublisherName(String publisherName);

}
