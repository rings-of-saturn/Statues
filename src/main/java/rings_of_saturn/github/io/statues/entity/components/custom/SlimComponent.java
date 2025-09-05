package rings_of_saturn.github.io.statues.entity.components.custom;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import rings_of_saturn.github.io.statues.entity.components.custom.types.IntComponent;

public class AngleComponent implements IntComponent {
    private int value = 100;
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public void decrement() {
        this.value--;
    }

    @Override
    public void decrement(int amount) {
        this.value -= amount;
    }

    @Override
    public void increment(int amount) {
        this.value += amount;
    }

    @Override
    public void set(int amount) {
        this.value = amount;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.value = nbtCompound.getInt("value");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("value", this.value);
    }
}
