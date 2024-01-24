package com.defusername.bookworm.entity;

import com.defusername.bookworm.entity.constants.BookStatus;
import com.defusername.bookworm.entity.constants.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@Builder
@AllArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "author_id")})
	private Set<Author> authors;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "book_publisher", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
	private Set<Publisher> publishers;

	@Column(name = "page_count")
	private int pageCount;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "book_genre", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "genre_id")})
	@Enumerated(EnumType.STRING)
	private Set<Genre> genres;

	@Column(name = "file_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private FileType fileType;

	@Column(name = "pages_read")
	private int pagesRead;

	@Column(name = "book_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private BookStatus status;

	public Book() {
		this.genres = new HashSet<>();
		this.authors = new HashSet<>();
		this.publishers = new HashSet<>();
	}

}
