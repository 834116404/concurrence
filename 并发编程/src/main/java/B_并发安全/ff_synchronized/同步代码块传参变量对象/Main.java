package B_并发安全.ff_synchronized.同步代码块传参变量对象;

/**
 * 同步代码块儿传参变量对象(锁住的是变量对象)
 * zjj_parent_9ad7be5e-1e3a-67fc-3e16-b7fa80cd5980
 */
public class Main {

	public static void main(String[] args) {
//		demo1();
//		demo2();
		demo3();
	}

	/*两个线程用的一个实例,变量也是一样的,因此第二个线程必须得等第一个线程释放锁才能进入临界区*/
	private static void demo3() {
		SynchronizedTest st = new SynchronizedTest(127);
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test3();
		}).start();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test3();
		}).start();

	}

	/*两个int 值不一样,同步代码块儿参数不是一个参数,因此两个线程可以实现了并行*/
	private static void demo2() {
		SynchronizedTest st = new SynchronizedTest(127);
		SynchronizedTest st2 = new SynchronizedTest(111);
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test3();
		}).start();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st2.test3();
		}).start();
	}

	/*
	同一个实例对象的成员属性肯定是同一个，此处列举的是不同实例的情况，但是 依旧实现了同步，原因如下：
Integer存在静态缓存，范围是-128 ~ 127，
当使用Integer A = 127 或者 Integer A = Integer.valueOf(127) 这样的形式，都是从此缓存拿。
如果使用 Integer A = new Integer(127)，每次都是一个新的对象。
此例中，两个对象实例的成员变量 lockObject 其实是同一个对象，因此实现了同步。还有字符串常量池也要注意。
所以此处关注是，同步代码块传参的对象是否是同一个。这跟第二个方式其实是同一种。

	* */
	private static void demo1() {

		SynchronizedTest st = new SynchronizedTest(127);
		SynchronizedTest st2 = new SynchronizedTest(127);
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st.test3();
		}).start();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 线程准备进入");
			st2.test3();
		}).start();

	}


}
