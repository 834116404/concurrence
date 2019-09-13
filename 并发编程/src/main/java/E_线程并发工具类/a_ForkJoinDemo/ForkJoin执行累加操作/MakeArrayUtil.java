package E_线程并发工具类.a_ForkJoinDemo.ForkJoin执行累加操作;

import java.util.Random;

/**
 * 准备数组工具类
 */
public class MakeArrayUtil {
	//数组长度 如果设置过大,会使得计算结果变为负数
 	public static final int ARRAY_LENGTH = 10000;
	public final static int THRESHOLD = 47;

	public static int[] makeArray() {

		//new一个随机数发生器
		Random r = new Random();
		int[] result = new int[ARRAY_LENGTH];
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			//用随机数填充数组
			result[i] = r.nextInt(ARRAY_LENGTH * 3);
		}
		return result;

	}
}
