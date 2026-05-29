package rings_of_saturn.github.io.statues.entity.custom;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
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
import rings_of_saturn.github.io.statues.util.MathUtil;
import rings_of_saturn.github.io.statues.util.StatuePosingUtil;
import rings_of_saturn.github.io.statues.util.WatchingUtil;

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
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public Box getBoundingBox(EntityPose pose) {
        return new Box(0.0F, 0.0F, 0.0F, 1.5F,1.8f, 1.5F);
    }

    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if(stack.isEmpty() && player.isSneaking()){
            if(!player.getWorld().isClient()) {
                OpenStatueScreenS2CPayload payload = new OpenStatueScreenS2CPayload(this.getId());
                ServerPlayerEntity serverPlayer = player.getServer().getPlayerManager().getPlayer(player.getUuid());
                if (serverPlayer != null) {
                    ServerPlayNetworking.send(serverPlayer, payload);
                }
            }
        } else {
            if(!player.getWorld().isClient() && !this.getWorld().isClient()) {
                if(this.getSlotFromPosition(hitPos) == EquipmentSlot.CHEST
                        && MathUtil.isInRange(player.getFacing().getHorizontal(), this.getFacing().getHorizontal(), 4)){
                    if(WatchingUtil.getWatching(this)) {
                        player.giveItemStack(new ItemStack(Items.ENDER_EYE, 1));
                        WatchingUtil.setWatching(this, false);
                        return ActionResult.SUCCESS_NO_ITEM_USED;
                    } else if(stack.getItem() == Items.ENDER_EYE){
                        stack.decrementUnlessCreative(1, player);
                        WatchingUtil.setWatching(this, true);
                        player.sendMessage(Text.of("Ender Eye"));
                        return ActionResult.CONSUME_PARTIAL;
                    }
                }
                player.sendMessage(Text.of(this.getSlotFromPosition(hitPos).name() + ", " + this.getSlotFromPosition(hitPos).getType().name()));
                this.equip(player, this.getSlotFromPosition(hitPos), stack.copyWithCount(1), hand);
                stack.decrementUnlessCreative(1, player);
                return ActionResult.CONSUME;
            }
        }
        player.sendMessage(Text.of(String.valueOf(hitPos)), true);
        return ActionResult.FAIL;
    }

    //region Watching Feature

    @Override
    public void tick() {
        if(WatchingUtil.getWatching(this)) {
            if (!this.getWorld().isClient() && this.getWorld().isPlayerInRange(this.getX(), this.getY(), this.getZ(), 8)) {
                StatuePosingUtil.setStatueRot(this, MathUtil.lookAt(this.getPos(), this.getWorld().getClosestPlayer(this, 8).getPos())+this.getYaw(), (byte) 4, (byte) 1);
            }
        }
        super.tick();
    }


    //endregion

    //region Equipment
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
        if (d >= 0 && d <= 0.25) {
            equipmentSlot = EquipmentSlot.FEET;
        } else if (d >= 0.25 && d <= 0.7) {
            equipmentSlot = EquipmentSlot.LEGS;
        } else if (d >= 1 && d <= 1.5) {
            equipmentSlot = EquipmentSlot.CHEST;
        } else if ((d >= 0.7 && d <= 1) && this.hasStackEquipped(EquipmentSlot.MAINHAND)) {
            equipmentSlot = EquipmentSlot.OFFHAND;
        } else if (d >= 1.5) {
            equipmentSlot = EquipmentSlot.HEAD;
        }

        return equipmentSlot;
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
    protected void dropInventory() {
        for (int i = 0; i < armorItems.toArray().length; i++) {
            this.dropStack(armorItems.get(i));
            armorItems.set(i, ItemStack.EMPTY);
        }

        for (int i = 0; i < heldItems.toArray().length; i++) {
            this.dropStack(heldItems.get(i));
            heldItems.set(i, ItemStack.EMPTY);
        }

        if(WatchingUtil.getWatching(this)){
            this.dropStack(new ItemStack(Items.ENDER_EYE, 1));
        }
    }

    //endregion


    //region Invulnerability & Unmoving

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
        if(source.getAttacker().isPlayer() && source.getWeaponStack().isIn(ItemTags.PICKAXES)){
            this.kill();
            if(source.getWeaponStack().isDamageable()){
                source.getWeaponStack().damage(1, this, EquipmentSlot.MAINHAND);
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    protected void pushAway(Entity entity) {
    }

    @Override
    public ProjectileDeflection getProjectileDeflection(ProjectileEntity projectile) {
        return ProjectileDeflection.SIMPLE;
    }

    @Override
    public void kill() {
        if(!this.getWorld().isClient()) {
            this.getWorld().getServer().getWorld(this.getWorld().getRegistryKey())
                    .spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), this.getX(), this.getY(), this.getZ(), 20, 0.5, 1.5, 0.5, 0);
            this.getWorld().getServer().getWorld(this.getWorld().getRegistryKey())
                    .spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY()+1, this.getZ(), 6, 0.25, 0.75, 0.25, 0);
            this.dropInventory();
        }
        this.remove(RemovalReason.KILLED);
        this.emitGameEvent(GameEvent.ENTITY_DIE);
    }

    static {
        RIDEABLE_MINECART_PREDICATE = (entity) -> entity instanceof AbstractMinecartEntity && ((AbstractMinecartEntity)entity).getMinecartType() == net.minecraft.entity.vehicle.AbstractMinecartEntity.Type.RIDEABLE;
    }
    //endregion
}
