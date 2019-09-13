package A_线程基础.a_多线程创建方式.实现方式;

public class Main {
	/**
	 * 实现方式创建线程
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		Thread thread = new Thread(myTask);
		thread.setName("Runnable");// 设置名字
		String name = thread.getName(); //获取名字
		boolean interrupted = thread.isInterrupted();//如果这个线程被中断就返回true
//		thread.checkAccess();
		ClassLoader contextClassLoader = thread.getContextClassLoader();//返回此线程的上下文类加载器
		long id = thread.getId();//返回此线程的标识符
		int priority = thread.getPriority();//返回线程的优先级

		Thread.State state = thread.getState(); //返回线程的状态 , 具体的点State枚举看源码
		ThreadGroup threadGroup = thread.getThreadGroup();//返回线程组

		boolean alive = thread.isAlive(); //测试线程是否活动
		boolean daemon = thread.isDaemon(); //测试线程是否是守护线程

		thread.start();

	}
}
