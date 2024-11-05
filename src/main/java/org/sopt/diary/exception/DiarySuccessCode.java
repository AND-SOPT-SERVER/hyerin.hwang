package org.sopt.diary.exception;

import org.sopt.global.common.exception.base.BaseSuccessCode;

public enum DiarySuccessCode implements BaseSuccessCode {
	DIARY_CREATE_SUCCESS(201, "일기가 성공적으로 등록되었습니다."),
	DIARY_LIST_RETRIEVE_SUCCESS(200, "일기 리스트가 성공적으로 조회되었습니다."),
	DIARY_DETAIL_RETRIEVE_SUCCESS(200, "일기 상세 정보가 성공적으로 조회되었습니다."),
	DIARY_UPDATE_SUCCESS(200, "일기가 성공적으로 수정되었습니다."),
	DIARY_DELETE_SUCCESS(200, "일기가 성공적으로 삭제되었습니다.");

	private final int status;
	private final String message;

	DiarySuccessCode(int status, String message) {
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
