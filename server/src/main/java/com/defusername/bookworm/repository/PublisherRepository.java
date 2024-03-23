package com.defusername.bookworm.repository;

import com.defusername.bookworm.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	Optional<Publisher> findPublisherByName(String name);

}
