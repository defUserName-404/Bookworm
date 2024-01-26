package com.defusername.bookworm.repository;

import com.defusername.bookworm.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	boolean existsByName(String name);
	
	Optional<Author> findByName(String name);
	
	List<Author> findAllByName(String name);

}
