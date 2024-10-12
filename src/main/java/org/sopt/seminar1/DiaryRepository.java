package org.sopt.seminar1;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiaryRepository {
	private final Map<Long, Diary> storage = new ConcurrentHashMap<>();

	public void saveDiary(Long id, Diary diary) {
		storage.put(id, diary);
	}

	public Diary findById(Long id) {
		if (!storage.containsKey(id)) {
			throw new IllegalArgumentException("해당 id의 일기가 존재하지 않습니다.");
		}
		return storage.get(id);
	}

	public List<Diary> findAll() {
		return new ArrayList<>(storage.values());
	}

	public void patchDiary(Long id, String body) {
		Diary diary = findById(id);
		diary.setBody(body);
	}

	public void deleteDiary(Long id) {
		if (!storage.containsKey(id)) {
			throw new IllegalArgumentException("해당 id의 일기가 존재하지 않습니다.");
		}
		storage.remove(id);
	}
}
