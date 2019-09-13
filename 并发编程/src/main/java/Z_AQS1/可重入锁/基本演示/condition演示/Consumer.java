package Z_AQS1.可重入锁.基本演示.condition演示;

/**
 * @decrition 消费者线程
 */
class Consumer implements Runnable {
	private ResourceByCondition r;

	Consumer(ResourceByCondition r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			r.consume();
		}
	}
}