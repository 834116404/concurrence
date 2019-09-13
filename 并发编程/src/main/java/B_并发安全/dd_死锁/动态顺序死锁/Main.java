package B_并发安全.dd_死锁.动态顺序死锁;


import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.ITransfer;
import B_并发安全.dd_死锁.动态顺序死锁.动态顺序死锁源头.pojo.UserAccount;
import B_并发安全.dd_死锁.动态顺序死锁.解决方案.SafeOperate;

/**
 * 类说明：模拟支付公司转账的动作
 * 演示动态顺序死锁
 */
public class Main {

	/*执行转账动作的线程*/
	private static class TransferThread extends Thread {

		private String name;
		private UserAccount from;
		private UserAccount to;
		private int amount;
		private ITransfer transfer;

		public TransferThread(String name, UserAccount from, UserAccount to,
							  int amount, ITransfer transfer) {
			this.name = name;
			this.from = from;
			this.to = to;
			this.amount = amount;
			this.transfer = transfer;
		}


		public void run() {
			Thread.currentThread().setName(name);
			try {
				/*产生死锁的原因是在这里, from to 传入的顺序是动态的.*/
				transfer.transfer(from, to, amount);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		UserAccount zhangsan = new UserAccount("zhangsan", 20000);
		UserAccount lisi = new UserAccount("lisi", 20000);
		/*查看效果就注释下面的几个方法*/
//		ITransfer transfer = new TrasnferAccount();  //产生死锁了
		ITransfer transfer = new SafeOperate(); //尝试拿锁机制1
//		ITransfer transfer = new SafeOperateToo(); //尝试拿锁机制2

		/*转出和转入的顺序就相反了,就产生了我们所谓的动态顺序死锁,
		动态顺序死锁排查的时候光看代码是看不出问题的,只有在调用代码的时候才发现调用方传入的参数顺序不对产生的
		* */
		TransferThread zhangsanToLisi = new TransferThread("zhangsanToLisi"
				, zhangsan, lisi, 2000, transfer);

		TransferThread lisiToZhangsan = new TransferThread("lisiToZhangsan"
				, lisi, zhangsan, 4000, transfer);
		zhangsanToLisi.start();
		lisiToZhangsan.start();

	}

}
