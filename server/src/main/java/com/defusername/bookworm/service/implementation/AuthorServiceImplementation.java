package com.defusername.bookworm.service.implementation;

import com.defusername.bookworm.entity.Author;
import com.defusername.bookworm.repository.AuthorRepository;
import com.defusername.bookworm.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImplementation implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Optional<Author> getAuthorById(Long id) {
		return authorRepository.findById(id);
	}

	@Override
	public Author addNewAuthor(Author author) {
		return authorRepository.save(author);
	}
	
	@Override
	public Author updateExistingAuthor(Author updatedAuthor, Long id) {
		if (!authorRepository.existsById(id)) {
			throw new IllegalStateException("Id not found");
		}

		return authorRepository.save(updatedAuthor);
	}

	@Override
	public boolean deleteAuthor(Long id) {
		if (authorRepository.existsById(id)) {
			authorRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
