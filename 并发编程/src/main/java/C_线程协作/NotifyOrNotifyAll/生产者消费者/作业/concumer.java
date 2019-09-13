package C_线程协作.NotifyOrNotifyAll.生产者消费者.作业;

import java.util.List;

/**
 * 消费者
 */
public class concumer implements Runnable {

	List<Integer> list;

	public concumer(List<Integer> list) {

		this.list = list;
	}

	@Override
	public void run() {

		synchronized (list) {
			try {
				if (list.size() == 0) {
					list.wait();
				}
				System.out.println(Thread.currentThread().getName() + "开始工作");
				while (list.size() != 0) {
					Integer remove = list.remove(0);
					System.out.println("发射子弹" + remove);
				}


			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}
}
