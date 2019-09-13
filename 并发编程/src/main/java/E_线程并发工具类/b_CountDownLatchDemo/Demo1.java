package E_线程并发工具类.b_CountDownLatchDemo;


import utils.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * zjj_parent-eb4130da-0744-b59f-0beb-a89ea23fb8f8
 * 类说明：演示CountDownLatch用法，
 * 共5个初始化子线程，6个闭锁扣除点，扣除完毕后，主线程和业务线程才能继续执行
 */
public class Demo1 {

	static CountDownLatch latch = new CountDownLatch(6); //定义初始化CTN计数器是6(6个闭锁扣除点)

	/*初始化线程*/
	private static class InitThread implements Runnable {

		public void run() {
			System.out.println("线程_" + Thread.currentThread().getId()
					+ " 开始初始化工作......");
			latch.countDown();//计数器减一
			for (int i = 0; i < 2; i++) {
				System.out.println("线程_" + Thread.currentThread().getId()
						+ " ........继续做它的工作");
			}
		}
	}

	/*业务线程等待latch的计数器为0完成*/
	private static class BusiThread implements Runnable {

		public void run() {
			try {
				latch.await();  //等待方法,当这个被唤醒的时候意味着Latch数量被扣减为0了.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 3; i++) {
				System.out.println("业务线程_" + Thread.currentThread().getId()
						+ " 开始业务-----");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		new Thread(new Runnable() {
			public void run() {
				SleepTools.ms(1);
				System.out.println("线程_" + Thread.currentThread().getId()
						+ " 准备好初始化工作步骤1......");
				latch.countDown();//扣减
				System.out.println("开始步骤2.......");
				SleepTools.ms(1);
				System.out.println("线程_" + Thread.currentThread().getId()
						+ " 准备初始化工作步骤3......");
				latch.countDown();//扣减
			}
		}).start();

		new Thread(new BusiThread()).start();

		for (int i = 0; i <= 3; i++) {
			Thread thread = new Thread(new InitThread());
			thread.start();
		}

		latch.await(); //主线程也可以等待
		System.out.println("Main线程开始它的工作了.........");
	}
}
