package game.scene.level.logic.command;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.Direction;
import game.scene.level.logic.condition.Condition;
import game.scene.level.logic.condition.ConditionFactory;
import game.scene.level.logic.execution.Executor;

import java.util.Arrays;

public final class CommandFactory {
    private CommandFactory() { }

    public static Command command(int lineNum, String instructionName, String[] args, Executor executor) {
        Instruction instruction = Instruction.getByName(instructionName);
        if (instruction.equals(Instruction.MOVE)) {
            return moveCommand(lineNum, args);
        }
        if (instruction.equals(Instruction.ROT)) {
            return rotateCommand(lineNum, args);
        }
        if (instruction.equals(Instruction.GOTO)) {
            return gotoCommand(lineNum, args, executor);
        }
        if (instruction.equals(Instruction.IF)) {
            return ifCommand(lineNum, args, executor);
        }
        if (instruction.equals(Instruction.END)) {
            return new EndCommand(lineNum, executor);
        }
        return new NoneCommand(lineNum);
    }

    private static Command moveCommand(int lineNum, String[] args) {
        Direction direction = Direction.getByName(args[0].toLowerCase());
        return new MoveCommand(lineNum, direction);
    }

    private static Command rotateCommand(int lineNum, String[] args) {
        Direction direction = Direction.getByName(args[0]);
        return new RotateCommand(lineNum, direction);
    }

    private static Command gotoCommand(int lineNum, String[] args, Executor executor) {
        int targetLineNum = Integer.parseInt(args[0]);
        return new GotoCommand(lineNum, targetLineNum, executor);
    }

    private static Command ifCommand(int lineNum, String[] args, Executor executor) {
        Condition condition = ConditionFactory.condition(args[0].toLowerCase(), args[1].toLowerCase());

        String newInstruction = args[2];
        String[] newInstructionArgs = Arrays.copyOfRange(args, 3, args.length);
        Command innerCommand = CommandFactory.command(lineNum, newInstruction, newInstructionArgs, executor);

        return new IfCommand(lineNum, condition, innerCommand, executor);
    }
}
