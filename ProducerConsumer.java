import java.util.*;

public class ProducerConsumer {

    static class BoundedBuffer{
        private List<Integer> arr = new ArrayList<Integer>();
        int max;
        boolean isfull;
        boolean isEmpty;

        BoundedBuffer(int max){
            this.max = max;
        }

        synchronized void add(){
            System.out.println(arr.size());
            while(arr.size() == max) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            arr.add((int) Math.floor(Math.random()*100+1));
            System.out.println(arr.toString());
            notifyAll();
        }

        synchronized void remove() {
            while(arr.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            arr.remove(arr.size()-1);
            System.out.println(arr.toString());
            notifyAll();
        }
        boolean isFull(){
            return arr.size() == max;
        }
        boolean isEmpty(){
            return arr.isEmpty();
        }
    }

    static BoundedBuffer bb = new BoundedBuffer(10);
    static class producer extends Thread{
        @Override
        public void run(){
            for(int i = 0; i < 20; i++) {
                bb.add();
            }
        }
    }
    static class consumer extends Thread{
        @Override
        public void run(){
            for(int i = 0; i < 10; i++) {
                bb.remove();
            }
        }
    }

    public static void main(String[] args) {
        int max;
        producer p = new producer();
        p.start();
        consumer c = new consumer();
        c.start();
    }


}
