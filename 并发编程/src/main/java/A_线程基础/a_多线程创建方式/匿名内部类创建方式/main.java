package A_线程基础.a_多线程创建方式.匿名内部类创建方式;

/**
 * 匿名内部类方式创建
 */
public class main {

	public static void main(String[] args) {

		//1.第一种方式 继承方式
		new Thread() {
			@Override
			public void run() {
				//任务代码
				for (int i = 0; i < 20; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
			}
		}.start();

//2.第二种方式 实现方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
			}
		}).start();

		//主线程代码
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}
}
