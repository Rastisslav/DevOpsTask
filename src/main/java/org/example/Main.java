package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int path(int[][] cost, ArrayList<Integer> numbers)
    {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        int M = cost.length;
        int N = cost[0].length;

        int[][] T = new int[M][N];

        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                T[i][j] = cost[i][j];
                if (i == 0 && j > 0) {
                    T[0][j] += T[0][j - 1];
                }
                else if (j == 0 && i > 0) {
                    T[i][0] += T[i - 1][0];
                }
                else if (i > 0 && j > 0) {
                    T[i][j] += Integer.min(T[i - 1][j], T[i][j - 1]);
                }
            }
        }
        int x = M - 1,
            y = N - 1;
        numbers.add(cost[x][y]);
        while(x != 0 || y != 0)
        {
            if(x == 0)
            {while(y != 0)
            {
                numbers.add(cost[x][y-1]);
                y--;
            }
            }
                if(y == 0)
                {while(x != 0)
                {

                    numbers.add(cost[x-1][y]);
                    x--;
                }
            }
            if(x > 0 && y > 0){
                if (T[x - 1][y] <= T[x][y - 1]) {
                    numbers.add(cost[x - 1][y]);
                    x--;
                } else {
                    numbers.add(cost[x][y - 1]);
                    y--;
                }
            }
        }
        return T[M - 1][N - 1];
    }
    public static int[][] loadMatrix(int x,int y){

        int[][] matrix = new int[x][y];
        try {
            Scanner myReader = new Scanner(new File("res/matrix.txt"));

            int i = 0;
            while (myReader.hasNextLine())
            {

                String line = myReader.nextLine();
                String[] tokens = line.split(",");

                for (int j = 0; j < tokens.length; j++)
                {
                    matrix[i][j] = Integer.parseInt(tokens[j]);
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return matrix;
    }

    public static void main(String[] args)
    {
        int x=80,y=80;
        int[][] matrix = loadMatrix(x,y);

        ArrayList<Integer> pathNumbers = new ArrayList<>();

        System.out.println("The minimum cost is " + path(matrix,pathNumbers));

        System.out.print("Path: (");
        for(int n = pathNumbers.size()-1; n >= 0; n--)
        {
           System.out.print(pathNumbers.get(n));
           if(n!=0) System.out.print(", ");
        }
        System.out.print(")");
    }
}