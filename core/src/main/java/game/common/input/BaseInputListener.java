package game.common.input;

public interface BaseInputListener {
    default void onEnterPressed() {}
    default void onEscapePressed() {}
    default void onSpace() {}
    default void onArrowUp() {}
    default void onArrowDown() {}
    default void onArrowLeft() {}
    default void onArrowRight() {}
    default void onTab() {}
    default void onBackspace() {}
    default void onDelete() {}
    default void onF1() {}

    default void onScreenTapped() {}

    default void onZoom(float amount) {}
}
