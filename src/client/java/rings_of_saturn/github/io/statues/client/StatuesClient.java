package rings_of_saturn.github.io.statues.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import rings_of_saturn.github.io.statues.client.entity.models.StatueArmorEntityModel;
import rings_of_saturn.github.io.statues.client.entity.models.StatueEntityModel;
import rings_of_saturn.github.io.statues.client.entity.renderers.StatueEntityRenderer;
import rings_of_saturn.github.io.statues.client.networking.ModPacketReceivers;
import rings_of_saturn.github.io.statues.entity.ModEntities;

public class StatuesClient implements ClientModInitializer {
    public static final MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.STATUE, StatueEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(StatueEntityModel.LAYER, StatueEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(StatueArmorEntityModel.ARMOR_INNER, StatueArmorEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(StatueArmorEntityModel.ARMOR_OUTER, StatueArmorEntityModel::getTexturedModelData);

        ModPacketReceivers.registerReceivers();
    }
}
