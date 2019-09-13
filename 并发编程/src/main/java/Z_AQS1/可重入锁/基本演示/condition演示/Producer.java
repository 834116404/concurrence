package Z_AQS1.可重入锁.基本演示.condition演示;

import utils.SleepTools;

/**
 * @decrition 生产者线程
 */
class Producer implements Runnable {
	private ResourceByCondition r;

	Producer(ResourceByCondition r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			SleepTools.second(1);
			System.out.println("生产者开始生产了*****");
			r.product("北京烤鸭");
		}
	}
}