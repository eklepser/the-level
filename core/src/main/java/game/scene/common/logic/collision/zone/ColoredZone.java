package game.scene.common.logic.collision.zone;

import game.scene.common.logic.entity.Entity;

public final class ColoredZone extends LevelZone {
    private final String colorName;

    public ColoredZone(int x, int y, String colorName) {
        super(x, y);
        this.colorName = colorName;
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.println("Colored zone collision!");
    }

    public String getColorName() {
        return colorName;
    }
}
