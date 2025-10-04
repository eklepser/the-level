package game.scene.builder.input;

public interface BuilderInputListener {
    void onEscapePressed();
    void onEnterPressed();
    void onZoom(float amountY);
    void onScreenTapped();
}
