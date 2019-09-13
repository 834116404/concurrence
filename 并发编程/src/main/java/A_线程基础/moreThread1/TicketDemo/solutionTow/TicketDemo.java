package A_线程基础.moreThread1.TicketDemo.solutionTow;

public class TicketDemo {
	public static void main(String[] args) {
		//1.任务对象
		TicketTask tt = new TicketTask();//卖票任务
		//2.三个线程
		Thread t1 = new Thread(tt, "小春");
		Thread t2 = new Thread(tt, "小菲菲");
		Thread t3 = new Thread(tt, "古力娜扎");
		//3.开启线程同时卖票
		t1.start();
		t2.start();
		t3.start();
	}
}
