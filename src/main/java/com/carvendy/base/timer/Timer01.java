package com.carvendy.base.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Timer01 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		
		/*
		 * 特定一天，的时间
		 */
		Date time1=new Date();
		time1.setTime(time1.getTime()+1000*10);
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("xixix");	
			}
		}, time1);
		
		
		//15秒后。。。。。。
		 Timer timer2 = new Timer();
		 timer2.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				System.out.println("job2......");
			}
		}, 1000*15);
		 
		 
		 //特定时间之后，每隔多少秒
		 Timer timer3 = new Timer();
		 timer3.schedule(new TimerTask() {
			
			@Override
			public void run() {	
				System.out.println("job3......");
			}
		}, time1,1000*15);
		 
		 //多少秒后，每隔多少秒
		 Timer timer4 = new Timer();
		 timer4.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				System.out.println(sdf.format(new Date())+"job4......");
			}
		}, 1000*13,1000*60*2);
		 
		
		 /*
		  * schedule和scheduleAtFixedRate的区别在于，如果指定开始执行的时间在当前系统运行时间之前，
		  * scheduleAtFixedRate会把已经过去的时间也作为周期执行，而schedule不会把过去的时间算上。 
		  */
		 
		Timer timer5 = new Timer();
		timer5.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(sdf.format(new Date())+":scheduleAtFixedRate");
			}
		}, 10*1000, 60*1000);
	}
}
