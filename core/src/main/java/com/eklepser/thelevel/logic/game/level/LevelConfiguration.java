package com.eklepser.thelevel.logic.game.level;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.game.common.Configuration;

import java.util.List;

public final class LevelConfiguration extends Configuration {
    private int codeLinesNum;
    private List<Instruction> allowedInstructions;
    private float startPosX;
    private float startPosY;
    private int width;
    private int height;
    private float cameraZoom;

    public int codeLinesNum() { return codeLinesNum; }

    public List<Instruction> getAllowedInstructions() { return allowedInstructions; }

    public Vector2 getStartPos() { return new Vector2(startPosX, startPosY); }

    public Vector2 getSize() { return new Vector2(width, height); }

    public int width() { return width; }

    public int height() { return height; }

    public float getCameraZoom() { return cameraZoom; }
}
