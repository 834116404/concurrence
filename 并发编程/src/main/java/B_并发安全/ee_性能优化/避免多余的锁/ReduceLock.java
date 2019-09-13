package B_并发安全.ee_性能优化.避免多余的锁;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 避免多余的锁
 */
public class ReduceLock {

	private Map<String, String> matchMap = new HashMap<>();


	/**
	 * @param name
	 * @param regexp
	 * @return
	 */
	private boolean isMatchReduce(String name, String regexp) {
		int count = 1;
		String key = "user." + name;
		String job;

		/*这样产生了多余的锁,是不合适的
		 count ++ ; 是一个非常简短的操作,
		 * 加锁也是要耗费cpu的,你加锁的时间比我中间的count++的时间还要长
		 * 这时候就进行锁的粗化,就是只拿一把锁
		 *
		 * 所以要避免这样!!!!!
		 *
		 * */
		synchronized (this) {
			job = matchMap.get(key);//给这里加锁
		}
		count++;
		synchronized (this) {
			//又进行了业务操作
		}

		if (job == null) {
			return false;
		} else {
			return Pattern.matches(regexp, job);
		}
	}

}
