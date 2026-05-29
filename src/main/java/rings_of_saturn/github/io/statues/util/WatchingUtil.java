package rings_of_saturn.github.io.statues.util;

import net.minecraft.entity.Entity;
import rings_of_saturn.github.io.statues.entity.components.ModComponents;

public class WatchingUtil {
    public static void setWatching(Entity entity, boolean newValue) {
        ModComponents.WATCHING.get(entity).set(newValue);
    }

    public static boolean getWatching(Entity entity){
        return ModComponents.WATCHING.get(entity).getValue();
    }
}
