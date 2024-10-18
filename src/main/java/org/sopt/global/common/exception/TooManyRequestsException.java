package org.sopt.global.common.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public class TooManyRequestsException extends DiaryException{
	public TooManyRequestsException(final BaseErrorCode baseErrorCode) {
		super(baseErrorCode);
	}
}
