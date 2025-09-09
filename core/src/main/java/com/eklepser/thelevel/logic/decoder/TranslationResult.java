package com.eklepser.thelevel.logic.decoder;

import java.util.List;

public record TranslationResult(boolean success, String message, List<Command> commands) {
    public TranslationResult(boolean success, String message) {
        this(success, message, null);
    }

    public List<Command> getCommands() {
        return commands;
    }
}
