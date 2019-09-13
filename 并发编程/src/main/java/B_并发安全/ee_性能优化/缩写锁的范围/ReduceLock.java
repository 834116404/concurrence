package B_并发安全.ee_性能优化.缩写锁的范围;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 缩小锁的范围
 */
public class ReduceLock {

	private Map<String, String> matchMap = new HashMap<>();

	/**
	 * 这个方法是给整个方法进行了加锁,因为HashMap是一个线程不安全的
	 * 而且正则是一个耗时的操作,此时不应该对方法进行加锁
	 *
	 * @param name
	 * @param regexp
	 * @return
	 */
	public synchronized boolean isMatch(String name, String regexp) {
		String key = "user." + name;
		String job = matchMap.get(key);
		if (job == null) {
			return false;
		} else {
			return Pattern.matches(regexp, job);//这里是一个耗时的操作
		}
	}

	/**
	 * 缩小锁的范围
	 * 只是给拿map里面的内容的 get方法加锁
	 *
	 * @param name
	 * @param regexp
	 * @return
	 */
	private boolean isMatchReduce(String name, String regexp) {
		String key = "user." + name;
		String job;
		synchronized (this) {
			job = matchMap.get(key);//给这里加锁
		}

		if (job == null) {
			return false;
		} else {
			return Pattern.matches(regexp, job); //把耗时的操作从同步代码块儿移走了,
		}
	}

}
