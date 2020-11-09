package lab3.threading;

import lab3.workers.TaskSplittingMode;
import util.MatrixUtil;

import java.util.ArrayList;

public class ThreadsMethod extends ThreadingMethod{
    private final ArrayList<ArrayList<Integer>> matrixA;
    private final ArrayList<ArrayList<Integer>> matrixB;
    private ArrayList<ArrayList<Integer>> resultMatrix;

    public ThreadsMethod(TaskSplittingMode mode) {
        super(mode);
        this.matrixA = MatrixUtil.generateRandomMatrix(SIZE);
        this.matrixB = MatrixUtil.generateRandomMatrix(SIZE);
        this.resultMatrix = MatrixUtil.generateEmptyMatrix(SIZE);
    }

    @Override
    public void compute() {
        ArrayList<Thread> threads = new ArrayList<>();

        ArrayList<Runnable> tasks = workSplitter.getTasks(resultMatrix, matrixA, matrixB, THREAD_COUNT);

        for(Runnable task: tasks) {
            threads.add(new Thread(task));
        }

        for(Thread thread: threads) {
            thread.start();
        }

        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(matrixA);
        System.out.println("\n");
        System.out.println(matrixB);
        System.out.println("\n");
        System.out.println(resultMatrix);
        System.out.println("\n");
    }
}
