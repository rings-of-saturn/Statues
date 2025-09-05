package rings_of_saturn.github.io.statues;

import net.fabricmc.api.ModInitializer;

import static rings_of_saturn.github.io.statues.entity.ModEntities.registerEntities;
import static rings_of_saturn.github.io.statues.item.ModItems.registerModItems;

public class Statues implements ModInitializer {

    public static final String MOD_ID = "statues";

    @Override
    public void onInitialize() {
        registerEntities();
        registerModItems();
    }
}
