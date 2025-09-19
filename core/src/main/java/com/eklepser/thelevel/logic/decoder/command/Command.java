package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;

import java.lang.reflect.Constructor;

public abstract class Command {
    public abstract void execute(Entity target);

    public static Command from(String commandName, String[] args, Executor executor) {
        Instruction instruction = Instruction.from(commandName);
        Class<? extends Command> commandsClass = instruction.commandClass;
        try {
            try {
                Constructor<? extends Command> constructor = commandsClass.getConstructor(String[].class, Executor.class);
                return constructor.newInstance(args, executor);
            } catch (NoSuchMethodException ignored) { }

            try {
                Constructor<? extends Command> constructor = commandsClass.getConstructor(String[].class);
                return constructor.newInstance((Object) args);
            } catch (NoSuchMethodException ignored) { }

            try {
                Constructor<? extends Command> constructor = commandsClass.getConstructor(Executor.class);
                return constructor.newInstance(executor);
            } catch (NoSuchMethodException ignored) { }

            try {
                Constructor<? extends Command> constructor = commandsClass.getConstructor();
                return constructor.newInstance();
            } catch (NoSuchMethodException ignored) { }

            throw new RuntimeException("No suitable constructor found for command: " + commandName);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to instantiate command: " + commandName, e);
        }
    }
}
