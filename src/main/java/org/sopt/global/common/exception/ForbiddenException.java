package org.sopt.global.common.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public class ForbiddenException extends DiaryException{
	public ForbiddenException(final BaseErrorCode baseErrorCode) {
		super(baseErrorCode);
	}
}
