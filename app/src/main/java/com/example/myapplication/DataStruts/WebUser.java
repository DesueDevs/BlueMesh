package com.example.myapplication.DataStruts;

import java.security.PublicKey;
import java.util.UUID;

public class WebUser {
    public WebUser(UUID userID, PublicKey publicKey, String routeTrace){
        // routeTrace: intended to be the path in which the user can be reached
        //  with each user that acts like a node appending their UUID
        //  end result userID > node1ID > node2ID user recieves
        //  this allows for a quick response
    }
}
