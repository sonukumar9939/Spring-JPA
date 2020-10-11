package code.DataBaseProject.designPatterns;

public class TestSingleton {
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		
		long startTime = System.currentTimeMillis();
		System.out.println(startTime);
		Singleton instance = Singleton.getInstance();
		instance.setData(99);
		
		System.out.println("Singleton Reference :"+ instance);
		System.out.println(instance.getData());
	    long endTime =System.currentTimeMillis();
	    
	    System.out.println(endTime-startTime);
		
		
		
//		instance=null;
//		instance = Singleton.getInstance();
//		
//		System.out.println("Singleton Reference :"+ instance);
//		
//		System.out.println(instance.getData());
		
	}

}
