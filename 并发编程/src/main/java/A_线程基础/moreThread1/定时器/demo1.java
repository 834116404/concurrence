package A_线程基础.moreThread1.定时器;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 定时器创建线程
 */
public class demo1 {
	static Integer POLL = 1000;


	public static void main(String[] args) {
		ConcurrentLinkedQueue<Integer> ints = new ConcurrentLinkedQueue<Integer>();
		ints.add(1000);
		ints.add(2000);
		ints.add(3000);
		ints.add(40000);
		Timer timer = new Timer();

		/*
		开启定时任务
		 */
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				POLL = ints.poll();
				// 实现定时任务
				System.out.println("timertask is run:当前的值是" + POLL);
				ints.add(POLL);
			}
		}, 0, POLL);


		/*timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// 实现定时任务
				System.out.println("timertask is run");

			}
		}, 0, 1000);
		*/
	}

}
