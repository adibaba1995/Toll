package com.apsit.toll.data.network.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class TollAuthenticatorService extends Service {

    // Instance field that stores the authenticator object
    private TollAuthenticator mAuthenticator;
    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new TollAuthenticator(this);
    }
    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
