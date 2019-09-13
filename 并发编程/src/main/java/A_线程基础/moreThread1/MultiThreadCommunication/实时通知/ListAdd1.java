package A_线程基础.moreThread1.MultiThreadCommunication.实时通知;

import java.util.ArrayList;
import java.util.List;

/**
 * t1添加元素   t2监视list集合的元素如果长度为5的时候就希望 t2 线程 给这个线程停止
 * 但是 线程停止失败了
 * <p>
 * 这种实现的方式不好,因为 t2 也在轮询状态中
 * 解决办法看本包下的 ListAdd2类
 */
public class ListAdd1 {

	private volatile static List list = new ArrayList();

	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final ListAdd1 list1 = new ListAdd1();

		Thread t1 = new Thread(new Runnable() {
			/**
			 *循环10次  调用10次添加代码
			 */
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						list1.add();
						System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			/**
			 * 如果list1长度为的时候停止线程 , 抛出异常
			 */
			@Override
			public void run() {
				while (true) {
					if (list1.size() == 5) {
						System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
						throw new RuntimeException();
					}
				}
			}
		}, "t2");

		t1.start();
		t2.start();
	}


}
