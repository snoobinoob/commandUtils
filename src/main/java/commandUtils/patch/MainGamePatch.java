package commandUtils.patch;

import commandUtils.CommandUtils;
import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.engine.state.MainGame;
import necesse.engine.window.GameWindow;
import net.bytebuddy.asm.Advice;

public class MainGamePatch {
    @ModMethodPatch(target = MainGame.class, name = "frameTick", arguments = {TickManager.class, GameWindow.class})
    public static class FrameTickPatch {
        @Advice.OnMethodExit
        public static void onExit(@Advice.FieldValue("client") Client client) {
            CommandUtils.aliasUtils.tick(client);
        }
    }
}
