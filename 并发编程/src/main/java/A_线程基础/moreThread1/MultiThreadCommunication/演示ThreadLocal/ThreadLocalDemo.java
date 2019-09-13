package A_线程基础.moreThread1.MultiThreadCommunication.演示ThreadLocal;

/**
 * ThreadLocal是线程的局部变量,目的是在多线程并发变量的解决方案
 * 但是并不具有绝对的优势,在并发不是很高的时候,加锁的性能会更好,
 * 但是作为一种与锁完全无关(无锁)的线程解决方案
 * 在高并发量或者竞争激烈的场景,使用ThreadLocal可以一定程度上减少锁竞争.
 */
public class ThreadLocalDemo {
	/**
	 * 1.实例出来
	 */
	public static ThreadLocal<String> th = new ThreadLocal<String>();

	//2设置值
	public void setTh(String value) {
		th.set(value);
	}

	//3 获取值
	public void getTh() {
		System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		final ThreadLocalDemo ct = new ThreadLocalDemo();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ct.setTh("张三");
				ct.getTh();
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					//ct.setTh("李四");
					ct.getTh();  //获取不到t1 set的值, 因为ThreadLocal是在当前线程存取值
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");

		t1.start();
		t2.start();
	}

}
