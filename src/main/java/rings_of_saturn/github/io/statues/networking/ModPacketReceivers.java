package rings_of_saturn.github.io.statues.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;
import rings_of_saturn.github.io.statues.networking.packets.c2s.*;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;

public class ModPacketReceivers {
    public static void registerReceivers(){
        ServerPlayNetworking.registerGlobalReceiver(UpdateStatueC2SPayload.ID, (payload, context) -> {
            StatueEntity statue = (StatueEntity) context.player().getWorld().getEntityById(payload.entityId());
            if(payload.bodyPart() == 6){
                statue.setYaw(payload.newValue()*359f);
            } else {
                StatuePosingUtil.setStatueRot(statue, payload.newValue()*6.25f, payload.bodyPart(), payload.axis());
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(UpdateStatueSlimC2SPayload.ID, (payload, context) -> {
            StatueEntity statue = (StatueEntity) context.server().getWorld(context.player().getWorld().getRegistryKey()).getEntityById(payload.entityId());
            StatuePosingUtil.setSlim(statue, payload.newValue());
        });

        ServerPlayNetworking.registerGlobalReceiver(CopyStatueC2SPayload.ID, (payload, context) -> {
            StatueEntity statue = (StatueEntity) context.server().getWorld(context.player().getWorld().getRegistryKey()).getEntityById(payload.entityId());
            StatuePosingUtil.copyToPlayer(statue, context.player());
        });

        ServerPlayNetworking.registerGlobalReceiver(PasteStatueC2SPayload.ID, (payload, context) -> {
            StatueEntity statue = (StatueEntity) context.server().getWorld(context.player().getWorld().getRegistryKey()).getEntityById(payload.entityId());
            StatuePosingUtil.copyFromPlayer(statue, context.player());
        });

        ServerPlayNetworking.registerGlobalReceiver(FlipStatueC2SPayload.ID, (payload, context) -> {
            StatueEntity statue = (StatueEntity) context.server().getWorld(context.player().getWorld().getRegistryKey()).getEntityById(payload.entityId());
            StatuePosingUtil.flip(statue);
        });
    }
}
