package E_线程并发工具类.d_Semaphore.实现数据库连接池;


import utils.SleepTools;

import java.sql.Connection;
import java.util.Random;

/**
 * 类说明：测试数据库连接池
 * zjj_SpringBoot_1f13d8d5-0bd1-e369-b316-f2416b5bbaeb
 */
public class Main {

	private static DBPoolSemaphore dbPool = new DBPoolSemaphore();

	private static class BusiThread extends Thread {
		@Override
		public void run() {
			Random r = new Random();//让每个线程持有连接的时间不一样
			long start = System.currentTimeMillis();
			try {
				Connection connect = dbPool.takeConnect();
				System.out.println("Thread_" + Thread.currentThread().getId()
						+ "_获取数据库连接共耗时【" + (System.currentTimeMillis() - start) + "】ms.");
				SleepTools.ms(100 + r.nextInt(100));//模拟业务操作，线程持有连接查询数据
				System.out.println("查询数据完成，归还连接！");
				dbPool.returnConnect(connect);
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			Thread thread = new BusiThread();
			thread.start();
		}
	}

}
