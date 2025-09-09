package rings_of_saturn.github.io.statues.networking.packets.s2c;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;

import java.util.UUID;

import static rings_of_saturn.github.io.statues.Statues.MOD_ID;

public record OpenStatueScreenS2CPayload(int statueData) implements CustomPayload {
	public static final Identifier OPEN_STATUE_SCREEN_PAYLOAD_ID = Identifier.of(MOD_ID, "open_statue_screen");
	public static final CustomPayload.Id<OpenStatueScreenS2CPayload> ID = new CustomPayload.Id<>(OPEN_STATUE_SCREEN_PAYLOAD_ID);
	public static final PacketCodec<RegistryByteBuf, OpenStatueScreenS2CPayload> CODEC =
			PacketCodec.tuple(PacketCodecs.INTEGER, OpenStatueScreenS2CPayload::statueData, OpenStatueScreenS2CPayload::new);

	@Override
	public Id<? extends CustomPayload> getId() {
		return ID;
	}
}