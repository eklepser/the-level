package game.scene.level.logic.editor.command;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.editor.execution.Executor;
import game.common.logic.entity.Entity;

public abstract class Command {
    public abstract void execute(Entity target);
    public abstract Image[] getIcons(Entity target);

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
        catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate command: " + commandName, e);
        }
    }
}
