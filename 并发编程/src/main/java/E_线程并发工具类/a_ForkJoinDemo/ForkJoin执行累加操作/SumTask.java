package E_线程并发工具类.a_ForkJoinDemo.ForkJoin执行累加操作;

import utils.SleepTools;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

	/*阈值(拆分到多小的时候不拆分了,直接进行计算) 数组长度除以10*/
	private final static int THRESHOLD = MakeArrayUtil.ARRAY_LENGTH / 10;
	private int[] src; //原始数组
	private int fromIndex;
	private int toIndex;

	/**
	 * 构造
	 *
	 * @param src       数组
	 * @param fromIndex 数组的起始索引
	 * @param toIndex   数组的最大索引
	 */
	public SumTask(int[] src, int fromIndex, int toIndex) {
		this.src = src;
		this.fromIndex = fromIndex;
		this.toIndex = toIndex;
	}

	/**
	 * 执行计算的方法
	 *
	 * @return
	 */
	@Override
	protected Integer compute() {
			/*任务的大小是否合适
				结束索引-起始索引小于阈值了就直接汇总统计
			* */
		if (toIndex - fromIndex < THRESHOLD) {
			int count = 0;
			for (int i = fromIndex; i <= toIndex; i++) {
				SleepTools.ms(1);
				count = count + src[i];
			}
			return count;  // 结果返回出去
		} else {//	大于阈值 就对半拆分


			//fromIndex....mid.....toIndex
			int mid = (fromIndex + toIndex) / 2; //求中间值

			//拆分成两个
			SumTask left = new SumTask(src, fromIndex, mid);//左半边
			SumTask right = new SumTask(src, mid + 1, toIndex);//右半边(注意一定要中间值索引加1)
			invokeAll(left, right);//把左右子任务拿到池子里面去执行
			return left.join() + right.join();  //拿到两个子任务的值(左边加右边)
		}
	}
}
