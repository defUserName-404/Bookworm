package com.defusername.bookworm.service;

import com.defusername.bookworm.entity.Author;
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

	Book addNewBook(Book book);

	Book updateExistingBook(Book updatedBook, Long id);

	boolean deleteBook(Long id);

}
