package rings_of_saturn.github.io.statues;

import net.fabricmc.api.ModInitializer;

import static rings_of_saturn.github.io.statues.entity.ModEntities.registerEntities;
import static rings_of_saturn.github.io.statues.item.ModItems.registerModItems;
import static rings_of_saturn.github.io.statues.networking.ModPacketReceivers.registerReceivers;
import static rings_of_saturn.github.io.statues.networking.ModPackets.registerPackets;

public class Statues implements ModInitializer {

    public static final String MOD_ID = "statues";

    @Override
    public void onInitialize() {
        registerEntities();
        registerModItems();
        registerPackets();
        registerReceivers();
    }
}
