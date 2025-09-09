package rings_of_saturn.github.io.statues.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.networking.packets.c2s.UpdateStatueC2SPayload;
import rings_of_saturn.github.io.statues.networking.packets.c2s.UpdateStatueSlimC2SPayload;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

public class ModPacketReceivers {
    public static void registerReceivers(){
        ServerPlayNetworking.registerGlobalReceiver(UpdateStatueC2SPayload.ID, (payload, context) -> {
            StatueEntity statue = (StatueEntity) context.player().getWorld().getEntityById(payload.entityId());
            StatuePosingUtil.setStatueRot(statue, payload.newValue(), payload.bodyPart(), payload.axis());
        });

        ServerPlayNetworking.registerGlobalReceiver(UpdateStatueSlimC2SPayload.ID, (payload, context) -> {
            StatueEntity statue = (StatueEntity) context.server().getWorld(context.player().getWorld().getRegistryKey()).getEntityById(payload.entityId());
            StatuePosingUtil.setSlim(statue, payload.newValue());
        });
    }
}
