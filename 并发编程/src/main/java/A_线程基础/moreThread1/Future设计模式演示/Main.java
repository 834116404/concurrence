package A_线程基础.moreThread1.Future设计模式演示;

/**
 * 以后使用不用像现在这样那么麻烦,
 * 直接用Java工具类就可以了
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		FutureClient fc = new FutureClient();
		/**
		 * data是返回数据 ,不能直接用,是一个接口对象
		 */
		Data data = fc.request("请求参数");
		System.out.println("请求发送成功!");
		System.out.println("做其他的事情...");


		String result = data.getRequest();
		System.out.println(result);

	}
}
