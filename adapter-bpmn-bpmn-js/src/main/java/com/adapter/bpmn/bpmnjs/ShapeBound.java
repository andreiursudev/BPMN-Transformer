package com.adapter.bpmn.bpmnjs;

public class ShapeBound {

    private int x;
    private int y;
    private int height;
    private int width;

    public ShapeBound(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
