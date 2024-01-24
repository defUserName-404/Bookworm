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
	public ResponseEntity<Genre> getBookGenre(@PathVariable Long id) {
		Optional<Genre> bookGenre = genreService.getGenreById(id);
		return bookGenre.map(ResponseEntity::ok)
						.orElseGet(() -> ResponseEntity.notFound()
													   .build());
	}

	@PostMapping
	public ResponseEntity<Genre> addNewGenre(@RequestBody Genre genre) {
		Genre savedGenre = genreService.addNewGenre(genre);
		return ResponseEntity.ok(savedGenre);
	}

}
