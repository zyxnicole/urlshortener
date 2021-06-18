package com.nicole.urlshortener;

import com.nicole.urlshortener.auth.Sessions;
import com.nicole.urlshortener.cache.LruCache;
import com.nicole.urlshortener.service.ShortUrlIterator;
import com.nicole.urlshortener.service.UrlShortener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

//	@Bean
//	Sessions getSessions() {
//		return new Sessions();
//	}

//	@Bean
//	LruCache getLruCache() {
//		return new LruCache();
//	}

//	@Bean
//	ShortUrlIterator getShortUrlIterator() {
//		return new ShortUrlIterator();
//	}

//	@Bean
//	UrlShortener getUrlShortener(ShortUrlIterator shortUrlIterator, LruCache lruCache) {
//		return new UrlShortener(shortUrlIterator, lruCache);
//	}
}
