package com.defusername.bookworm.api.repository;

import com.defusername.bookworm.api.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findAuthorByName(String authorName);

}
