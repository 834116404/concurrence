package Z_AQS1.可重入锁.conditionDemo.moreLock.老师代码.Thread;

import Z_AQS1.可重入锁.conditionDemo.moreLock.老师代码.ExpressCond;

/*检查里程数变化的线程,不满足条件，线程一直等待*/
public class CheckKm extends Thread {
	private ExpressCond express;

	public CheckKm(ExpressCond express) {
		this.express = express;
	}

	@Override
	public void run() {
		express.waitKm();
	}
}
