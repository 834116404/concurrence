package B_并发安全.ff_synchronized.修饰普通方法.Thread;

public class Instance2Syn implements Runnable {
	private DiffInstance diffInstance;

	/**
	 * 构造注入
	 *
	 * @param diffInstance
	 */
	public Instance2Syn(DiffInstance diffInstance) {
		this.diffInstance = diffInstance;
	}

	/**
	 * 执行synchronized修饰的方法
	 */
	@Override
	public void run() {
		System.out.println("Instance2Syn.run");
		diffInstance.instance2();
	}
}
