package commandUtils.command;

import commandUtils.CommandUtils;
import necesse.engine.commands.CmdParameter;
import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.commands.parameterHandlers.PresetStringParameterHandler;
import necesse.engine.commands.parameterHandlers.StringParameterHandler;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class AliasCommand extends ModularChatCommand {

    public AliasCommand() {
        super("alias", "Aliases for long commands", PermissionLevel.USER, false,
                new CmdParameter("get/set/run/delete",
                        new PresetStringParameterHandler("get", "set", "run", "delete")),
                new CmdParameter("name", new AliasNameParameterHandler(), true,
                        new CmdParameter("value", new StringParameterHandler(), true)));
    }

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] args,
            String[] errors, CommandLog log) {
        String op = (String) args[0];
        String name = (String) args[1];
        String value = (String) args[2];

        if (op.equals("get")) {
            CommandUtils.aliasUtils.getAliases(name, log);
        } else if (op.equals("set")) {
            CommandUtils.aliasUtils.setAlias(name, value, log);
        } else if (op.equals("run")) {
            CommandUtils.aliasUtils.runAlias(name, client, log);
        } else if (op.equals("delete")) {
            CommandUtils.aliasUtils.deleteAlias(name, log);
        }
    }
}
