package A_线程基础.生产者消费者1;

/**
 * 容器数据类型
 *
 * @author ctk
 */
public class PCData {
	private final int intData;

	public PCData(int d) {
		intData = d;
	}

	public PCData(String d) {
		intData = Integer.valueOf(d);
	}

	public int getData() {
		return intData;
	}

	@Override
	public String toString() {
		return "data:" + intData;
	}
}