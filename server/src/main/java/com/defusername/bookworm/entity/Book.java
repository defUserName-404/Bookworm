package com.defusername.bookworm.entity;

import com.defusername.bookworm.entity.enums.BookStatus;
import com.defusername.bookworm.entity.enums.FileType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name = "book_author", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "author_id")})
	private Set<Author> authors = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_publisher", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
	private Set<Publisher> publishers = new HashSet<>();

	@Column(name = "page_count")
	private int pageCount;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_genre", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "genre_id")})
	private Set<Genre> genres = new HashSet<>();

	@Column(name = "file_type", nullable = false)
	private FileType fileType;

	@Column(name = "pages_read")
	private int pagesRead;

	@Column(name = "book_status", nullable = false)
	private BookStatus status;

//	@OneToOne
//	@JoinColumn(name = "file_path_id")
//	private FilePath filePath;

}
