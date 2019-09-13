package E_线程并发工具类.f_FutureDemo;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * 类说明：演示Future等的使用
 * zjj_SpringBoot_6c2816d5-3a48-7e74-3fed-9b4cada62d53
 */
public class UseFuture {


	public static void main(String[] args)
			throws InterruptedException, ExecutionException {

		UseCallable useCallable = new UseCallable(); //拿到Callable线程
		//包装
		FutureTask<Integer> futureTask = new FutureTask<>(useCallable);//用FutureTask包装Callable

		new Thread(futureTask).start();//交给线程来执行

		Thread.sleep(1);

		Random r = new Random();
		if (r.nextInt(100) > 50) {
			/*get()方法获取结果*/
			System.out.println("Get UseCallable result = " + futureTask.get());
		} else {
			System.out.println("Cancel................. ");
			futureTask.cancel(true);/*终止任务*/
		}

	}

}
