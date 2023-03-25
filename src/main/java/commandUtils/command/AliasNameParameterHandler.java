package commandUtils.command;

import java.util.List;
import commandUtils.CommandUtils;
import necesse.engine.commands.AutoComplete;
import necesse.engine.commands.CmdArgument;
import necesse.engine.commands.parameterHandlers.StringParameterHandler;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class AliasNameParameterHandler extends StringParameterHandler {

    @Override
    public List<AutoComplete> autocomplete(Client client, Server server, ServerClient serverClient,
            CmdArgument argument) {
        return autocompleteFromList(CommandUtils.aliasUtils.aliases, a -> true, a -> a.name,
                argument);
    }
}
