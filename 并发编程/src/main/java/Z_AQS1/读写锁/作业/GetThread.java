package Z_AQS1.读写锁.作业;

public class GetThread implements Runnable {
	private GoodsService goodsService;

	public GetThread(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {//操作100次
			goodsService.getNum();
		}
		System.out.println(Thread.currentThread().getName() + "读取商品数据耗时："
				+ (System.currentTimeMillis() - start) + "ms");
	}


}
