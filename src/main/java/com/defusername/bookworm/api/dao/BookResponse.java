package com.defusername.bookworm.api.dao;

import com.defusername.bookworm.api.entity.constant.BookStatus;
import com.defusername.bookworm.api.entity.constant.FileType;

import java.util.Set;

public record BookResponse(
		String title,
		String description,
		String isbn,
		Set<AuthorResponse> authors,
		Set<PublisherResponse> publishers,
		Set<GenreResponse> genres,
		int pageCount,
		int pagesRead,
		BookStatus status,
		FileType fileType
) {

}
