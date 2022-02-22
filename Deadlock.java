import java.util.*;

public class Deadlock   {
	static List<String> arr = new ArrayList<String>();
	static List<String> arr2 = new ArrayList<String>();
	
	static class writer1 extends Thread{
		
		public writer1() {
		}
		
		@Override
		public void run() {
			
			synchronized(arr) {
				try {
					for(int i = 0; i < 20; i++) {arr.add(i+"");}
					this.sleep(1000);
					System.out.println("Writer1 arr: " + arr.toString());
					synchronized(arr2) {
					for(int i = 0; i < 20; i++) {arr2.add(i + "");}	
					}
					System.out.println("Writer1 arr2: " + arr2.toString());
				}catch(InterruptedException e){e.printStackTrace();}
			}
		}
	}
	
	static class writer2 extends Thread{		
		public writer2(){
		}
		
		@Override
		public void run() {
			synchronized(arr2) {
				try {
					for(int i = 0; i < 20; i++) {arr2.add(i+"");}
					System.out.println("Writer2 arr2: " + arr2.toString());
					this.sleep(1000);
					synchronized(arr) {
					for(int i = 0; i < 20; i++) {arr.add(i + "");}
					System.out.println("Writer2 arr: " + arr.toString());
					}
				}catch(InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
	public static void main(String [] args) {
		
				writer1 w = new writer1();
				w.start();
				writer2 w2 = new writer2();
				w2.start();
			
		}
	
}
