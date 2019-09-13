package A_线程基础.moreThread1.TicketDemo.solutionTow;

//卖票任务
public class TicketTask implements Runnable {

	private static int count = 100;//默认开始100张票
	private Object obj = new Object();

	@Override
	public void run() {

		while (true) {
			//卖票
			sell();
		}
	}

	//同步方法,基于锁对象,默认使用当前对象this作为锁对象
	public static synchronized void sell() {
		if (count > 0) {
			//让程序休息会
			System.out.println(Thread.currentThread().getName() + "卖出第" + count + "张票");
			count--;
		}
	}
}
