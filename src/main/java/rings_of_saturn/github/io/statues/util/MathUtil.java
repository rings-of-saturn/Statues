package rings_of_saturn.github.io.statues.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathUtil {

    public static boolean isInRange(float input, float range, float radius){
        return input >= range-radius && input <= range+radius;
    }

    public static float lookAt(Vec3d pos, Vec3d target){
        float angle = 0;
        double d = target.x - pos.x;
        double f = target.z - pos.z;
        angle = (float) MathHelper.atan2(d, f)/-1;
        return angle;
    }

}
