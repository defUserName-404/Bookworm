package com.defusername.bookworm.service.implementation;

import com.defusername.bookworm.entity.Genre;
import com.defusername.bookworm.entity.constants.BookCategories;
import com.defusername.bookworm.repository.GenreRepository;
import com.defusername.bookworm.service.GenreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreServiceImplementation implements GenreService {

	@Autowired
	private GenreRepository genreRepository;

	public Optional<Genre> getGenreById(Long id) {
		return genreRepository.findById(id);
	}

	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}

	public Genre findGenreByName(BookCategories name) {
		return genreRepository.findGenreByName(name)
							  .orElse(null);
	}

	public Genre addNewGenre(Genre genre) {
		return genreRepository.save(genre);
	}

}
