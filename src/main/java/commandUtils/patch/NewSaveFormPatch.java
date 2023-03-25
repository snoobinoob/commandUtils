package commandUtils.patch;

import java.awt.Rectangle;
import commandUtils.form.CheatsEnabledCheckBox;
import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.gfx.forms.components.FormComponent;
import necesse.gfx.forms.components.FormContentBox;
import necesse.gfx.forms.position.FormPositionContainer;
import necesse.gfx.forms.presets.NewSaveForm;
import net.bytebuddy.asm.Advice;

public class NewSaveFormPatch {
    @ModConstructorPatch(target = NewSaveForm.class, arguments = {String.class})
    public static class ConstructorPatch {
        @Advice.OnMethodExit
        public static void onExit(
                @Advice.FieldValue("settingsContent") FormContentBox settingsContent) {
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
            cheatsBox.setPositionXCentered(settingsContent.getWidth(), 80);
        }
    }
}
