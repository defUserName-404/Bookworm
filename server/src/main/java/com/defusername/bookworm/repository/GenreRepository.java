package com.defusername.bookworm.repository;

import com.defusername.bookworm.entity.Genre;
import com.defusername.bookworm.entity.constants.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	Optional<Genre> findGenreByName(BookCategory name);

}
