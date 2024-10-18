package org.sopt.diary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

// db에서 가져오는 findAll, save 등 행위들만 저장하기에 interface
@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

	Page<DiaryEntity> findAll(Pageable pageable);

	Page<DiaryEntity> findByCategory(Category category, Pageable pageable);

	@Query("SELECT d FROM DiaryEntity d ORDER BY LENGTH(d.body) DESC, d.createdAt DESC")
	Page<DiaryEntity> findAllOrderByBodyLengthDesc(Pageable pageable);

	@Query("SELECT d FROM DiaryEntity d WHERE d.category = :category ORDER BY LENGTH(d.body) DESC, d.createdAt DESC")
	Page<DiaryEntity> findByCategoryOrderByBodyLengthDesc(Category category, Pageable pageable);

	boolean existsByTitle(String title);
}
