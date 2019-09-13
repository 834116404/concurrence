package Z_AQS1.可重入锁.conditionDemo.moreLock.老师代码.Thread;

import Z_AQS1.可重入锁.conditionDemo.moreLock.老师代码.ExpressCond;

/*检查地点变化的线程,不满足条件，线程一直等待*/
public class CheckSite extends Thread {
	private ExpressCond express;

	public CheckSite(ExpressCond express) {
		this.express = express;
	}

	@Override
	public void run() {
		express.waitSite();
	}
}