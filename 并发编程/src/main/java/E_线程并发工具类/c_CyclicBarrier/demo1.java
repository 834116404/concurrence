package E_线程并发工具类.c_CyclicBarrier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 类说明：演示CyclicBarrier用法,共4个子线程，他们全部完成工作后，交出自己结果，
 * 再被统一释放去做自己的事情，而交出的结果被另外的线程拿来拼接字符串
 * <p>
 *zjj_parent_4ce9fd69-e237-0781-b348-39acb324f6da
 */
public class demo1 {
	/**
	 * 第一个参数必须和要互相协调的线程数一样,必须一样,如果多的话,会使劲等,无法继续执行
	 */
	private static CyclicBarrier barrier
			= new CyclicBarrier(4, new CollectThread());

	//存放子线程工作结果的容器
	private static ConcurrentHashMap<String, Long> resultMap
			= new ConcurrentHashMap<>();

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			Thread thread = new Thread(new SubThread());
			thread.start();
		}

	}

	/*汇总的任务*/
	private static class CollectThread implements Runnable {

		@Override
		public void run() {
			StringBuilder result = new StringBuilder();
			/*把多个线程的结果汇总一下*/
			for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
				result.append("[" + workResult.getValue() + "]");
			}
			System.out.println(" 结果是 = " + result);
			System.out.println("做其它的 business........");
		}
	}

	/*相互等待的子线程*/
	private static class SubThread implements Runnable {

		@Override
		public void run() {
			long id = Thread.currentThread().getId();
			resultMap.put(Thread.currentThread().getId() + "", id);
			try {
				Thread.sleep(1000 + id);
				System.out.println("Thread_" + id + " ....do something ");//四个线程会一起做这个
				/**
				 * await()方法可以反复调用, 调用完了CyclicBarrier可以反复重复的去触发
				 */
				barrier.await();
				Thread.sleep(1000 + id);
				System.out.println("Thread_" + id + " ....do its business ");
				/**
				 * await()方法可以反复调用, 调用完了CyclicBarrier可以反复重复的去触发
				 * 
				 */
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
