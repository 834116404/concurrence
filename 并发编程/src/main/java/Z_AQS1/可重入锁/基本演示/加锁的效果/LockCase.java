package Z_AQS1.可重入锁.基本演示.加锁的效果;

public class LockCase {

	/**
	 * 两个线程,一个线程是增加100000
	 * 另一个线程是减少100000
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		int age = 100000;//初始100000

		WorkerThread workerThread = new WorkerThread(age);
		workerThread.start();

		for (int i = 0; i < 100000; i++) {
			workerThread.decrease();//递减操作
		}
		// 执行了两个线程,一个是主线程,一个是workerThread线程
		//加锁的效果  i 结果永远是 100000


	}

}
