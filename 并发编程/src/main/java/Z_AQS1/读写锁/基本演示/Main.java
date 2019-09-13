package Z_AQS1.读写锁.基本演示;

import Z_AQS1.读写锁.基本演示.Thread.ReadThread;
import Z_AQS1.读写锁.基本演示.Thread.WriteThread;
import Z_AQS1.读写锁.基本演示.pojo.GoodsInfo;
import Z_AQS1.读写锁.基本演示.service.GoodsService;
import Z_AQS1.读写锁.基本演示.serviceImpl.UseReentrantReadWriteLock;
import utils.SleepTools;
/**
 * 切换两个子类来查看效果
 */
public class Main {
	static final int readWriteRatio = 10;//读写线程的比例
	static final int minthreadCount = 3;//最少线程数

	public static void main(String[] args) {

		GoodsInfo goodsInfo = new GoodsInfo("杯子", 100000, 100000);
		/**
		 * 切换两个子类来查看效果
		 */
		GoodsService goodsService = new UseReentrantReadWriteLock(goodsInfo);
//		GoodsService goodsService = new UseSynchronized(goodsInfo);
		for (int i = 0; i < minthreadCount; i++) {
			Thread writeThread = new Thread(new WriteThread(goodsService));
			for (int j = 0; j < readWriteRatio; j++) {
				Thread readThread = new Thread(new ReadThread(goodsService));
				readThread.start();
			}
			SleepTools.ms(100);
			writeThread.start();
		}

	}
}
