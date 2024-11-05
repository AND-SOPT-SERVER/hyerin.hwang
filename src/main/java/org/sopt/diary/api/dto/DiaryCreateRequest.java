package org.sopt.diary.api.dto;

import org.sopt.diary.repository.Category;

public record DiaryCreateRequest(
	String title,
	String body,
	Category category
) {
}
