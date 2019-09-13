package C_线程协作.a_join案例;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		FirstThread firstThread = new FirstThread("第一个线程");
		SecondThread secondThread = new SecondThread("第二个线程");
		firstThread.start();
		/*
		 不加join()方法, 两个线程会交替执行
		 *firstThread线程加了join()之后 firstThread 线程会优先全部执行完secondThread线程才会执行
		 * */
		firstThread.join();
		secondThread.start();


	}

}