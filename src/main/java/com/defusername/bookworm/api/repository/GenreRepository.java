package com.defusername.bookworm.api.repository;

import com.defusername.bookworm.api.entity.Genre;
import com.defusername.bookworm.api.entity.constant.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	Optional<Genre> findGenreByName(BookCategory name);

}
