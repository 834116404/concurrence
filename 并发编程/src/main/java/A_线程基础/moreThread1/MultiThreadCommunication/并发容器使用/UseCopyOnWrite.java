package A_线程基础.moreThread1.MultiThreadCommunication.并发容器使用;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UseCopyOnWrite {

	public static void main(String[] args) {
		/**
		 * 用法很简单,之前怎么用 list和set 现在就怎么用
		 * 它底层都是加锁了.
		 */
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();


	}
}
