package A_线程基础.c_停止线程.cc_异常中中断;

import utils.SleepTools;

public class UseRunnable implements Runnable {
	@Override
	public void run() {
//		boolean interrupted = Thread.currentThread().isInterrupted(); 主要这么写,中断线程无效
//		while (!interrupted) {// 不要这么写,中断线程无效

		while (!Thread.currentThread().isInterrupted()) {
			try {

				System.out.println(Thread.currentThread().getName() + "我是实现Runnable的线程,此时的interrupt标记位是 " + Thread.currentThread().isInterrupted());
				int a = 1 / 0;
			} catch (Exception e) {
				/*需要在catch里面手动中断一下,如果想知道不这样写的后果,
				可以注释这个代码试试*/
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}


		}

		System.out.println(Thread.currentThread().getName() + "interrupt 标记 是" +
				Thread.currentThread().isInterrupted());
	}


	public static void main(String[] args) {
		UseRunnable useRunnable = new UseRunnable();
		Thread thread = new Thread(useRunnable, "Runnable线程");
		thread.start();

		SleepTools.second(1);//睡眠一秒

//		Thread.currentThread().interrupt(); 这样中断线程是无效的,不要这么写

		thread.interrupt(); //设置中断线程

	}
}
