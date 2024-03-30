package com.defusername.bookworm.api.controller;

import com.defusername.bookworm.api.entity.Genre;
import com.defusername.bookworm.api.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/genres")
public class GenreController {

	private final GenreService genreService;

	@Autowired
	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Genre>> getAllGenres() {
		final List<Genre> allGenres = genreService.getAllGenres();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allGenres);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
		final Optional<Genre> bookGenre = genreService.getGenreById(id);
		final HttpStatus status = bookGenre.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(bookGenre.orElse(null));
	}

	@PostMapping(path = "/admin")
	public ResponseEntity<Genre> addNewGenre(@RequestBody Genre genre) {
		final Genre createdGenre = genreService.addNewOrUpdateExistingGenre(genre);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdGenre);
	}

	@PutMapping(path = "/admin")
	public ResponseEntity<Genre> updateExistingGenre(@RequestBody Genre updatedGenre) {
		final Genre createdGenre = genreService.addNewOrUpdateExistingGenre(updatedGenre);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdGenre);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<String> deleteGenre(@PathVariable Long id) {
		final boolean deleted = genreService.deleteGenre(id);
		final HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .build();
	}

}
