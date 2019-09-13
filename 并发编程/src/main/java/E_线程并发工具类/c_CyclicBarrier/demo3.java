package E_线程并发工具类.c_CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 演示阻塞的线程是否被中断
 */
public class demo3 {
	static CyclicBarrier c = new CyclicBarrier(2);

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {

				}
			}
		});
		thread.start();

		thread.interrupt(); //中断线程
		try {
			c.await();
		} catch (Exception e) {
			System.out.println(c.isBroken()); //阻塞的线程是否被中断

		}
	}


}
