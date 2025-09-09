package rings_of_saturn.github.io.statues.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import rings_of_saturn.github.io.statues.networking.packets.c2s.UpdateStatueC2SPayload;
import rings_of_saturn.github.io.statues.networking.packets.c2s.UpdateStatueSlimC2SPayload;
import rings_of_saturn.github.io.statues.networking.packets.s2c.OpenStatueScreenS2CPayload;

public class ModPackets {
    public static void registerPackets(){
        PayloadTypeRegistry.playS2C().register(OpenStatueScreenS2CPayload.ID, OpenStatueScreenS2CPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(UpdateStatueC2SPayload.ID, UpdateStatueC2SPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(UpdateStatueSlimC2SPayload.ID, UpdateStatueSlimC2SPayload.CODEC);
    }
}
