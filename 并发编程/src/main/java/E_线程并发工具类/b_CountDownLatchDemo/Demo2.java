package E_线程并发工具类.b_CountDownLatchDemo;

import utils.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * zjj_parent-eb4130da-0744-b59f-0beb-a89ea23fb8f8
 *
 * boss线程必须等待work线程执行完了, 计数器变为0的时候 boss线程才能开始工作.
 */
public class Demo2 {


	static CountDownLatch latch = new CountDownLatch(2);


	public static void main(String[] args) {


		Thread boss1 = new Thread("boss1") {
			@Override
			public void run() {
				System.out.println("[boss线程] 线程开始执行了");
				try {
					System.out.println("[boss线程] 线程准备阻塞等待子线程完成工作!!!!");
					latch.await();
					System.out.println("[boss线程] 子线程完成工作了,boss1线程开始唤醒继续工作!!!!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};


		Thread worker1 = new Thread("worker1") {
			@Override
			public void run() {
				System.out.println("work1线程开始执行了");
				SleepTools.second(3);
				long count = latch.getCount();
				System.out.println("当前的计数器 :" + count);

				System.out.println("work1线程执行结束,计数器准备减一");
				latch.countDown();

			}
		};

		Thread worker2 = new Thread("worker2") {
			@Override
			public void run() {
				System.out.println("work2线程开始执行了");
				SleepTools.second(3);
				long count = latch.getCount();
				System.out.println("当前的计数器 :" + count);

				System.out.println("work2线程执行结束,计数器准备减一");
				latch.countDown();
			}
		};
		boss1.start();
		worker1.start();
		worker2.start();


	}
}
