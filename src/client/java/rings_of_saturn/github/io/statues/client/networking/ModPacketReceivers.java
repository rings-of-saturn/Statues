package rings_of_saturn.github.io.statues.client.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import rings_of_saturn.github.io.statues.client.screen.StatueScreen;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.networking.packets.s2c.OpenStatueScreenS2CPayload;

public class ModPacketReceivers {
    public static void registerReceivers() {
        ClientPlayNetworking.registerGlobalReceiver(OpenStatueScreenS2CPayload.ID, (payload, context) -> {
            ClientWorld world = context.client().world;

            if (world == null)
                return;

            int id = payload.statueData();
            StatueEntity statue = (StatueEntity) world.getEntityById(id);

            if (statue != null) {
                MinecraftClient.getInstance().setScreen(
                        new StatueScreen(statue)
                );
            }
        });
    }
}
