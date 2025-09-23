package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.decoder.condition.Condition;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.interaction.collision.Entity;
import com.eklepser.thelevel.logic.game.level.zone.LevelZone;

import java.util.Arrays;
import java.util.List;

public class IfCommand extends Command {
    private final Condition condition;
    private final Command command;
    private final List<LevelZone> zones;

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

    @Override
    public Image[] getIcons(Entity target) {
        //Image commandImage = new Image(new Texture(Gdx.files.internal("ui/icon/if.png")));
        Image conditionImage = condition.getIcon();
        if (condition.check(target, zones)) {
            Image conditionCommandImage = command.getIcons(target)[0];
            return new Image[] {conditionImage, conditionCommandImage};
        }
        //commandImage.setColor(Color.DARK_GRAY);
        conditionImage.setColor(Color.DARK_GRAY);
        return new Image[] {conditionImage};
    }
}
