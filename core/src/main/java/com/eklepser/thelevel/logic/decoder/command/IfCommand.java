package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.decoder.condition.Condition;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.Arrays;
import java.util.List;

public class IfCommand extends Command {
    private final Condition condition;
    private final Command command;
    private final List<Zone> zones;

    public IfCommand(String[] args, Executor executor) {
        String conditionName = args[0].toLowerCase();
        String conditionArg = args[1].toLowerCase();
        this.condition = Condition.from(conditionName, conditionArg);
        String newInstruction = args[2];
        String[] newArgs = Arrays.copyOfRange(args, 3, args.length);
        this.command = Command.from(newInstruction, newArgs, executor);
        this.zones = executor.getZones();
    }

    @Override
    public void execute(Entity target) {
        System.out.println("IF");
        if (condition.check(target, zones))
        {
            System.out.println("EXECUTING COND CMD");
            command.execute(target);
        }
    }
}
