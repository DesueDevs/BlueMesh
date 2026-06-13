package com.example.myapplication.Utility;

import java.security.Key;

public class EncryptionAES {
    // TODO: change to actual AES functions
    public static byte[] encryptAES(byte[] data, Key AESkey) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, AESkey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.err.println("Attempted to encrypt data with RSA publi key but had: " + e);
            return null;
        }
    }
    public static byte[] decryptAES(byte[] data, Key AESkey) {
        try {

            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, AESkey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.err.println("attempted RSA decrypt but data isnt able to decrypted due to: " + e);
            return null;
        }
    }
}
