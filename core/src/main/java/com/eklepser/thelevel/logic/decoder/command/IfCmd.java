package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.decoder.condition.Condition;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.List;

public class IfCmd extends Command {
    private final Condition condition;
    private final Command command;
    private final List<Zone> zones;

    public IfCmd(Condition condition, Command command, List<Zone> zones) {
        this.condition = condition;
        this.command = command;
        this.zones = zones;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("IF");
        if (condition.check(target, zones))
            command.execute(target);
    }
}
