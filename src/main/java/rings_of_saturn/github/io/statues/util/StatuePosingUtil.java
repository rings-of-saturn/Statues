package rings_of_saturn.github.io.statues.util;

import rings_of_saturn.github.io.statues.entity.components.ModComponents;
import rings_of_saturn.github.io.statues.entity.custom.StatueEntity;

public class StatuePosingUtil {
    public static float[] getArmRot(StatueEntity entity, boolean right){
        if(right)
            return ModComponents.RIGHT_ARM_ANGLE.get(entity).getValue();
        else
            return ModComponents.LEFT_ARM_ANGLE.get(entity).getValue();
    }

    public static float[] getLegRot(StatueEntity entity, boolean right){
        if(right)
            return ModComponents.RIGHT_LEG_ANGLE.get(entity).getValue();
        else
            return ModComponents.LEFT_LEG_ANGLE.get(entity).getValue();
    }

    public static float[] getHeadRot(StatueEntity entity){
        return ModComponents.HEAD_ANGLE.get(entity).getValue();
    }

    public static float[] getBodyRot(StatueEntity entity){
        return ModComponents.BODY_ANGLE.get(entity).getValue();
    }

    public static boolean getSlim(StatueEntity entity){
        return ModComponents.SLIM.get(entity).getValue();
    }

    public static void setSlim(StatueEntity entity, boolean newValue) {
        ModComponents.SLIM.get(entity).set(newValue);
    }

    public static void  setStatueRot(StatueEntity entity, float newValue, byte bodyPart, byte axis) {
        float[] newArray = getStatueRot(entity, bodyPart);
        newArray[axis] = newValue;
        //fuck you switch statements
        if(bodyPart == 0)
            ModComponents.RIGHT_ARM_ANGLE.get(entity).set(newArray);
        if(bodyPart == 1)
            ModComponents.LEFT_ARM_ANGLE.get(entity).set(newArray);
        if(bodyPart == 2)
            ModComponents.RIGHT_LEG_ANGLE.get(entity).set(newArray);
        if(bodyPart == 3)
            ModComponents.LEFT_LEG_ANGLE.get(entity).set(newArray);
        if(bodyPart == 4)
            ModComponents.HEAD_ANGLE.get(entity).set(newArray);
        if(bodyPart == 5)
            ModComponents.BODY_ANGLE.get(entity).set(newArray);
    }

    public static float[] getStatueRot(StatueEntity entity, byte bodyPart) {
        //fuck you switch statements
        if(bodyPart == 0)
            return ModComponents.RIGHT_ARM_ANGLE.get(entity).getValue();
        if(bodyPart == 1)
            return ModComponents.LEFT_ARM_ANGLE.get(entity).getValue();
        if(bodyPart == 2)
            return ModComponents.RIGHT_LEG_ANGLE.get(entity).getValue();
        if(bodyPart == 3)
            return ModComponents.LEFT_LEG_ANGLE.get(entity).getValue();
        if(bodyPart == 4)
            return ModComponents.HEAD_ANGLE.get(entity).getValue();
        if(bodyPart == 5)
            return ModComponents.BODY_ANGLE.get(entity).getValue();
        return ModComponents.RIGHT_ARM_ANGLE.get(entity).getValue();
    }

    public static float getStatueRotInAxis(StatueEntity entity,byte bodyPart, byte axis) {
        return getStatueRot(entity,bodyPart)[axis];
    }
}
