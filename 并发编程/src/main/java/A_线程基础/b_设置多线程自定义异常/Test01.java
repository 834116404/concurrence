package A_线程基础.b_设置多线程自定义异常;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 案例代码:zjj_parent_7aa0587a-9db1-e2de-1c6c-8299d246423e
 * 参考:
 * https://blog.csdn.net/lvxiangan/article/details/79076276
 * <p>
 * https://www.cnblogs.com/baiqiantao/p/79bf1e7f0803557c9195acec7af1e244.html
 * https://www.cnblogs.com/jadic/p/3532580.html
 */
public class Test01 {
	public static void main(String[] args) {
//		test(); // 没设置自定义异常的
		test2(); //设置自定义异常的
	}

	/**
	 * 设置自定义异常
	 * 如果线程出现了未被捕获的异常,则会执行 uncaughtException方法,
	 */
	private static void test2() {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("子线程异常前");
				System.out.println(1 / 0);


			}
		});


		Thread.UncaughtExceptionHandler currentHandler = new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("[当前线程的Handler处理器信息] : " + t.toString() + "\n" + e.getMessage());
			}
		};
		/*如果注释下面的,程序出现了未捕获的错误信息不会执行currentHandler方法的代码,
		如果有设置setDefaultUncaughtExceptionHandler 就执行该设置的Handler代码.

		  如果不注释下面的代码,则执行当前线程的未捕获错误信息代码,默认的未捕获错误信息代码(defaultHandler)将不执行
		* */
		thread.setUncaughtExceptionHandler(currentHandler);//设置未捕获异常处理器

		Thread.UncaughtExceptionHandler defaultHandler = new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				StringWriter writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				printWriter.write("start------------\n");
				e.printStackTrace(printWriter);
				printWriter.write("------------end");
				printWriter.close();
				System.out.println("【默认的Handler处理异常信息】" + writer.getBuffer().toString());
			}
		};
		/*如果注释掉,main线程报错会直接打印到控制台,不执行defaultHandler的代码
		 * 如果不注释,主线程和子线程的未捕获的错误出现都会执行defaultHandler的代码
		 * */
		Thread.setDefaultUncaughtExceptionHandler(defaultHandler);//设置默认未捕获异常处理器


		thread.start();

		System.out.println("当前线程异常前");
		System.out.println(1 / 0);
		System.out.println("异常后的代码不能执行");  //发生异常之后 这个代码是不能执行的.
	}

	/**
	 * 没设置自定义异常的
	 */
	private static void test() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("子线程异常前");
				System.out.println(1 / 0);
			}
		}).start();
		System.out.println("当前线程异常前");
		System.out.println(1 / 0);
		System.out.println("异常后的代码不能执行");  //发生异常之后 这个代码是不能执行的.
	}

}
