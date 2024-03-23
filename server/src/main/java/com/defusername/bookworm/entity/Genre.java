package com.defusername.bookworm.entity;

import com.defusername.bookworm.entity.constants.BookCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
@DynamicUpdate
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private BookCategory name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "genres")
	private Set<Book> books;

	public Genre() {
		this.books = new HashSet<>();
	}

}
