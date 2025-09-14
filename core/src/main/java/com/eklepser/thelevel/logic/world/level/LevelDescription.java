package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.math.Vector2;

public final class LevelDescription {
    private String mapPath;
    private int codeLinesNum;
    private String name;
    private float startPosX;
    private float startPosY;

    public LevelDescription() { }

    public Vector2 getStartPos() { return new Vector2(startPosX, startPosY); }

    public String mapPath() { return mapPath; }

    public int codeLinesNum() { return codeLinesNum; }

    public String name() { return name; }
}
