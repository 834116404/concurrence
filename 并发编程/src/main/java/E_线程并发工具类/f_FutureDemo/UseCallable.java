package E_线程并发工具类.f_FutureDemo;

import java.util.concurrent.Callable;

/*实现Callable接口，允许有返回值 ,泛型就是返回值类型
 * */
public class UseCallable implements Callable<Integer> {
	private int sum;

	@Override
	public Integer call() throws Exception {
		System.out.println("Callable子线程开始计算！");
//			Thread.sleep(1000);
		for (int i = 0; i < 5000; i++) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Callable子线程计算任务中断！");
				return null;
			}
			sum = sum + i;
			System.out.println("sum=" + sum);
		}
		System.out.println("Callable子线程计算结束！结果为: " + sum);
		return sum;
	}
}