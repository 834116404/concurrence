package C_线程协作.手写连接池;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 类说明：连接池的实现
 */
public class DBPool {

	/*容器，存放连接  ,也可以用数组*/
	private static LinkedList<Connection> pool = new LinkedList<Connection>();

	/*限制了池的大小=20*/
	public DBPool(int initialSize) {
		if (initialSize > 0) {
			for (int i = 0; i < initialSize; i++) {
				/*SqlConnectImpl.fetchConnection() 已经创建好的连接*/
				pool.addLast(SqlConnectImpl.fetchConnection());
			}
		}
	}

	/*
	释放连接,通知其他的等待连接的线程,别的线程需要里面的链接
	 * */
	public void releaseConnection(Connection connection) {
		if (connection != null) {
			synchronized (pool) {
				//锁住连接池把链接放回去
				pool.addLast(connection);  //将指定的元素追加到此列表的末尾。

				//通知其他等待连接的线程(上面放回去连接池了,池子就不空了,然后就进行通知了,等待其它的线程去连接池里面获取链接)
				pool.notifyAll();
			}
		}
	}


	/**
	 * 获取
	 * 会有等待超时时间
	 *
	 * @param mills 超时时间
	 */
	// 在mills内无法获取到连接，将会返回null 1S
	public Connection fetchConnection(long mills)
			throws InterruptedException {
		synchronized (pool) {
			//如果用户传入参数小于等于0就是永不超时,就是拿不到就一直等待去拿
			if (mills <= 0) {
				while (pool.isEmpty()) {//如果池子里面为空,就等待
					pool.wait();
				}
				return pool.removeFirst(); // 如果被唤醒了,说明有连接了,就从头部拿连接,同时删除集合容器的第一个.
			} else {

				/*future是超时时刻,多久内没拿到就返回空的出去*/
				long future = System.currentTimeMillis() + mills;//计算出一个超时的值,
				/*等待时长*/
				long remaining = mills;
				// 如果容器里面是空的,&&当第一个条件为假之后，后面的条件就不执行了

				/**
				 * 如果连接池不为空,就会跳出循环,或者是等待时长用完了,也会跳出连接
				 */
				while (pool.isEmpty() && remaining > 0) {
					pool.wait(remaining);//等待多长时间
					/*唤醒一次，重新计算等待时长,然后无限进入循环*/
					remaining = future - System.currentTimeMillis();
				}

				// 如果被唤醒了
				Connection connection = null;
				if (!pool.isEmpty()) {  //如果池子里面不为空就说明拿到连接了,
					connection = pool.removeFirst();
				}

				return connection; //如果没有从池子里面获取到连接也返回空的连接给调用者,意思就是告诉调用者,调用超时了,但是还没有抢到.
			}

		}


	}
}
