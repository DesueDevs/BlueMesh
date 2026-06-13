package com.example.myapplication.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionHash {
    public static String hashString(String input) {
        return hashBytes(input.getBytes());
    }
    public static String hashBytes(byte[] bytes){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(bytes);
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
