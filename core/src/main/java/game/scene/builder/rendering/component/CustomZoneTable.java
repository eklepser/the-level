package game.scene.builder.rendering.component;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.common.rendering.TableLayout;
import game.common.rendering.component.InputField;
import game.common.rendering.component.TextLabel;
import game.common.rendering.tilemap.TileDefinition;
import game.resources.Assets;
import game.scene.builder.logic.Builder;

public class CustomZoneTable extends TableLayout {
    private final TextField zoneTypeField;
    private final TextField zonePropertiesField;

    private final TextButton saveButton;

    public CustomZoneTable(Builder builder) {
        zoneTypeField = new InputField("", 10);
        zonePropertiesField = new InputField("", 20);

        saveButton = new TextButton("Save", Assets.getSkin());
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                TileDefinition def = builder.getSelectedTileDef();
                if (!def.type.equals("custom_zone")) return;

                TileDefinition newDef = new TileDefinition();
                newDef.id = def.id;
                newDef.name = def.name;
                newDef.type = def.type;
                newDef.zoneType = zoneTypeField.getText();
                newDef.zoneProperties = zonePropertiesField.getText().split("\\s+");

                builder.setSelectedTileDef(newDef);
            }
        });

        setup();
    }

    @Override
    protected void setup() {
        add(new TextLabel("Custom zone setup:")).left().colspan(2).row();

        add(new TextLabel("type")).padRight(6).left();
        add(zoneTypeField).padTop(4).row();

        add(new TextLabel("properties")).padRight(4).left();
        add(zonePropertiesField).padTop(4).row();

        add();
        add(saveButton).padTop(4).center().fillX();
    }
}
