package com.defusername.bookworm.api.service;

import com.defusername.bookworm.api.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface AuthorService {

	List<Author> getAllAuthors();

	Optional<Author> getAuthorById(Long id);

	Author addNewOrUpdateExistingAuthor(Author author);

	boolean deleteAuthor(Long id);

}