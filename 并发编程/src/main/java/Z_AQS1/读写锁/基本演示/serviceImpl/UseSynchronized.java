package Z_AQS1.读写锁.基本演示.serviceImpl;

import Z_AQS1.读写锁.基本演示.pojo.GoodsInfo;
import Z_AQS1.读写锁.基本演示.service.GoodsService;
import utils.SleepTools;

public class UseSynchronized implements GoodsService {
	private  GoodsInfo goodsInfo ;

	public UseSynchronized(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	@Override
	public synchronized GoodsInfo getNum() {
		SleepTools.ms(5);
		return this.goodsInfo;
	}

	@Override
	public synchronized void setNum(int number) {
		SleepTools.ms(5);
		goodsInfo.changeNumber(number);

	}
}
