package com.example.myapplication.DataStruts;

import com.example.myapplication.Mesh.MeshProfile;
import com.example.myapplication.UserProfile;
import com.example.myapplication.Utility.EncryptionRSA;
import com.example.myapplication.Utility.EncryptionAES;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.ArrayList;

public class Message {
    private UserProfile profile = UserProfile.getINSTANCE();
    public MeshProfile Sender = null;
    public MeshProfile Recipient = null;
    public byte[] KeyEncrypted = null;
    public Key KeyDecrypted = null;
    public byte[] textEncrypted = null;
    public byte[] textDecrypted = null;

    // No privilege message
    // received this message and is not the one sending it
    public Message (MeshProfile Sender, MeshProfile Recipient, byte[] KeyEncrypted, byte[] textEncrypted){
        this.Sender = Sender;
        this.Recipient = Recipient;
        this.KeyEncrypted = KeyEncrypted;
        this.textEncrypted = textEncrypted;

        if (Recipient == this.profile.meshProfile){
            decryptMessage();
        }
    }
    // Privileged message
    // user is the one sending the message and constructing it
    public Message (MeshProfile Recipient, Key keyDecrypted, byte[] textDecrypted){
        this.Sender = this.profile.meshProfile;
        this.Recipient = Recipient;
        this.KeyDecrypted =  keyDecrypted;
        this.textDecrypted = textDecrypted;

        encryptMessage();
    }

    public byte[] getSendingBytes(){
        // Combine Sender, Recipient, keyEncrypted, and MessageEncrypted
        ArrayList<Byte[]> senderBytes = [this.Sender.getBytes(), this.Recipient.getBytes(), this.KeyEncrypted, this.textEncrypted];
        return null;
    }
    public void encryptMessage(){
        if (Recipient == null || KeyDecrypted == null || textDecrypted == null) return;

        this.KeyEncrypted = EncryptionRSA.encryptRSA(this.KeyDecrypted.getEncoded(), Recipient.publicKey);
        this.textEncrypted = EncryptionAES.encryptAES(textDecrypted, this.KeyDecrypted);
    }

    public void decryptMessage(){
        // null check
        if (this.Recipient == null || this.Sender == null || this.profile == null || this.textEncrypted == null){
            System.err.println("Unable to decrypt message due to some fields being null");
            return;
        }
        // Recipent check
        if (this.Recipient.id != this.profile.userID){
            System.err.println(
                    "Unable to decrypt message as recipient for " + this
                            + " is " + this.Recipient.id + " with public key: " + this.Recipient.publicKey
            );
            return;
        }
        // public key match check
        if (this.Recipient.publicKey != this.profile.publicKey){
            System.err.println(
                    "Unable to decrypt message as recipient for " + this
                            + " is " + this.Recipient.id + " with public key: " + this.Recipient.publicKey
            );
            return;
        }

        Key AESkey = null;
        if (this.KeyDecrypted != null){
            AESkey = KeyDecrypted;
        }else {
            byte[] keyBytes = EncryptionRSA.decryptRSA(this.KeyEncrypted, this.profile.privateKey);
            if (keyBytes == null){
                System.err.println(
                        "Unable to decrypt AES key"
                );
                return;
            }else {
                AESkey = new SecretKeySpec(keyBytes, "AES");
                this.KeyDecrypted = AESkey;
            }
        }
        this.textDecrypted = EncryptionAES.decryptAES(this.textEncrypted, AESkey);
    }
}
