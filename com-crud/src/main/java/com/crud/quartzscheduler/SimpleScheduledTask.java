package com.crud.quartzscheduler;

import org.springframework.stereotype.Component;

@Component("printingTask")
public class SimpleScheduledTask {

	public void printToConsole() {
		System.out.println("I'm scheduled by QUARTZ scheduler to print after every minute.");
	}
}
