package com.eklepser.thelevel.logic.world.level;

import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.util.Resources;

import java.awt.*;
import java.util.List;

public final class LevelConfiguration extends Configuration {
    public int codeLinesNum;
    public List<Instruction> allowedInstructions;
}
