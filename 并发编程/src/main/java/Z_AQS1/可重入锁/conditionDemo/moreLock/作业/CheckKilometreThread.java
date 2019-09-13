package Z_AQS1.可重入锁.conditionDemo.moreLock.作业;

public class CheckKilometreThread implements Runnable {

	private ExpressCond expressCond;

	public CheckKilometreThread(ExpressCond expressCond) {
		this.expressCond = expressCond;
	}

	@Override
	public void run() {

		this.expressCond.listenKilometre();
	}
}
