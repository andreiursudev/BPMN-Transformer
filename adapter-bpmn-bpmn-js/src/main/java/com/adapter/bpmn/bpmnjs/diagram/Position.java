package com.adapter.bpmn.bpmnjs.diagram;

public class Position {


    private int x;
    private int y;

    public Position(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x += 200;
    }

    public void incrementY() {
        y+= 100;

    }

    public void decrementY() {
        y-=100;
    }

    public void decrementX() {
        x-=200;
    }
}

