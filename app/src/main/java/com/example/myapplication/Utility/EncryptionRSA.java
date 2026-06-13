package com.example.myapplication.Utility;

import java.security.KeyPair;

public class EncryptionRSA {
    public static KeyPair generateRSAKeyPair() {
        try {
            java.security.KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Error generating RSA key pair", e);
        }
    }
    // RSA Utility
    public static byte[] encryptRSA(byte[] data, java.security.PublicKey publicKey) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.err.println("Attempted to encrypt data with RSA publi key but had: " + e);
            return null;
        }
    }
    public static byte[] decryptRSA(byte[] data, java.security.PrivateKey privateKey) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.err.println("attempted RSA decrypt but data isnt able to decrypted due to: " + e);
            return null;
        }
    }
}
