package com.eklepser.thelevel.graphics.ui.editor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.util.Resources;

public class ParametersTable extends Table {
    private final Executor executor;
    private final Slider slider;
    private final TextLabel label;

    public ParametersTable(Executor executor) {
        this.executor = executor;
        float[] values = {0.5f, 1.0f, 2.0f, 4.0f, 8.0f};
        int selected = 1;

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

        this.add(label).padTop(10);
        this.row();
        this.add(slider);
    }
}
