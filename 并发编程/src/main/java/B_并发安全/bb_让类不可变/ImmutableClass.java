package B_并发安全.bb_让类不可变;

import B_并发安全.aa_无状态的类.UserVo;

/**
 * 类不可变
 * <p>
 * 只能提供get 不能提供set方法,外人无法改动类里面的成员变量, 这样也是线程安全的
 */
public class ImmutableClass {
	private final int a;
	private final int b;

	//这样是线程不安全的,因为get方法给UserVo实例引用发布输出了. 这样别的线程也有这个UserVo线程的引用了.
	//就意味着这个线程的UserVo实例被多个线程看到了.
	//类加final只是这个类的引用不可变,不能保证这个类的内容不可变,类加final的话类也无法extends

//	private final UserVo user = new UserVo();
//	public UserVo getUser() {
//		return user;
//	}


	/*加synchronized就意味着线程安全了,因为get的时候就给加锁了*/
	private final UserVo user = new UserVo();

	public synchronized UserVo getUser() {
		return user;
	}


	public int getA() {
		return a;
	}


	public ImmutableClass(int a) {
		this.a = a;
		this.b = a;
	}

	public static class User {
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
