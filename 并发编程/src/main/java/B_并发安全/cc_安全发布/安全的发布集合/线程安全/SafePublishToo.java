package B_并发安全.cc_安全发布.安全的发布集合.线程安全;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这样就是安全的发布
 */
public class SafePublishToo {
	/*如果考虑性能的话,可以用线程安全的集合*/
	private List<Integer> list
			= Collections.synchronizedList(new ArrayList<>(3));

	public SafePublishToo() {
		list.add(1);
		list.add(2);
		list.add(3);
	}

	public List getList() {
		return list;
	}

	public static void main(String[] args) {
		SafePublishToo safePublishToo = new SafePublishToo();
		List<Integer> list = safePublishToo.getList();
		System.out.println(list);
		list.add(4);
		System.out.println(list);
		System.out.println(safePublishToo.getList());
	}
}
