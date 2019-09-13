package B_并发安全.cc_安全发布.线程安全包装User对象.final修饰的类;

/**
 * 类说明：委托给线程安全的类来做
 * <p>
 * final修饰的方法无法继承了, 就得用委托的方式来做了
 */
public class SafePublicFinalUser {
	private final SynFinalUser user;

	public SynFinalUser getUser() {
		return user;
	}

	public SafePublicFinalUser(FinalUserVo user) {
		this.user = new SynFinalUser(user);
	}

	public static class SynFinalUser {
		private final FinalUserVo userVo;  //组合的方式
		private final Object lock = new Object();

		public SynFinalUser(FinalUserVo userVo) {
			this.userVo = userVo;
		}

		public int getAge() {
			synchronized (lock) {
				return userVo.getAge();
			}
		}

		public void setAge(int age) {
			synchronized (lock) {
				userVo.setAge(age);
			}
		}
	}

}
