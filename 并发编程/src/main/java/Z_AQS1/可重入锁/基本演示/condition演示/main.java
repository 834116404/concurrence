package Z_AQS1.可重入锁.基本演示.condition演示;

/**
 */
public class main {

	public static void main(String[] args) {
		ResourceByCondition r = new ResourceByCondition();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);

		Thread t0 = new Thread(pro);

//		Thread t1 = new Thread(pro);
//
//		Thread t2 = new Thread(con);
//
		Thread t3 = new Thread(con);

		t0.start();
//		t1.start();
//		t2.start();
		t3.start();
	}
}



