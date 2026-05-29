package rings_of_saturn.github.io.statues.entity.components.custom;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import rings_of_saturn.github.io.statues.entity.components.ModComponents;
import rings_of_saturn.github.io.statues.entity.components.custom.types.FloatComponent;

public class YawComponent implements FloatComponent, AutoSyncedComponent {
    private float value;
    private final Object provider;

    public YawComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public float getValue() {
        return this.value;
    }

    @Override
    public void set(float amount) {
        this.value = amount;
        ModComponents.YAW.sync(this.provider);
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.value = nbtCompound.getFloat("value");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putFloat("value", this.value);
    }
}
