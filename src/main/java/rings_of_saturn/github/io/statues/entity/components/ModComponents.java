package rings_of_saturn.github.io.statues.entity.components;

import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import rings_of_saturn.github.io.statues.entity.components.custom.AngleComponent;
import rings_of_saturn.github.io.statues.entity.components.custom.SlimComponent;
import rings_of_saturn.github.io.statues.entity.components.custom.WatchingComponent;
import rings_of_saturn.github.io.statues.entity.components.custom.YawComponent;
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
    public static final ComponentKey<YawComponent> YAW =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "yaw"), YawComponent.class);
    public static final ComponentKey<SlimComponent> SLIM =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "slim"), SlimComponent.class);
    public static final ComponentKey<WatchingComponent> WATCHING =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "watching"), WatchingComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, LEFT_ARM_ANGLE, AngleComponent::new);
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, RIGHT_ARM_ANGLE, AngleComponent::new);
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, LEFT_LEG_ANGLE, AngleComponent::new);
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, RIGHT_LEG_ANGLE, AngleComponent::new);
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, HEAD_ANGLE, AngleComponent::new);
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, BODY_ANGLE, AngleComponent::new);
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, SLIM, SlimComponent::new);
        entityComponentFactoryRegistry.registerFor(StatueEntity.class, WATCHING, WatchingComponent::new);

        entityComponentFactoryRegistry.registerForPlayers(LEFT_ARM_ANGLE, AngleComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(RIGHT_ARM_ANGLE, AngleComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(LEFT_LEG_ANGLE, AngleComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(RIGHT_LEG_ANGLE, AngleComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(HEAD_ANGLE, AngleComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(BODY_ANGLE, AngleComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(YAW, YawComponent::new, RespawnCopyStrategy.ALWAYS_COPY);


    }
}
