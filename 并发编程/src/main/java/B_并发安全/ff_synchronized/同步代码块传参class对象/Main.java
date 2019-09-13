package B_并发安全.ff_synchronized.同步代码块传参class对象;

/**
 * 全局锁
 * zjj_parent_510687a6-8132-588c-b5ac-45241a2f3575
 */
public class Main {
	public static void main(String[] args) {
		demo2();
//		demo1();

	}

	/*两个线程用了一个对象,B线程依旧需要等待A线程释放锁才能进入临界区*/
	private static void demo2() {
		SynchronizedTest st = new SynchronizedTest();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test4();
		}).start();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test4();
		}).start();
	}

	/*两个线程用了两个实现, 线程B必须等待线程A释放锁,才能进入临界区*/
	private static void demo1() {
		SynchronizedTest st = new SynchronizedTest();
		SynchronizedTest st2 = new SynchronizedTest();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test4();
		}).start();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st2.test4();
		}).start();
	}
}
