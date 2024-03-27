package com.defusername.bookworm.api.genre;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreServiceImplementation implements GenreService {

	private final GenreRepository genreRepository;

	@Autowired
	public GenreServiceImplementation(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public Optional<Genre> getGenreById(Long id) {
		return genreRepository.findById(id);
	}

	@Override
	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}

	@Override
	public Genre addNewOrUpdateExistingGenre(Genre genre) {
		return genreRepository.save(genre);
	}
	
	@Override
	public boolean deleteGenre(Long id) {
		if (genreRepository.existsById(id)) {
			genreRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
