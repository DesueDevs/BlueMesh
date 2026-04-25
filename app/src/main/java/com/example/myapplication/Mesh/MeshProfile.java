package com.example.myapplication.Mesh;

import com.example.myapplication.Utility.EncryptionHash;

import java.security.PublicKey;
import java.util.Base64;

public class MeshProfile {
    public String id;
    public PublicKey publicKey;

    public MeshProfile(PublicKey publicKey) {
        this.publicKey = publicKey;
        this.id = EncryptionHash.hashString(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
    }

}
