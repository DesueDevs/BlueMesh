package com.example.myapplication.Mesh;

import com.example.myapplication.Utility.EncryptionHash;
import org.jetbrains.annotations.NotNull;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

public class MeshProfile {
    public String id;
    public PublicKey publicKey;

    public MeshProfile(PublicKey publicKey) {
        this.publicKey = publicKey;
        this.id = EncryptionHash.hashBytes(publicKey.getEncoded());
    }

    public byte[] getBytes(){
        String dataString = "[" + this.publicKey.toString() + "|" + this.id + "]";
        return dataString.getBytes();
    }
    public MeshProfile BytesToProfile(@NotNull byte[] profileBytes){
        String dataString = Arrays.toString(profileBytes);
        return null;
    }
}
