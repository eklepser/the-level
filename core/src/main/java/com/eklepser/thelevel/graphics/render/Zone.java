package com.eklepser.thelevel.graphics.render;

public class Zone {
    public int id;
    public int x;
    public int y;
    public String type;

    public Zone() { }

    public Zone(int id, int x, int y, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
