package game.scene.level.logic.editor.command;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.editor.condition.Condition;
import game.scene.level.logic.editor.execution.Executor;
import game.common.logic.entity.Entity;
import game.scene.level.logic.Level;

import java.util.Arrays;

public class IfCommand extends Command {
    private final Condition condition;
    private final Command command;
    private final Level level;

    public IfCommand(String[] args, Executor executor) {
        String conditionName = args[0].toLowerCase();
        String conditionArg = args[1].toLowerCase();
        condition = Condition.from(conditionName, conditionArg);

        String newInstruction = args[2];
        String[] newArgs = Arrays.copyOfRange(args, 3, args.length);
        command = Command.from(newInstruction, newArgs, executor);

        level = executor.getEditor().getRoot().getScreen().getLevel();
    }

    @Override
    public void execute(Entity target) {
        System.out.println("IF");
        if (condition.check(target, level))
        {
            System.out.println("EXECUTING COND CMD");
            command.execute(target);
        }
    }

    @Override
    public Image[] getIcons(Entity target) {
        //Image commandImage = new Image(new Texture(Gdx.files.internal("ui/icon/if.png")));
        Image conditionImage = condition.getIcon();
        if (condition.check(target, level)) {
            Image conditionCommandImage = command.getIcons(target)[0];
            return new Image[] {conditionImage, conditionCommandImage};
        }
        //commandImage.setColor(Color.DARK_GRAY);
        conditionImage.setColor(Color.DARK_GRAY);
        return new Image[] {conditionImage};
    }
}
