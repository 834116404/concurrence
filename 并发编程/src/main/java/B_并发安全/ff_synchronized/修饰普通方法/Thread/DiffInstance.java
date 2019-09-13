package B_并发安全.ff_synchronized.修饰普通方法.Thread;


import utils.SleepTools;

public class DiffInstance {

	protected synchronized void instance() {
		SleepTools.second(3);
		System.out.println("synInstance is going..." + this.toString());
		SleepTools.second(3);
		System.out.println("synInstance ended " + this.toString());
	}

	protected synchronized void instance2() {
		SleepTools.second(3);
		System.out.println("synInstance2 is going..." + this.toString());
		SleepTools.second(3);
		System.out.println("synInstance2 ended " + this.toString());
	}
}
