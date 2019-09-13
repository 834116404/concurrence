package E_线程并发工具类.c_CyclicBarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 演示主线程等待四个子线程统计完了,主线程再获取四个子线程的结果.
 * <p>
 * zjj_parent_4ce9fd69-e237-0781-b348-39acb324f6da
 */
public class demo2 implements Runnable {

	/*创建四个屏障,处理完之后执行当前类的run方法*/
	private CyclicBarrier c = new CyclicBarrier(4, this);
	/*假设只有4个sheet，所以只启动4个线程*/
	private Executor executor = Executors.newFixedThreadPool(4);
	/*保存每个sheet计算出的银流结果*/
	private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new
			ConcurrentHashMap<String, Integer>();


	protected void count() {
		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
					try {
						int await = c.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});

		}

	}


	@Override
	public void run() {
		int result = 0;
		// 汇总每个sheet计算出的结果
		for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
			result += sheet.getValue();
		}
		// 将结果输出
		sheetBankWaterCount.put("result", result);
		System.out.println(result);
	}

	public static void main(String[] args) {
		demo2 bankWaterService = new demo2();
		bankWaterService.count(); //结果是 4  因为四个线程统计,每个线程假设为1 所以结果就是4
	}
}
