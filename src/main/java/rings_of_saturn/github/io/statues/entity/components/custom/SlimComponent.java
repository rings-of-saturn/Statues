package rings_of_saturn.github.io.statues.entity.components.custom;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import rings_of_saturn.github.io.statues.entity.components.custom.types.BooleanComponent;

public class SlimComponent implements BooleanComponent {
    private boolean value;
    @Override
    public boolean getValue() {
        return this.value;
    }

    @Override
    public void set(boolean newValue) {
        this.value = newValue;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.value = nbtCompound.getBoolean("value");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("value", this.value);
    }
}
