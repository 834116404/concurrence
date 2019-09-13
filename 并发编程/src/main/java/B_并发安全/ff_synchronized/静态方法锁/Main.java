package B_并发安全.ff_synchronized.静态方法锁;

/**
 * 全局锁
 * <p>
 * zjj_parent_ea5066b5-599d-5d0f-9eb5-580ea0724af5
 */
public class Main {

	//全局锁，静态方法全局唯一的
	public synchronized static void test5() {
		try {

			System.out.println(Thread.currentThread().getName() + " test  进入同步方法");
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " test  休眠结束--------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 串行执行
	 */
	public static void main(String[] args) {
		Main st = new Main();
		Main st2 = new Main();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " test 准备进入");
			st.test5();
		}, "线程1").start();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " test 准备进入");
			st2.test5();
		}, "线程2").start();
		/*线程3对象和线程1对象用的是一个对象*/
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " test 准备进入");
			st.test5();
		}, "线程3").start();
	}
}