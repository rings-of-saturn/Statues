package rings_of_saturn.github.io.statues.entity.custom;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import rings_of_saturn.github.io.statues.entity.ModEntities;
import rings_of_saturn.github.io.statues.item.ModItems;
import rings_of_saturn.github.io.statues.networking.packets.s2c.OpenStatueScreenS2CPayload;

import java.util.function.Predicate;

public class StatueEntity extends LivingEntity {
    private final DefaultedList<ItemStack> armorItems;
    private final DefaultedList<ItemStack> heldItems;
    private static final Predicate<Entity> RIDEABLE_MINECART_PREDICATE;

    protected StatueEntity(World world) {
        super(ModEntities.STATUE, world);
        this.heldItems = DefaultedList.ofSize(2, ItemStack.EMPTY);
        this.armorItems = DefaultedList.ofSize(4, ItemStack.EMPTY);
    }

    public StatueEntity(EntityType<? extends StatueEntity> entityType, World world) {
        super(ModEntities.STATUE, world);
        this.heldItems = DefaultedList.ofSize(2, ItemStack.EMPTY);
        this.armorItems = DefaultedList.ofSize(4, ItemStack.EMPTY);
    }

    public static DefaultAttributeContainer createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20f)
                .add(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, 0)
                .add(EntityAttributes.GENERIC_GRAVITY, 1)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 0.5)
                .build();
    }


    @Override
    public @Nullable ItemStack getPickBlockStack() {
        return new ItemStack(ModItems.STATUE_ITEM, 1);
    }

    @Override
    public Box getBoundingBox(EntityPose pose) {
        return new Box(0.0F, 0.0F, 0.0F, 1.5F,1.8f, 1.5F);
    }

    @Override
    protected void tickCramming() {
        for(Entity entity : this.getWorld().getOtherEntities(this, this.getBoundingBox(), RIDEABLE_MINECART_PREDICATE)) {
            if (this.squaredDistanceTo(entity) <= 0.2) {
                entity.pushAwayFrom(this);
            }
        }

    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isSourceCreativePlayer())
            this.kill();
        return super.damage(source, amount);
    }

    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.isEmpty() && player.isSneaking()){
            if(!player.getWorld().isClient()) {
                OpenStatueScreenS2CPayload payload = new OpenStatueScreenS2CPayload(this.getId());
                ServerPlayerEntity serverPlayer = player.getServer().getPlayerManager().getPlayer(player.getUuid());
                if (serverPlayer != null) {
                    ServerPlayNetworking.send(serverPlayer, payload);
                }
            }
        }
        if (!itemStack.isOf(Items.NAME_TAG) && !player.isSneaking()) {
            if (player.isSpectator()) {
                return ActionResult.SUCCESS;
            } else if (player.getWorld().isClient) {
                return ActionResult.CONSUME;
            } else {
                EquipmentSlot equipmentSlot = this.getPreferredEquipmentSlot(itemStack);
                if (itemStack.isEmpty()) {
                    EquipmentSlot equipmentSlot3 = this.getSlotFromPosition(hitPos);
                    if (this.hasStackEquipped(equipmentSlot3) && this.equip(player, equipmentSlot3, itemStack, hand)) {
                        return ActionResult.SUCCESS;
                    }
                } else {
                    if (equipmentSlot.getType() == EquipmentSlot.Type.HAND) {
                        return ActionResult.FAIL;
                    }

                    if (this.equip(player, equipmentSlot, itemStack, hand)) {
                        return ActionResult.SUCCESS;
                    }
                }

                return ActionResult.PASS;
            }
        } else {
            return ActionResult.PASS;
        }
    }

    private boolean equip(PlayerEntity player, EquipmentSlot slot, ItemStack stack, Hand hand) {
        ItemStack itemStack = this.getEquippedStack(slot);
        if (itemStack.isEmpty() && !stack.isEmpty()) {
            this.equipStack(slot, stack.copyWithCount(1));
            stack.decrementUnlessCreative(1, player);
            return true;
        } else if (!stack.isEmpty() && stack.getCount() > 1) {
            this.equipStack(slot, stack.split(1));
            stack.decrementUnlessCreative(1, player);
            return true;
        }
        if(stack.isEmpty() && !itemStack.isEmpty()){
            this.equipStack(slot, stack);
            player.setStackInHand(hand, itemStack);
            return true;
        }
        return false;
    }

    private EquipmentSlot getSlotFromPosition(Vec3d hitPos) {
        EquipmentSlot equipmentSlot = EquipmentSlot.MAINHAND;
        double d = hitPos.y / (double)(this.getScale() * this.getScaleFactor());
        EquipmentSlot equipmentSlot2 = EquipmentSlot.FEET;
        if (d >= 0.1 && d < 0.1 + 0.8 && this.hasStackEquipped(equipmentSlot2)) {
            equipmentSlot = EquipmentSlot.FEET;
        } else if (d >= 0.9 + 0.3 && d < 0.9 + (double)1.0F && this.hasStackEquipped(EquipmentSlot.CHEST)) {
            equipmentSlot = EquipmentSlot.CHEST;
        } else if (d >= 0.4 && d < 0.4 + (double)1.0F && this.hasStackEquipped(EquipmentSlot.LEGS)) {
            equipmentSlot = EquipmentSlot.LEGS;
        } else if (d >= 1.6 && this.hasStackEquipped(EquipmentSlot.HEAD)) {
            equipmentSlot = EquipmentSlot.HEAD;
        } else if (!this.hasStackEquipped(EquipmentSlot.MAINHAND) && this.hasStackEquipped(EquipmentSlot.OFFHAND)) {
            equipmentSlot = EquipmentSlot.OFFHAND;
        }

        return equipmentSlot;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluids() {
        return true;
    }

    @Override
    protected void pushAway(Entity entity) {
    }

    @Override
    public void kill() {
        if(!this.getWorld().isClient()) {
            this.getWorld().getServer().getWorld(this.getWorld().getRegistryKey())
                    .spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), this.getX(), this.getY(), this.getZ(), 20, 0.5, 1.5, 0.5, 0);
            this.getWorld().getServer().getWorld(this.getWorld().getRegistryKey())
                    .spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY(), this.getZ(), 3, 0.25, 0, 0.25, 0);
        }
        this.remove(RemovalReason.KILLED);
        this.emitGameEvent(GameEvent.ENTITY_DIE);
    }

    @Override
    public Iterable<ItemStack> getHandItems() {
        return heldItems;
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return armorItems;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        switch (slot.getType()) {
            case HAND -> {
                return this.heldItems.get(slot.getEntitySlotId());
            }
            case HUMANOID_ARMOR -> {
                return this.armorItems.get(slot.getEntitySlotId());
            }
            default -> {
                return ItemStack.EMPTY;
            }
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        NbtList armorList = new NbtList();

        for(ItemStack itemStack : this.armorItems) {
            armorList.add(itemStack.encodeAllowEmpty(this.getRegistryManager()));
        }

        nbt.put("ArmorItems", armorList);
        NbtList handItemList = new NbtList();

        for(ItemStack itemStack2 : this.heldItems) {
            handItemList.add(itemStack2.encodeAllowEmpty(this.getRegistryManager()));
        }

        nbt.put("HandItems", handItemList);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("ArmorItems", 9)) {
            NbtList nbtList = nbt.getList("ArmorItems", 10);

            for(int i = 0; i < this.armorItems.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                this.armorItems.set(i, ItemStack.fromNbtOrEmpty(this.getRegistryManager(), nbtCompound));
            }
        }

        if (nbt.contains("HandItems", 9)) {
            NbtList nbtList = nbt.getList("HandItems", 10);

            for(int i = 0; i < this.heldItems.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                this.heldItems.set(i, ItemStack.fromNbtOrEmpty(this.getRegistryManager(), nbtCompound));
            }
        }
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        this.processEquippedStack(stack);
        switch (slot.getType()) {
            case HAND -> this.onEquipStack(slot, this.heldItems.set(slot.getEntitySlotId(), stack), stack);
            case HUMANOID_ARMOR -> this.onEquipStack(slot, this.armorItems.set(slot.getEntitySlotId(), stack), stack);
        }
    }

    public boolean canEquip(ItemStack stack) {
        EquipmentSlot equipmentSlot = this.getPreferredEquipmentSlot(stack);
        return this.getEquippedStack(equipmentSlot).isEmpty();
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    static {
        RIDEABLE_MINECART_PREDICATE = (entity) -> entity instanceof AbstractMinecartEntity && ((AbstractMinecartEntity)entity).getMinecartType() == net.minecraft.entity.vehicle.AbstractMinecartEntity.Type.RIDEABLE;
    }
}
