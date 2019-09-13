package C_线程协作.a_join案例;

public class FirstThread extends Thread {

	public FirstThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i <= 20; i++) {
			System.out.println(this.getName() + ":" + i);
		}
	}
}