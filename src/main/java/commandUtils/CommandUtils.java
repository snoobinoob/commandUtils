package commandUtils;

import commandUtils.command.AliasCommand;
import necesse.engine.commands.CommandsManager;
import necesse.engine.modLoader.ModSettings;
import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class CommandUtils {
    public static Settings settings;
    public static boolean cheatsEnabledAtStart;
    public static AliasUtils aliasUtils;

    public void init() {
        aliasUtils = new AliasUtils();
    }

    public ModSettings initSettings() {
        settings = new Settings();
        return settings;
    }

    public void postInit() {
        CommandsManager.registerClientCommand(new AliasCommand());
    }
}
