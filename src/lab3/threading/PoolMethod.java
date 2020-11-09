package lab3.threading;

import lab3.workers.TaskSplittingMode;
import util.MatrixUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolMethod extends ThreadingMethod {
    private final ArrayList<ArrayList<Integer>> matrixA;
    private final ArrayList<ArrayList<Integer>> matrixB;
    private ArrayList<ArrayList<Integer>> resultMatrix;

    public PoolMethod(TaskSplittingMode mode) {
        super(mode);
        this.matrixA = MatrixUtil.generateRandomMatrix(SIZE);
        this.matrixB = MatrixUtil.generateRandomMatrix(SIZE);
        this.resultMatrix = MatrixUtil.generateEmptyMatrix(SIZE);
    }

    @Override
    public void compute() {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

        ArrayList<Runnable> taskList = this.workSplitter.getTasks(resultMatrix, matrixA, matrixB, THREAD_COUNT);

        for(Runnable task: taskList) {
            pool.execute(task);
        }

        pool.shutdown();

        System.out.println(matrixA);
        System.out.println("\n");
        System.out.println(matrixB);
        System.out.println("\n");
        System.out.println(resultMatrix);
        System.out.println("\n");
    }
}
