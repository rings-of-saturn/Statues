package rings_of_saturn.github.io.statues.entity.components;

import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import rings_of_saturn.github.io.statues.entity.components.custom.AngleComponent;
import rings_of_saturn.github.io.statues.entity.components.custom.SlimComponent;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<AngleComponent> LEFT_ARM_ANGLE =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "left_arm_angle"), AngleComponent.class);
    public static final ComponentKey<AngleComponent> RIGHT_ARM_ANGLE =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "right_arm_angle"), AngleComponent.class);
    public static final ComponentKey<AngleComponent> RIGHT_LEG_ANGLE =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "right_leg_angle"), AngleComponent.class);
    public static final ComponentKey<AngleComponent> LEFT_LEG_ANGLE =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "left_leg_angle"), AngleComponent.class);
    public static final ComponentKey<AngleComponent> HEAD_ANGLE =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "head_angle"), AngleComponent.class);
    public static final ComponentKey<AngleComponent> BODY_ANGLE =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "body_angle"), AngleComponent.class);
    public static final ComponentKey<SlimComponent> SLIM =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "slim"), SlimComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, LEFT_ARM_ANGLE, statueEntity -> new AngleComponent());
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, RIGHT_ARM_ANGLE, statueEntity -> new AngleComponent());
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, LEFT_LEG_ANGLE, statueEntity -> new AngleComponent());
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, RIGHT_LEG_ANGLE, statueEntity -> new AngleComponent());
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, HEAD_ANGLE, statueEntity -> new AngleComponent());
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, BODY_ANGLE, statueEntity -> new AngleComponent());
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, SLIM, statueEntity -> new SlimComponent());
    }
}
