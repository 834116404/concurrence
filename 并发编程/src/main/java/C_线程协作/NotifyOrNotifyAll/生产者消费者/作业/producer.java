package C_线程协作.NotifyOrNotifyAll.生产者消费者.作业;

import java.util.List;

/**
 * 生产者
 */
public class producer implements Runnable {
	List<Integer> list;

	public producer(List<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		synchronized (list) {
			for (int i = 1; i < 21; i++) {
				list.add(i);
				System.out.println(Thread.currentThread().getName() + "我已经装满子弹了,装了20发");
				list.notify();

			}

		}

	}


}
