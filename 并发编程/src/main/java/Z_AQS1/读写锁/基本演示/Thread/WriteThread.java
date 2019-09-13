package Z_AQS1.读写锁.基本演示.Thread;

import Z_AQS1.读写锁.基本演示.service.GoodsService;
import utils.SleepTools;

import java.util.Random;

public class WriteThread implements Runnable {
	private GoodsService goodsService;
	public WriteThread(GoodsService goodsService) {
		this.goodsService = goodsService;
	}


	@Override
	public void run() {
		long start = System.currentTimeMillis();
		Random r = new Random();
		for(int i=0;i<10;i++){//操作10次
			SleepTools.ms(50);
			goodsService.setNum(r.nextInt(10));
		}
		System.out.println(Thread.currentThread().getName()
				+"写商品数据耗时："+(System.currentTimeMillis()-start)+"ms---------");

	}
}
