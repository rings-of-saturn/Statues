package rings_of_saturn.github.io.statues.client.entity.renderers.features;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;
import rings_of_saturn.github.io.statues.util.WatchingUtil;

public class EnderEyeFeatureRenderer<T extends LivingEntity, M extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    private final BlockRenderManager blockRenderer;
    public EnderEyeFeatureRenderer(FeatureRendererContext<T, M> ctx, BlockRenderManager renderManager) {
        super(ctx);
        this.blockRenderer = renderManager;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        BlockState state = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.EYE, true);
        if(WatchingUtil.getWatching(entity) && entity.getEquippedStack(EquipmentSlot.CHEST) == ItemStack.EMPTY){
            matrixStack.push();
            matrixStack.translate(-0.225F, 0.6F, -0.12);
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            matrixStack.scale(0.45F, 0.3F, 0.45F);
            this.blockRenderer.renderBlockAsEntity(state, matrixStack, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
            matrixStack.pop();
        }
    }
}
