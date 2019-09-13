package A_线程基础.c_停止线程.aa_中断Thread线程;

import utils.SleepTools;

public class UseThread extends Thread {
	public UseThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		while (!isInterrupted()) {//检查当前线程是否被中断(平时开发用这个就行了)
			System.out.print(threadName + " 正在运行    ,");
			System.out.println(threadName + "内部interrrupt标记 = "
					+ isInterrupted());

		}

		//建议在下面的输出语句打断点,更好的查看效果
		System.out.print("线程将要停止了,   ");
		System.out.println(threadName + " interrrupt标记 =" + isInterrupted());//变成true就是中断了
	}

	public static void main(String[] args) {
		UseThread thread = new UseThread("线程1");
		thread.start();
		SleepTools.second(1);//睡眠一秒
		thread.interrupt();//中断线程，其实设置线程的标识位true

	}
}
