package Z_AQS1.读写锁.基本演示.Thread;

import Z_AQS1.读写锁.基本演示.service.GoodsService;

public class ReadThread implements Runnable {
	private GoodsService goodsService;

	public ReadThread(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {//操作100次
			goodsService.getNum();
		}
		System.out.println(Thread.currentThread().getName() +
				"读取商品数据耗时 : " + (System.currentTimeMillis() - start) + "ms");

	}
}
