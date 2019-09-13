package A_线程基础.moreThread1.MultiThreadCommunication.并发容器使用.UsePriorityBlockingQueue;

/**
 * 任务实体类, 必须implements Comparable 接口
 */
public class Task implements Comparable<Task> {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param task
	 * @return
	 */
	@Override
	public int compareTo(Task task) {
		return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
	}

	public String toString() {
		return this.id + "," + this.name;
	}

}
