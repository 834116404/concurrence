package A_线程基础.a_多线程创建方式.实现方式;

public class MyTask implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			//获取线程名字
			System.out.println(Thread.currentThread().getName());
		}
	}
}
