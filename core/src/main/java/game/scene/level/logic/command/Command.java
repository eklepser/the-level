package game.scene.level.logic.command;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.execution.Executor;
import game.common.logic.entity.Entity;

public abstract class Command {
    protected int lineNum;

    public Command(int lineNum) {
        this.lineNum = lineNum;
    }

    public abstract void execute(Entity target);
    public abstract Image[] getIcons();

    public static Command from(int lineNum, String commandName, String[] args, Executor executor) {
        Instruction instruction = Instruction.from(commandName);
        Class<? extends Command> commandsClass = instruction.commandClass;
        try {
            try {
                Constructor<? extends Command> constructor = commandsClass.
                    getConstructor(int.class, String[].class, Executor.class);
                return constructor.newInstance(lineNum, args, executor);
            } catch (NoSuchMethodException ignored) { }

            try {
                Constructor<? extends Command> constructor = commandsClass.
                    getConstructor(int.class, String[].class);
                return constructor.newInstance(lineNum, args);
            } catch (NoSuchMethodException ignored) { }

            try {
                Constructor<? extends Command> constructor = commandsClass.
                    getConstructor(int.class, Executor.class);
                return constructor.newInstance(lineNum, executor);
            } catch (NoSuchMethodException ignored) { }

            try {
                Constructor<? extends Command> constructor = commandsClass.
                    getConstructor(int.class);
                return constructor.newInstance(lineNum);
            } catch (NoSuchMethodException ignored) { }

            throw new RuntimeException("No suitable constructor found for command: " + commandName);
        }
        catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate command: " + commandName, e);
        }
    }

    public int getLineNum() {
        return lineNum;
    }
}
