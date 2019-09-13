package Z_AQS1.读写锁.作业;

import Z_AQS1.读写锁.作业.pojo.GoodsInfo;
import utils.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseRwLock implements GoodsService {

	private GoodsInfo goodsInfo;
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock getLock = readWriteLock.readLock(); //读锁
	private final Lock setLock = readWriteLock.writeLock(); // 写锁


	@Override
	public GoodsInfo getNum() {
		getLock.lock();
		try {
			SleepTools.ms(5);
			return this.goodsInfo;
		} finally {
			getLock.unlock();
		}


	}

	@Override
	public void setNum(int number) {
		setLock.lock();
		try {
			SleepTools.ms(5);
			goodsInfo.changeNumber(number);
		} finally {
			setLock.unlock();
		}

	}


	public UseRwLock(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}


}
