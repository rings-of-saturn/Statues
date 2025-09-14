package rings_of_saturn.github.io.statues.networking.packets.c2s;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public record FlipStatueC2SPayload(int entityId) implements CustomPayload {
	public static final Identifier FLIP_STATUE_PAYLOAD_ID = Identifier.of(MOD_ID, "flip_statue");
	public static final Id<FlipStatueC2SPayload> ID = new Id<>(FLIP_STATUE_PAYLOAD_ID);
	public static final PacketCodec<RegistryByteBuf, FlipStatueC2SPayload> CODEC = PacketCodec.tuple(
			PacketCodecs.INTEGER, FlipStatueC2SPayload::entityId,
			FlipStatueC2SPayload::new);

	@Override
	public Id<? extends CustomPayload> getId() {
		return ID;
	}
}