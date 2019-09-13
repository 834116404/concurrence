package A_线程基础.moreThread1.TicketDemo.solutionThree;

import java.util.concurrent.locks.ReentrantLock;

//卖票任务
public class TicketTask implements Runnable {

	private static int count = 100;//默认开始100张票
	private ReentrantLock lock = new ReentrantLock();//Lock对象

	@Override
	public void run() {

		while (true) {
			//上锁,加锁
			lock.lock();
			if (count > 0) {
				//让程序休息会
				System.out.println(Thread.currentThread().getName() + "卖出第" + count + "张票");
				count--;
			}
			//解锁,释放锁
			lock.unlock();
		}
	}
}
