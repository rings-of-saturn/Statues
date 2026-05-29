package rings_of_saturn.github.io.statues.entity.components.custom;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import rings_of_saturn.github.io.statues.entity.components.ModComponents;
import rings_of_saturn.github.io.statues.entity.components.custom.types.FloatArrayComponent;

import java.util.Arrays;

public class AngleComponent implements FloatArrayComponent, AutoSyncedComponent {
    private float[] value = new float[3];
    private static final int floatPrecision = 100;
    private final Object provider;

    public AngleComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public float[] getValue() {
        return this.value;
    }


    @Override
    public void set(float[] amount) {
        this.value = amount;
        ModComponents.RIGHT_ARM_ANGLE.sync(this.provider);
        ModComponents.LEFT_ARM_ANGLE.sync(this.provider);
        ModComponents.LEFT_LEG_ANGLE.sync(this.provider);
        ModComponents.RIGHT_LEG_ANGLE.sync(this.provider);
        ModComponents.HEAD_ANGLE.sync(this.provider);
        ModComponents.BODY_ANGLE.sync(this.provider);
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        float[] newArray = new float[3];
        newArray[0] = (float) nbtCompound.getIntArray("value")[0] / floatPrecision;
        newArray[1] = (float) nbtCompound.getIntArray("value")[1] / floatPrecision;
        newArray[2] = (float) nbtCompound.getIntArray("value")[2] / floatPrecision;
        this.value = newArray;
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        int[] newArray = new int[3];
        newArray[0] = (int) (this.value[0] * floatPrecision);
        newArray[1] = (int) (this.value[1] * floatPrecision);
        newArray[2] = (int) (this.value[2] * floatPrecision);
        nbtCompound.putIntArray("value", newArray);
    }
}
