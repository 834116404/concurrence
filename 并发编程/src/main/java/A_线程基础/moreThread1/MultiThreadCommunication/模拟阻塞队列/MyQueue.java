package A_线程基础.moreThread1.MultiThreadCommunication.模拟阻塞队列;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟Queue
 * 模拟阻塞队列
 */
public class MyQueue {

	/**
	 * 测试类
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * mq已经满了(初始化5个长度)
		 */
		final MyQueue m = new MyQueue(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		System.out.println("当前元素个数：" + m.size());

		/**
		 * 线程1
		 * 还想添加 2个元素
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				m.put("h");
				m.put("i");
			}
		}, "t1");

		/**
		 * 线程2
		 */
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					Object t1 = m.take();
					//System.out.println("被取走的元素为：" + t1);
					Thread.sleep(1000);
					Object t2 = m.take();
					//System.out.println("被取走的元素为：" + t2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		/**
		 * 开启两个线程
		 */
		t1.start();
		Thread.sleep(1000);
		t2.start();

	}


	/**
	 * 1.需要装元素的集合
	 */
	private final LinkedList<Object> list = new LinkedList<Object>();
	/**
	 * 2.需要计数器,统计装元素容器的集合里面内容的个数的.
	 */
	private final AtomicInteger count = new AtomicInteger(0);


	private final int minSize = 0;//3.最小长度0,因为linkedList容器长度不能是负数


	private final int maxSize;//4.最大长度(需要初始化)

	/**
	 * 5.在构造方法去初始化最大长度
	 *
	 * @param maxSize 参数: 最大长度
	 */
	public MyQueue(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 6.初始化对象用于加锁
	 */
	private final Object lock = new Object();


	/**
	 * 7.
	 * 使用wait()  和notify()
	 * 往里面添加元素,如果容器已经满了,就需要进入阻塞状态
	 *
	 * @param obj
	 */
	public void put(Object obj) {
		synchronized (lock) {
			/**
			 * 如果计数器达到容器初始化的最大长度的时候,就开始进入阻塞状态.
			 */
			while (count.get() == maxSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//如果不满的情况下就添加内容
			list.add(obj);
			//计数器递增
			count.getAndIncrement();
			System.out.println(" 元素 " + obj + " 被添加 ");
			//通知另外一个线程去(唤醒操作)
			lock.notify();

		}
	}

	/**
	 * 从容器里面取走元素
	 *
	 * @return
	 */
	public Object take() {
		Object temp = null;
		synchronized (lock) {
			//如果当前容器是空的
			while (count.get() == minSize) {
				try {
					//阻塞线程
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//如果容器不为空的话

			//count计数器做一个递减的操作
			count.getAndDecrement();
			//移除当前容器里面的第一个元素(返回值是具体的元素)
			temp = list.removeFirst();
			System.out.println(" 元素 " + temp + " 被消费 ");
			//唤醒另外一个线程
			lock.notify();
		}
		//返回获得的对象
		return temp;
	}

	/**
	 * 返回当前容器的个数
	 *
	 * @return
	 */
	public int size() {
		return count.get();
	}


}
