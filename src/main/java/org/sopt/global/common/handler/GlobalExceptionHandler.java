package org.sopt.global.common.handler;

import java.util.Optional;

import org.sopt.global.common.dto.ErrorResponse;
import org.sopt.global.common.exception.BadRequestException;
import org.sopt.global.common.exception.ConflictException;
import org.sopt.global.common.exception.DiaryException;
import org.sopt.global.common.exception.ForbiddenException;
import org.sopt.global.common.exception.NotFoundException;
import org.sopt.global.common.exception.TooManyRequestsException;
import org.sopt.global.common.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(final BadRequestException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponse.from(e.getBaseErrorCode()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String errorMessage = Optional.ofNullable(e.getBindingResult().getFieldError())
			.map(FieldError::getDefaultMessage)
			.orElse("Validation Error");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), errorMessage));
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(final UnauthorizedException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(ErrorResponse.from(e.getBaseErrorCode()));
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(final ForbiddenException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
			.body(ErrorResponse.from(e.getBaseErrorCode()));
	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(ErrorResponse.from(e.getBaseErrorCode()));
	}

	@ExceptionHandler(ConflictException.class)
	protected ResponseEntity<ErrorResponse> handleConflictException(final ConflictException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(ErrorResponse.from(e.getBaseErrorCode()));
	}

	@ExceptionHandler(TooManyRequestsException.class)
	protected ResponseEntity<ErrorResponse> handleTooManyRequestsException(final TooManyRequestsException e) {
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
			.body(ErrorResponse.from(e.getBaseErrorCode()));
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부 오류입니다."));
	}

	@ExceptionHandler(DiaryException.class)
	public ResponseEntity<ErrorResponse> handleDiaryException(final DiaryException e) {
		return ResponseEntity.status(e.getBaseErrorCode().getStatus())
			.body(ErrorResponse.from(e.getBaseErrorCode()));
	}
}
