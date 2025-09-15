package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.logic.decoder.command.Instruction;

import java.util.List;

public final class LevelConfiguration {
    private String name;
    private String mapPath;
    private int codeLinesNum;
    private List<Instruction> allowedInstructions;
    private float startPosX;
    private float startPosY;
    private int width;
    private int height;
    private float cameraZoom;

    public LevelConfiguration() { }

    public String name() { return name; }

    public int codeLinesNum() { return codeLinesNum; }

    public List<Instruction> getAllowedInstructions() { return allowedInstructions; }

    public Vector2 getStartPos() { return new Vector2(startPosX, startPosY); }

    public Vector2 getSize() { return new Vector2(width, height); }

    public String mapPath() { return mapPath; }

    public int width() { return width; }

    public int height() { return height; }

    public float getCameraZoom() { return cameraZoom; }
}
