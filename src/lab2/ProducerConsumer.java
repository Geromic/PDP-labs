package lab2;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumer {
    private final LinkedList<Integer> sharedList;
    private final Integer capacity;
    private Integer sum;

    public ProducerConsumer() {
        this.sharedList = new LinkedList<>();
        this.capacity = 2;
        this.sum = 0;
    }

    public void produce() throws InterruptedException {
        while(true) {
            synchronized (this) {
                while(sharedList.size() == capacity) {
                    System.out.println("Blocked, waiting for consumer.");
                    wait();
                }

                Random r = new Random();
                int randomNr1 = r.nextInt(15) + 1;
                int randomNr2 = r.nextInt(10) + 1;

                sharedList.add(randomNr1 * randomNr2);

                System.out.println("Producer thread: produced " + randomNr1*randomNr2);

                notify();

                Thread.sleep(200);
            }
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            synchronized (this) {
                while(sharedList.isEmpty()) {
                    System.out.println("Blocked, waiting for producer.");
                    wait();
                }

                int current = this.sharedList.removeFirst();
                this.sum += current;

                System.out.println("Consumer thread: consumed " + current + "; current sum is " + sum);

                notify();

                Thread.sleep(200);
            }
        }
    }
}
