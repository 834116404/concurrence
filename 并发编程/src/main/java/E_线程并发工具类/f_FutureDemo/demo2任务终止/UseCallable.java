package E_线程并发工具类.f_FutureDemo.demo2任务终止;

import java.util.concurrent.Callable;

public class UseCallable implements Callable<Integer> {
	private int sum;

	@Override
	public Integer call() throws Exception {
		System.out.println("Callable子线程开始计算了");

		for (int i = 0; i < 5000; i++) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("主线程终止了我");
				return  null ;
			}
			sum = sum + i;
		}
		System.out.println("Callable子线程计算结束! 结果为 : " + sum);
		return sum;
	}
}
