package commandUtils;

import necesse.engine.commands.ParsedCommand;
import necesse.engine.input.Control;
import necesse.engine.localization.message.StaticMessage;
import necesse.engine.network.client.Client;
import necesse.engine.network.packet.PacketChatMessage;
import necesse.engine.save.LoadData;

import java.util.Arrays;

public class Alias {
    public final String name;
    public final String command;
    public final Control control;

    public Alias(String name, String command) {
        this(name, command, null);
    }

    private Alias(String name, String command, Control control) {
        this.name = name;
        this.command = command;
        this.control = control;
    }

    public static Alias fromLoadData(LoadData data) {
        String name = data.getName();
        String command = data.getData();
        Control control = Control.addModControl(new Control(-1, name, new StaticMessage(name)));
        return new Alias(name, command, control);
    }

    public void execute(Client client) {
        Arrays.stream(command.split(";")).forEach(command -> executeSingle(client, command));
    }

    private void executeSingle(Client client, String rawCommand) {
        String command = rawCommand.trim();
        boolean send = !client.commandsManager.runClientCommand(new ParsedCommand(command));
        if (send) {
            client.network.sendPacket(new PacketChatMessage(client.getSlot(), "/" + command));
        }
    }

    public void tick(Client client) {
        if (control == null || !control.isPressed()) {
            return;
        }
        execute(client);
    }

    @Override
    public String toString() {
        return name + " = \"" + command + "\"";
    }
}
