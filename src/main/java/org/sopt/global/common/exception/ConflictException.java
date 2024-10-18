package org.sopt.global.common.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public class ConflictException extends DiaryException {
	public ConflictException(final BaseErrorCode baseErrorCode) {
		super(baseErrorCode);
	}
}
