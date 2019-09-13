package C_线程协作.NotifyOrNotifyAll.demo1;

/**
 * 4b14d21d-36f7-5c9c-8b99-77d91e01c4c1
 * 演示 Notify 和NotifyAll 能唤醒几个线程的问题
 */
public class demo1 {
	public static void main(String[] args) throws InterruptedException {
		waitTask waitTask = new waitTask();


		new Thread(new waitTask(waitTask), "WT1").start();
		new Thread(new waitTask(waitTask), "WT2").start();
		new Thread(new waitTask(waitTask), "WT3").start();
		Thread.sleep(200);
		new Thread(new notifyTask(waitTask), "NT1").start();


	}


}
