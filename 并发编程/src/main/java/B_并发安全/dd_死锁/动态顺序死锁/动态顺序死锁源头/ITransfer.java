package B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头;


import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.pojo.UserAccount;

/**
 * 类说明：银行转账动作接口
 */
public interface ITransfer {
	void transfer(UserAccount from, UserAccount to, int amount)
			throws InterruptedException;
}
