package org.sopt.seminar1;

import java.time.LocalDateTime;

public class Diary {
	private final Long id;
	private String body;
	private final LocalDateTime createdAt;

	private Diary(Long id, String body) {
		this.id = id;
		this.body = body;
		this.createdAt = LocalDateTime.now();
	}

	public static Diary create(Long id, String body) {
		return new Diary(id, body);
	}

	public Long getId() {
		return id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
