package com.uguraytekin;

import java.util.*;

/**
 * @Author: Ugur Aytekin
 * @create: 15.09.2022
 */
public class Node {
    private final int index;

    private List<Node> shortestPath = new LinkedList<>();

    private final Speed speed;

    private Integer numberOfJump = Integer.MAX_VALUE;

    Set<Node> adjacentNodes = new HashSet<>();

    private final int x;
    private final int y;

    public void addNextNode(Node destination) {
        adjacentNodes.add(destination);
    }

    public Node(int index, int x, int y, Speed speed) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Node(int index, int x, int y, Speed speed, int numberOfJump) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.numberOfJump = numberOfJump;
    }

    public int getIndex() {
        return index;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Integer getNumberOfJump() {
        return numberOfJump;
    }

    public void setNumberOfJump(Integer numberOfJump) {
        this.numberOfJump = numberOfJump;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
