package com.defusername.bookworm.api.service;

import com.defusername.bookworm.api.entity.Genre;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface GenreService {

	Optional<Genre> getGenreById(Long id);

	List<Genre> getAllGenres();

	Genre addNewOrUpdateExistingGenre(Genre genre);

	boolean deleteGenre(Long id);

}
