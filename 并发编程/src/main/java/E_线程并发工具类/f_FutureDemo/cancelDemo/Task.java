package E_线程并发工具类.f_FutureDemo.cancelDemo;

import java.util.concurrent.*;

public class Task implements Callable<String> {

	@Override
	public String call() throws Exception {
		while (true) {
			System.out.println("Task: Test\n");
			Thread.sleep(100);
		}
	}

}

