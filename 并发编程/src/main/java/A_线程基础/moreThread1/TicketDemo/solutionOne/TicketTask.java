package A_线程基础.moreThread1.TicketDemo.solutionOne;

//��Ʊ����
public class TicketTask implements Runnable {

	private int count = 100;//Ĭ�Ͽ�ʼ100��Ʊ
	private Object obj = new Object();

	@Override
	public void run() {

		while (true) {
			//��Ʊ
			synchronized (obj) {
				if (count > 0) {
					//�ó�����Ϣ��
					System.out.println(Thread.currentThread().getName() + "������" + count + "��Ʊ");
					count--;
				}
			}
		}
	}

}
