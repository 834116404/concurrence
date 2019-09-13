package Z_AQS1.读写锁.基本演示.serviceImpl;

import Z_AQS1.读写锁.基本演示.pojo.GoodsInfo;
import Z_AQS1.读写锁.基本演示.service.GoodsService;
import utils.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseReentrantReadWriteLock implements GoodsService {
	private GoodsInfo goodsInfo;


	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();//获取读锁
	private final Lock writeLock = lock.writeLock(); //获取写锁

	public UseReentrantReadWriteLock(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	@Override
	public GoodsInfo getNum() {
		readLock.lock();
		try {
			SleepTools.ms(5);
			return this.goodsInfo;
		} finally {
			readLock.unlock();
		}


	}

	@Override
	public void setNum(int number) {
		writeLock.lock();
		try {
			SleepTools.ms(5);
			goodsInfo.changeNumber(number);
		} finally {
			writeLock.unlock();
		}


	}
}
