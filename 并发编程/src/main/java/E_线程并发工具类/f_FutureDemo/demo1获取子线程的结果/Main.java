package E_线程并发工具类.f_FutureDemo.demo1获取子线程的结果;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
	public static void main(String[] args) {

		UseCallable useCallable = new UseCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(useCallable);

		//开启线程
		new Thread(futureTask).start();

		try {
			/*当FutureTask处于未启动或已启动状态时，执行FutureTask.get()方法将导致调用线程阻塞；
			当FutureTask处于已完成状态时，执行FutureTask.get()方法将导致调用线程立即返回结果或抛出异常。
			*/
			Integer integer = futureTask.get();

			System.out.println("integer = " + integer); //获取到了线程返回的结果
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}


	}
}
