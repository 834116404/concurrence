package B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.pojo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *类说明：用户账户的实体类
 */
public class UserAccount {
    ///private int id;
    private final String name;//账户名称
    private int money;//账户余额

    private final Lock lock = new ReentrantLock();  //每个账户里面自带一把锁,为了配合尝试拿锁机制

    public Lock getLock() {
        return lock;
    }

    public UserAccount(String name, int amount) {
        this.name = name;
        this.money = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

	/**转入资金
	 *
	 * @param amount
	 */
	public void addMoney(int amount){
        money = money + amount;
    }

	/**转出资金
	 *
	 * @param amount
	 */
	public void flyMoney(int amount){
        money = money - amount;
    }
}
