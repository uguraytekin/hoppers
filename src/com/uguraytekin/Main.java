package com.uguraytekin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Author: Ugur Aytekin
 * @create: 15.09.2022
 */
public class Main {

    public static void main(String[] args) {
        for (Graph graph : read()) {
            System.out.println(graph.calculateShortestPath());
        }
    }

    static List<Graph> read() {
        Scanner input = new Scanner(System.in);
        int N; //number of test cases
        N = input.nextInt();
        input.nextLine();

        List<Graph> graphs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            //read board X Y
            List<Integer> board = Arrays.stream(input.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            if (board.get(0) < 0 || board.get(0) > 30 || board.get(1) < 0 || board.get(1) > 30) {
                System.out.println("Rule: X Y , (1 <= X <= 30) and (1 <= Y <= 30)");
                return graphs;
            }

            //read (x1,y1) start point , (x2,y2) end point
            List<Integer> points = Arrays.stream(input.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            if (points.get(0) < 0 || points.get(1) >= board.get(0) || points.get(2) < 0 || points.get(3) >= board.get(1)) {
                System.out.println("Rule: x1 y1 x2 y2 , 0 <= x1, x2 < X, 0 <= y1, y2 < Y");
                return graphs;
            }

            //P number of obstacle
            int P = input.nextInt();
            input.nextLine();

            //read obstacle list
            List<Obstacle> obstacleList = new ArrayList<>();
            for (int j = 0; j < P; j++) {
                List<Integer> obstacleInput = Arrays.stream(input.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                Obstacle obstacle = new Obstacle(obstacleInput.get(0), obstacleInput.get(1), obstacleInput.get(2), obstacleInput.get(3));
                obstacleList.add(obstacle);
                if (obstacle.x1 < 0 || obstacle.x1 > obstacle.x2 || obstacle.x2 >= board.get(0) ||
                        obstacle.y1 < 0 || obstacle.y1 > obstacle.y2 || obstacle.y2 >= board.get(0)) {
                    System.out.println("Rule: x1 x2 y1 y2 , (0 <= x1 <= x2 < X, 0 <= y1 <= y2 < Y)");
                    return graphs;
                }
            }

            Graph graph = new Graph(board.get(0), board.get(1), obstacleList, new Square(points.get(0), points.get(1)), new Square(points.get(2), points.get(3)));
            graphs.add(graph);
        }

        return graphs;
    }
}
