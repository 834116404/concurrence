package 原子操作CAS1.作业;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 类说明：演示基本类型的原子操作类
 */
public class UseAtomicInt {
	/**
	 * 参数1 初始化值
	 * 参数2 初始化时间戳
	 */
	static AtomicStampedReference<String> asr
			= new AtomicStampedReference("mark", 0);

	public static void main(String[] args) throws InterruptedException {
		int oldStamp = asr.getStamp();
		String oldReference = asr.getReference();

		Thread rightStampThread = new Thread(new Runnable() {
			@Override
			public void run() {
				/**
				 * 参数1 : 老的值
				 *  参数2 : 准备赋值的新值
				 *  参数3:  老的版本号 (如果和当前的版本号不一致就会修改失败)
				 *  参数4:  新的版本号(设置新的版本号)
				 *
				 *
				 */
				boolean b = asr.compareAndSet(oldReference,
						oldReference + "+Java", oldStamp,
						10);
				System.out.println(Thread.currentThread().getName() + ":当前变量值："
						+ oldReference + "-当前版本戳：" + oldStamp + "-"
						+ "是否设置成功 : " + b);
			}
		});
		/**
		 * 错误时机
		 */
//		Thread errorStampThread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				String reference = asr.getReference();
//
//				boolean b = asr.compareAndSet(reference,
//						reference + "+C", oldStamp,
//						oldStamp + 1);  //是否设置成功
//				System.out.println(Thread.currentThread().getName()
//						+ ":当前变量值："
//						+ reference + "-当前版本戳：" + asr.getStamp() + "-"
//						+ "是否设置成功 :" + b);
//			}
//		});
		rightStampThread.setName("rightStampThread");
		rightStampThread.start();
		rightStampThread.join();
//		errorStampThread.setName("errorStampThread");
//		errorStampThread.start();
//		errorStampThread.join();


		System.out.println("修改完成后的值" + asr.getReference() + "修改后的版本戳" + asr.getStamp());

	}




}
