package com.defusername.bookworm.entity;

import com.defusername.bookworm.entity.constants.BookCategories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
@AllArgsConstructor
@Builder
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private BookCategories name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "genres")
	private Set<Book> books;

	public Genre() {
		this.books = new HashSet<>();
	}

}
