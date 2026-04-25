package com.example.myapplication;


import java.security.CryptoPrimitive;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.UUID;

public class UserProfile {
    private static UserProfile INSTANCE = null;
    public static UserProfile getINSTANCE()
    {
        if (INSTANCE == null){
            INSTANCE = new UserProfile();
        }
        return INSTANCE;
    }
    //PlaceHolder for the device user's user profile
    private UUID userID = UUID.randomUUID();
    private int privateKey = 0;
    //TODO implement key gen and key storage for future sessions
    private int publicKey = 1;

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
