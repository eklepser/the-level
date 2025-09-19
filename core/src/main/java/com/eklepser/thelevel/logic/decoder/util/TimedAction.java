package com.eklepser.thelevel.logic.decoder.util;

import com.badlogic.gdx.scenes.scene2d.Action;

public class TimedAction extends Action {
    private final TimeController source;
    private float timeElapsed;

    public TimedAction(TimeController source) {
        this.source = source;
    }

    @Override
    public boolean act(float delta) {
        timeElapsed += delta;
        float currentDelay = source.getDelay();
        return timeElapsed >= currentDelay;
    }

    @Override
    public void restart() {
        timeElapsed = 0;
    }
}
