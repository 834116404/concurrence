package B_并发安全.gg_TheadLocal.演示线程共享和线程隔离;

/**
 * 没用ThreadLocal  五个线程共享了 num变量
 */
public class demo1 {

	private static int num = 0;

	/**
	 * 结果下个线程的值是上一个线程改变的值
	 * 因为 num 是线程共享的
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Thread[] threads = new Thread[5]; //定义五个线程
		for (int i = 0; i < threads.length; i++) {

			threads[i] = new Thread(() -> {
				num += 5;
				System.out.println(Thread.currentThread().getName() + ":" + num);
			}, "thread-" + i);

		}

		for (Thread thread : threads) {
			thread.start();

		}

	}
}
