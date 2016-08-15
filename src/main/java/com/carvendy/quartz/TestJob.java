package com.carvendy.quartz;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job{

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		System.out.println("myjob:"+new Date().getTime());
	}

	
}
