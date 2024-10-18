package org.sopt.global.common.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public class BadRequestException extends DiaryException {
	public BadRequestException(final BaseErrorCode baseErrorCode) {
		super(baseErrorCode);
	}
}
