package rings_of_saturn.github.io.statues.client.entity.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public class StatueArmorEntityModel extends BipedEntityModel<StatueEntity> {

    public static final EntityModelLayer ARMOR_INNER = new EntityModelLayer(Identifier.of(MOD_ID, "statue"), "inner_armor");
    public static final EntityModelLayer ARMOR_OUTER = new EntityModelLayer(Identifier.of(MOD_ID, "statue"), "outer_armor");

    public StatueArmorEntityModel(ModelPart root) {
        super(root);
        this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
        this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
    }
    public static TexturedModelData getTexturedModelData() {
        Dilation dilation = new Dilation(1);
        ModelData modelData = BipedEntityModel.getModelData(dilation, 0.0F);
        ModelPartData root = modelData.getRoot();
        ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation), ModelTransform.pivot(0.0F, 1.0F, 0.0F));
        ModelPartData hat = root.addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation.add(0.5F)), ModelTransform.pivot(0.0F, 1.0F, 0.0F));
        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -6F, -2.0F, 8.0F, 12.0F, 4.0F, dilation.add(-0.5f)), ModelTransform.pivot(0.0F, 6.0F, 0.0F));
        ModelPartData right_leg = root.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(-0.25F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
        ModelPartData left_leg = root.addChild("left_leg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(-0.25F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
        ModelPartData leftArm = root.addChild("left_arm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(0.0F, -1.75F, -2.0F, 4.0F, 6.0F, 4.0F, dilation.add(-0.25f)), ModelTransform.pivot(4.0F, 1.0F, 0.0F));
        ModelPartData rightArm = root.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-4.0F, -1.75F, -2.0F, 4.0F, 6.0F, 4.0F, dilation.add(-0.25f)), ModelTransform.pivot(-4.0F, 1.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected ModelPart getArm(Arm arm) {
        if(arm.equals(Arm.RIGHT)){
            return this.rightArm;
        } else {
            return this.leftArm;
        }
    }

    public void setAngles(StatueEntity entity, float f, float g, float h, float i, float j) {
        this.head.pitch = StatuePosingUtil.getHeadRot(entity)[0];
        this.head.yaw = StatuePosingUtil.getHeadRot(entity)[1];
        this.head.roll = StatuePosingUtil.getHeadRot(entity)[2];
        this.body.pitch = StatuePosingUtil.getBodyRot(entity)[0];
        this.body.yaw = StatuePosingUtil.getBodyRot(entity)[1];
        this.body.roll = StatuePosingUtil.getBodyRot(entity)[2];
        this.leftArm.pitch = StatuePosingUtil.getArmRot(entity, false)[0];
        this.leftArm.yaw = StatuePosingUtil.getArmRot(entity, false)[1];
        this.leftArm.roll = StatuePosingUtil.getArmRot(entity, false)[2];
        this.rightArm.pitch = StatuePosingUtil.getArmRot(entity, true)[0];
        this.rightArm.yaw = StatuePosingUtil.getArmRot(entity, true)[1];
        this.rightArm.roll = StatuePosingUtil.getArmRot(entity, true)[2];
        this.leftLeg.pitch = StatuePosingUtil.getLegRot(entity, false)[0];
        this.leftLeg.yaw = StatuePosingUtil.getLegRot(entity, false)[1];
        this.leftLeg.roll = StatuePosingUtil.getLegRot(entity, false)[2];
        this.rightLeg.pitch = StatuePosingUtil.getLegRot(entity, true)[0];
        this.rightLeg.yaw = StatuePosingUtil.getLegRot(entity, true)[1];
        this.rightLeg.roll = StatuePosingUtil.getLegRot(entity, true)[2];
        this.hat.copyTransform(this.head);
        this.rightArm.hidden = false;
        this.leftArm.hidden = false;
    }
}