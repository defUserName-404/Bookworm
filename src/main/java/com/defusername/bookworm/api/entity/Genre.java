package com.defusername.bookworm.api.entity;

import com.defusername.bookworm.api.entity.constant.BookCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
@Builder
@AllArgsConstructor
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private BookCategory name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, mappedBy = "genres")
	private Set<Book> books;

	public Genre() {
		this.books = new HashSet<>();
	}

}
