package rings_of_saturn.github.io.statues.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public class ModEntities {
    public static EntityType<StatueEntity> STATUE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "statue"),
            EntityType.Builder.create(StatueEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE)
                    .dimensions(0.8f,2f)
                    .build()
    );

    public static void registerEntities(){
        FabricDefaultAttributeRegistry.register(ModEntities.STATUE, StatueEntity.createAttributes());
    }
}
