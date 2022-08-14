package com.defusername.bookworm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "category", nullable = false)
	private List<BookCategory> categories;

	@Column(name = "keywords", nullable = false)
	private List<String> keywords;

	@Column(name = "no_of_copies", nullable = false)
	private int noOfCopies;

	public Book(
			String isbn,
			String title,
			String author,
			List<BookCategory> bookCategory,
			List<String> keywords,
			int noOfCopies
	) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.categories = bookCategory;
		this.keywords = keywords;
		this.noOfCopies = noOfCopies;
	}
}
