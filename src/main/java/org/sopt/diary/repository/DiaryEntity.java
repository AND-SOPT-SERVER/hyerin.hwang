package org.sopt.diary.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// database 표를 클래스로 매핑시켜주는 역할
@Entity
public class DiaryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(nullable = false)
	public String title;

	@Column(nullable = false)
	public String body;

	@Column(nullable = false)
	public LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;

	public DiaryEntity() {
	}

	public DiaryEntity(final String title, final String body, final Category category) {
		this.title = title;
		this.body = body;
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

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
