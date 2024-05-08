package com.defusername.bookworm.api.dto;

import com.defusername.bookworm.api.entity.constant.BookStatus;
import com.defusername.bookworm.api.entity.constant.FileType;

import java.util.Set;

public record BookRequest(Long id, String title,
						  String description,
						  String isbn,
						  Set<AuthorRequest> authors,
						  Set<PublisherRequest> publishers,
						  Set<GenreRequest> genres,
						  int pageCount,
						  int pagesRead,
						  BookStatus status,
						  FileType fileType) {

}
