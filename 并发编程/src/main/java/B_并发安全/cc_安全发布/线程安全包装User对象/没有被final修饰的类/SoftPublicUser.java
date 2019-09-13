package B_并发安全.cc_安全发布.线程安全包装User对象.没有被final修饰的类;


/**
 * 仿Collections对容器的包装，将内部成员对象进行线程安全包装
 */
public class SoftPublicUser {
	private final UserVo user;

	public UserVo getUser() {
		return user;
	}

	public SoftPublicUser(UserVo user) {
		this.user = new SynUser(user);
	}

	/*内部类*/
	private static class SynUser extends UserVo {
		private final UserVo userVo;
		private final Object lock = new Object();

		public SynUser(UserVo userVo) {
			this.userVo = userVo;
		}

		@Override
		public int getAge() {
			synchronized (lock) {//只有拿到锁才能调用这个getAge方法
				return userVo.getAge();
			}
		}

		@Override
		public void setAge(int age) {
			synchronized (lock) { //只有拿到锁才能调用这个setAge方法
				userVo.setAge(age);
			}
		}
	}

}
