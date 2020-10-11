package code.DataBaseProject.designPatterns;

public class Singleton {

	//Volatile is used so that Singleton instance is synchronized across all the threads
	private volatile static Singleton uniqueInstance = null;

	private int data = 0;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			// We call Synchronized only for One time instead of calling everytime in double-checked locking
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

}
