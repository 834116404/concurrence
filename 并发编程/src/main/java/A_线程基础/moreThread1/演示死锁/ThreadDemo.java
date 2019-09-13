package A_线程基础.moreThread1.演示死锁;

/**
 * 同步锁使用的弊端：
 * 当线程任务中出现了多个同步(多个锁)时，
 * 如果同步中嵌套了其他的同步。这时容易引发一种现象：
 * 程序出现无限等待，这种现象我们称为死锁。
 * 这种情况能避免就避免掉。
 */
public class ThreadDemo {
	public static void main(String[] args) {
		//创建线程任务类对象
		ThreadTask task = new ThreadTask();
		//创建两个线程
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		//启动线程
		t1.start();
		t2.start();
	}
}
