package B_并发安全.ff_synchronized.同步代码块儿传参this;

/**
 * 同一个实例调用会阻塞
 * 不同实例调用不会阻塞
 * 案例代码:zjj_parent_da955de6-8ea1-a25f-487b-c2f29029f95d
 */
public class Main {

	public static void main(String[] args) {
		demo2();
//demo1();


	}

	/*不同实例调用不会阻塞 ,两个线程可以并行执行*/
	private static void demo2() {
		SynchronizedTest aaaa = new SynchronizedTest();
		SynchronizedTest bbbb = new SynchronizedTest();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			aaaa.test2();
		}).start();
		new Thread(() -> {

			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			bbbb.test2();
		}).start();

	}

	/*同一实例调用会阻塞*/
	private static void demo1() {
		SynchronizedTest st = new SynchronizedTest();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test2();
		}).start();
		new Thread(() -> {

			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test2();
		}).start();
	}
}
