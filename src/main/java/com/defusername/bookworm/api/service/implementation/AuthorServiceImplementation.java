package com.defusername.bookworm.api.service.implementation;

import com.defusername.bookworm.api.dao.AuthorResponse;
import com.defusername.bookworm.api.dao.EntityToDaoMapper;
import com.defusername.bookworm.api.dto.AuthorRequest;
import com.defusername.bookworm.api.dto.DtoToEntityMapper;
import com.defusername.bookworm.api.entity.Author;
import com.defusername.bookworm.api.repository.AuthorRepository;
import com.defusername.bookworm.api.service.AuthorService;
import com.defusername.bookworm.util.exception.IdNotFoundException;
import com.defusername.bookworm.util.logging.LoggerManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImplementation implements AuthorService {

	private final AuthorRepository authorRepository;
	private final Logger logger;

	@Autowired
	public AuthorServiceImplementation(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
		logger = LoggerManager.getInstance()
							  .getLogger(this.getClass());
	}

	@Override
	public List<AuthorResponse> getAllAuthors() {
		return authorRepository.findAll()
							   .stream()
							   .map(EntityToDaoMapper::mapAuthorEntityToResponse)
							   .collect(Collectors.toList());
	}

	@Override
	public Optional<AuthorResponse> getAuthorById(Long id) {
		final Optional<Author> author = authorRepository.findById(id);
		if (author.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		return author.map(EntityToDaoMapper::mapAuthorEntityToResponse);
	}

	@Override
	public AuthorResponse addNewOrUpdateExistingAuthor(AuthorRequest authorRequest) {
		final Author author = authorRepository.save(Optional.of(authorRequest)
															.map(DtoToEntityMapper::mapAuthorRequestToEntity)
															.orElse(null));
		return Optional.of(author)
					   .map(EntityToDaoMapper::mapAuthorEntityToResponse)
					   .orElse(null);
	}

	@Override
	public Optional<AuthorResponse> deleteAuthor(Long id) {
		final Optional<Author> author = authorRepository.findById(id);
		if (author.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		authorRepository.deleteById(id);
		return author.map(EntityToDaoMapper::mapAuthorEntityToResponse);
	}

}
