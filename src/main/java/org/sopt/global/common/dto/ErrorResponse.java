package org.sopt.global.common.dto;

import org.sopt.global.common.exception.base.BaseErrorCode;

public record ErrorResponse (
	int status,
	String message
){

	public static ErrorResponse of(final int status, final String message) {
		return new ErrorResponse(status, message);
	}

	public static ErrorResponse from(final BaseErrorCode baseErrorCode) {
		return new ErrorResponse(baseErrorCode.getStatus(), baseErrorCode.getMessage());
	}

}
