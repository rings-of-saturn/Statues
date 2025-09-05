package rings_of_saturn.github.io.statues.util;

import rings_of_saturn.github.io.statues.entity.components.ModComponents;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;

public class StatuePosingUtil {
    public static int[] getArmRot(StatueEntity entity, boolean right){
        if(right)
            return ModComponents.RIGHT_ARM_ANGLE.get(entity).getValue();
        else
            return ModComponents.LEFT_ARM_ANGLE.get(entity).getValue();
    }

    public static int[] getLegRot(StatueEntity entity, boolean right){
        if(right)
            return ModComponents.RIGHT_LEG_ANGLE.get(entity).getValue();
        else
            return ModComponents.LEFT_LEG_ANGLE.get(entity).getValue();
    }

    public static int[] getHeadRot(StatueEntity entity){
        return ModComponents.HEAD_ANGLE.get(entity).getValue();
    }

    public static int[] getBodyRot(StatueEntity entity){
        return ModComponents.BODY_ANGLE.get(entity).getValue();
    }

    public static boolean getSlim(StatueEntity entity){
        return ModComponents.SLIM.get(entity).getValue();
    }
}
