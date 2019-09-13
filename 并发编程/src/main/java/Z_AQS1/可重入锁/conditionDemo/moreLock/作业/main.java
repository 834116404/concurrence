package Z_AQS1.可重入锁.conditionDemo.moreLock.作业;

import utils.SleepTools;

/**
 * 类说明：测试Lock和Condition实现等待通知
 */
public class main {


	public static void main(String[] args) throws InterruptedException {
		ExpressCond expressCond = new ExpressCond(0, "上海");
		Thread t1 = new Thread(new CheckCityThread(expressCond));
		Thread t2 = new Thread(new CheckKilometreThread(expressCond));

		t2.setName("监听公里数线程");
		t1.setName("监听城市线程");

		t1.start();
		t2.start();
		SleepTools.second(2);
		expressCond.setCity("北京");

		expressCond.setKilometre(50);
		//****************************
	}
}
