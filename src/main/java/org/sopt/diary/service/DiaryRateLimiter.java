package org.sopt.diary.service;

import java.time.Duration;

import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Component
public class DiaryRateLimiter {
	private final Bucket bucket;

	public DiaryRateLimiter(){
		Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofMinutes(5)));
		this.bucket = Bucket.builder().addLimit(limit).build();
	}

	public boolean tryConsume(){
		return bucket.tryConsume(1);
	}
}
