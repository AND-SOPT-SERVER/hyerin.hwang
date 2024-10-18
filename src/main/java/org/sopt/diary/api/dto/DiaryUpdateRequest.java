package org.sopt.diary.api.dto;

import java.time.LocalDateTime;

import org.sopt.diary.repository.Category;

public record DiaryUpdateRequest(
	Long id,
	String title,
	String body,
	Category category
) {
}
