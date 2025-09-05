package rings_of_saturn.github.io.statues.entity.components.custom;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import rings_of_saturn.github.io.statues.entity.components.custom.types.IntArrayComponent;

public class AngleComponent implements IntArrayComponent {
    private int[] value = new int[3];
    @Override
    public int[] getValue() {
        return this.value;
    }


    @Override
    public void set(int[] amount) {
        this.value = amount;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.value = nbtCompound.getIntArray("value");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putIntArray("value", this.value);
    }
}
