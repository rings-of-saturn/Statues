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
public class StatueEntityModel extends EntityModel<StatueEntity> {

	public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "assets/statues/textures/entity/statue_slim.png"), "main");

	private final ModelPart head;
	private final ModelPart headwear;
	private final ModelPart body;
	private final ModelPart jacket;
	private final ModelPart left_arm_slim;
	private final ModelPart left_sleeve_slim;
	private final ModelPart right_arm_slim;
	private final ModelPart right_sleeve_slim;
	private final ModelPart left_arm_wide;
	private final ModelPart left_sleeve_wide;
	private final ModelPart right_arm_wide;
	private final ModelPart right_sleeve_wide;
	private final ModelPart left_leg;
	private final ModelPart left_pants;
	private final ModelPart right_leg;
	private final ModelPart right_pants;
	public StatueEntityModel(ModelPart root) {
		this.head = root.getChild("head");
		this.headwear = this.head.getChild("headwear");
		this.body = root.getChild("body");
		this.jacket = this.body.getChild("jacket");
		this.left_arm_slim = root.getChild("left_arm_slim");
		this.left_sleeve_slim = this.left_arm_slim.getChild("left_sleeve_slim");
		this.right_arm_slim = root.getChild("right_arm_slim");
		this.right_sleeve_slim = this.right_arm_slim.getChild("right_sleeve_slim");
		this.left_arm_wide = root.getChild("left_arm_wide");
		this.left_sleeve_wide = this.left_arm_wide.getChild("left_sleeve_wide");
		this.right_arm_wide = root.getChild("right_arm_wide");
		this.right_sleeve_wide = this.right_arm_wide.getChild("right_sleeve_wide");
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

		ModelPartData left_arm_slim = modelPartData.addChild("left_arm_slim", ModelPartBuilder.create().uv(32, 48).cuboid(0.0F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 1.0F, 0.0F));

		ModelPartData left_sleeve_slim = left_arm_slim.addChild("left_sleeve_slim", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData right_arm_slim = modelPartData.addChild("right_arm_slim", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 1.0F, 0.0F));

		ModelPartData right_sleeve_slim = right_arm_slim.addChild("right_sleeve_slim", ModelPartBuilder.create().uv(40, 32).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData left_arm_wide = modelPartData.addChild("left_arm_wide", ModelPartBuilder.create().uv(32, 48).cuboid(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 1.0F, 0.0F));

		ModelPartData left_sleeve_wide = left_arm_wide.addChild("left_sleeve_wide", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.0F, 1.0F, 0.0F));

		ModelPartData right_arm_wide = modelPartData.addChild("right_arm_wide", ModelPartBuilder.create().uv(40, 16).cuboid(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 1.0F, 0.0F));

		ModelPartData right_sleeve_wide = right_arm_wide.addChild("right_sleeve_wide", ModelPartBuilder.create().uv(40, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData left_pants = left_leg.addChild("left_pants", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData right_pants = right_leg.addChild("right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(StatueEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if(StatuePosingUtil.getSlim(entity)) {
			right_arm_slim.hidden = false;
			left_arm_slim.hidden = false;
			right_sleeve_slim.hidden = false;
			left_sleeve_slim.hidden = false;

			right_arm_slim.pitch = StatuePosingUtil.getArmRot(entity, true)[0];
			right_arm_slim.yaw = StatuePosingUtil.getArmRot(entity, true)[1];
			right_arm_slim.roll = StatuePosingUtil.getArmRot(entity, true)[2];

			left_arm_slim.pitch = StatuePosingUtil.getArmRot(entity, false)[0];
			left_arm_slim.yaw = StatuePosingUtil.getArmRot(entity, false)[1];
			left_arm_slim.roll = StatuePosingUtil.getArmRot(entity, false)[2];
			right_arm_wide.hidden = true;
			left_arm_wide.hidden = true;
			right_sleeve_wide.hidden = true;
			left_sleeve_wide.hidden = true;
		} else {
			right_arm_wide.hidden = false;
			left_arm_wide.hidden = false;
			right_sleeve_wide.hidden = false;
			left_sleeve_wide.hidden = false;

			right_arm_wide.pitch = StatuePosingUtil.getArmRot(entity, true)[0];
			right_arm_wide.yaw = StatuePosingUtil.getArmRot(entity, true)[1];
			right_arm_wide.roll = StatuePosingUtil.getArmRot(entity, true)[2];

			left_arm_wide.pitch = StatuePosingUtil.getArmRot(entity, false)[0];
			left_arm_wide.yaw = StatuePosingUtil.getArmRot(entity, false)[1];
			left_arm_wide.roll = StatuePosingUtil.getArmRot(entity, false)[2];

			right_arm_slim.hidden = true;
			left_arm_slim.hidden = true;
			right_sleeve_slim.hidden = true;
			left_sleeve_slim.hidden = true;
		}
		right_leg.pitch = StatuePosingUtil.getLegRot(entity, true)[0];
		right_leg.yaw = StatuePosingUtil.getLegRot(entity, true)[1];
		right_leg.roll = StatuePosingUtil.getLegRot(entity, true)[2];

		left_leg.pitch = StatuePosingUtil.getLegRot(entity, false)[0];
		left_leg.yaw = StatuePosingUtil.getLegRot(entity, false)[1];
		left_leg.roll = StatuePosingUtil.getLegRot(entity, false)[2];

		head.pitch = StatuePosingUtil.getHeadRot(entity)[0];
		head.yaw = StatuePosingUtil.getHeadRot(entity)[1];
		head.roll = StatuePosingUtil.getHeadRot(entity)[2];

		body.pitch = StatuePosingUtil.getBodyRot(entity)[0];
		body.yaw = StatuePosingUtil.getBodyRot(entity)[1];
		body.roll = StatuePosingUtil.getBodyRot(entity)[2];
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		head.render(matrices, vertices, light, overlay, color);
		body.render(matrices, vertices, light, overlay, color);
		left_arm_slim.render(matrices, vertices, light, overlay, color);
		right_arm_slim.render(matrices, vertices, light, overlay, color);
		left_arm_wide.render(matrices, vertices, light, overlay, color);
		right_arm_wide.render(matrices, vertices, light, overlay, color);
		left_leg.render(matrices, vertices, light, overlay, color);
		right_leg.render(matrices, vertices, light, overlay, color);
	}
}