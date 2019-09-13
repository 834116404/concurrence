package B_并发安全.bb_让类不可变;

import java.util.ArrayList;
import java.util.List;

/**
 * 类不可变--事实不可变
 * <p>
 * 不提供让别人修改的地方  这个也是线程安全的
 */
public class ImmutableClassToo {
	private final List<Integer> list = new ArrayList<>(3);

	public ImmutableClassToo() {
		list.add(1);
		list.add(2);
		list.add(3);
	}

	public boolean isContain(int i) {
		return list.contains(i);
	}
}
