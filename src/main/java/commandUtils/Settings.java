package commandUtils;

import necesse.engine.modLoader.ModSettings;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

public class Settings extends ModSettings {
    public boolean cheatsEnabledAtStart;

    public Settings() {
        cheatsEnabledAtStart = false;
    }

    @Override
    public void addSaveData(SaveData data) {
        data.addBoolean("cheatsenabledatstart", cheatsEnabledAtStart);
    }

    @Override
    public void applyLoadData(LoadData save) {
        cheatsEnabledAtStart = save.getBoolean("cheatsenabledatstart", cheatsEnabledAtStart);
    }
}
