package C_线程协作.NotifyOrNotifyAll.生产者消费者.作业;

import java.util.ArrayList;

/**
 * 自己的作业
 * b3d7dd7d-6dbe-5e55-9776-8187474e64d1
 */
public class demo {

	public static void main(String[] args) {

		ArrayList<Integer> integers = new ArrayList<>();
		Object o = new Object();
		Thread t1 = new Thread(new producer(integers));
		t1.setName("生产者");

		Thread t2 = new Thread(new concumer(integers));

		t2.setName("消费者");

		t1.start();
		t2.start();

	}
}
