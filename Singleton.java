
public class Singleton {
	private volatile static Singleton instance = null;
	
	private Singleton() {}
	
	public Singleton getInstance() {
		if(instance == null) {         //check 1
			synchronized(this) {       
				if(instance == null) {  //check 2
					instance = new Singleton();
				}
			}
		}
		return instance;
		
	}
}
