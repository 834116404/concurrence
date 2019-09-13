package B_并发安全.ee_性能优化.减小锁的粒度;

import java.util.HashSet;
import java.util.Set;

/**
 * 锁分离
 * 减小锁的粒度
 */
public class FinenessLock {


	public final Set<String> users = new HashSet<String>();
	public final Set<String> queries = new HashSet<String>();

	/*这个是一把锁,在操作Users和queries的时候会抢一把锁,
	 * 所以不能同时操作users和queries这两个实例,
	 * */
	public synchronized void addUser(String u) {
		users.add(u);
	}

	public synchronized void addQuery(String q) {
		queries.add(q);
	}

	public synchronized void removeUser(String u) {
		users.remove(u);
	}

	public synchronized void removeQuery(String q) {
		queries.remove(q);
	}

	/*下面是两把锁分别锁住两个条件，一把锁是users实例,另一把锁是queries实例,
	操作谁就锁谁,操作Users实例就锁Users实例,这样就不会发生锁竞争上的冲突
	就可以同时操作users和queries了(串行变成了并行了)
	*/
	public void addUserDiv(String u) {
		synchronized (users) {
			users.add(u);
		}
	}

	public void addQueryDiv(String q) {
		synchronized (queries) {
			queries.add(q);
		}
	}

}
