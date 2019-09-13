package Z_AQS1.可重入锁.conditionDemo.oneLock.生产者消费者作业;

import Z_AQS1.可重入锁.conditionDemo.oneLock.生产者消费者作业.service.ExpressCondOneLockService;
import utils.SleepTools;

public class main {

	public static void main(String[] args) {
		ExpressCondOneLockService expressCondOneLockService = new ExpressCondOneLockService();
		Consumer consumer = new Consumer(expressCondOneLockService);
		Thread consumerThread = new Thread(consumer);
		consumerThread.setName("consumer");
		consumerThread.start();

		SleepTools.second(1);
		for (int i = 0; i < 20; i++) {
//			SleepTools.ms(500);  //可以调整时间  ,也可以注释掉, 然后多运行几次看效果
			expressCondOneLockService.add("名字 : " + i);

		}


	}
}
