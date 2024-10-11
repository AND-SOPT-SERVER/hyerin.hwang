package org.sopt.seminar1;

import java.util.List;

public class DiaryController {
	private Status status = Status.READY;
	private final DiaryService diaryService = new DiaryService();

	public Status getStatus() {
		return status;
	}

	public void boot() {
		this.status = Status.RUNNING;
	}

	public void finish() {
		this.status = Status.FINISHED;
	}

	public List<Diary> getList() {
		return diaryService.getDiaryList();
	}

	public void post(String body) {
		diaryService.saveDiary(body);
	}

	public void patch(String id, String body) {
		diaryService.patchDiary(convertIdType(id), body);
	}

	public void delete(String id) {
		diaryService.deleteDiary(convertIdType(id));
	}

	private Long convertIdType(final String id) {
		try {
			return Long.parseLong(id);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("ID 값은 숫자만 가능합니다.");
		}
	}

	public enum Status {
		READY,
		RUNNING,
		FINISHED,
		ERROR
	}
}
