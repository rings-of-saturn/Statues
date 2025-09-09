package rings_of_saturn.github.io.statues.mixin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.statues.entity.ModEntities;
import rings_of_saturn.github.io.statues.networking.packets.s2c.OpenStatueScreenS2CPayload;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Unique
    private final PlayerEntity thisAsPlayer = (PlayerEntity)(Object)this;
    @Inject(method = "interact", at = @At("TAIL"))
    private void openStatueScreen(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        if(entity.getType() == ModEntities.STATUE && !thisAsPlayer.getWorld().isClient()) {
            OpenStatueScreenS2CPayload payload = new OpenStatueScreenS2CPayload(entity.getId());
            ServerPlayerEntity serverPlayer = thisAsPlayer.getServer().getPlayerManager().getPlayer(thisAsPlayer.getUuid());
            if (serverPlayer != null) {
                ServerPlayNetworking.send(serverPlayer, payload);
            }
        }
    }
}
