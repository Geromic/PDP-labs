package lab3;

import lab3.threading.PoolMethod;
import lab3.threading.ThreadsMethod;
import lab3.workers.TaskSplittingMode;

public class Main {
    public static void main(String[] argv) {
        PoolMethod firstPool = new PoolMethod(TaskSplittingMode.FIRST);
        PoolMethod secondPool = new PoolMethod(TaskSplittingMode.SECOND);
        PoolMethod thirdPool = new PoolMethod(TaskSplittingMode.THIRD);

        ThreadsMethod firstThreads = new ThreadsMethod(TaskSplittingMode.FIRST);
        ThreadsMethod secondThreads = new ThreadsMethod(TaskSplittingMode.SECOND);
        ThreadsMethod thirdThreads = new ThreadsMethod(TaskSplittingMode.THIRD);


        long start_time = System.nanoTime();

        //-------------------- insert computation here

        //firstPool.compute();
        //secondPool.compute();
        //thirdPool.compute();

        //firstThreads.compute();
        //secondThreads.compute();
        thirdThreads.compute();

        //--------------------

        long end_time = System.nanoTime();
        double difference = (end_time - start_time) / 1e6;

        System.out.println("Compute time: " + difference + "s");
    }
}
