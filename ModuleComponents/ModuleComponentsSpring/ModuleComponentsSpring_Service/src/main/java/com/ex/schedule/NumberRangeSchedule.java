package com.ex.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumberRangeSchedule implements ApplicationRunner {

	Long max;

	@Value("${spring.external.url.doc.max}")
	String url;

	@Autowired
	RestTemplate rt;

	@Scheduled(cron = "${spring.cron.series.max}")
	public void scheduledFetchMax() {
		this.max = rt.getForObject(url, Long.class);

	}

	public void startupFetchMax() {
		this.max = rt.getForObject(url, Long.class);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		startupFetchMax();
	}

	@Bean
	public Long getMax() {
		return this.max;
	}
}
