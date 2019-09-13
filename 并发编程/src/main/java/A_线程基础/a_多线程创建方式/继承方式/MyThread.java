package A_线程基础.a_多线程创建方式.继承方式;

public class MyThread extends Thread {
	public MyThread() {
	}

	//private String name;
	MyThread(String name) {
		//this.name = name;
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			/**
			 * Thread.currentThread() 返回当前线程的引用
			 * this.getName()  返回当前线程的名字 外部方法怎么setName设置线程名字,内部就可以获取线程名字
			 */
			System.out.println(this.getName() + " : " + i);

		}
	}
}
