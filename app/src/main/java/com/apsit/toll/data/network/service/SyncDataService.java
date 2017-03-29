package com.apsit.toll.data.network.service;

import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by adityathanekar on 28/03/17.
 */

public class SyncDataService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Toast.makeText(this, "Message Recieved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
