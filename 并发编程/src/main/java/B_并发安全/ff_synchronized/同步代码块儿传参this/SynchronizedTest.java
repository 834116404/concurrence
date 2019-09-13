package B_并发安全.ff_synchronized.同步代码块儿传参this;

public class SynchronizedTest {
	//锁住了本类的实例对象
	public void test2() {
		synchronized (this) {
			try {
				System.out.println(Thread.currentThread().getName() + " 线程进入了同步块");

				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + " 线程休眠结束");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}