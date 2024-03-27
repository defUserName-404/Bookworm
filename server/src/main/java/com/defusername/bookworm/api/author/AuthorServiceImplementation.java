package com.defusername.bookworm.api.author;

import com.defusername.bookworm.util.exception.IdNotFoundException;
import com.defusername.bookworm.util.logging.LoggerManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImplementation implements AuthorService {

	private final AuthorRepository authorRepository;
	private final Logger logger;

	@Autowired
	public AuthorServiceImplementation(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
		logger = LoggerManager.getInstance()
							  .getLogger(AuthorServiceImplementation.class);
	}

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Optional<Author> getAuthorById(Long id) {
		if (!authorRepository.existsById(id)) {
			logger.info(new IdNotFoundException().getMessage());
		}
		return authorRepository.findById(id);
	}

	@Override
	public Author addNewOrUpdateExistingAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public boolean deleteAuthor(Long id) {
		if (!authorRepository.existsById(id)) {
			logger.info(new IdNotFoundException().getMessage());
			return false;
		}
		authorRepository.deleteById(id);
		return true;
	}

}
