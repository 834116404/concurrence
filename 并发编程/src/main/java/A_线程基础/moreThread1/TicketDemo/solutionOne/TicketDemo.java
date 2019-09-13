package A_线程基础.moreThread1.TicketDemo.solutionOne;

public class TicketDemo {
	public static void main(String[] args) {
		//1.�������
		TicketTask tt = new TicketTask();//��Ʊ����
		//2.�����߳�
		Thread t1 = new Thread(tt, "С��");
		Thread t2 = new Thread(tt, "С�Ʒ�");
		Thread t3 = new Thread(tt, "��������");
		//3.�����߳�ͬʱ��Ʊ
		t1.start();
		t2.start();
		t3.start();
	}
}
