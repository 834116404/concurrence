package B_并发安全.ff_synchronized.修饰普通方法.Thread;

public class InstanceSyn implements Runnable {
	/**
	 * 声明成员变量
	 */
	private DiffInstance diffInstance;

	/**
	 * 构造注入
	 *
	 * @param diffInstance
	 */
	public InstanceSyn(DiffInstance diffInstance) {
		this.diffInstance = diffInstance;
	}

	/**
	 * 执行synchronized修饰的方法
	 */
	@Override
	public void run() {
		System.out.println("InstanceSyn.run");
		diffInstance.instance();
	}
}
