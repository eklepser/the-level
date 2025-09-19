package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.logic.decoder.condition.Condition;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Direction;

import java.util.Arrays;

public interface Command {
    void execute(Entity target);

    static Command from(String commandName, String[] args, Executor executor) {
        Instruction instruction = Instruction.from(commandName);
        return switch (instruction) {
            case MOVE -> new MoveCommand(Direction.getByName(args[0].toLowerCase()));
            case ROT -> new RotateCommand(Direction.getByName(args[0].toLowerCase()));
            case GOTO -> new GotoCommand(executor, Integer.parseInt(args[0]));
            case TP -> new TeleportCommand(new Vector2((
                Float.parseFloat(args[0])), Float.parseFloat(args[1])));
            case IF -> {
                String conditionName = args[0].toLowerCase();
                String conditionArg = args[1].toLowerCase();
                Condition condition = Condition.from(conditionName, conditionArg);

                String newInstruction = args[2];
                String[] newArgs = Arrays.copyOfRange(args, 3, args.length);
                Command conditionalCommand = Command.from(newInstruction, newArgs, executor);
                yield new IfCommand(condition, conditionalCommand, executor.getZones());
            }
            case END -> new EndCommand(executor);
            default -> new NoneCommand();
        };
    }
}
