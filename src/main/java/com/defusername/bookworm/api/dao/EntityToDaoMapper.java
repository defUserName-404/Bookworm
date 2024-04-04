package com.defusername.bookworm.api.dao;

import com.defusername.bookworm.api.entity.Author;
import com.defusername.bookworm.api.entity.Book;
import com.defusername.bookworm.api.entity.Genre;
import com.defusername.bookworm.api.entity.Publisher;

import java.util.stream.Collectors;

public class EntityToDaoMapper {

	private EntityToDaoMapper() {
	}

	public static BookResponse mapBookEntityToResponse(Book book) {
		return new BookResponse(book.getTitle(), book.getDescription(), book.getIsbn(),
								book.getAuthors()
								   .stream()
								   .map(EntityToDaoMapper::mapAuthorEntityToResponse)
								   .collect(Collectors.toSet()),
								book.getPublishers()
								   .stream()
								   .map(EntityToDaoMapper::mapPublisherEntityToResponse)
								   .collect(Collectors.toSet()),
								book.getGenres()
								   .stream()
								   .map(EntityToDaoMapper::mapGenreEntityToResponse)
								   .collect(Collectors.toSet()), book.getPageCount(),
								book.getPagesRead(), book.getStatus(), book.getFileType()
		);
	}

	public static AuthorResponse mapAuthorEntityToResponse(Author author) {
		return new AuthorResponse(author.getName(), author.getDescription());
	}

	public static GenreResponse mapGenreEntityToResponse(Genre genre) {
		return new GenreResponse(genre.getName());
	}

	public static PublisherResponse mapPublisherEntityToResponse(Publisher publisher) {
		return new PublisherResponse(publisher.getName());
	}

}
