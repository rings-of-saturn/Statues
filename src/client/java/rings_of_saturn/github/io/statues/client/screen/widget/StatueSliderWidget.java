package rings_of_saturn.github.io.statues.client.screen.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import rings_of_saturn.github.io.statues.networking.packets.c2s.UpdateStatueC2SPayload;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

@Environment(EnvType.CLIENT)
public class StatueSliderWidget extends SliderWidget {
    private final StatueEntity statue;
    private final byte bodyPart;
    private final byte axis;

    public StatueSliderWidget(int x, int y, int width, int height, Text text, double value, StatueEntity statue, byte bodyPart, byte axis) {
        super(x, y, width, height, text, value);
        this.statue = statue;
        this.bodyPart = bodyPart;
        this.axis = axis;
    }

    protected void updateMessage() {

    }

    protected void applyValue() {
        System.out.println(bodyPart);
        UpdateStatueC2SPayload payload = new UpdateStatueC2SPayload(this.statue.getId(), (float)this.value*6.25f, this.bodyPart, this.axis);
        ClientPlayNetworking.send(payload);
        StatuePosingUtil.setStatueRot(this.statue, (float)this.value*6.25f, this.bodyPart, this.axis);
    }
}