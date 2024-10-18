package org.sopt.diary.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public enum DiaryErrorCode implements BaseErrorCode {
	DIARY_BODY_LENGTH_EXCEEDED(400, "일기 글자수가 30자를 초과했습니다."),
	DIARY_TITLE_ALREADY_EXISTS(409, "이미 같은 제목의 일기가 존재합니다."),
	DIARY_NOT_FOUND(404, "해당 일기는 존재하지 않습니다."),
	DIARY_CREATE_TIME_LIMIT_LEFT(429, "일기 작성은 5분에 1번씩 가능합니다.");

	private final int status;
	private final String message;

	DiaryErrorCode(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return this.status;
	}

	public String getMessage() {
		return this.message;
	}
}
