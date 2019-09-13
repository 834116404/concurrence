package E_线程并发工具类.e_Exchanger;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * 类说明：演示Exchange用法
 * zjj_SpringBoot_c741f758-0067-d8bb-2573-df5f6df28ddf
 */
public class Main {
	private static final Exchanger<Set<String>> exchange = new Exchanger<Set<String>>();

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				Set<String> setA = new HashSet<String>();//存放数据的容器
				try {
					for (int i = 0; i < 10; i++) {
						setA.add("A线程添加的" + i);
					}


					/*添加数据
					 * */
					setA = exchange.exchange(setA);//交换set
					/*处理交换后的数据*/
					System.out.println("setA = " + setA);
				} catch (InterruptedException e) {
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				Set<String> setB = new HashSet<String>();//存放数据的容器
				try {
					/*添加数据
					 * */
					for (int i = 0; i < 10; i++) {
						setB.add("B线程添加的" + i);
					}

					setB = exchange.exchange(setB);//交换set

					/*处理交换后的数据*/
					System.out.println("setB = " + setB);
				} catch (InterruptedException e) {
				}
			}
		}).start();

	}
}
