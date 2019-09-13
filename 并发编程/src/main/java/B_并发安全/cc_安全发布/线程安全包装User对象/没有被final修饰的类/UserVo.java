package B_并发安全.cc_安全发布.线程安全包装User对象.没有被final修饰的类;

/**
 * 类说明：
 * 正常应该是在写这个类的时候就应该注意线程安全,这才是根本的解决问题的办法
 * 如果这个类是别人的,你无法修改这个类, 你就只能用继承或者组合方式来实现线程安全.
 */
public class UserVo {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
