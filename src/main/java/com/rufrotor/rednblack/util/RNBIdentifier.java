package com.rufrotor.rednblack.util;

import com.rufrotor.rednblack.RedNBlack;
import net.minecraft.util.Identifier;

public class RNBIdentifier {
    public static Identifier ofRNB(String id) {
        return Identifier.of(RedNBlack.MOD_ID, id);
    }
}
