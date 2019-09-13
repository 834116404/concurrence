package A_线程基础.moreThread1.MultiThreadCommunication.并发容器使用;

import java.util.Vector;

/**
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 *
 * @author alienware
 */
public class UseVector_Tickets {

	public static void main(String[] args) {
		//初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap

		final Vector<String> tickets = new Vector<String>();
		/**
		 * Collections.synchronizedMap()方法:
		 * 	你给我传递实现Collection接口的对象,或者list map set等等,
		 * 	我会给你传进来的容器传一把锁,实现线程安全.
		 */

		//Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

		//
		for (int i = 1; i <= 1000; i++) {
			tickets.add("火车票" + i);
		}

//		for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			tickets.remove(20);
//		}
		/**
		 * 不用考虑并发问题
		 * 10个线程对容器进行  减减    操作
		 *
		 */
		for (int i = 1; i <= 10; i++) {
			new Thread("线程" + i) {
				public void run() {
					while (true) {
						if (tickets.isEmpty()) break;
						System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
					}
				}
			}.start();
		}
	}
}
