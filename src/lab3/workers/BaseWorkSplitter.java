package lab3.workers;

import util.MatrixUtil;
import util.Pair;

import java.util.ArrayList;

public class BaseWorkSplitter {
    public ArrayList<Runnable> getTasks(ArrayList<ArrayList<Integer>> resultMatrix, ArrayList<ArrayList<Integer>> matrixA, ArrayList<ArrayList<Integer>> matrixB, int thread_count) {
        return null;
    }

    protected ArrayList<Runnable> generateRunnables(ArrayList<ArrayList<Integer>> resultMatrix, ArrayList<ArrayList<Pair>> taskArrays, ArrayList<ArrayList<Integer>> matrixA, ArrayList<ArrayList<Integer>> matrixB) {
        ArrayList<Runnable> runnables = new ArrayList<>();
        for(ArrayList<Pair> task: taskArrays) {
            Runnable newRun = new Runnable() {
                @Override
                public void run() {
                    for(Pair p: task) {
                        int resultValue = MatrixUtil.getRowColMultiplication(MatrixUtil.getRow(matrixA, p.row), MatrixUtil.getCol(matrixB, p.col));
                        resultMatrix.get(p.row).set(p.col, resultValue);
                    }
                }
            };

            runnables.add(newRun);
        }

        return runnables;
    }
}
