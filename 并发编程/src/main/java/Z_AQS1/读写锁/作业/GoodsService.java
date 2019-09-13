package Z_AQS1.读写锁.作业;


import Z_AQS1.读写锁.作业.pojo.GoodsInfo;

public interface GoodsService {
	GoodsInfo getNum();// 获取商品的信息

	void setNum(int number); //设置商品的数量

}
