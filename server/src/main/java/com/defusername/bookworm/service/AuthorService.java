package com.defusername.bookworm.service;

import com.defusername.bookworm.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface AuthorService {

	List<Author> getAllAuthors();

	Optional<Author> getAuthorById(Long id);

	Author addNewAuthor(Author author);
	
	boolean doesAuthorExistByName(String name);

	Optional<Author> findAuthorByName(String name);

	Author updateAuthor(Author updatedAuthor, Long id);

	boolean deleteAuthor(Long id);

}
