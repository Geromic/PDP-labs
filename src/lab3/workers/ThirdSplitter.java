package lab3.workers;

import util.Pair;

import java.util.ArrayList;

public class ThirdSplitter extends BaseWorkSplitter {
    @Override
    public ArrayList<Runnable> getTasks(ArrayList<ArrayList<Integer>> resultMatrix, ArrayList<ArrayList<Integer>> matrixA, ArrayList<ArrayList<Integer>> matrixB, int thread_count) {
        ArrayList<ArrayList<Pair>> taskArrays = new ArrayList<>();

        int tasksPerThread = matrixA.size()*matrixA.size() / thread_count;
        int remainingTasks = matrixA.size()*matrixA.size() % thread_count;
        int currentElement = 0;



        for(int i=0; i<thread_count; i++) {
            ArrayList<Pair> currentTaskArray = new ArrayList<>();
            int startPos = i;

            int row = startPos / matrixA.size();
            int col = startPos % matrixA.size();

            while(isValid(row, col, matrixA.size())) {
                currentTaskArray.add(new Pair(row, col));
                startPos += thread_count;
                row = startPos / matrixA.size();
                col = startPos % matrixA.size();
            }

            taskArrays.add(currentTaskArray);
        }

        return generateRunnables(resultMatrix, taskArrays, matrixA, matrixB);
    }

    private boolean isValid(int row, int col, int size) {
        return row < size && col < size;
    }
}
