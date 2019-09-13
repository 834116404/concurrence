package E_线程并发工具类.d_Semaphore.实现数据库连接池;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * 类说明：演示Semaphore用法，一个数据库连接池的实现
 */
public class DBPoolSemaphore {
	//初始化10个连接
	private final static int POOL_SIZE = 10;
	//两个指示器，分别表示池子还有可用连接和已用连接
	/**
	 * 为什么要定义两个,如果不定义两个的话,多个线程会拿到多个连接,就超过了限定的10个大小了.
	 * 这是Semaphore的坑
	 */
	private final Semaphore useful;
	private final Semaphore useless;

	//存放数据库连接的容器
	private static LinkedList<Connection> pool = new LinkedList<Connection>();

	//初始化池
	static {
		for (int i = 0; i < POOL_SIZE; i++) {
			pool.addLast(SqlConnectImpl.fetchConnection());
		}
	}

	public DBPoolSemaphore() {
		this.useful = new Semaphore(10);//初始化可以连接
		this.useless = new Semaphore(0); //不可用连接
	}

	/*归还连接*/
	public void returnConnect(Connection connection) throws InterruptedException {
		if (connection != null) {
			System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接!!"
					+ "可用连接数：" + useful.availablePermits());
			/*为什么用了acquire()之后还会pool加锁呢?
			 * 这是两回事,acquire是操作许可证而已,不是操作连接池
			 * */
			useless.acquire();
			synchronized (pool) {
				pool.addLast(connection);
			}
			useful.release();//释放掉许可证(规范连接)
		}
	}

	/*从池子拿连接*/
	public Connection takeConnect() throws InterruptedException {
		useful.acquire();
		Connection connection;
		synchronized (pool) {
			connection = pool.removeFirst();
		}
		useless.release();
		return connection;
	}

}
