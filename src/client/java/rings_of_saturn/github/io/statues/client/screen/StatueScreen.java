package rings_of_saturn.github.io.statues.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import rings_of_saturn.github.io.statues.client.screen.widget.StatueSliderWidget;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.networking.packets.c2s.CopyStatueC2SPayload;
import rings_of_saturn.github.io.statues.networking.packets.c2s.PasteStatueC2SPayload;
import rings_of_saturn.github.io.statues.networking.packets.c2s.UpdateStatueSlimC2SPayload;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

@Environment(EnvType.CLIENT)
public class StatueScreen extends Screen {
    int centerCorrection = -50;
    int sideCorrection = 100;
    int bgCorrection = 50;
    int bgSideCorrection = -32;
    public static StatueEntity statue;
    public StatueScreen(StatueEntity statue) {
        super(Text.literal("Statue"));
        StatueScreen.statue = statue;

    }
    @Override
    protected void init() {
        byte bodyPart = 0;
        StatueSliderWidget rightArmSliderX = new StatueSliderWidget(width/2+sideCorrection+bgSideCorrection, height/2 + 17 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget rightArmSliderY = new StatueSliderWidget(width/2+sideCorrection+bgSideCorrection, height/2 + 34 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget rightArmSliderZ = new StatueSliderWidget(width/2+sideCorrection+bgSideCorrection, height/2 + 51 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(rightArmSliderX);
        addDrawableChild(rightArmSliderY);
        addDrawableChild(rightArmSliderZ);

        bodyPart = 1;
        StatueSliderWidget leftArmSliderX = new StatueSliderWidget(width/2-sideCorrection+bgSideCorrection, height/2 + 17 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget leftArmSliderY = new StatueSliderWidget(width/2-sideCorrection+bgSideCorrection, height/2 + 34 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget leftArmSliderZ = new StatueSliderWidget(width/2-sideCorrection+bgSideCorrection, height/2 + 51 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(leftArmSliderX);
        addDrawableChild(leftArmSliderY);
        addDrawableChild(leftArmSliderZ);

        bodyPart = 2;
        StatueSliderWidget rightLegSliderX = new StatueSliderWidget(width/2+sideCorrection+bgSideCorrection, height/2 + 17 - 100 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget rightLegSliderY = new StatueSliderWidget(width/2+sideCorrection+bgSideCorrection, height/2 + 34 - 100 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget rightLegSliderZ = new StatueSliderWidget(width/2+sideCorrection+bgSideCorrection, height/2 + 51 - 100 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(rightLegSliderX);
        addDrawableChild(rightLegSliderY);
        addDrawableChild(rightLegSliderZ);

        bodyPart = 3;
        StatueSliderWidget leftLegSliderX = new StatueSliderWidget(width/2-sideCorrection+bgSideCorrection, height/2 + 17 - 100 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget leftLegSliderY = new StatueSliderWidget(width/2-sideCorrection+bgSideCorrection, height/2 + 34 - 100 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget leftLegSliderZ = new StatueSliderWidget(width/2-sideCorrection+bgSideCorrection, height/2 + 51 - 100 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(leftLegSliderX);
        addDrawableChild(leftLegSliderY);
        addDrawableChild(leftLegSliderZ);

        bodyPart = 4;
        StatueSliderWidget headSliderX = new StatueSliderWidget(width/2+bgSideCorrection, height/2 + 17 - 190 + centerCorrection,64,16, Text.literal("X"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 0)/6.25, statue, bodyPart, (byte)0);
        StatueSliderWidget headSliderY = new StatueSliderWidget(width/2+bgSideCorrection, height/2 + 34 - 190 + centerCorrection,64,16, Text.literal("Y"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 1)/6.25, statue, bodyPart, (byte)1);
        StatueSliderWidget headSliderZ = new StatueSliderWidget(width/2+bgSideCorrection, height/2 + 51 - 190 + centerCorrection,64,16, Text.literal("Z"),
                StatuePosingUtil.getStatueRotInAxis(statue, bodyPart, (byte) 2)/6.25, statue, bodyPart, (byte)2);
        addDrawableChild(headSliderX);
        addDrawableChild(headSliderY);
        addDrawableChild(headSliderZ);
        bodyPart = 6;
        StatueSliderWidget rotationSlider = new StatueSliderWidget(width/2+bgSideCorrection, height/2 + 17 + 100 + centerCorrection,64,16, Text.literal("Rotation"),
                statue.getYaw()/359, statue, bodyPart, (byte)0);
        addDrawableChild(rotationSlider);

        ButtonWidget copyButton = ButtonWidget.builder(Text.literal("Copy"),
                (button -> {
                    ClientPlayNetworking.send(new CopyStatueC2SPayload(statue.getId()));
                    StatuePosingUtil.copyToPlayer(statue, client.player);
                    reOpen();
                }))
                .dimensions(width/2 - sideCorrection + bgSideCorrection, height/2 + 17 + 125 + centerCorrection,
                64, 16)
                .tooltip(Tooltip.of(Text.literal("Copies statue data")))
                .build();
        addDrawableChild(copyButton);

        ButtonWidget pasteButton = ButtonWidget.builder(Text.literal("Paste"),
                        (button -> {
                            ClientPlayNetworking.send(new PasteStatueC2SPayload(statue.getId()));
                            StatuePosingUtil.copyFromPlayer(statue, client.player);
                            reOpen();
                        }))
                .dimensions(width/2 + sideCorrection + bgSideCorrection, height/2 + 17 + 125 + centerCorrection,
                        64, 16)
                .tooltip(Tooltip.of(Text.literal("Pastes statue data")))
                .build();
        addDrawableChild(pasteButton);

//        ButtonWidget flipButton = ButtonWidget.builder(Text.literal("Flip"),
//                        (button -> {
//                            ClientPlayNetworking.send(new FlipStatueC2SPayload(statue.getId()));
//                            StatuePosingUtil.flip(statue);
//                        }))
//                .dimensions(width/2 + bgSideCorrection, height/2 + 17 + 125 + centerCorrection,
//                        64, 16)
//                .tooltip(Tooltip.of(Text.literal("Flips the statue's rotations")))
//                .build();
//        addDrawableChild(flipButton);

        CheckboxWidget slimCheckbox = CheckboxWidget.builder(Text.literal("Slim"), MinecraftClient.getInstance().textRenderer)
                .callback((checkbox, checked) -> {
                    ClientPlayNetworking.send(new UpdateStatueSlimC2SPayload(statue.getId(), checked));
                    StatuePosingUtil.setSlim(statue, checked);
                })
                .checked(StatuePosingUtil.getSlim(statue))
                .pos(width / 2 + bgSideCorrection, height/2 + centerCorrection - 100)
                .build();
        addDrawableChild(slimCheckbox);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(Identifier.of(MOD_ID, "textures/gui/container/statue_screen.png"), width/2-136, height/2-192-bgCorrection, 272, 384, 272, 384, 272, 384);
        super.renderBackground(context, mouseX, mouseY, delta);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTextWithShadow(client.textRenderer, Text.literal("Right Arm"), width/2+100+bgSideCorrection, height/2+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));
        context.drawTextWithShadow(client.textRenderer, Text.literal("Left Arm"), width/2-100+bgSideCorrection, height/2+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));
        context.drawTextWithShadow(client.textRenderer, Text.literal("Right Leg"), width/2+100+bgSideCorrection, height/2-100+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));
        context.drawTextWithShadow(client.textRenderer, Text.literal("Left Leg"), width/2-100+bgSideCorrection, height/2-100+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));
        context.drawTextWithShadow(client.textRenderer, Text.literal("Head"), width/2+bgSideCorrection, height/2-190+centerCorrection, ColorHelper.Argb.getArgb(255,255,255));

        InventoryScreen.drawEntity(context, (float) width /2, (float) height /2, (float) (client.getWindow().getScaleFactor()*25), new Vector3f(), new Quaternionf().rotationXYZ(0, 180, (float)Math.PI), new Quaternionf().rotationXYZ(0, 180, (float)Math.PI), statue);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    protected void applyBlur(float delta) {
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    public void reOpen(){
        this.close();
        client.setScreen(this);
    }
}
