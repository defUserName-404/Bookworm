package com.defusername.bookworm.api.book;

import com.defusername.bookworm.api.genre.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthors_NameContainingIgnoreCase(String authorName);

	List<Book> findByGenres_Name(BookCategory genreName);

	List<Book> findByPublishers_NameContainingIgnoreCase(String publisherName);

}
