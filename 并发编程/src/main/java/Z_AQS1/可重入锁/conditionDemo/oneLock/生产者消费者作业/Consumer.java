package Z_AQS1.可重入锁.conditionDemo.oneLock.生产者消费者作业;

import Z_AQS1.可重入锁.conditionDemo.oneLock.生产者消费者作业.service.ExpressCondOneLockService;

/**
 * 消费者
 */
public class Consumer implements Runnable {

	private ExpressCondOneLockService expressCondOneLockService;

	public Consumer(ExpressCondOneLockService expressCondOneLockService) {
		this.expressCondOneLockService = expressCondOneLockService;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "已经启动了");
		expressCondOneLockService.consume();
	}
}
