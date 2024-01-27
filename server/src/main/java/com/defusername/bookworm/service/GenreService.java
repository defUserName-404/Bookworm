package com.defusername.bookworm.service;

import com.defusername.bookworm.entity.Genre;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface GenreService {

	Optional<Genre> getGenreById(Long id);

	List<Genre> getAllGenres();

	Genre addNewGenre(Genre genre);

	Genre updateExistingGenre(Genre updatedGenre, Long id);

	boolean deleteGenre(Long id);

}
