package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.graphics.ui.game.editor.CodeLine;
import com.eklepser.thelevel.logic.decoder.commands.*;
import com.eklepser.thelevel.util.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator {
    private final Executor executor;
    private final List<CodeLine> codeLines;
    private final List<Instruction> allowedInstructions;

    public Translator(List<Instruction> allowedInstructions, List<CodeLine> codeLines, Executor executor) {
        this.executor = executor;
        this.codeLines = codeLines;
        this.allowedInstructions = allowedInstructions;
    }

    public TranslationResult translateAll() {
        List<String> allowedInstructionNames = allowedInstructions.stream()
            .map(instruction -> instruction.name).toList();

        Map<CodeLine, Command> codeMap = new HashMap<>();
        for (int i = 0; i < codeLines.size(); i++) {
            CodeLine currentLine = codeLines.get(i);
            codeMap.put(currentLine, null);
            String text = currentLine.getText();

            // Empty line and commentary check:
            if (text.isBlank()) continue;
            if (text.startsWith(";")) continue;
            if (text.contains(";")) {
                String[] commentarySplited = text.split(";");
                if (commentarySplited.length > 1) {
                    text = commentarySplited[0];
                }
            }

            // Instruction existing check:
            String[] words = text.split("\\s+");
            String instructionName = words[0].toLowerCase();
            Instruction instruction;
            if (allowedInstructionNames.contains(instructionName)) {
                instruction = Instruction.fromName(instructionName);
            }
            else {
                String message = String.format("LINE %d: INSTRUCTION %s IS NOT ALLOWED", i + 1, instructionName);
                return new TranslationResult(false, message);
            }

            // Instruction format check:
            if (instruction.expectedArgs != words.length - 1) {
                String message = String.format("LINE %d: INSTRUCTION FORMAT IS NOT CORRECTED", i + 1);
                return new TranslationResult(false, message);
            }

            // Instruction arguments check:
            Command cmd = null;
            switch (instruction) {
                case MOVE:
                    String directionName = words[1].toLowerCase();
                    if (!instruction.allowedArgs.contains(directionName)) {
                        String message = String.format("LINE %d: ARGUMENT %s IS NOT ALLOWED", i + 1, words[1]);
                        return new TranslationResult(false, message);
                    }
                    else {
                        cmd = new MoveCmd(instruction, Direction.getByName(directionName));
                    }
                    break;
                case ROT:
                    String rotationDirection = words[1].toLowerCase();
                    if (!instruction.allowedArgs.contains(rotationDirection)) {
                        String message = String.format("LINE %d: ARGUMENT %s IS NOT ALLOWED", i + 1, words[1]);
                        return new TranslationResult(false, message);
                    }
                    else {
                        cmd = new RotateCmd(instruction, Direction.getByName(rotationDirection));
                    }
                    break;
                case GOTO:
                    try {
                        int lineNum = Integer.parseInt(words[1]);
                        if ((lineNum < 1) || (lineNum > codeLines.size())) {
                            String message = String.format("LINE %d: ARGUMENT %d OUT OF RANGE", i + 1, lineNum);
                            return new TranslationResult(false, message);
                        }
                        else {
                            cmd = new GotoCmd(instruction, executor, lineNum);
                        }
                    }
                    catch (NumberFormatException e) {
                        String message = String.format("LINE %d: ARGUMENT %s IS NOT ALLOWED", i + 1, words[1]);
                        return new TranslationResult(false, message);
                    }
                    break;
                case TP:
                    try {
                        int posX = Integer.parseInt(words[1]);
                        int posY = Integer.parseInt(words[2]);
                        cmd = new TeleportCmd(instruction, new Vector2(posX, posY));
                    }
                    catch (NumberFormatException e) {
                        String message = String.format("LINE %d: ARGUMENT %s IS NOT ALLOWED", i + 1, words[1]);
                        return new TranslationResult(false, message);
                    }
                    break;
                case NONE:
                    cmd = new NoneCmd(instruction);
            }
            // Adding command-object to code map
            codeMap.put(currentLine, cmd);
        }
        return new TranslationResult(true, "TRANSLATION: SUCCESS", codeMap);
    }
}
