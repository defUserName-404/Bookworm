package com.defusername.bookworm.service.implementation;

import com.defusername.bookworm.entity.Book;
import com.defusername.bookworm.repository.BookRepository;
import com.defusername.bookworm.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImplementation implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> getBooksById(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public Book addNewBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateExistingBook(Book updatedBook, Long id) {
		if (!bookRepository.existsById(id)) {
			throw new IllegalStateException("Id not found");
		}

		return bookRepository.save(updatedBook);
	}

	@Override
	public boolean deleteBook(Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
