package A_线程基础.c_停止线程.bb_中断Runnable线程;

import utils.SleepTools;

public class UseRunnable implements Runnable {
	@Override
	public void run() {
//		boolean interrupted = Thread.currentThread().isInterrupted(); 主要这么写,中断线程无效
//		while (!interrupted) {// 不要这么写,中断线程无效

		while (!Thread.currentThread().isInterrupted()) {
			System.out.println(Thread.currentThread().getName() + "我是实现Runnable的线程,此时的interrupt标记位是 " + Thread.currentThread().isInterrupted());
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
