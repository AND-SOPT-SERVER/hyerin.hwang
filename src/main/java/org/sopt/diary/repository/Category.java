package org.sopt.diary.repository;

public enum Category {
	FOOD("음식"),
	WORKOUT("운동"),
	STUDY("공부"),
	HOBBY("취미");

	private final String displayname;

	private Category(final String displayname) {
		this.displayname = displayname;
	}

	public String getDisplayname() {
		return displayname;
	}
}
