package org.sopt.diary.api;

import java.util.ArrayList;
import java.util.List;

import org.sopt.diary.api.dto.DiaryCreateRequest;
import org.sopt.diary.api.dto.DiaryDeleteRequest;
import org.sopt.diary.api.dto.DiaryDetailRequest;
import org.sopt.diary.api.dto.DiaryListResponse;
import org.sopt.diary.api.dto.DiaryResponse;
import org.sopt.diary.api.dto.DiaryUpdateRequest;
import org.sopt.diary.api.dto.OrderBy;
import org.sopt.diary.exception.DiarySuccessCode;
import org.sopt.global.common.dto.SuccessResponse;
import org.sopt.global.common.exception.BadRequestException;
import org.sopt.diary.exception.DiaryErrorCode;
import org.sopt.diary.repository.Category;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// controller 클라 요청 받고 주는 역할
@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
	private final DiaryService diaryService;

	public DiaryController(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	// 일기 생성
	@PostMapping("/post")
	ResponseEntity<SuccessResponse<DiaryResponse>> post(@RequestBody DiaryCreateRequest diaryCreateRequest) {
		if (diaryCreateRequest.body().length() > 30) {
			throw new BadRequestException(DiaryErrorCode.DIARY_BODY_LENGTH_EXCEEDED);
		}
		Diary diary = diaryService.createDiary(diaryCreateRequest);
		DiaryResponse diaryResponse = new DiaryResponse(diary.getId(), diary.getTitle(), diary.getBody(),
			diary.getCreatedAt(), diary.getCategory());

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(SuccessResponse.of(DiarySuccessCode.DIARY_CREATE_SUCCESS, diaryResponse));
	}

	// 일기 목록 조회
	@GetMapping("/list")
	ResponseEntity<SuccessResponse<DiaryListResponse>> getList(
		@RequestParam(required = false) Category category,
		@RequestParam(defaultValue = "CREATED_AT") OrderBy orderBy
	) {
		// (1) Service로부터 가져온 DiaryList
		List<Diary> diaryList = diaryService.getList(category, orderBy);

		// (2) Client 와 협의한 interface로 변환
		List<DiaryResponse> diaryResponseList = new ArrayList<>();
		for (Diary diary : diaryList) {
			diaryResponseList.add(
				new DiaryResponse(diary.getId(), diary.getTitle(), diary.getBody(), diary.getCreatedAt(),
					diary.getCategory()));
		}

		return ResponseEntity.status(HttpStatus.OK)
			.body(SuccessResponse.of(DiarySuccessCode.DIARY_LIST_RETRIEVE_SUCCESS, new DiaryListResponse(diaryResponseList)));
	}

	// 일기 상세 조회
	@GetMapping("/detail")
	ResponseEntity<SuccessResponse<DiaryResponse>> getDetail(@RequestBody DiaryDetailRequest diaryDetailRequest) {
		Diary diary = diaryService.getDiaryDetail(diaryDetailRequest);
		DiaryResponse diaryResponse = new DiaryResponse(diary.getId(), diary.getTitle(), diary.getBody(),
			diary.getCreatedAt(), diary.getCategory());

		return ResponseEntity.status(HttpStatus.OK)
			.body(SuccessResponse.of(DiarySuccessCode.DIARY_DETAIL_RETRIEVE_SUCCESS, diaryResponse));
	}

	// 일기 수정
	@PatchMapping("/update")
	ResponseEntity<SuccessResponse<DiaryResponse>> update(@RequestBody DiaryUpdateRequest diaryUpdateRequest) {
		if (diaryUpdateRequest.body().length() > 30) {
			throw new BadRequestException(DiaryErrorCode.DIARY_BODY_LENGTH_EXCEEDED);
		}
		Diary diary = diaryService.updateDiary(diaryUpdateRequest);
		DiaryResponse diaryResponse = new DiaryResponse(diary.getId(), diary.getTitle(), diary.getBody(),
			diary.getCreatedAt(), diary.getCategory());

		return ResponseEntity.status(HttpStatus.OK)
			.body(SuccessResponse.of(DiarySuccessCode.DIARY_UPDATE_SUCCESS, diaryResponse));
	}

	// 일기 삭제
	@DeleteMapping("/delete")
	ResponseEntity<SuccessResponse<Void>> delete(@RequestBody DiaryDeleteRequest diaryDeleteRequest) {
		diaryService.deleteDiary(diaryDeleteRequest);
		return ResponseEntity.status(HttpStatus.OK)
			.body(SuccessResponse.from(DiarySuccessCode.DIARY_DELETE_SUCCESS));
	}
}
