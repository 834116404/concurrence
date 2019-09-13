package 原子操作CAS1;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 类说明：演示引用类型的原子操作类
 */
public class UseAtomicReference {
	static AtomicReference<UserInfo> atomicUserRef;

	public static void main(String[] args) {
		UserInfo user = new UserInfo("Mark", 15);//要修改的实体的实例
		atomicUserRef = new AtomicReference(user);// 包装一下

		UserInfo updateUser = new UserInfo("Bill", 17);
		atomicUserRef.compareAndSet(user, updateUser); //原子性的设置新的值

		System.out.println("修改过后的 :" + atomicUserRef.get());
		System.out.println("原来的user对象 :" + user);
	}

	//定义一个实体类
	static class UserInfo {
		private volatile String name;
		private int age;

		public UserInfo(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		@Override
		public String toString() {
			return "UserInfo{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}

}
