package B_并发安全.aa_无状态的类;

/**
 * 无状态的类
 * <p>
 * 类的状态就是指  类的成员变量
 */
public class StatelessClass {
	public int service(int a, int b) {

		return a + b;
	}

	/**
	 * 这个类还是线程安全的
	 * 这个类没有持有UserVo类
	 * <p>
	 * 如果出现线程安全问题了是UserVo这个类本身线程不安全导致的,背锅的也是UserVo
	 *
	 * @param user
	 */
	public void serviceUser(UserVo user) {
		//do sth user
		//方法对UserVo做了操作
	}


}
