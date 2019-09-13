package B_并发安全.dd_死锁.动态顺序死锁.解决方案;


import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.pojo.UserAccount;
import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.ITransfer;

/**
 * 解决动态顺序死锁方式1
 * 类说明：不会产生死锁的安全转账
 * <p>
 * 思路 内部通过顺序比较确定拿锁的顺序
 */
public class SafeOperate implements ITransfer {

	private static Object tieLock = new Object();//第三把锁

	@Override
	public void transfer(UserAccount from, UserAccount to, int amount)
			throws InterruptedException {
		/*拿到hash值*/
		int fromHash = System.identityHashCode(from);
		int toHash = System.identityHashCode(to);

		if (fromHash < toHash) { //拿两个hash值做比较，哪个hash小哪个就在前面
			synchronized (from) {
				System.out.println(Thread.currentThread().getName() + " get " + from.getName());
				Thread.sleep(100);
				synchronized (to) {
					System.out.println(Thread.currentThread().getName() + " get " + to.getName());
					from.flyMoney(amount);
					to.addMoney(amount);
					System.out.println(from);
					System.out.println(to);
				}
			}
		} else if (toHash < fromHash) {
			synchronized (to) {
				System.out.println(Thread.currentThread().getName() + " get" + to.getName());
				Thread.sleep(100);
				synchronized (from) {
					System.out.println(Thread.currentThread().getName() + " get" + from.getName());
					from.flyMoney(amount);
					to.addMoney(amount);
					System.out.println(from);
					System.out.println(to);
				}
			}
		} else { // 如果hash值相同呢？(可能只有千万分之一的几率会hash相同)
			synchronized (tieLock) {  //先抢夺第三把锁
				synchronized (from) { //然后分别抢两个锁
					synchronized (to) {//都抢完了再执行业务动作
						from.flyMoney(amount);
						to.addMoney(amount);
					}
				}
			}
		}
	}
}
