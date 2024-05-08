package com.defusername.bookworm.api.service;

import com.defusername.bookworm.api.dao.GenreResponse;
import com.defusername.bookworm.api.dto.GenreRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface GenreService {

	Optional<GenreResponse> getGenreById(Long id);

	List<GenreResponse> getAllGenres();

	GenreResponse addNewOrUpdateExistingGenre(GenreRequest genre);

	Optional<GenreResponse> deleteGenre(Long id);

}
