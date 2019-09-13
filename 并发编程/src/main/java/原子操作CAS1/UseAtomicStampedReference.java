package 原子操作CAS1;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 类说明：演示带版本戳的原子操作类
 * 可以解决ABA问题
 */
public class UseAtomicStampedReference {
	/**
	 * 参数1 初始化值
	 * 参数2 初始化时间戳
	 */
	static AtomicStampedReference<String> asr
			= new AtomicStampedReference("mark", 0);

	public static void main(String[] args) throws InterruptedException {
		//拿到当前的版本号(旧)
		final int oldStamp = asr.getStamp(); //当前时间戳, 此时是0
		final String oldReference = asr.getReference();//返回引用的当前值
		System.out.println("当前的值 : " + oldReference + "============" + "当前的时间戳 :" + oldStamp);
		/**
		 * 恰好时机
		 */
		Thread rightStampThread = new Thread(new Runnable() {
			@Override
			public void run() {
				/**
				 * 参数1 : 老的值
				 *  参数2 : 准备赋值的新值
				 *  参数3:  老的版本号 (如果和当前的版本号不一致就会修改失败)
				 *  参数4:  新的版本号(设置新的版本号,设置的是谁,最后新的版本号就是谁.)
				 *
				 */
				boolean b = asr.compareAndSet(oldReference,
						oldReference + "+Java", oldStamp,
						1);
				System.out.println(Thread.currentThread().getName() + ":当前变量值："
						+ oldReference + "-当前版本戳：" + oldStamp + "-"
						+ "是否设置成功 : " + b);
			}
		});
		/**
		 * 错误时机
		 */
		Thread errorStampThread = new Thread(new Runnable() {
			@Override
			public void run() {
				String reference = asr.getReference();

				boolean b = asr.compareAndSet(reference,
						reference + "+C", oldStamp,
						oldStamp + 1);  //是否设置成功
				System.out.println(Thread.currentThread().getName()
						+ ":当前变量值："
						+ reference + "-当前版本戳：" + asr.getStamp() + "-"
						+ "是否设置成功 :" + b);
			}
		});

		/**
		 *  又一次正确的赋值操作
		 */
		Thread second = new Thread(new Runnable() {
			@Override
			public void run() {
				String reference = asr.getReference();
				/**
				 * 参数3 要和当前版本号对应上,不然就会设置值失败
				 * 参数4 是保存成功之后的新版本号
				 */
				boolean b = asr.compareAndSet(reference,
						reference + "+second", 1, 2);  //是否设置成功
				System.out.println(Thread.currentThread().getName()
						+ ":当前变量值："
						+ reference + "-当前版本戳：" + asr.getStamp() + "-"
						+ "是否设置成功 :" + b);
			}
		});

		rightStampThread.setName("rightStampThread");
		rightStampThread.start();
		rightStampThread.join();
		errorStampThread.setName("errorStampThread");
		errorStampThread.start();
		errorStampThread.join();
		second.setName("second");
		second.start();
		second.join();

		System.out.println("修改完成后的值" + asr.getReference() + "修改后的版本戳" + asr.getStamp());
	}
}
