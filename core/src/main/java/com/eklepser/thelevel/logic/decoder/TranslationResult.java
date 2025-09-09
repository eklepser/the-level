package com.eklepser.thelevel.logic.decoder;

import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;

import java.util.Map;

public record TranslationResult(boolean success, String message, Map<CodeLine, Command> codeMap) {
    public TranslationResult(boolean success, String message) {
        this(success, message, null);
    }

    public Map<CodeLine, Command> getCodeMap() {
        return codeMap;
    }
}
