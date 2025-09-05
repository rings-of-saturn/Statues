package rings_of_saturn.github.io.fragments.entity.components;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import rings_of_saturn.github.io.fragments.entity.components.custom.FragmentsComponent;
import rings_of_saturn.github.io.fragments.entity.components.custom.GhostComponent;
import rings_of_saturn.github.io.fragments.entity.components.custom.LivesComponent;
import rings_of_saturn.github.io.fragments.entity.components.custom.GhostTimerComponent;

import static rings_of_saturn.github.io.fragments.Fragments.MOD_ID;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<LivesComponent> LIVES =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "lives"), LivesComponent.class);
    public static final ComponentKey<FragmentsComponent> FRAGMENTS =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "fragments"), FragmentsComponent.class);
    public static final ComponentKey<GhostComponent> GHOST =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "ghost"), GhostComponent.class);
    public static final ComponentKey<GhostTimerComponent> TIMER =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "ghost_timer"), GhostTimerComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.beginRegistration(PlayerEntity.class, GHOST).impl(GhostComponent.class).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(GhostComponent::new);
        entityComponentFactoryRegistry.registerForPlayers(LIVES, player -> new LivesComponent(), RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(FRAGMENTS, player -> new FragmentsComponent(), RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(TIMER, player -> new GhostTimerComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
