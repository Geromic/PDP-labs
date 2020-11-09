package util;

import java.util.ArrayList;
import java.util.Random;

public class MatrixUtil {
    public static ArrayList<ArrayList<Integer>> generateRandomMatrix(int size) {
        Random r = new Random();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        for(int i=0; i<size; i++) {
            ArrayList<Integer> row = new ArrayList<>();

            for(int j=0; j<size; j++) {
                row.add(r.nextInt(5));
            }

            matrix.add(row);
        }

        return matrix;
    }

    public static ArrayList<ArrayList<Integer>> generateEmptyMatrix(int size) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        for(int i=0; i<size; i++) {
            ArrayList<Integer> row = new ArrayList<>();

            for(int j=0; j<size; j++) {
                row.add(0);
            }

            matrix.add(row);
        }

        return matrix;
    }

    public static int getRowColMultiplication(ArrayList<Integer> rowMatrixA, ArrayList<Integer> colMatrixB) {
        int rez = 0;

        for(int i=0; i<rowMatrixA.size(); i++) {
            rez += rowMatrixA.get(i) * colMatrixB.get(i);
        }

        return rez;
    }

    public static ArrayList<Integer> getRow(ArrayList<ArrayList<Integer>> matrix, int index) {
        return matrix.get(index);
    }

    public static ArrayList<Integer> getCol(ArrayList<ArrayList<Integer>> matrix, int index) {
        ArrayList<Integer> col = new ArrayList<>();

        for (ArrayList<Integer> row : matrix) {
            col.add(row.get(index));
        }

        return col;
    }

    public static ArrayList<ArrayList<Integer>> matrixA() {
        ArrayList<ArrayList<Integer>> matrixA = new ArrayList<>();
        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(4);
        firstRow.add(4);
        firstRow.add(4);
        ArrayList<Integer> secondRow = new ArrayList<>();
        secondRow.add(4);
        secondRow.add(1);
        secondRow.add(0);
        ArrayList<Integer> thirdRow = new ArrayList<>();
        thirdRow.add(0);
        thirdRow.add(3);
        thirdRow.add(0);
        matrixA.add(firstRow);
        matrixA.add(secondRow);
        matrixA.add(thirdRow);
        return matrixA;
    }

    public static ArrayList<ArrayList<Integer>> matrixB() {
        ArrayList<ArrayList<Integer>> matrixA = new ArrayList<>();
        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(0);
        firstRow.add(4);
        firstRow.add(2);
        ArrayList<Integer> secondRow = new ArrayList<>();
        secondRow.add(2);
        secondRow.add(1);
        secondRow.add(2);
        ArrayList<Integer> thirdRow = new ArrayList<>();
        thirdRow.add(2);
        thirdRow.add(1);
        thirdRow.add(1);
        matrixA.add(firstRow);
        matrixA.add(secondRow);
        matrixA.add(thirdRow);
        return matrixA;
    }
}
