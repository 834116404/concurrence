package C_线程协作.NotifyOrNotifyAll;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java程序演示如何在Java和Java中使用notify和notifyAll方法
 *   *如何通知和notifyAll方法通知线程，哪个线程被唤醒等。
 */
public class NotificationTest {

	private volatile boolean go = false;

	public static void main(String args[]) throws InterruptedException {
		final NotificationTest test = new NotificationTest();

		Runnable waitTask = new Runnable() {

			@Override
			public void run() {
				try {
					test.shouldGo();
				} catch (InterruptedException ex) {
					Logger.getLogger(NotificationTest.class.getName()).
							log(Level.SEVERE, null, ex);
				}
				System.out.println(Thread.currentThread() + " 完成执行");
			}
		};

		Runnable notifyTask = new Runnable() {

			@Override
			public void run() {
				test.go();
				System.out.println(Thread.currentThread() + " 完成执行");
			}
		};

		Thread t1 = new Thread(waitTask, "WT1"); //will wait
		Thread t2 = new Thread(waitTask, "WT2"); //will wait
		Thread t3 = new Thread(waitTask, "WT3"); //will wait
		Thread t4 = new Thread(notifyTask, "NT1"); //will notify

		//starting all waiting thread
		t1.start();
		t2.start();
		t3.start();

		//暂停，以确保所有等待的线程都已成功启动
		Thread.sleep(200);

		//starting notifying thread
		t4.start();

	}

	/*
	 * 只能从synchronized方法或bock中调用wait和notify
	 */
	private synchronized void shouldGo() throws InterruptedException {
		while (go != true) {
			System.out.println(Thread.currentThread()
					+ " 会等待这个对象吗");
			wait(); //释放锁定并在唤醒时重新获取
			System.out.println(Thread.currentThread() + " 是醒来");
		}
		go = false; //resetting condition
	}

	/*
	 * both shouldGo() and go() are locked on current object referenced by "this" keyword
	 */
	private synchronized void go() {
		while (go == false) {
			System.out.println(Thread.currentThread()
					+ "将通知所有或一个线程等待这个对象吗");

			go = true; //使等待线程的条件为真
//			notify(); // 等待线程WT1、WT2、WT3中只有一个会被唤醒
			notifyAll(); // 所有等待线程WT1、WT2、WT3将被唤醒
		}

	}

}