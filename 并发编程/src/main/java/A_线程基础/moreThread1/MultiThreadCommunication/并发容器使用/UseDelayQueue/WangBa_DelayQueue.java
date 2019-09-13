package A_线程基础.moreThread1.MultiThreadCommunication.并发容器使用.UseDelayQueue;

import java.util.concurrent.DelayQueue;

public class WangBa_DelayQueue implements Runnable {


	/**
	 * 模拟网吧上网
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			System.out.println("网吧开始营业");
			WangBa_DelayQueue siyu = new WangBa_DelayQueue();
			Thread shangwang = new Thread(siyu);
			shangwang.start();

			siyu.shangji("路人甲", "123", 1);
			siyu.shangji("路人乙", "234", 10);
			siyu.shangji("路人丙", "345", 5);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * DelayQueue里面有一堆 Wangmin实体类
	 */
	private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();

	public boolean yinye = true;

	/**
	 * @param name  上机器人的名字
	 * @param id    上机器人的身份证号
	 * @param money 交了多少钱
	 */
	public void shangji(String name, String id, int money) {
		/**
		 *第三个参数是:
		 * 根据你的交钱来计算你上网时间  +  System.currentTimeMillis() 当前时间
		 *
		 * 意思就是从当前时间开始计费,你交了多少钱就上多少时间网,到时间了就该下机了.
		 */
		Wangmin man = new Wangmin(name, id, 1000 * money + System.currentTimeMillis());

		System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "交钱" + money + "块,开始上机...");
		this.queue.add(man);
	}

	public void xiaji(Wangmin man) {
		System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "时间到下机...");
	}

	@Override
	public void run() {
		while (yinye) {
			try {
				/**
				 * 如果时间到了
				 */
				Wangmin man = queue.take();
				/**
				 * 就调用下机方法
				 */
				xiaji(man);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}  