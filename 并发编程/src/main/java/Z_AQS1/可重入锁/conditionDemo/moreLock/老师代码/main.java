package Z_AQS1.可重入锁.conditionDemo.moreLock.老师代码;

import Z_AQS1.可重入锁.conditionDemo.moreLock.老师代码.Thread.CheckKm;
import Z_AQS1.可重入锁.conditionDemo.moreLock.老师代码.Thread.CheckSite;

/**
 * 类说明：测试Lock和Condition实现等待通知
 */
public class main {
	private static ExpressCond express = new ExpressCond(0, ExpressCond.CITY);


	public static void main(String[] args) throws InterruptedException {
		new CheckSite(express).start();
		new CheckKm(express).start();

		Thread.sleep(1000);
		express.changeKm();//快递里程变化
		express.changeSite(); // 变化地点
	}
}
