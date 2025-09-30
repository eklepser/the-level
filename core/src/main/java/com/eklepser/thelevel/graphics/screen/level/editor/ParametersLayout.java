package com.eklepser.thelevel.graphics.screen.level.editor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.util.Resources;

public class ParametersLayout extends TableLayout {
    private final Executor executor;
    private final TextLabel label;
    private final Slider slider;
    private final float[] values = {0.5f, 1.0f, 2.0f, 4.0f, 8.0f, 16.0f};
    private final int selected = 1;

    public ParametersLayout(Executor executor) {
        this.executor = executor;
        label = new TextLabel("Execution speed: " + values[selected]);
        slider = new Slider(0, values.length - 1, 1, false, Resources.getSkin());
        slider.setValue(selected);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float duration = values[(int) slider.getValue()];
                executor.setExecutionSpeed(1 / duration);
                label.setText("Execution speed: " + duration);
            }
        });

        setup();
    }

    @Override
    public void setup() {
        align(Align.left);
        add(label).padTop(10).left().row();
        add(slider).left();
    }

    // Class logic:
    public void setNextSliderValue() {
        slider.setValue(slider.getValue() - 1);
    }

    public void setPrevSliderValue() {
        slider.setValue(slider.getValue() + 1);
    }
}
