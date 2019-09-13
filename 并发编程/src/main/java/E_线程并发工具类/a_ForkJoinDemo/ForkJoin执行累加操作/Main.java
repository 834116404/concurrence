package E_线程并发工具类.a_ForkJoinDemo.ForkJoin执行累加操作;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Main {
	/**
	 * 统计数组的所有的元素的和案例
	 * 用 ForkJoin
	 *
	 * @param args
	 */
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		int[] src = MakeArrayUtil.makeArray();
		/*new出池的实例*/
		ForkJoinPool pool = new ForkJoinPool();
		/*new出Task的实例*/
		SumTask innerFind = new SumTask(src, 0, src.length - 1);


		long start = System.currentTimeMillis();
		//开始执行
		Integer invoke = pool.invoke(innerFind); //同步执行 获取结果,会在计算之后打印下面的话
//		ForkJoinTask<Integer> submit = pool.submit(innerFind); //异步执行,会在计算完成之前打印下面的话
		System.out.println("执行完成了");

		//System.out.println("Task is Running.....");
		Integer count = innerFind.join();

		System.out.println("总数是 " + count
				+ " 耗时" + (System.currentTimeMillis() - start) + "ms");

	}
}
