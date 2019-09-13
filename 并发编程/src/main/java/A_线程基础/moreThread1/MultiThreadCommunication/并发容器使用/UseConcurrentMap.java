package A_线程基础.moreThread1.MultiThreadCommunication.并发容器使用;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UseConcurrentMap {

	public static void main(String[] args) {
		/**
		 * 使用起来很简单,之前hashmap怎么使用,
		 * 它就怎么使用
		 */
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
		chm.put("k1", "v1");
		chm.put("k2", "v2");
		chm.put("k3", "v3");
		//如果key相同就不加进来,如果key不同就会加进来
		chm.putIfAbsent("k3", "key不同,不会加进来");
		chm.putIfAbsent("k4", "key不同,会加进来");
		//System.out.println(chm.get("k2"));
		//System.out.println(chm.size());

		for (Map.Entry<String, Object> me : chm.entrySet()) {
			System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
		}


	}
}
