package commandUtils.patch;

import commandUtils.form.CheatsEnabledCheckBox;
import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.gfx.forms.ButtonOptions;
import necesse.gfx.forms.components.FormComponent;
import necesse.gfx.forms.components.FormContentBox;
import necesse.gfx.forms.position.FormPositionContainer;
import necesse.gfx.forms.presets.NewSaveWorldSettingsForm;
import net.bytebuddy.asm.Advice;

import java.awt.*;

public class NewSaveFormPatch {
    @ModConstructorPatch(target = NewSaveWorldSettingsForm.class, arguments = {ButtonOptions.class, ButtonOptions.class})
    public static class ConstructorPatch {
        @Advice.OnMethodExit
        public static void onExit(@Advice.FieldValue("settingsContent") FormContentBox settingsContent) {
            for (FormComponent comp : settingsContent.getComponents()) {
                if (comp instanceof FormPositionContainer) {
                    FormPositionContainer c = (FormPositionContainer) comp;
                    if (c.getY() > 50) {
                        c.setY(c.getY() + 40);
                    }
                }
            }
            Rectangle contentBox = settingsContent.getContentBox();
            contentBox.height = contentBox.height + 40;
            settingsContent.setContentBox(contentBox);

            CheatsEnabledCheckBox cheatsBox = settingsContent
                .addComponent(new CheatsEnabledCheckBox(settingsContent.getWidth() - 20));
            cheatsBox.useButtonTexture();
            cheatsBox.setPositionXCentered(settingsContent.getWidth(), 70);
        }
    }
}
