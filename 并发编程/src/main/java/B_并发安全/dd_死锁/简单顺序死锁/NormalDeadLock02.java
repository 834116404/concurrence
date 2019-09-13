package B_并发安全.dd_死锁.简单顺序死锁;

/**
 * 类说明：演示普通账户的死锁和解决
 */
public class NormalDeadLock02 {
	private static Object valueFirst = new Object();//第一个锁
	private static Object valueSecond = new Object();//第二个锁

	/*获取锁顺序不对造成了死锁*/
	//先拿第一个锁，再拿第二个锁
	/*private static void fisrtToSecond() throws InterruptedException {
		String threadName = Thread.currentThread().getName();
		synchronized (valueSecond){
			System.out.println(threadName+" get 1st");
			Thread.sleep(100);
			synchronized (valueFirst){
				System.out.println(threadName+" get 2nd");
			}
		}
	}

	//先拿第二个锁，再拿第一个锁
	private static void SecondToFisrt() throws InterruptedException {
		String threadName = Thread.currentThread().getName();
		synchronized (valueFirst){
			System.out.println(threadName+" get 2nd");
			Thread.sleep(100);
			synchronized (valueSecond){
				System.out.println(threadName+" get 1st");
			}
		}
	}*/
	/*获取锁顺序不对造成了死锁*/

	/*解决办法,就是改变锁的顺序*/

	//先拿第一个锁，再拿第二个锁
	private static void fisrtToSecond() throws InterruptedException {
		String threadName = Thread.currentThread().getName();
		synchronized (valueFirst) {
			System.out.println(threadName + " get 1st");
			Thread.sleep(100);
			synchronized (valueSecond) {
				System.out.println(threadName + " get 2nd");
			}
		}
	}

	//先拿第二个锁，再拿第一个锁
	private static void SecondToFisrt() throws InterruptedException {
		String threadName = Thread.currentThread().getName();
		synchronized (valueFirst) {
			System.out.println(threadName + " get 2nd");
			Thread.sleep(100);
			synchronized (valueSecond) {
				System.out.println(threadName + " get 1st");
			}
		}
	}

	private static class TestThread extends Thread {

		private String name;

		public TestThread(String name) {
			this.name = name;
		}

		public void run() {
			Thread.currentThread().setName(name);
			try {
				SecondToFisrt();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread.currentThread().setName("TestDeadLock");
		TestThread testThread = new TestThread("SubTestThread");
		testThread.start();
		try {
			fisrtToSecond();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
