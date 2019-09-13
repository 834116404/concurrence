package A_线程基础.moreThread1.MultiThreadCommunication.并发容器使用.UsePriorityBlockingQueue;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {


	public static void main(String[] args) throws Exception {


		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();

		Task t1 = new Task();
		t1.setId(3);
		t1.setName("id为3");
		Task t2 = new Task();
		t2.setId(4);
		t2.setName("id为4");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("id为1");

		//return this.id > task.id ? 1 : 0;
		q.add(t1);    //3
		q.add(t2);    //4
		q.add(t3);  //1
		/**
		 * 输出结果:
		 * task = id为1
		 * task = id为4
		 * task = id为3
		 */
		for (Iterator iterator = q.iterator(); iterator.hasNext(); ) {
			Task task = (Task) iterator.next();
			System.out.println("task = " + task.getName());

		}

		/**
		 * add() 添加元素时候并不会排序,这个是底层优化机制.
		 *
		 * 而调用take()的方法后才会进行里面内容排序
		 */
		System.out.println("===============");
		// 1 3 4
		System.out.println("容器：" + q); //输出: 容器：[1,id为1, 4,id为4, 3,id为3]
		System.out.println(q.take().getId()); //输出: 1
		System.out.println("容器：" + q);//输出:容器：[3,id为3, 4,id为4]
//		System.out.println(q.take().getId());
//		System.out.println(q.take().getId());


	}


}





