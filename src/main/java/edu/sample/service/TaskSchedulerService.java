package edu.sample.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskSchedulerService {

	@Scheduled(fixedRate=5000)
	public void schedulerExecutor() {
		System.out.println("Cron executor---------------------@@@@@@@@@@@@@@@@@@@@---------------------");
	}
}
