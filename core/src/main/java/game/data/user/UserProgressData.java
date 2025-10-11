package game.data.user;

import com.badlogic.gdx.utils.ObjectMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class UserProgressData {
    public List<CompletionStatus> completionList;

    private Map<String, LevelStatus> statusMap;

    public Map<String, LevelStatus> getStatusMap() {
        if (statusMap == null) {
            statusMap = new HashMap<>();
            for (CompletionStatus entry : completionList) {
                if (entry.levelTag != null && entry.levelStatus != null) {
                    statusMap.put(entry.levelTag, entry.levelStatus);
                }
            }
        }
        return statusMap;
    }
}
