package E_线程并发工具类.f_FutureDemo.demo2任务终止;

import java.util.concurrent.FutureTask;

public class UseFuture {
	public static void main(String[] args) throws InterruptedException {

		UseCallable useCallable = new UseCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
		new Thread(futureTask).start();
		Thread.sleep(1);
		boolean cancel = futureTask.cancel(true);// 终止线程


	}
}
