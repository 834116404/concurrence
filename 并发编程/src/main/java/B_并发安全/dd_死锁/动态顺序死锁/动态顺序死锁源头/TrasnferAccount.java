package B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头;


import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.pojo.UserAccount;

/**
 * 类说明：不安全的转账动作的实现
 */
public class TrasnferAccount implements ITransfer {
	@Override
	public void transfer(UserAccount from, UserAccount to, int amount)
			throws InterruptedException {
		synchronized (from) {
			System.out.println(Thread.currentThread().getName()
					+ " get" + from.getName());
			Thread.sleep(100);
			synchronized (to) {
				System.out.println(Thread.currentThread().getName()
						+ " get" + to.getName());
				from.flyMoney(amount);
				to.addMoney(amount);
			}
		}
	}
}
