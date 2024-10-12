package org.sopt.seminar1;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryService {
	private final DiaryRepository diaryRepository = new DiaryRepository();
	private final AtomicLong idGenerator = new AtomicLong();

	public List<Diary> getDiaryList() {
		return diaryRepository.findAll();
	}

	public void saveDiary(String body) {
		validateBodyLength(body);
		Diary diary = Diary.create(idGenerator.incrementAndGet(), body);
		diaryRepository.saveDiary(diary.getId(), diary);
	}

	public void patchDiary(Long id, String body) {
		validateBodyLength(body);
		diaryRepository.patchDiary(id, body);
	}

	public void deleteDiary(Long id) {
		diaryRepository.deleteDiary(id);
	}

	private void validateBodyLength(String body) {
		if (body.length() > 30) {
			throw new IllegalArgumentException("일기 내용은 30자 이하만 가능합니다.");
		}
	}
}

