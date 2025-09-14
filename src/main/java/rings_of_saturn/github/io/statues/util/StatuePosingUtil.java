package rings_of_saturn.github.io.statues.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import rings_of_saturn.github.io.statues.entity.components.ModComponents;

import java.io.Serializable;

public class StatuePosingUtil {
    public static float[] getArmRot(Entity entity, boolean right){
        if(right)
            return ModComponents.RIGHT_ARM_ANGLE.get(entity).getValue();
        else
            return ModComponents.LEFT_ARM_ANGLE.get(entity).getValue();
    }

    public static float[] getLegRot(Entity entity, boolean right){
        if(right)
            return ModComponents.RIGHT_LEG_ANGLE.get(entity).getValue();
        else
            return ModComponents.LEFT_LEG_ANGLE.get(entity).getValue();
    }

    public static float[] getHeadRot(Entity entity){
        return ModComponents.HEAD_ANGLE.get(entity).getValue();
    }

    public static float[] getBodyRot(Entity entity){
        return ModComponents.BODY_ANGLE.get(entity).getValue();
    }

    public static boolean getSlim(Entity entity){
        return ModComponents.SLIM.get(entity).getValue();
    }

    public static void setSlim(Entity entity, boolean newValue) {
        ModComponents.SLIM.get(entity).set(newValue);
    }

    public static void  setStatueRot(Entity entity, float newValue, byte bodyPart, byte axis) {
        if(bodyPart == 6)
            entity.setYaw(newValue);
        else{
            float[] newArray = (float[]) getStatueRot(entity, bodyPart);
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
    }

    public static Serializable getStatueRot(Entity entity, byte bodyPart) {
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
        if(bodyPart == 6)
            return entity.getYaw();
        return ModComponents.RIGHT_ARM_ANGLE.get(entity).getValue();
    }

    public static float getStatueRotInAxis(Entity entity, byte bodyPart, byte axis) {
        return ((float[])getStatueRot(entity,bodyPart))[axis];
    }

    public static void copyFromPlayer(Entity entity, PlayerEntity player){
        entity.setYaw(ModComponents.YAW.get(player).getValue());
        for (byte i = 0; i <= 5; i++) {
            for (byte j = 0; j <= 2; j++) {
                setStatueRot(entity, getStatueRotInAxis(player, i, j), i, j);
            }
        }
    }

    public static void copyToPlayer(Entity entity, PlayerEntity player){
        ModComponents.YAW.get(player).set(entity.getYaw());
        for (byte i = 0; i <= 5; i++) {
            for (byte j = 0; j <= 2; j++) {
                setStatueRot(player, getStatueRotInAxis(entity, i, j), i, j);
            }
        }
    }

    public static float[][] getStatueRots(Entity entity){
        float[][] value = new float[6][3];
        for (byte i = 0; i < 5; i++) {
            for (byte j = 0; j < 2; j++) {
                value[i][j] = getStatueRotInAxis(entity, i, j);
            }
        }
        return value;
    }

    public static void setStatueRots(Entity entity, float[][] value){
        for (byte i = 0; i < 5; i++) {
            for (byte j = 0; j < 2; j++) {
                setStatueRot(entity, value[i][j], i, j);
            }
        }
    }

    public static void flip(Entity entity){
        float[][] fakeStatue =  getStatueRots(entity);
        entity.setYaw(entity.getYaw()*-1);
        setStatueRot(entity,  fakeStatue[4][0] - (6.25f/2), (byte) 4, (byte) 0);
        setStatueRot(entity,  fakeStatue[4][1] - (6.25f/2), (byte) 4, (byte) 1);
        setStatueRot(entity,  fakeStatue[4][2] - (6.25f/2), (byte) 4, (byte) 2);
        for (byte i = 0; i <= 3; i++) {
            for (byte j = 0; j <= 2; j++) {
                float newRot = 0;
                if(i == 0) {
                    newRot = fakeStatue[1][j];
                }
                if(i == 1) {
                    newRot = fakeStatue[0][j];
                }
                if(i == 2) {
                    newRot = fakeStatue[3][j];
                }
                if(i == 3) {
                    newRot = fakeStatue[2][j];
                }
                setStatueRot(entity, newRot, i, j);
            }
        }
    }

}
