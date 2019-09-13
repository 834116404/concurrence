package C_线程协作.waitAndNotify;

/**
 * 先下载图片,图片下载展示附件 ,
 * 再下载附件
 * 64faabc3-5fad-5d5b-ba5d-f0803a32eff1
 */
public class Demo1 {


	public static void main(String[] args) {

		Object obj = new Object();

		Thread download = new Thread("我是下载线程 :") {
			@Override
			public void run() {

				System.out.println(this.getName() + "***开始下载图片");
				for (int i = 0; i < 101; i += 50) {
					System.out.println(this.getName() + "下载进度 : " + i + "%");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
				}
				System.out.println(this.getName() + "***图片下载成功");

				synchronized (obj) {
					obj.notify(); //唤醒线程
					System.err.println(this.getName() + "我释放了我自己的锁");
				}

				System.out.println(this.getName() + "***开始下载附件");
				for (int i = 0; i < 101; i += 50) {
					System.out.println(this.getName() + "附件下载进度 : " + i + "%");
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(this.getName() + "***附件下载成功");
			}
		};
		//图片展示

		Thread show = new Thread("我是查看线程 :") {
			@Override
			public void run() {
				synchronized (obj) {
					try {
						obj.wait();//阻塞线程等图片下载完毕
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(this.getName() + "***开始展示图片");
					System.out.println(this.getName() + "***图片展示完毕");


				}
			}
		};
		/*即使是 展示线程先启动,如果图片没下载完了,也不让你展示, 让你优先运行下载线程*/


		/*同时启动两个线程,然后show线程开始等待状态,等待下载线程下载完毕之后才开启线程*/
		download.start();
		show.start();


	}

}
