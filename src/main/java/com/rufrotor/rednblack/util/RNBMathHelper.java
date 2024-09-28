package com.rufrotor.rednblack.util;

public class RNBMathHelper {
    public static int rangeInt(int min,int max){
        return min + (int)Math.round(Math.random() * ( max - min ));
    }
}
