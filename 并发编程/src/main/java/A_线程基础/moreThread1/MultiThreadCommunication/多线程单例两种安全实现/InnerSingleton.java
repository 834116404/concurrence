package A_线程基础.moreThread1.MultiThreadCommunication.多线程单例两种安全实现;

/**
 * 单例
 * 静态内部类的方式
 * 在多线程中 最好用的一种解决方案
 * ,也是最安全的解决方案
 */
public class InnerSingleton {
	/**
	 *
	 */
	private static class Singletion {
		private static Singletion single = new Singletion();
	}

	/**
	 * 返回实例对象
	 *
	 * @return 获取实例对象
	 */
	public static Singletion getInstance() {
		return Singletion.single;
	}

}
