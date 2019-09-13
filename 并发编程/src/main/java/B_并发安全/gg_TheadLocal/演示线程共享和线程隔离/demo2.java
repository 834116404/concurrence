package B_并发安全.gg_TheadLocal.演示线程共享和线程隔离;

/**
 * 在demo1上做一个改良,使用ThreadLocal
 * <p>
 * 通过 ThreadLocal 封装了一个 Integer 类型的 num 静态成员变量，并且初始值是0
 * 每个线程相互独立了，同样是 static 变量，对于不同的线程而言，它没有被共享，
 * 而是每个线程各一份，这样也就保证了线程安全。 也就是说，TheadLocal 为每一个线程提供了一个独立的副本！
 * 搞清楚 ThreadLocal 的原理之后，有必要总结一下 ThreadLocal 的 API，其实很简单。
 */
public class demo2 {

	//	private static int num = 0;
	static ThreadLocal<Integer> num = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0; //初始化0
		}

	};

	/**
	 * 通过ThreadLocal去拿到值 然后修改完了再设置进去
	 * 结果:
	 * 每个线程获得都是副本
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Thread[] threads = new Thread[5]; //定义五个线程
		for (int i = 0; i < threads.length; i++) {

			threads[i] = new Thread(() -> {
				Integer localNum = num.get();//获取ThreadLocal中初始化的值
				localNum = localNum + 5;
				num.set(localNum); //设置修改以后的值
//				num += 5;
				System.out.println(Thread.currentThread().getName() + ":" + num.get());
			}, "thread-" + i);

		}

		for (Thread thread : threads) {
			thread.start();

		}

	}
}
