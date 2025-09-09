package rings_of_saturn.github.io.statues.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Arm;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import rings_of_saturn.github.io.statues.entity.ModEntities;

public class StatueEntity extends LivingEntity {
    private final DefaultedList<ItemStack> armorItems;
    private final DefaultedList<ItemStack> heldItems;

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
}
