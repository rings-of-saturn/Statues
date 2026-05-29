package rings_of_saturn.github.io.statues.entity.components.custom;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import rings_of_saturn.github.io.statues.entity.components.ModComponents;
import rings_of_saturn.github.io.statues.entity.components.custom.types.BooleanComponent;

public class WatchingComponent implements BooleanComponent, AutoSyncedComponent {
    private boolean value;
    private final Object provider;

    public WatchingComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public boolean getValue() {
        return this.value;
    }

    @Override
    public void set(boolean newValue) {
        this.value = newValue;
        ModComponents.WATCHING.sync(provider);
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
