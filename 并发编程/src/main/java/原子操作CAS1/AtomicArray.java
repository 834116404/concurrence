package 原子操作CAS1;

import java.util.concurrent.atomic.AtomicIntegerArray;


/**
 * 类说明：
 */
public class AtomicArray {
	static int[] value = new int[]{1, 2};
	static AtomicIntegerArray ai = new AtomicIntegerArray(value); //包装一下value属性

	public static void main(String[] args) {
		ai.getAndSet(0, 3);
		System.out.println(ai.get(0));  //0号索引变成3 了
		System.out.println(value[0]);//原数组不会变化
	}
}
