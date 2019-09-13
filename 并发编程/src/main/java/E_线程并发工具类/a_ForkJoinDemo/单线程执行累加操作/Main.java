package E_线程并发工具类.a_ForkJoinDemo.单线程执行累加操作;

import utils.SleepTools;
import E_线程并发工具类.a_ForkJoinDemo.ForkJoin执行累加操作.MakeArrayUtil;

public class Main {
	public static void main(String[] args) {
		int count = 0;
		int[] src = MakeArrayUtil.makeArray();

		long start = System.currentTimeMillis();
		for (int i = 0; i < src.length; i++) {
			SleepTools.ms(1);
			count = count + src[i];
		}
		System.out.println("总数是 " + count
				+ " 耗时" + (System.currentTimeMillis() - start) + "ms");
	}
}
