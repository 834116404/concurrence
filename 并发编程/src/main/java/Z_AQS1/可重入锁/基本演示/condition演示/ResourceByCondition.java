package Z_AQS1.可重入锁.基本演示.condition演示;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 */
public class ResourceByCondition {
	private String name;
	private int count = 1;
	private boolean flag = false;


	Lock lock = new ReentrantLock();


	Condition producer_con = lock.newCondition();
	Condition consumer_con = lock.newCondition();

	/**
	 * 生产
	 *
	 * @param name
	 */
	public void product(String name) {
		lock.lock();
		try {
			while (flag) {
				try {
					producer_con.await();
				} catch (InterruptedException e) {
				}
			}
			this.name = name + count;
			count++;
			System.out.println(Thread.currentThread().getName() + "...生产者5.0..." + this.name);
			flag = true;
			consumer_con.signal();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 消费
	 */
	public void consume() {
		lock.lock();
		try {
			while (!flag) {
				try {
					consumer_con.await();
				} catch (InterruptedException e) {
				}
			}
			System.out.println(Thread.currentThread().getName() + "...消费者.5.0......." + this.name);
			flag = false;
			producer_con.signal();
		} finally {
			lock.unlock();
		}
	}
}