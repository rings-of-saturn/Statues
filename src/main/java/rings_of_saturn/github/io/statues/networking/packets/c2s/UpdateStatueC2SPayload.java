package rings_of_saturn.github.io.statues.networking.packets.c2s;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public record UpdateStatueC2SPayload(int entityId, float newValue, byte bodyPart, byte axis) implements CustomPayload {
	public static final Identifier GIVE_GLOWING_EFFECT_PAYLOAD_ID = Identifier.of(MOD_ID, "update_statue");
	public static final CustomPayload.Id<UpdateStatueC2SPayload> ID = new CustomPayload.Id<>(GIVE_GLOWING_EFFECT_PAYLOAD_ID);
	public static final PacketCodec<RegistryByteBuf, UpdateStatueC2SPayload> CODEC = PacketCodec.tuple(
			PacketCodecs.INTEGER, UpdateStatueC2SPayload::entityId,
			PacketCodecs.FLOAT, UpdateStatueC2SPayload::newValue,
			PacketCodecs.BYTE, UpdateStatueC2SPayload::bodyPart,
			PacketCodecs.BYTE, UpdateStatueC2SPayload::axis,
			UpdateStatueC2SPayload::new);

	@Override
	public Id<? extends CustomPayload> getId() {
		return ID;
	}
}