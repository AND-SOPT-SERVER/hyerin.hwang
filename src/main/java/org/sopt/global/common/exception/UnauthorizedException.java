package org.sopt.global.common.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public class UnauthorizedException extends DiaryException{
	public UnauthorizedException(final BaseErrorCode baseErrorCode) {
		super(baseErrorCode);
	}
}
