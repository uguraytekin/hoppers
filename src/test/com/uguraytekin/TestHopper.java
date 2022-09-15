package com.uguraytekin;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: Ugur Aytekin
 * @create: 15.09.2022
 */
public class TestHopper {


    @Test
    public void test1() {

        List<Obstacle> obstacleList = Collections.singletonList(new Obstacle(1, 4, 2, 3));

        Graph graph = new Graph(5, 5, obstacleList, new Square(4, 0), new Square(4, 4));

        assertEquals("Optimal solution takes 7 hops.", graph.calculateShortestPath());
    }

    @Test
    public void test2() {

        List<Obstacle> obstacleList = Arrays.asList(
                new Obstacle(1, 1, 0, 2),
                new Obstacle(0, 2, 1, 1));

        Graph graph = new Graph(3, 3, obstacleList, new Square(0, 0), new Square(2, 2));

        assertEquals("No solution.", graph.calculateShortestPath());
    }
}
