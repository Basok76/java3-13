package org.example.Glava2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class C {
    public static void One(int[][] matrix, int k) {
        Arrays.sort(matrix, Comparator.comparingInt(row -> row[k]));

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void Two(int[][] matrix, int k, String direction) {
        shiftMatrix(matrix, k, direction);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void shiftMatrix(int[][] matrix, int k, String direction) {
        int rows = matrix.length, cols = matrix[0].length;
        int[] temp = new int[rows * cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                temp[(i * cols + j + getShift(k, direction, rows, cols)) % (rows * cols)] = matrix[i][j];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = temp[i * cols + j];
    }

    static int getShift(int k, String direction, int rows, int cols) {
        return switch (direction) {
            case "right" -> k;
            case "left" -> -k;
            case "down" -> k * cols;
            case "up" -> -k * cols;
            default -> 0;
        };
    }

    public static void Three(int[][] matrix) {
        System.out.println("Max increasing sequence: " + findMaxSequence(matrix, true));
        System.out.println("Max decreasing sequence: " + findMaxSequence(matrix, false));
    }

    static int findMaxSequence(int[][] matrix, boolean increasing) {
        int maxLen = 0, currLen = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if ((increasing && matrix[i][j] > matrix[i][j - 1]) || (!increasing && matrix[i][j] < matrix[i][j - 1])) {
                    currLen++;
                } else {
                    maxLen = Math.max(maxLen, currLen);
                    currLen = 1;
                }
            }
            maxLen = Math.max(maxLen, currLen);
            currLen = 1;
        }
        return maxLen;
    }

    public static void Four(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            int first = -1, second = -1;
            for (int j = 0; j < row.length; j++) {
                if (row[j] > 0) {
                    if (first == -1) first = j;
                    else {
                        second = j;
                        break;
                    }
                }
            }
            if (first != -1 && second != -1)
                sum += Arrays.stream(row, first + 1, second).sum();
        }
        System.out.println(sum);
    }

    public static void Five(int[][] matrix, int N, int k) {
        for (int i = 0; i < N * N && i < k; i++) {
            matrix[i / N][i % N] = i + 1;
        }

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void Six(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.round(matrix[i][j]);
            }
        }

        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void Seven(int[][] matrix, int degrees) {
        int N = matrix.length;
        int rotations = (degrees / 90) % 4;

        for (int r = 0; r < rotations; r++) {
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    temp[N - j - 1][i] = matrix[i][j];
            for (int i = 0; i < N; i++)
                matrix[i] = Arrays.copyOf(temp[i], N);
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int Eight(int[][] matrix) {
        int N = matrix.length;
        if (N == 1) return matrix[0][0];

        int det = 0;
        for (int j = 0; j < N; j++) {
            int sign = (j % 2 == 0) ? 1 : -1;
            int[][] minor = new int[N - 1][N - 1];

            for (int i = 1; i < N; i++) {
                for (int k = 0, col = 0; k < N; k++) {
                    if (k == j) continue;
                    minor[i - 1][col++] = matrix[i][k];
                }
            }

            det += sign * matrix[0][j] * Eight(minor);
        }

        return det;
    }

    public static void Nine(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            double mean = sum / matrix[i].length;

            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] -= mean;
            }
        }
        printMatrix(matrix);
    }

//    public static void Ten(int[][] matrix) {
//        int N = matrix.length;
//        int M = matrix[0].length;
//
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                max = Math.max(max, matrix[i][j]);
//            }
//        }
//
//        boolean[] deleteRows = new boolean[N];
//        boolean[] deleteCols = new boolean[M];
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (matrix[i][j] == max) {
//                    deleteRows[i] = true;
//                    deleteCols[j] = true;
//                }
//            }
//        }
//
//        int newN = N - (int) Arrays.stream(deleteRows).filter(b -> b).count();
//        int newM = M - (int) Arrays.stream(deleteCols).filter(b -> b).count();
//        int[][] newMatrix = new int[newN][newM];
//
//        for (int i = 0, ni = 0; i < N; i++) {
//            if (deleteRows[i]) continue;
//            for (int j = 0, nj = 0; j < M; j++) {
//                if (deleteCols[j]) continue;
//                newMatrix[ni][nj++] = matrix[i][j];
//            }
//            ni++;
//        }
//
//        for (int i = 0; i < newN; i++) {
//            matrix[i] = Arrays.copyOf(newMatrix[i], newM);
//        }
//    }

    private static void Ten(int[][] matrix) {
        int n = matrix.length;
        int maxElement = Integer.MIN_VALUE;
        int maxRow = -1;
        int maxCol = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > maxElement) {
                    maxElement = matrix[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        int newRows = n - 1;
        int newCols = n - 1;
        int[][] result = new int[newRows][newCols];

        for (int i = 0, newRow = 0; i < n; i++) {
            if (i == maxRow) continue;
            for (int j = 0, newCol = 0; j < n; j++) {
                if (j == maxCol) continue;
                result[newRow][newCol++] = matrix[i][j];
            }
            newRow++;
        }

        printMatrix(result);
    }

    public static void Eleven(int[][] matrix) {
        boolean[] rowsToKeep = new boolean[matrix.length];
        boolean[] colsToKeep = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    rowsToKeep[i] = true;
                    colsToKeep[j] = true;
                }
            }
        }

        int newRowCount = 0;
        for (boolean row : rowsToKeep) {
            if (row) newRowCount++;
        }

        int newColCount = 0;
        for (boolean col : colsToKeep) {
            if (col) newColCount++;
        }
        int[][] compressedMatrix = new int[newRowCount][newColCount];
        int newRow = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (rowsToKeep[i]) {
                int newCol = 0;
                for (int j = 0; j < matrix[i].length; j++) {
                    if (colsToKeep[j]) {
                        compressedMatrix[newRow][newCol++] = matrix[i][j];
                    }
                }
                newRow++;
            }
        }

        printMatrix(compressedMatrix);
    }

    public static void Twelve(int[][] matrix, int targetRow, int targetCol) {
        int minValue = Integer.MAX_VALUE;
        int minRow = -1, minCol = -1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < minValue) {
                    minValue = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }

        if (minRow != targetRow) {

            int[] tempRow = matrix[minRow];
            matrix[minRow] = matrix[targetRow];
            matrix[targetRow] = tempRow;
        }

        if (minCol != targetCol) {
            for (int i = 0; i < matrix.length; i++) {
                int temp = matrix[i][minCol];
                matrix[i][minCol] = matrix[i][targetCol];
                matrix[i][targetCol] = temp;
            }
        }
        printMatrix(matrix);
    }

    public static void Thirteen(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int nonZeroIndex = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {

                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][nonZeroIndex];
                    matrix[i][nonZeroIndex] = temp;
                    nonZeroIndex++;
                }
            }
        }
        printMatrix(matrix);
    }

    public static int Fourteen(int[][] matrix) {
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            int rowMin = Integer.MAX_VALUE;
            int colIndexOfMin = -1;

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                    colIndexOfMin = j;
                }
            }

            boolean isMaxInCol = true;
            for (int k = 0; k < matrix.length; k++) {
                if (matrix[k][colIndexOfMin] > rowMin) {
                    isMaxInCol = false;
                    break;
                }
            }

            if (isMaxInCol) {
                count++;
            }
        }

        return count;
    }

    public static void Fiveteen(int[][] matrix) {

        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] row1, int[] row2) {
                int sum1 = Arrays.stream(row1).sum();
                int sum2 = Arrays.stream(row2).sum();
                return Integer.compare(sum1, sum2);
            }
        });
        printMatrix(matrix);
    }

    public static int Sixteen(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        int[] directions = {-1, 0, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isLocalMin = true;

                for (int di : directions) {
                    for (int dj : directions) {
                        if (di == 0 && dj == 0) continue;

                        int ni = i + di;
                        int nj = j + dj;

                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {

                            if (matrix[ni][nj] <= matrix[i][j]) {
                                isLocalMin = false;
                                break;
                            }
                        }
                    }
                    if (!isLocalMin) break;
                }

                if (isLocalMin) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int Seventeen(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int smallestLocalMax = Integer.MAX_VALUE;

        int[] directions = {-1, 0, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isLocalMax = true;

                for (int di : directions) {
                    for (int dj : directions) {
                        if (di == 0 && dj == 0) continue;
                        int ni = i + di;
                        int nj = j + dj;
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                            if (matrix[ni][nj] >= matrix[i][j]) {
                                isLocalMax = false;
                                break;
                            }
                        }
                    }
                    if (!isLocalMax) break;
                }

                if (isLocalMax) {
                    smallestLocalMax = Math.min(smallestLocalMax, matrix[i][j]);
                }
            }
        }

        return smallestLocalMax;
    }

    public static void Eighteen(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] columnSums = new int[cols];

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                columnSums[j] += Math.abs(matrix[i][j]);
            }
        }

        Integer[] columnIndices = new Integer[cols];
        for (int i = 0; i < cols; i++) {
            columnIndices[i] = i;
        }

        Arrays.sort(columnIndices, new Comparator<Integer>() {
            @Override
            public int compare(Integer col1, Integer col2) {
                return Integer.compare(columnSums[col2], columnSums[col1]);
            }
        });

        int[][] rearrangedMatrix = new int[rows][cols];
        for (int i = 0; i < cols; i++) {
            int originalColumn = columnIndices[i];
            for (int j = 0; j < rows; j++) {
                rearrangedMatrix[j][i] = matrix[j][originalColumn];
            }
        }

        printMatrix(rearrangedMatrix);
    }
    private static void Nineteen(int[][] matrix) {
        int n = matrix.length;
        ArrayList<Integer> elements = new ArrayList<>();

        for (int[] row : matrix) {
            for (int element : row) {
                elements.add(element);
            }
        }

        elements.sort(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            matrix[i][i] = elements.get(i);
        }

        int idx = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matrix[i][j] = elements.get(idx++);
                }
            }
        }
        printMatrix(matrix);
    }

    public static void main(String[] args) {
            int[][] matrix = {
                    {1,2,3},
                    {3,4,5},
                    {6,2,3}
            };
            double[][] doubleMatrix = {
                    {2.2,3.2,4.4},
                    {3.4,5.5,6.7},
                    {1.1,2.7,4.5}
            };

            int k = 1;
            int N = 3;
            String direction = "right";

            One(matrix,k);
            Two(matrix,k,direction);
            Three(matrix);
            Four(matrix);
            Five(matrix,N,k);
            Six(doubleMatrix);
            Seven(matrix,90);
            int Eight = Eight(matrix);
            System.out.println(Eight);
            Nine(matrix);
            Ten(matrix);
            Eleven(matrix);
            Twelve(matrix,2,1);
            Thirteen(matrix);
            int fourteen = Fourteen(matrix);
            System.out.println(fourteen);
            Fiveteen(matrix);
            int sixteen = Sixteen(matrix);
            System.out.println(sixteen);
            int seventeen = Seventeen(matrix);
            System.out.println(seventeen);
            Eighteen(matrix);
            Nineteen(matrix);


    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

