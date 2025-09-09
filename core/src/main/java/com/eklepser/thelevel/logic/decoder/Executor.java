package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;
import com.eklepser.thelevel.logic.Cat;
import com.eklepser.thelevel.logic.Direction;

import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final Translator translator;
    private final Actor gameField;
    private final List<CodeLine> codeLines;
    private final Cat target; // need remove and be taken from gamefield

    public Executor(List<CodeLine> codeLines, Actor gameField, Cat target) {
        this.codeLines = codeLines;
        this.gameField = gameField;
        this.target = target;
        translator = new Translator(codeLines, target, gameField);
    }

    public void executeAll(int start) {
        System.out.println("Running");
        gameField.clearActions();
        SequenceAction sequence = new SequenceAction();
        for (int i = start; i < codeLines.size(); i++) {
            CodeLine currentLine = codeLines.get(i);
            sequence.addAction(Actions.run(() -> currentLine.setCompleting(true)));
            sequence.addAction(Actions.delay(0.25f));
            sequence.addAction(Actions.run(() -> executeCommand(currentLine, target)));
            sequence.addAction(Actions.run(() -> currentLine.setCompleting(false)));
        }
        gameField.addAction(sequence);
    }

    private void executeCommand(CodeLine codeLine, Cat cat) {
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
                int lineNum = Integer.parseInt(parts[1]);
                System.out.println("GOTO " + lineNum);
                executeAll(lineNum - 1);
                codeLine.setCompleting(false);
            }
            else System.out.println("NONE");
        }
    }

    public String translateAll() {
        TranslationResult result = translator.translateAll();
        if (result.success()) {
            List<Command> commands = new ArrayList<>(result.getCommands());
            System.out.println(result.message());
            System.out.println("Commands count: " + commands.size());
            executeAll(0);
        }
        else {
            System.out.println(result.message());
        }
        return result.message();
    }
}
