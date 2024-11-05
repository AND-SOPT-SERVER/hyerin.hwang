package org.sopt.global.common.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public class NotFoundException extends DiaryException{
	public NotFoundException(final BaseErrorCode baseErrorCode) {
		super(baseErrorCode);
	}
}
