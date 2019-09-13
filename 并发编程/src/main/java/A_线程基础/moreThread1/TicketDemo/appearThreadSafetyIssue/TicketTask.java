package A_线程基础.moreThread1.TicketDemo.appearThreadSafetyIssue;

//卖票任务
public class TicketTask implements Runnable {

	private int count = 100;//默认开始100张票

	@Override
	public void run() {

		while (true) {
			//卖票
			if (count > 0) {
				//让程序休息会
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				System.out.println(Thread.currentThread().getName() + "卖出第" + count + "张票");
				count--;
			}
		}
	}

}
