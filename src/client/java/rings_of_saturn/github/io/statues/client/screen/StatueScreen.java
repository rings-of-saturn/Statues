package rings_of_saturn.github.io.statues.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import rings_of_saturn.github.io.statues.client.screen.widget.StatueSliderWidget;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.networking.packets.c2s.UpdateStatueSlimC2SPayload;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

@Environment(EnvType.CLIENT)
public class StatueScreen extends Screen {
    int centerCorrection = -50;
    int sideCorrection = 100;
    int bgCorrection = 50;
    public static StatueEntity statue;
    public StatueScreen(StatueEntity statue) {
        super(Text.literal("Statue"));
        StatueScreen.statue = statue;

    }
    @Override
    protected void init() {
        byte bodyPart = 0;
        StatueSliderWidget rightArmSliderX = new StatueSliderWidget(width/2+sideCorrection, height/2 + 17 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget rightArmSliderY = new StatueSliderWidget(width/2+sideCorrection, height/2 + 34 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget rightArmSliderZ = new StatueSliderWidget(width/2+sideCorrection, height/2 + 51 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(rightArmSliderX);
        addDrawableChild(rightArmSliderY);
        addDrawableChild(rightArmSliderZ);

        bodyPart = 1;
        StatueSliderWidget leftArmSliderX = new StatueSliderWidget(width/2-sideCorrection, height/2 + 17 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget leftArmSliderY = new StatueSliderWidget(width/2-sideCorrection, height/2 + 34 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget leftArmSliderZ = new StatueSliderWidget(width/2-sideCorrection, height/2 + 51 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(leftArmSliderX);
        addDrawableChild(leftArmSliderY);
        addDrawableChild(leftArmSliderZ);

        bodyPart = 2;
        StatueSliderWidget rightLegSliderX = new StatueSliderWidget(width/2+sideCorrection, height/2 + 17 - 100 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget rightLegSliderY = new StatueSliderWidget(width/2+sideCorrection, height/2 + 34 - 100 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget rightLegSliderZ = new StatueSliderWidget(width/2+sideCorrection, height/2 + 51 - 100 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(rightLegSliderX);
        addDrawableChild(rightLegSliderY);
        addDrawableChild(rightLegSliderZ);

        bodyPart = 3;
        StatueSliderWidget leftLegSliderX = new StatueSliderWidget(width/2-sideCorrection, height/2 + 17 - 100 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget leftLegSliderY = new StatueSliderWidget(width/2-sideCorrection, height/2 + 34 - 100 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget leftLegSliderZ = new StatueSliderWidget(width/2-sideCorrection, height/2 + 51 - 100 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(leftLegSliderX);
        addDrawableChild(leftLegSliderY);
        addDrawableChild(leftLegSliderZ);

        bodyPart = 4;
        StatueSliderWidget headSliderX = new StatueSliderWidget(width/2, height/2 + 17 - 200 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget headSliderY = new StatueSliderWidget(width/2, height/2 + 34 - 200 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget headSliderZ = new StatueSliderWidget(width/2, height/2 + 51 - 200 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(headSliderX);
        addDrawableChild(headSliderY);
        addDrawableChild(headSliderZ);

        bodyPart = 5;
        StatueSliderWidget bodySliderX = new StatueSliderWidget(width/2, height/2 + 17 + 100 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget bodySliderY = new StatueSliderWidget(width/2, height/2 + 34 + 100 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget bodySliderZ = new StatueSliderWidget(width/2, height/2 + 51 + 100 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(bodySliderX);
        addDrawableChild(bodySliderY);
        addDrawableChild(bodySliderZ);


        CheckboxWidget slimCheckbox = CheckboxWidget.builder(Text.literal("Slim"), MinecraftClient.getInstance().textRenderer)
                .callback((checkbox, checked) -> {
                    ClientPlayNetworking.send(new UpdateStatueSlimC2SPayload(statue.getId(), checked));
                    StatuePosingUtil.setSlim(statue, checked);
                })
                .checked(StatuePosingUtil.getSlim(statue))
                .pos(width / 2, 20)
                .build();
        addDrawableChild(slimCheckbox);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(Identifier.of(MOD_ID, "textures/gui/container/statue_screen.png"), width/2-128, height/2-192-bgCorrection, 256, 384, 256, 384, 256, 384);
        super.renderBackground(context, mouseX, mouseY, delta);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTextWithShadow(client.textRenderer, Text.literal("Right Arm"), width/2+100, height/2+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));
        context.drawTextWithShadow(client.textRenderer, Text.literal("Left Arm"), width/2-100, height/2+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));
        context.drawTextWithShadow(client.textRenderer, Text.literal("Right Leg"), width/2+100, height/2+100+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));
        context.drawTextWithShadow(client.textRenderer, Text.literal("Left Leg"), width/2-100, height/2+100+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));

        InventoryScreen.drawEntity(context, (float) width /2, (float) height /2, (float) (client.getWindow().getScaleFactor()*25), new Vector3f(), new Quaternionf().rotationXYZ(0, 180.0F, (float)Math.PI), null, statue);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    protected void applyBlur(float delta) {
    }
}
