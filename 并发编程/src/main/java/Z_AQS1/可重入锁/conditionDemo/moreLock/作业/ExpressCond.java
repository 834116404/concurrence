package Z_AQS1.可重入锁.conditionDemo.moreLock.作业;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示两个锁
 */
public class ExpressCond {
	//公里
	private int kilometre = 0;
	//地点
	private String city = "上海";

	private Lock kilometreLock = new ReentrantLock();
	private Lock cityLock = new ReentrantLock();

	private Condition kilometreCondition = kilometreLock.newCondition();
	private Condition cityCondition = cityLock.newCondition();


	public void listenKilometre() {
		kilometreLock.lock();
		try {
			while (kilometre == 0) {
				System.out.println("现在公里数是0 , 继续监听");
				kilometreCondition.await();
				System.out.println("我被唤醒了");

			}


		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			kilometreLock.unlock();
		}
		System.out.println("现在的公里数是 :" + kilometre);

	}

	public void setKilometre(int kilometre) {
		kilometreLock.lock();
		try {
			this.kilometre = kilometre;
			kilometreCondition.signal(); //唤醒线程
		} finally {
			kilometreLock.unlock();
		}

	}



	/*设置城市和监听城市*/

	/**
	 * 监视城市
	 */
	public void listenCity() throws InterruptedException {
		cityLock.lock();
		try {

			while (this.city.equals("上海")) {
				System.out.println("现在的城市是上海,我正在监听");
				cityCondition.await(); //等待状态
				System.out.println("我已经被唤醒了" + Thread.currentThread().getName());
			}

			System.out.println("我已经监听到城市发生修改了,, 现在的城市是" + this.city);


		} finally {
			cityLock.unlock();
		}
	}


	/**
	 * 设置城市
	 *
	 * @param city
	 */
	public void setCity(String city) {
		cityLock.lock();
		try {
			this.city = city;
			cityCondition.signal();//唤醒
		} finally {
			cityLock.unlock();
		}

	}


	public ExpressCond(int kilometre, String city) {
		this.city = city;
		this.kilometre = kilometre;
	}


}