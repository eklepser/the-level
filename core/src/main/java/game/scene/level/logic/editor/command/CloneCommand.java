package game.scene.level.logic.editor.command;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.editor.execution.Executor;
import game.scene.common.logic.entity.Entity;
import game.scene.common.logic.Direction;

import java.util.List;

public class CloneCommand extends Command {
    private final Direction direction;
    private final Executor executor;
    private final List<Entity> entities;

    public CloneCommand(String[] args, Executor executor) {
        direction = Direction.getByName(args[0]);
        this.executor = executor;
        entities = executor.getEditor().getLevel().getEntitiesToAdd();
    }

    @Override
    public void execute(Entity target) {
        System.out.println("CLONE");
        //Entity newEntity = new Entity(target.getWorldPos().cpy(), Layout.TILE_SIZE, "world/entity/target.png");
//        entities.add(newEntity);
//        newEntity.setMoving(direction);
//        SequenceAction action = executor.createCommandAction(executor.getCurrentLineNum() + 1, executor.getCodeMap(), newEntity);
//        newEntity.addAction(action);
    }

    @Override
    public Image[] getIcons(Entity target) {
        return null;
    }
}
