package A_线程基础.moreThread1.MultiThreadCommunication.实时通知;

import java.util.ArrayList;
import java.util.List;

/**
 * 先添加了五个元素,再发出了通知接着又加了五个元素,
 * <p>
 * t1添加了元素之后, t2才收到通知
 * <p>
 * 最后还是控制失败了
 * 具体的后续看 ListAdd3
 */
public class ListAdd2 {
	private volatile static List list = new ArrayList();


	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final ListAdd2 list2 = new ListAdd2();
		final Object lock = new Object();  // 创建了一个Object,把这个对象当成了一把锁
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (lock) {//获得了这把锁
						System.out.println("t1启动..");
						for (int i = 0; i < 10; i++) {//还是循环10次往容器里面添加10次元素
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if (list2.size() == 5) {//如果为5的时候
								System.out.println("已经发出通知了....");
								//直接.notify() 唤醒另外一个线程(Object 方法),但是唤醒了并没有释放锁,
								//什么时候释放锁.当t1线程已经执行完毕之后才释放锁
								// t2才能获得锁进去判断一下是否等于5
								lock.notify();
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {//获得上面同样的锁
					System.out.println("t2启动..");
					if (list2.size() != 5) { //如果不等于5的时候
						try {
							lock.wait(); //Object的方法 wait()是等待的意思, 程序就阻塞到这一行了;//wait()会释放锁
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//如果等于5就走下面的,停止线程抛异常
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				}
			}
		}, "t2");
		//先启动 t2 线程再启动 t1线程
		t2.start();
		t1.start();

	}

}
