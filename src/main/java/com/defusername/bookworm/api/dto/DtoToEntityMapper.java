package com.defusername.bookworm.api.dto;

import com.defusername.bookworm.api.entity.Author;
import com.defusername.bookworm.api.entity.Book;
import com.defusername.bookworm.api.entity.Genre;
import com.defusername.bookworm.api.entity.Publisher;

import java.util.stream.Collectors;

public class DtoToEntityMapper {

	private DtoToEntityMapper() {
	}

	public static Book mapBookRequestToEntity(BookRequest bookRequest) {
		Book book = Book.builder()
						.title(bookRequest.title())
						.isbn(bookRequest.isbn())
						.description(bookRequest.description())
						.authors(bookRequest.authors()
											.stream()
											.map(DtoToEntityMapper::mapAuthorRequestToEntity)
											.collect(Collectors.toSet()))
						.publishers(bookRequest.publishers()
											   .stream()
											   .map(DtoToEntityMapper::mapPublisherRequestToEntity)
											   .collect(Collectors.toSet()))
						.genres(bookRequest.genres()
										   .stream()
										   .map(DtoToEntityMapper::mapGenreRequestToEntity)
										   .collect(Collectors.toSet()))
						.fileType(bookRequest.fileType())
						.pageCount(bookRequest.pageCount())
						.pagesRead(bookRequest.pagesRead())
						.status(bookRequest.status())
						.build();
		if (bookRequest.id() != null) {
			book.setId(bookRequest.id());
		}
		return book;
	}

	public static Author mapAuthorRequestToEntity(AuthorRequest authorRequest) {
		Author author = Author.builder()
							  .name(authorRequest.name())
							  .description(authorRequest.description())
							  .build();
		if (authorRequest.id() != null) {
			author.setId(authorRequest.id());
		}
		return author;
	}

	public static Genre mapGenreRequestToEntity(GenreRequest genreRequest) {
		Genre genre = Genre.builder()
						   .name(genreRequest.name())
						   .build();
		if (genreRequest.id() != null) {
			genre.setId(genreRequest.id());
		}
		return genre;
	}

	public static Publisher mapPublisherRequestToEntity(PublisherRequest publisherRequest) {
		Publisher publisher = Publisher.builder()
									   .name(publisherRequest.name())
									   .build();
		if (publisherRequest.id() != null) {
			publisher.setId(publisherRequest.id());
		}
		return publisher;
	}

}
