package game.scene.builder.rendering.component;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.data.IO.Assets;
import game.scene.builder.logic.Builder;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.InputField;
import game.scene.common.rendering.component.TextLabel;
import game.scene.common.rendering.tilemap.TileDefinition;
import game.utils.NumberUtils;

public class CustomZoneTable extends TableLayout {
    private final TextField tileIdField;
    private final TextField nameField;
    private final TextField zoneTypeField;
    private final TextField zonePropertiesField;

    private final TextButton saveButton;

    public CustomZoneTable(Builder builder) {
        tileIdField = new InputField("", 10);
        nameField = new InputField("", 20);
        zoneTypeField = new InputField("", 10);
        zonePropertiesField = new InputField("", 20);

        saveButton = new TextButton("Apply", Assets.getSkin());
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                TileDefinition def = builder.getSelectedTileDef();
                if (!def.type.equals("custom_zone")) return;

                TileDefinition newDef = new TileDefinition();
                newDef.type = def.type; // must be "custom_zone" type

                newDef.id = NumberUtils.tryParseInt(tileIdField.getText(), def.id);
                newDef.zoneType = zoneTypeField.getText();
                newDef.zoneProperties = zonePropertiesField.getText().split("\\s+");

                builder.setCustomZoneDef(newDef);
            }
        });

        setup();
    }

    @Override
    protected void setup() {
        add(new TextLabel("Custom zone setup:")).left().colspan(2).row();

        add(new TextLabel("tile id")).padRight(6).left();
        add(tileIdField).padTop(4).row();

        add(new TextLabel("name")).padRight(4).left();
        add(nameField).padTop(4).row();

        add(new TextLabel("type")).padRight(6).left();
        add(zoneTypeField).padTop(4).row();

        add(new TextLabel("properties")).padRight(4).left();
        add(zonePropertiesField).padTop(4).row();

        add();
        add(saveButton).padTop(4).center().fillX();
    }


}
