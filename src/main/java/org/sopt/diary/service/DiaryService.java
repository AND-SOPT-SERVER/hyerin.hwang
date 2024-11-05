package org.sopt.diary.service;

import java.util.List;
import java.util.stream.Collectors;

import org.sopt.diary.api.dto.DiaryCreateRequest;
import org.sopt.diary.api.dto.DiaryDeleteRequest;
import org.sopt.diary.api.dto.DiaryDetailRequest;
import org.sopt.diary.api.dto.DiaryUpdateRequest;
import org.sopt.diary.api.dto.OrderBy;
import org.sopt.diary.exception.DiaryErrorCode;
import org.sopt.diary.repository.Category;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.sopt.global.common.exception.ConflictException;
import org.sopt.global.common.exception.NotFoundException;
import org.sopt.global.common.exception.TooManyRequestsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final DiaryRateLimiter diaryRateLimiter;
	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final int DEFAULT_PAGE_NUMBER = 0;

	// bean 주입 di
	public DiaryService(DiaryRepository diaryRepository, DiaryRateLimiter diaryRateLimiter) {
		this.diaryRepository = diaryRepository;
		this.diaryRateLimiter = diaryRateLimiter;
	}

	// 일기 작성
	public Diary createDiary(DiaryCreateRequest diaryCreateRequest) {
		if(!diaryRateLimiter.tryConsume()){
			throw new TooManyRequestsException(DiaryErrorCode.DIARY_CREATE_TIME_LIMIT_LEFT);
		}

		boolean existsByTitle = diaryRepository.existsByTitle(diaryCreateRequest.title());
		if (existsByTitle) {
			throw new ConflictException(DiaryErrorCode.DIARY_TITLE_ALREADY_EXISTS);
		}

		final DiaryEntity diaryEntity = new DiaryEntity(diaryCreateRequest.title(), diaryCreateRequest.body(),
			diaryCreateRequest.category());
		diaryRepository.save(diaryEntity);

		final Diary diary = convertToDiary(diaryEntity);

		return diary;
	}

	// 일기 상세 조회
	public Diary getDiaryDetail(DiaryDetailRequest diaryDetailRequest) {
		final DiaryEntity diaryEntity = diaryRepository.findById(diaryDetailRequest.id())
			.orElseThrow(() -> new NotFoundException(DiaryErrorCode.DIARY_NOT_FOUND));
		final Diary diary = convertToDiary(diaryEntity);

		return diary;
	}

	// db에서 가져온 diaryEntity를 diary 객체로 만들어서 list로 리턴
	// 일기 목록 조회
	public List<Diary> getList(Category category, OrderBy orderBy) {
		Pageable pageable;

		if (orderBy == OrderBy.BODY_LENGTH) {
			pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);

			Page<DiaryEntity> diaryEntityPage;
			if (category == null) {
				diaryEntityPage = diaryRepository.findAllOrderByBodyLengthDesc(pageable);
			} else {
				diaryEntityPage = diaryRepository.findByCategoryOrderByBodyLengthDesc(category, pageable);
			}
			return diaryEntityPage.getContent().stream().map(this::convertToDiary).collect(Collectors.toList());
		} else {
			Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
			pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, sort);

			Page<DiaryEntity> diaryEntityPage;
			if (category == null) {
				diaryEntityPage = diaryRepository.findAll(pageable);
			} else {
				diaryEntityPage = diaryRepository.findByCategory(category, pageable);
			}
			return diaryEntityPage.getContent().stream().map(this::convertToDiary).collect(Collectors.toList());
		}
	}

	// 일기 수정
	public Diary updateDiary(DiaryUpdateRequest diaryUpdateRequest) {
		final DiaryEntity diaryEntity = diaryRepository.findById(diaryUpdateRequest.id())
			.orElseThrow(() -> new NotFoundException(DiaryErrorCode.DIARY_NOT_FOUND));

		if (!diaryEntity.getTitle().equals(diaryUpdateRequest.title())){
			boolean existsByTitle = diaryRepository.existsByTitle(diaryUpdateRequest.title());
			if (existsByTitle) {
				throw new ConflictException(DiaryErrorCode.DIARY_TITLE_ALREADY_EXISTS);
			}
		}

		diaryEntity.setTitle(diaryUpdateRequest.title());
		diaryEntity.setBody(diaryUpdateRequest.body());
		diaryEntity.setCategory(diaryUpdateRequest.category());

		diaryRepository.save(diaryEntity);
		final Diary diary = convertToDiary(diaryEntity);

		return diary;
	}

	// 일기 삭제
	public void deleteDiary(DiaryDeleteRequest diaryDeleteRequest) {
		diaryRepository.deleteById(diaryDeleteRequest.id());
	}

	private Diary convertToDiary(DiaryEntity diaryEntity) {
		return new Diary(
			diaryEntity.getId(),
			diaryEntity.getTitle(),
			diaryEntity.getBody(),
			diaryEntity.getCreatedAt(),
			diaryEntity.getCategory()
		);
	}

}
