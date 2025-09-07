package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;
import com.eklepser.thelevel.logic.Cat;
import com.eklepser.thelevel.logic.Direction;

import java.util.List;

public class Translator {
    static public void translateAll(List<CodeLine> codeLines, Cat cat, Actor actor) {
        System.out.println("Translating");
        SequenceAction sequence = Actions.sequence();
        for (CodeLine codeLine : codeLines) {

            sequence.addAction(Actions.delay(0.5f));
            sequence.addAction(Actions.run(() -> {
                if (codeLine.getText().equals("MOVE UP"))
                {
                    System.out.println("UP");
                    cat.move(Direction.UP);
                }
                if (codeLine.getText().equals("MOVE DOWN"))
                {
                    System.out.println("DOWN");
                    cat.move(Direction.DOWN);
                }
                if (codeLine.getText().equals("MOVE LEFT"))
                {
                    System.out.println("LEFT");
                    cat.move(Direction.LEFT);
                }
                if (codeLine.getText().equals("MOVE RIGHT"))
                {
                    System.out.println("RIGHT");
                    cat.move(Direction.RIGHT);
                }
            }));
        }
        actor.addAction(sequence);
    }

    static public Command translateCommand(CodeLine codeLine) {
        return new Command();
    }
}
