package C_线程协作.waitAndNotify;

public class demo2 {

	public static void main(String[] args) {

		Object o = new Object();
		Thread t1 = new Thread("t1线程") {
			@Override
			public void run() {
				try {
					synchronized (o) {
						o.wait();
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(this.getName() + "起来了");
			}
		};


		Thread t2 = new Thread("t2线程") {
			@Override
			public void run() {
				try {
					synchronized (o) {
						o.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(this.getName() + "起来了");
			}
		};

		Thread t3 = new Thread("t3线程") {
			@Override
			public void run() {
				try {
					synchronized (o) {
						o.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(this.getName() + "起来了");
			}
		};

		Thread t4 = new Thread("t4线程") {
			@Override
			public void run() {
				synchronized (o) {
					o.notifyAll();
				}

				System.out.println(this.getName() + "释放锁了");
			}
		};
		t4.start();
		t1.start();
		t2.start();
		t3.start();


	}
}
