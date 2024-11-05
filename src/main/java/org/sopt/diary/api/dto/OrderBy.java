package org.sopt.diary.api.dto;

public enum OrderBy {
	CREATED_AT("최신 등록 기준 정렬"),
	BODY_LENGTH("글자 수 많은 순 정렬");

	private final String displayname;

	OrderBy(String displayname) {
		this.displayname = displayname;
	}
}
