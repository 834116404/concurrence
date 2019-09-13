package B_并发安全.ff_synchronized.修饰普通方法;

import B_并发安全.ff_synchronized.修饰普通方法.Thread.DiffInstance;
import B_并发安全.ff_synchronized.修饰普通方法.Thread.Instance2Syn;
import B_并发安全.ff_synchronized.修饰普通方法.Thread.InstanceSyn;

/**
 * 修饰普通方法, 运行main函数看打印顺序
 * 案例代码: zjj_parent_74e4d254-ed67-215e-8c57-1a4ec2e3939a
 */
public class Main {

	/*main方法*/
	public static void main(String[] args) {
//		ceui1();
		ceui2();

	}

	/**
	 * 两个线程共用一个对象,共用一个锁, 线程2 只能等待线程1先执行完释放锁,然后线程2才能获得锁执行
	 */
	public static void ceui1() {
		DiffInstance diffInstance = new DiffInstance();//共用一个类

		Thread t1 = new Thread(new InstanceSyn(diffInstance));//共用一个类
		Thread t2 = new Thread(new Instance2Syn(diffInstance));//共用一个类
		t1.start();
		t2.start();

	}

	/**
	 * 两个线程分别用两个对象,两个对象之间每个对象都有自己的一把锁,所以t2线程不用等待t1执行完释放锁,
	 * 因为是两把锁.
	 * 因此,两个线程可以并行来执行
	 */
	public static void ceui2() {
		DiffInstance diffInstance1 = new DiffInstance();//两个类
		DiffInstance diffInstance2 = new DiffInstance();//两个类
		Thread t1 = new Thread(new InstanceSyn(diffInstance1));//每个线程用的是不同的类
		Thread t2 = new Thread(new Instance2Syn(diffInstance2));//每个线程用的是不同的类
		t1.start();
		t2.start();


	}


}
