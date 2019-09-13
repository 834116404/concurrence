package C_线程协作.NotifyOrNotifyAll.生产者消费者;

import java.util.ArrayList;
import java.util.List;

public class testGun {
	public static void main(String[] args) {
		GunClip clip = new GunClip();
		Producer p = new Producer(clip);
		Concumer c = new Concumer(clip);
		p.start();
		c.start();
	}
}

/* 首先我要有一个弹夹，*/
class GunClip   //弹夹
{
	private List<Integer> list = null;//用来放子弹
	private boolean bFull = false; //理解boolean变量很重要，表示满没满

	//函数功能，像弹夹中放子弹
	public synchronized void put(List list) {
		if (!bFull) {//意思是说弹夹没有满，那么就像里面放子弹
			this.list = list;
			bFull = true;//放满了子弹，在把boolean变量变为true表示，弹夹满了
			notify();//通知，等待取子弹的线程来取子弹
		}
		//下面的try中得wait是当弹夹满了的情况下，
		//即boolean为true，暂停本线程，等待取子弹
		try {
			wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void get() {
		if (!bFull) {//如果弹夹没满那么就不能取子弹
			try {
				wait();//弹夹没满，所以取不了，只能等待，只有弹夹满了才能取
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//下面是弹夹满了，那么我开始取子弹
		System.out.println("\n" + "机枪开始射出子弹:" + "\n");
		for (int i = list.size(); i > 0; i--) {
			int j = (Integer) list.get(i - 1);
			System.out.println("已射出第" + j + "颗子弹");
		}
		//弹夹被取空了，即没子弹了，那么只能等待重新填充，
		//所以boolean变为false，表示没满，通知装子弹的线程装子弹
		bFull = false;
		notify();
	}
}

//生产者，即装子弹，下面和公告板差不多，要有一个弹夹的实体
class Producer extends Thread {
	private GunClip clip;
	private List<Integer> list;

	Producer(GunClip clip) {
		this.clip = clip;
	}

	public void run() {

		list = new ArrayList();
		System.out.println("\n" + "机枪开始压入子弹：" + "\n");
		for (int j = 0; j < 12; j++) {
			list.add(j + 1);
			System.out.println("已压入第" + (j + 1) + "颗子弹");
		}
		clip.put(list);

	}
}

//消费者，发射子弹
class Concumer extends Thread {
	private GunClip clip;

	Concumer(GunClip clip) {
		this.clip = clip;
	}

	public void run() {
		while (true) {
			clip.get();
		}
	}
}