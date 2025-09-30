package com.eklepser.thelevel.logic.world.level;

import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.world.common.Configuration;

import java.util.List;

public final class LevelConfiguration extends Configuration {
    public int codeLinesNum;
    public List<Instruction> allowedInstructions;
}
