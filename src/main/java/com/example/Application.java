package com.example;

import com.example.entities.Feed;
import com.example.repositories.FeedRepository;
import com.example.services.FeedService;
import com.example.utilities.FeedScraper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(FeedScraper scraper, FeedRepository feedRepository, FeedService feedService) {
		return args -> {
			Runnable runnableScraper = () -> {
				try {
					List<Feed> scrapedData = scraper.scrape();
					feedService.saveIfNotExist(scrapedData, feedRepository);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			};

			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(runnableScraper, 0, 5, TimeUnit.MINUTES);
		};
	}
}