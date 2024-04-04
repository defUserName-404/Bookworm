package com.defusername.bookworm.api.service;

import com.defusername.bookworm.api.dao.AuthorResponse;
import com.defusername.bookworm.api.dto.AuthorRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface AuthorService {

	List<AuthorResponse> getAllAuthors();

	Optional<AuthorResponse> getAuthorById(Long id);

	AuthorResponse addNewOrUpdateExistingAuthor(AuthorRequest author);

	Optional<AuthorResponse> deleteAuthor(Long id);

}
