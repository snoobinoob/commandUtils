package commandUtils.form;

import commandUtils.CommandUtils;
import necesse.engine.localization.Localization;
import necesse.gfx.forms.components.localComponents.FormLocalCheckBox;
import necesse.gfx.gameTooltips.GameTooltips;
import necesse.gfx.gameTooltips.StringTooltips;

public class CheatsEnabledCheckBox extends FormLocalCheckBox {
    public CheatsEnabledCheckBox(int maxWidth) {
        super("ui", "cheatsenabled", 0, 0, maxWidth);

        checked = CommandUtils.settings.cheatsEnabledAtStart;
        CommandUtils.cheatsEnabledAtStart = checked;

        onClicked(e -> CommandUtils.cheatsEnabledAtStart = checked);
    }

    @Override
    public GameTooltips getTooltip() {
        if (!checked) {
            return null;
        }
        return new StringTooltips(Localization.translate("ui", "achdisabledhelp"), 400);
    }

    public void setPositionXCentered(int width, int y) {
        setPosition((width - getBoundingBox().width) / 2, y);
    }
}
