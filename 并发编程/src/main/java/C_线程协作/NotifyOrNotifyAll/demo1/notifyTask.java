package C_线程协作.NotifyOrNotifyAll.demo1;

/**
 * 唤醒操作
 * 在 run方法里面可以切换 notify() 和 notifyAll() 来尝试唤醒哪个线程
 */
public class notifyTask implements Runnable {
	private waitTask test;
	private volatile boolean go = false;

	public notifyTask(waitTask test) {
		this.test = test;
	}

	@Override
	public void run() {
		synchronized (test) {
			while (go == false) {
				System.out.println(Thread.currentThread()
						+ "将通知所有或一个线程等待这个对象吗");

				go = true; //使等待线程的条件为真
				test.notify(); // 等待线程WT1、WT2、WT3中只有一个会被唤醒
//				test.notifyAll(); // 所有等待线程WT1、WT2、WT3将被唤醒
			}

		}
		System.out.println(Thread.currentThread() + " 完成执行");

	}
}
