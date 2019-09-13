package B_并发安全.ff_synchronized.同步代码块传参class对象;

public class SynchronizedTest {

	//全局锁，类是全局唯一的
	public void test4() {
		synchronized (SynchronizedTest.class) {
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
