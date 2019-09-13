package Z_AQS1.可重入锁.基本演示.不加锁的效果;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkerThread extends Thread {

	private Lock lock = new ReentrantLock();//可重入锁
	private int age;

	public WorkerThread(int age) {
		this.age = age;
	}

	@Override
	public void run() {

		for (int i = 0; i < 100000; i++) {
			progressive();
		}
		System.out.println("递增后的操作" + age);
	}

	/**
	 * 递减操作
	 */
	public void decrease() {

		try {
//			lock.lock();
			age--;
		} finally {
//			lock.unlock();
		}

	}


	/**
	 * 递增操作
	 */
	public void progressive() {

		try {
//			lock.lock();
			age++;
		} finally {
//			lock.unlock();
		}
	}
}
