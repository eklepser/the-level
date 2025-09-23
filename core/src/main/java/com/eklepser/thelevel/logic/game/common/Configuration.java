package com.eklepser.thelevel.logic.game.common;

import com.badlogic.gdx.math.Vector2;

public abstract class Configuration {
    private String name;
    private String mapPath;
    private float startPosX;
    private float startPosY;
    private int width;
    private int height;
    private float cameraZoom;

    public Configuration() { }

    public String getName() { return name; }

    public String getMapPath() { return mapPath; }

    public Vector2 getStartPos() { return new Vector2(startPosX, startPosY); }

    public Vector2 getSize() { return new Vector2(width, height); }

    public int width() { return width; }

    public int height() { return height; }

    public float getCameraZoom() { return cameraZoom; }
}
