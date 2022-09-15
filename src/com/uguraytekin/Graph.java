package com.uguraytekin;

import java.util.*;

/**
 * @Author: Ugur Aytekin
 * @create: 15.09.2022
 */
public class Graph {
    private final int boardX;
    private final int boardY;
    private final Square dest;
    private final List<Obstacle> obstacleList;
    private static final List<Integer> directions = Arrays.asList(0, 1, -1);
    private final Node startNode;

    public Graph(int boardX, int boardY, List<Obstacle> obstacleList, Square source,  Square dest) {
        this.boardX = boardX;
        this.boardY = boardY;
        this.obstacleList = obstacleList;
        this.dest = dest;
        startNode = new Node(coordinateToNodeIndex(source.getX(), source.getY()), source.getX(), source.getY(), new Speed(0, 0), 0);
    }

    // Level BFS function to find minimum path
    // from source to sink
    public String calculateShortestPath() {
        int destNodeIndex = coordinateToNodeIndex(dest.getX(), dest.getY());

        // Create a HashSet for BFS
        Set<Node> unsettledNodes = new HashSet<>();

        //add the startNode to list;
        unsettledNodes.add(startNode);

        while (unsettledNodes.size() != 0) {
            //find lowest distance node in unsettledNodes list
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            //If the current node is the destination, the closest distance is found.
            if (currentNode.getIndex() == destNodeIndex) {
                printShortestPath(currentNode);
                return "Optimal solution takes " + currentNode.getNumberOfJump() + " hops.";
            }

            //looking for squares that can be jumped on.
            for (Speed speed : getListOfAccessibleSpeeds(currentNode.getSpeed())) {

                //skip (0,0) speed
                if (speed.getX() == 0 && speed.getY() == 0) continue;

                //add speed to coordinates
                int newX = currentNode.getX() + speed.getX();
                int newY = currentNode.getY() + speed.getY();

                //check if the new square is safe
                if (isSafe(newX, newY)) {
                    //create new node and add to current node
                    Node nextNode = new Node(coordinateToNodeIndex(newX, newY), newX, newY, speed);
                    currentNode.addNextNode(nextNode);

                    //calculate number of jump of new node
                    calculateNumberOfJump(nextNode, currentNode);

                    //add the nextNode to list;
                    unsettledNodes.add(nextNode);
                }
            }
        }
        return "No solution.";
    }

    private void printShortestPath(Node currentNode) {
        for (Node parentNode : currentNode.getShortestPath()) {
            System.out.print(parentNode.getIndex() + "(" + parentNode.getSpeed().getX() + "," + parentNode.getSpeed().getY() + ")" + " --> ");
        }
        System.out.println(currentNode.getIndex() + "(" + currentNode.getSpeed().getX() + "," + currentNode.getSpeed().getY() + ")" + " distance: " + currentNode.getNumberOfJump());
    }


    //check whether the coordinates are in safe area or obstacle
    private boolean isSafe(int x, int y) {
        return (x >= 0 && x < boardX) && (y >= 0 && y < boardY) && !isObstacle(x, y);
    }

    //get List of Accessible Speeds
    private List<Speed> getListOfAccessibleSpeeds(Speed speed) {

        List<Speed> list = new ArrayList<>();
        for (int dx : directions) {
            for (int dy : directions) {
                list.add(new Speed(speed.getX() + dx, speed.getY() + dy));
            }
        }
        return list;
    }

    private int coordinateToNodeIndex(int x, int y) {
        return (x + 1) + (y * boardX);
    }

    private boolean isObstacle(int x, int y) {
        for (Obstacle obs : obstacleList) {
            if (x >= obs.x1 && x <= obs.x2 && y >= obs.y1 && y <= obs.y2) {
                return true;
            }
        }
        return false;
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getNumberOfJump();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateNumberOfJump(Node nextNode, Node sourceNode) {
        Integer sourceDistance = sourceNode.getNumberOfJump();
        if (sourceDistance + 1 < nextNode.getNumberOfJump()) {
            nextNode.setNumberOfJump(sourceDistance + 1);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            nextNode.setShortestPath(shortestPath);
        }
    }
}
