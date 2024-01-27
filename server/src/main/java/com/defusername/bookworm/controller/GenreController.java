package com.defusername.bookworm.controller;

import com.defusername.bookworm.entity.Genre;
import com.defusername.bookworm.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/genres")
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping(path = "/all")
	public List<Genre> getAllGenres() {
		return genreService.getAllGenres();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
		Optional<Genre> bookGenre = genreService.getGenreById(id);
		return bookGenre.map(ResponseEntity::ok)
						.orElseGet(() -> ResponseEntity.notFound()
													   .build());
	}

	@PostMapping
	public ResponseEntity<Genre> addNewGenre(@RequestBody Genre genre) {
		return ResponseEntity.ok(genreService.addNewGenre(genre));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Genre> updateExistingGenre(@RequestBody Genre updatedGenre, @PathVariable Long id) {
		return ResponseEntity.ok(genreService.updateExistingGenre(updatedGenre, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGenre(@PathVariable Long id) {
		boolean deleted = genreService.deleteGenre(id);
		return deleted ? ResponseEntity.ok("Genre deleted successfully") : ResponseEntity.notFound()
																						 .build();
	}

}
