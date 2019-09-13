package Z_AQS1.可重入锁.conditionDemo.moreLock.作业;

public class CheckCityThread implements Runnable {

	private  ExpressCond expressCond ;

	public CheckCityThread(ExpressCond expressCond) {
		this.expressCond = expressCond;
	}

	@Override
	public void run() {
		try {
			this.expressCond.listenCity();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}
}
