package rings_of_saturn.github.io.statues.client.entity.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class StatueEntitySlim extends EntityModel<StatueEntity> {

	public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "assets/textures/entity/statue_slim.png"), "main");

	private final ModelPart head;
	private final ModelPart headwear;
	private final ModelPart body;
	private final ModelPart jacket;
	private final ModelPart left_arm;
	private final ModelPart left_sleeve;
	private final ModelPart right_arm;
	private final ModelPart right_sleeve;
	private final ModelPart left_leg;
	private final ModelPart left_pants;
	private final ModelPart right_leg;
	private final ModelPart right_pants;
	public StatueEntitySlim(ModelPart root) {
		this.head = root.getChild("head");
		this.headwear = this.head.getChild("headwear");
		this.body = root.getChild("body");
		this.jacket = this.body.getChild("jacket");
		this.left_arm = root.getChild("left_arm");
		this.left_sleeve = this.left_arm.getChild("left_sleeve");
		this.right_arm = root.getChild("right_arm");
		this.right_sleeve = this.right_arm.getChild("right_sleeve");
		this.left_leg = root.getChild("left_leg");
		this.left_pants = this.left_leg.getChild("left_pants");
		this.right_leg = root.getChild("right_leg");
		this.right_pants = this.right_leg.getChild("right_pants");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData headwear = head.addChild("headwear", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 0.0F));

		ModelPartData jacket = body.addChild("jacket", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 48).cuboid(0.0F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 1.0F, 0.0F));

		ModelPartData left_sleeve = left_arm.addChild("left_sleeve", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 1.0F, 0.0F));

		ModelPartData right_sleeve = right_arm.addChild("right_sleeve", ModelPartBuilder.create().uv(40, 32).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_pants = left_leg.addChild("left_pants", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_pants = right_leg.addChild("right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(StatueEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		right_arm.translate(new Vector3f(StatuePosingUtil.getArmRot(entity, true)[0],
				StatuePosingUtil.getArmRot(entity, true)[1],
				StatuePosingUtil.getArmRot(entity, true)[2]));
		left_arm.translate(new Vector3f(StatuePosingUtil.getArmRot(entity, false)[0],
				StatuePosingUtil.getArmRot(entity, false)[1],
				StatuePosingUtil.getArmRot(entity, false)[2]));
		right_leg.translate(new Vector3f(StatuePosingUtil.getLegRot(entity, true)[0],
				StatuePosingUtil.getArmRot(entity, true)[1],
				StatuePosingUtil.getArmRot(entity, true)[2]));
		left_leg.translate(new Vector3f(StatuePosingUtil.getLegRot(entity, false)[0],
				StatuePosingUtil.getArmRot(entity, false)[1],
				StatuePosingUtil.getArmRot(entity, false)[2]));
		head.translate(new Vector3f(StatuePosingUtil.getHeadRot(entity)[0],
				StatuePosingUtil.getHeadRot(entity)[1],
				StatuePosingUtil.getHeadRot(entity)[2]));
		body.translate(new Vector3f(StatuePosingUtil.getBodyRot(entity)[0],
				StatuePosingUtil.getBodyRot(entity)[1],
				StatuePosingUtil.getBodyRot(entity)[2]));
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		head.render(matrices, vertices, light, overlay, color);
		body.render(matrices, vertices, light, overlay, color);
		left_arm.render(matrices, vertices, light, overlay, color);
		right_arm.render(matrices, vertices, light, overlay, color);
		left_leg.render(matrices, vertices, light, overlay, color);
		right_leg.render(matrices, vertices, light, overlay, color);
	}
}