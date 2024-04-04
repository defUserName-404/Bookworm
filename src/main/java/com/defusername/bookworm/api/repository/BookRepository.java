package com.defusername.bookworm.api.repository;

import com.defusername.bookworm.api.entity.Book;
import com.defusername.bookworm.api.entity.constant.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findAllByTitleContainingIgnoreCase(String title);

	List<Book> findAllByAuthors_NameContainingIgnoreCase(String authorName);

	List<Book> findAllByGenres_NameIgnoreCase(BookCategory genreName);

	List<Book> findAllByPublishers_NameContainingIgnoreCase(String publisherName);

}
