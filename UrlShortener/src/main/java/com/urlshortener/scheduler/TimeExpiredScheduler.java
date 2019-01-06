package com.urlshortener.scheduler;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.urlshortener.database.URLShortenerMapper;

@Component
public class TimeExpiredScheduler {

	@Autowired
	URLShortenerMapper urlShortenerMapper;

	 @Scheduled(cron = "0 0 0 * * *")
//	@Scheduled(fixedDelay = 100000)
	public void removeExpiredURL() {

		urlShortenerMapper.deleteExpiredURL(getPivotDate());
		
	}

	// Expired Condition Setting
	public Date getPivotDate() {

		Date date = new Date();
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.DATE, -2);
		
		date = cal.getTime();
		
		return date;
	}

}
