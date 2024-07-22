package com.zad;

import java.io.*;
import java.util.*;

public class SHO {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("SHO.IN"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("SHO.OUT"));

            int numberOfBlocks = Integer.parseInt(reader.readLine().trim());

            for (int blockIndex = 0; blockIndex < numberOfBlocks; blockIndex++) {
                String[] dimensions = reader.readLine().trim().split(" ");
                int rows = Integer.parseInt(dimensions[0]);
                int cols = Integer.parseInt(dimensions[1]);

                int[][] whitePositions = new int[cols][2];
                for (int col = 0; col < cols; col++) {
                    String[] pos = reader.readLine().trim().split(" ");
                    whitePositions[col][0] = Integer.parseInt(pos[0]) - 1;
                    whitePositions[col][1] = Integer.parseInt(pos[1]) - 1;
                }

                String result = findValidShotSeries(rows, cols, whitePositions);
                writer.write(result);
                writer.newLine();
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String findValidShotSeries(int rows, int cols, int[][] whitePositions) {
        int[] shots = new int[cols];
        boolean[] rowUsed = new boolean[rows];
        
        Arrays.fill(shots, -1);
        if (dfs(0, cols, whitePositions, shots, rowUsed)) {
            StringBuilder result = new StringBuilder();
            for (int shot : shots) {
                result.append(shot + 1).append(" ");
            }
            return result.toString().trim();
        } else {
            return "NO";
        }
    }

    private static boolean dfs(int col, int cols, int[][] whitePositions, int[] shots, boolean[] rowUsed) {
        if (col == cols) {
            return true;
        }
        for (int i = 0; i < 2; i++) {
            int row = whitePositions[col][i];
            if (!rowUsed[row]) {
                shots[col] = row;
                rowUsed[row] = true;
                if (dfs(col + 1, cols, whitePositions, shots, rowUsed)) {
                    return true;
                }
                rowUsed[row] = false;
            }
        }
        return false;
    }
}
