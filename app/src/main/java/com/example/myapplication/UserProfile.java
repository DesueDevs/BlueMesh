package com.example.myapplication;


import com.example.myapplication.Mesh.MeshProfile;
import com.example.myapplication.Utility.EncryptionRSA;

import java.security.CryptoPrimitive;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.UUID;

public class UserProfile {
    private static UserProfile INSTANCE = null;
    private String userID;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    private UserProfile() {
        // Generate RSA key pair
        try {
            var keyPair = EncryptionRSA.generateRSAKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
            this.userID = UUID.randomUUID().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static UserProfile getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserProfile();
        }
        return INSTANCE;
    }

}
