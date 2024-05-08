package com.defusername.bookworm.api.dto;

import com.defusername.bookworm.api.entity.constant.BookCategory;

public record GenreRequest(Long id, BookCategory name) {

}
