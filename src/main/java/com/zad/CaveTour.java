package com.zad;

import java.io.*;
import java.util.*;

public class CaveTour {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CAV.IN"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("CAV.OUT"));

            String[] firstLine = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int k = Integer.parseInt(firstLine[1]);

            Graph graph = new Graph(n);

            for (int i = 0; i < 3 * n / 2; i++) {
                String[] line = reader.readLine().trim().split(" ");
                int a = Integer.parseInt(line[0]) - 1;
                int b = Integer.parseInt(line[1]) - 1;
                int c = Integer.parseInt(line[2]);
                graph.addEdge(a, b, c);
            }
            reader.close();

            List<Integer> resultPath = graph.findOptimalPath();
            if (resultPath != null) {
                for (int i = 0; i < resultPath.size(); i++) {
                    writer.write((resultPath.get(i) + 1) + (i == resultPath.size() - 1 ? "" : " "));
                }
                writer.newLine();
            } else {
                writer.write("NO\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
