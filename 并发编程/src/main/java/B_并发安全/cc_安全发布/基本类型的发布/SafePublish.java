package B_并发安全.cc_安全发布.基本类型的发布;

/**
 * 演示基本类型的发布
 * <p>
 * 基本类型通过get方法传递出去以后
 * 传递出去的是一个副本,外界对这个i修改的 , 修改的是一个副本
 * 它是线程安全的
 */
public class SafePublish {
	private int i;

	public SafePublish() {
		i = 2;
	}

	public int getI() {
		return i;
	}

	public static void main(String[] args) {
		SafePublish safePublish = new SafePublish();
		int j = safePublish.getI();
		System.out.println("before j=" + j);  //2
		j = 3;
		System.out.println("after j=" + j); // 3
		System.out.println("getI = " + safePublish.getI()); //2
	}
}
