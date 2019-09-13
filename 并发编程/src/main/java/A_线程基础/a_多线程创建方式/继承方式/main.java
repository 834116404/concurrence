package A_线程基础.a_多线程创建方式.继承方式;

public class main {
	/**
	 * 继承方式实现线程
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.setName("我是mian线程");
		myThread.run();
	}
}
