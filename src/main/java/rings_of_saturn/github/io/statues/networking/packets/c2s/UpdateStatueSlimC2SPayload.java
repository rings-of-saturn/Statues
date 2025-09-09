package rings_of_saturn.github.io.statues.networking.packets.c2s;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public record UpdateStatueSlimC2SPayload(int entityId, boolean newValue) implements CustomPayload {
	public static final Identifier UPDATE_STATUE_SLIM_PAYLOAD_ID = Identifier.of(MOD_ID, "update_statue_slim");
	public static final Id<UpdateStatueSlimC2SPayload> ID = new Id<>(UPDATE_STATUE_SLIM_PAYLOAD_ID);
	public static final PacketCodec<RegistryByteBuf, UpdateStatueSlimC2SPayload> CODEC = PacketCodec.tuple(
			PacketCodecs.INTEGER, UpdateStatueSlimC2SPayload::entityId,
			PacketCodecs.BOOL, UpdateStatueSlimC2SPayload::newValue,
			UpdateStatueSlimC2SPayload::new);

	@Override
	public Id<? extends CustomPayload> getId() {
		return ID;
	}
}