package C_线程协作.NotifyOrNotifyAll.demo1;

public class waitTask implements Runnable {
	private volatile boolean go = false;

	private waitTask test;

	public waitTask(waitTask test) {
		this.test = test;
	}

	public waitTask() {

	}

	@Override
	public void run() {
		try {
			synchronized (test){
				while (go != true) {
					System.out.println(Thread.currentThread()
							+ " 会等待这个对象吗");
					test.wait(); //释放锁定并在唤醒时重新获取
					System.out.println(Thread.currentThread() + " 已经醒来");
				}
				go = false; //resetting condition
			}


		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread() + " 完成执行");
	}

}
