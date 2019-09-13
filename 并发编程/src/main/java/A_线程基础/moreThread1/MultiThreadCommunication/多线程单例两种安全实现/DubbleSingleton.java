package A_线程基础.moreThread1.MultiThreadCommunication.多线程单例两种安全实现;

/**
 * 双重确认
 * 懒加载模式
 */
public class DubbleSingleton {

	private static DubbleSingleton ds;

	/**
	 * 调用此方法时候才加载对象
	 * 判断两次获取当前实例对象才是相同的,
	 * 如果不第二次判断的话,
	 * 三个线程打印hashCode会是三个值(3个值说明是三个对象)
	 * 为什么需要两次判断,因为构造ds对象的时候可能很长(代码里面用睡眠三秒来模拟了一下),
	 * 第一个线程进来的时候到了第二次判断的时候(第二个判断那里加了个锁),
	 * 表示另外的对象不能创建锁里面的实例了
	 *
	 * @return
	 */
	public static DubbleSingleton getDs() {
		if (ds == null) {
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/**
			 *锁定当前的类, 如果是null才实例化出来
			 *
			 *
			 */

			synchronized (DubbleSingleton.class) {
				if (ds == null) {
					ds = new DubbleSingleton();
				}
			}
		}
		return ds;
	}

	/**
	 * 三个线程调用方法 打印hashCode, 如果是单例的话, 打印的hashCode应该是一样的.,
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
		}, "t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
		}, "t3");

		t1.start();
		t2.start();
		t3.start();
	}

}
