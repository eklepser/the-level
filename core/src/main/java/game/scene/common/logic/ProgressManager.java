package game.scene.common.logic;

import game.data.level.LevelMetadata;
import game.data.user.LevelStatus;
import game.data.user.UserData;
import game.data.IO.UserDataIO;
import game.scene.common.logic.event.GameEvent;

import java.util.List;

public final class ProgressManager {
    private final UserData userData;
    private final AbstractScene<? extends GameEvent> scene;

    public ProgressManager(AbstractScene<? extends GameEvent> scene) {
        userData = UserDataIO.loadUserData();
        this.scene = scene;
    }

    public void saveWorldPosition() {
        userData.worldPosition.x = (int)scene.getPlayer().getWorldPos().x;
        userData.worldPosition.y = (int)scene.getPlayer().getWorldPos().y;
        UserDataIO.saveUserData(userData);
    }

    public void completeLevel(LevelMetadata metadata) {
        userData.progressData.setStatus(metadata.tag, LevelStatus.COMPLETED);
        List<String> unlocks = metadata.unlocks;
        for (String tag : unlocks) {
            userData.progressData.setStatus(tag, LevelStatus.UNLOCKED);
        }
        UserDataIO.saveUserData(userData);
    }
}
