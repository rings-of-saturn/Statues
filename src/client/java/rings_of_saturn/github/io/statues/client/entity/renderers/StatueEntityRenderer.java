package rings_of_saturn.github.io.statues.client.entity.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.statues.client.entity.models.StatueEntityModel;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public class StatueEntityRenderer extends LivingEntityRenderer<StatueEntity, StatueEntityModel> {

    public StatueEntityRenderer(EntityRendererFactory.Context ctx, StatueEntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    public StatueEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new StatueEntityModel(context.getPart(StatueEntityModel.LAYER)), 0f);
    }

    @Override
    protected void renderLabelIfPresent(StatueEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float tickDelta) {
        if(entity.hasCustomName())
            super.renderLabelIfPresent(entity, text, matrices, vertexConsumers, light, tickDelta);
    }

    @Override
    public void render(StatueEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.model.setAngles(livingEntity,0,0,0,0,0);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(StatueEntity entity) {
        if(StatuePosingUtil.getSlim(entity))
            return Identifier.of(MOD_ID, "textures/entity/statue_slim.png");
        else
            return Identifier.of(MOD_ID, "textures/entity/statue_wide.png");
    }

    @Override
    protected float getShadowRadius(StatueEntity entity) {
        return 0;
    }
}
