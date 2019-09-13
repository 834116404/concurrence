package Z_AQS1.读写锁.基本演示.service;


import Z_AQS1.读写锁.基本演示.pojo.GoodsInfo;

/**
 * 类说明：商品的服务的接口
 */
public interface GoodsService {

	  GoodsInfo getNum();//获得商品的信息

	  void setNum(int number);//设置商品的数量
}
