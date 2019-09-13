package Z_AQS1.可重入锁.conditionDemo.oneLock.生产者消费者作业.service;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExpressCondOneLockService {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private ArrayList<String> list = new ArrayList();

	/**
	 * 添加
	 */
	public void add(String name) {

		lock.lock();
		try {

			list.add(name);
			System.out.println("已经添加完集合了.:" + name);


			condition.signal();
		} finally {
			lock.unlock();
		}


	}

	/**
	 * 消耗
	 */
	public void consume() {

		while (true) {
			lock.lock();
			try {
				while (list.size() == 0) {
					System.out.println("当前集合容器为空, 我先等待...");
					condition.await();
					//当被别的线程唤醒的时候,这个线程就会中await()下面开始启动执行
					System.out.println("我被唤醒了.");
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

			if (list.size() != 0) {

				String remove = list.remove(0);

				System.out.println("我消耗了一个集合的数量,这个集合为: " + remove);
				System.out.println("我执行完了,可以干别的事情了... " +
						"在这里可以执行业务逻辑代码");
				System.out.println("===========================");
			}


		}

	}
}
