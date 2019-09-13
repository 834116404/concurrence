package B_并发安全.ff_synchronized.同步代码块传参变量对象;

public class SynchronizedTest {

	public Integer lockObject;

	public SynchronizedTest(Integer lockObject) {
		this.lockObject = lockObject;
	}

	//锁住了实例中的成员变量
	public void test3() {
		synchronized (lockObject) {
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