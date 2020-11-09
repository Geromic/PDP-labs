package lab3.workers;

import util.Pair;

import java.util.ArrayList;

public class FirstSplitter extends BaseWorkSplitter {
    @Override
    public ArrayList<Runnable> getTasks(ArrayList<ArrayList<Integer>> resultMatrix, ArrayList<ArrayList<Integer>> matrixA, ArrayList<ArrayList<Integer>> matrixB, int thread_count) {
        ArrayList<ArrayList<Pair>> taskArrays = new ArrayList<>();

        int tasksPerThread = matrixA.size()*matrixA.size() / thread_count;
        int remainingTasks = matrixA.size()*matrixA.size() % thread_count;
        int currentElement = 0;



        for(int i=0; i<thread_count; i++) {
            ArrayList<Pair> currentTaskArray = new ArrayList<>();

            for(int j=0; j<tasksPerThread; j++) {
                int row = currentElement / matrixA.size();
                int col = currentElement % matrixA.size();
                currentTaskArray.add(new Pair(row, col));
                currentElement++;
            }

            if(i == thread_count-1) {
                for(int j=0; j<remainingTasks; j++) {
                    int row = currentElement / matrixA.size();
                    int col = currentElement % matrixA.size();
                    currentTaskArray.add(new Pair(row, col));
                    currentElement++;
                }
            }

            taskArrays.add(currentTaskArray);
        }

        return generateRunnables(resultMatrix, taskArrays, matrixA, matrixB);
    }
}
