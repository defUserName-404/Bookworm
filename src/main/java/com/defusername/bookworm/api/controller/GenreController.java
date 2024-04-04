package com.defusername.bookworm.api.controller;

import com.defusername.bookworm.api.dao.GenreResponse;
import com.defusername.bookworm.api.dto.GenreRequest;
import com.defusername.bookworm.api.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/genres")
public class GenreController {

	private final GenreService genreService;

	@Autowired
	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<GenreResponse>> getAllGenres() {
		final List<GenreResponse> allGenres = genreService.getAllGenres();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(allGenres);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<GenreResponse> getGenreById(@PathVariable Long id) {
		final Optional<GenreResponse> bookGenre = genreService.getGenreById(id);
		final HttpStatus status = bookGenre.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
							 .body(bookGenre.orElse(null));
	}

	@PostMapping(path = "/admin")
	public ResponseEntity<GenreResponse> addNewGenre(@RequestBody GenreRequest genre) {
		final GenreResponse createdGenre = genreService.addNewOrUpdateExistingGenre(genre);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdGenre);
	}

	@PutMapping(path = "/admin")
	public ResponseEntity<GenreResponse> updateExistingGenre(@RequestBody GenreRequest updatedGenre) {
		final GenreResponse createdGenre = genreService.addNewOrUpdateExistingGenre(updatedGenre);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(createdGenre);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<GenreResponse> deleteGenre(@PathVariable Long id) {
		final Optional<GenreResponse> deletedGenre = genreService.deleteGenre(id);
		final HttpStatus status = deletedGenre.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return ResponseEntity.status(status)
							 .body(deletedGenre.orElse(null));
	}

}
