package rings_of_saturn.github.io.statues.item;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.statues.entity.ModEntities;
import rings_of_saturn.github.io.statues.item.custom.StatueItem;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public class ModItems {
    public static Item createItem(Item item, String name){
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name),
                item);
    }

    public static final Item STATUE_ITEM = createItem(
            new StatueItem(new Item.Settings().maxCount(1)),
            "statue");

    public static void registerModItems(){

    }
}
