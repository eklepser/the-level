package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;
import com.eklepser.thelevel.logic.Cat;
import com.eklepser.thelevel.logic.Direction;

import java.util.List;

public class Translator {
    public static void translateAll(List<CodeLine> codeLines, Cat cat, Actor actor) {
        System.out.println("Translating");
        runCommands(codeLines, cat, actor, 0);
    }

    private static void runCommands(List<CodeLine> codeLines, Cat cat, Actor actor, int start) {
        SequenceAction sequence = new SequenceAction();
        for (int i = start; i < codeLines.size(); i++) {
            int finalI = i;
            sequence.addAction(Actions.run(() -> executeCommand(codeLines.get(finalI), cat, codeLines, actor)));
            sequence.addAction(Actions.run(() -> codeLines.get(finalI).setCompleting(true)));
            sequence.addAction(Actions.delay(0.25f));
            sequence.addAction(Actions.run(() -> codeLines.get(finalI).setCompleting(false)));
        }
        actor.clearActions();
        actor.addAction(sequence);
    }

    private static void executeCommand(CodeLine codeLine, Cat cat, List<CodeLine> codeLines, Actor actor) {
        {
            String text = codeLine.getText();
            String[] parts = text.split("\\s+");
            if (parts[0].equals("MOVE")) {
                switch (parts[1]) {
                    case "UP":
                        System.out.println("UP");
                        cat.move(Direction.UP);
                        break;
                    case "RIGHT":
                        System.out.println("RIGHT");
                        cat.move(Direction.RIGHT);
                        break;
                    case "DOWN":
                        System.out.println("DOWN");
                        cat.move(Direction.DOWN);
                        break;
                    case "LEFT":
                        System.out.println("LEFT");
                        cat.move(Direction.LEFT);
                        break;
                }
            }
            else if (parts[0].equals("GOTO")) {
                int start = Integer.parseInt(parts[1]) - 1;
                runCommands(codeLines, cat, actor, start);
            }
        }
    }
}
