package org.sopt.global.common.exception;

import org.sopt.global.common.exception.base.BaseErrorCode;

public class DiaryException extends RuntimeException {
	private final BaseErrorCode baseErrorCode;

	public DiaryException(BaseErrorCode baseErrorCode) {
		super(baseErrorCode.getMessage());
		this.baseErrorCode = baseErrorCode;
	}

	public BaseErrorCode getBaseErrorCode() {
		return baseErrorCode;
	}

}
