package commandUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import necesse.engine.GlobalData;
import necesse.engine.commands.CommandLog;
import necesse.engine.localization.message.LocalMessage;
import necesse.engine.network.client.Client;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

public class AliasUtils {
    private static final File cfgFile = new File(GlobalData.cfgPath() + "snoobinoob.aliases.cfg");

    public List<Alias> aliases;

    public AliasUtils() {
        aliases = new ArrayList<>();
        LoadData data = getAliasLoadData();

        for (LoadData aliasData : data.getLoadData()) {
            aliases.add(Alias.fromLoadData(aliasData));
        }
    }

    private static LoadData getAliasLoadData() {
        if (!cfgFile.exists()) {
            saveDefaultConfig(cfgFile);
        }
        return new LoadData(cfgFile);
    }

    private static void saveDefaultConfig(File cfgFile) {
        SaveData save = new SaveData("");
        save.saveScript(cfgFile);
    }

    private void saveCommandsData() {
        SaveData save = new SaveData("");
        for (Alias alias : aliases) {
            save.addSafeString(alias.name, alias.command);
        }
        save.saveScript(cfgFile);
    }

    public void tick(Client client) {
        aliases.forEach(alias -> alias.tick(client));
    }

    private Alias getAlias(String name) {
        Optional<Alias> alias = aliases.stream().filter(a -> a.name.equals(name)).findFirst();
        return alias.isPresent() ? alias.get() : null;
    }

    public void getAliases(String name, CommandLog log) {
        if (name == null) {
            if (aliases.size() == 0) {
                log.add(new LocalMessage("command", "noaliases"));
            } else {
                for (Alias cmd : aliases) {
                    log.add(cmd.toString());
                }
            }
            return;
        }

        Alias alias = getAlias(name);
        if (alias == null) {
            log.add(new LocalMessage("command", "noalias", "name", name));
            return;
        }
        log.add(alias.toString());
    }

    public void setAlias(String name, String value, CommandLog log) {
        if (name == null) {
            log.add(new LocalMessage("command", "missingarg", "arg", "name"));
            return;
        }

        if (value == null) {
            log.add(new LocalMessage("command", "missingarg", "arg", "value"));
            return;
        }

        if (getAlias(name) != null) {
            log.add(new LocalMessage("command", "aliasexists", "name", name));
            return;
        }

        aliases.add(new Alias(name, value));
        saveCommandsData();
        log.add(new LocalMessage("command", "aliasset", "name", name));
    }

    public void runAlias(String name, Client client, CommandLog log) {
        if (name == null) {
            log.add(new LocalMessage("command", "missingarg", "arg", "name"));
            return;
        }

        Alias alias = getAlias(name);
        if (alias == null) {
            log.add(new LocalMessage("command", "noalias", "name", name));
            return;
        }

        log.add(new LocalMessage("command", "runningalias", "name", name));
        alias.execute(client);
    }

    public void deleteAlias(String name, CommandLog log) {
        Alias alias = getAlias(name);
        if (alias == null) {
            log.add(new LocalMessage("command", "noalias", "name", name));
            return;
        }

        aliases.remove(alias);
        log.add(new LocalMessage("command", "deletedalias", "name", name));
    }
}
