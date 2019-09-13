package Z_AQS1.读写锁.作业;

import Z_AQS1.读写锁.作业.pojo.GoodsInfo;
import utils.SleepTools;

public class Main {
	public static void main(String[] args) {

		GoodsInfo goodsInfo = new GoodsInfo("p苹果", 100000, 10000);

		GoodsService goodsService = new UseRwLock(goodsInfo);


		for (int i = 0; i < 3; i++) {
			Thread t1 = new Thread(new SetThread(goodsService));

			for (int j = 0; j < 10; j++) {
				Thread t2 = new Thread(new GetThread(goodsService));
				t2.start();
			}
			SleepTools.ms(100);
			t1.start();
		}

	}
}
