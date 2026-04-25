package com.example.myapplication.DataStruts;

import com.example.myapplication.Mesh.MeshProfile;
import com.example.myapplication.UserProfile;

import java.util.UUID;

public class Message {
    private UserProfile profile = UserProfile.getINSTANCE();
    private MeshProfile Sender = null;
    private MeshProfile Recipient = null;
    private byte[] KeyEncrypted = null;
    private byte[] KeyDecrypted = null;
    private byte[] MessageEncrypted = null;
    private byte[] MessageDecrypted = null;

    public Message (MeshProfile Sender, MeshProfile Recipient, byte[] KeyEncrypted, byte[] MessageEncrypted){
        this.Sender = Sender;
        this.Recipient = Recipient;
        this.KeyEncrypted = KeyEncrypted;
        this.MessageEncrypted = MessageEncrypted;
    }
}
