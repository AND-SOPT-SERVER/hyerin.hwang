package org.sopt.diary.api.dto;

import java.time.LocalDateTime;

import org.sopt.diary.repository.Category;

public class DiaryResponse {
	private long id;
	private String title;
	private String body;
	private LocalDateTime createdAt;
	private Category category;

	public DiaryResponse(long id, String title, String body, LocalDateTime createdAt, Category category) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.createdAt = createdAt;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Category getCategory() {
		return category;
	}
}
