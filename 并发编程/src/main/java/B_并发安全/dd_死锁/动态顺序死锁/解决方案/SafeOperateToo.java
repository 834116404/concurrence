package B_并发安全.dd_死锁.动态顺序死锁.解决方案;


import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.pojo.UserAccount;
import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.ITransfer;

import java.util.Random;

/**
 * 解决动态顺序死锁方式2
 * <p>
 * 使用显示锁
 * <p>
 * 尝试拿锁机制 (显示锁就有尝试拿锁机制)
 * 解决死锁问题
 */
public class SafeOperateToo implements ITransfer {

	@Override
	public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
		Random r = new Random();
		/*拿不到from锁就开始第二次while循环,
		 * 如果拿到from锁之后又尝试拿to这把锁,如果to这把锁拿不到锁它会在finally释放自己的锁,
		 * 同时也会释放掉from的那把锁,
		 * 这样就不会出现死锁了.
		 * */
		while (true) {
			if (from.getLock().tryLock()) {//尝试获取锁
				System.out.println(Thread.currentThread().getName()
						+ " get" + from.getName());
				try {
					if (to.getLock().tryLock()) {//再尝试获取第二把锁,拿到了再去做实际的业务工作
						try {
							System.out.println(Thread.currentThread().getName()
									+ " get" + to.getName());
							from.flyMoney(amount);
							to.addMoney(amount);
							System.out.println(from);
							System.out.println(to);
							break;  //不能缺少这个
						} finally {
							to.getLock().unlock(); //释放锁
						}
					}
				} finally {
					from.getLock().unlock(); //释放锁
				}

			}
			/*如果不休眠的话就会产生活锁*/
			Thread.sleep(r.nextInt(2));
		}

	}
}
