package org.sopt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// spring boot 시작점 trigger. main으로 java application 실행
@SpringBootApplication
public class DiaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiaryApplication.class, args);
	}
}
