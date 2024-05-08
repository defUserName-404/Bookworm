package com.defusername.bookworm.api.service.implementation;

import com.defusername.bookworm.api.dao.EntityToDaoMapper;
import com.defusername.bookworm.api.dao.GenreResponse;
import com.defusername.bookworm.api.dto.DtoToEntityMapper;
import com.defusername.bookworm.api.dto.GenreRequest;
import com.defusername.bookworm.api.entity.Genre;
import com.defusername.bookworm.api.repository.GenreRepository;
import com.defusername.bookworm.api.service.GenreService;
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
public class GenreServiceImplementation implements GenreService {

	private final GenreRepository genreRepository;
	private final Logger logger;

	@Autowired
	public GenreServiceImplementation(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
		logger = LoggerManager.getInstance()
							  .getLogger(this.getClass());
	}

	@Override
	public Optional<GenreResponse> getGenreById(Long id) {
		final Optional<Genre> genre = genreRepository.findById(id);
		if (genre.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		return genre.map(EntityToDaoMapper::mapGenreEntityToResponse);
	}

	@Override
	public List<GenreResponse> getAllGenres() {
		return genreRepository.findAll()
							  .stream()
							  .map(EntityToDaoMapper::mapGenreEntityToResponse)
							  .collect(Collectors.toList());
	}

	@Override
	public GenreResponse addNewOrUpdateExistingGenre(GenreRequest genreRequest) {
		final Genre genre = genreRepository.save(Optional.of(genreRequest)
														 .map(DtoToEntityMapper::mapGenreRequestToEntity)
														 .orElse(null));
		return Optional.of(genre)
					   .map(EntityToDaoMapper::mapGenreEntityToResponse)
					   .orElse(null);
	}

	@Override
	public Optional<GenreResponse> deleteGenre(Long id) {
		final Optional<Genre> genre = genreRepository.findById(id);
		if (genre.isEmpty()) {
			logger.info(new IdNotFoundException().getMessage());
			return Optional.empty();
		}
		genreRepository.deleteById(id);
		return genre.map(EntityToDaoMapper::mapGenreEntityToResponse);
	}

}
