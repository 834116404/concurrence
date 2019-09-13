package B_并发安全.cc_安全发布.安全的发布集合.线程不安全;

import java.util.ArrayList;
import java.util.List;

/**
 * 不安全的发布
 * 线程不安全. 因为外面拿到list和里面的list是
 */
public class UnSafePublish {
	private List<Integer> list = new ArrayList<>(3);  //不安全. 因为

	public UnSafePublish() {
		list.add(1);
		list.add(2);
		list.add(3);
	}

	public List getList() {
		return list;
	}

	/**
	 * 线程不安全的
	 * @param args
	 */
	public static void main(String[] args) {
		UnSafePublish unSafePublish = new UnSafePublish();
		List<Integer> list = unSafePublish.getList();//把对象的引用复制了一份给外面的list

		System.out.println(list);
		list.add(4);
		//真正持有的还是堆中的那一个实例,所以你改其中的一个,另外一个也会有同样的影响
		System.out.println(list);   //改了之后对象内部list也跟着变化了
		System.out.println(unSafePublish.getList());//发布的list
	}



}
