package com.apsit.toll.data.network.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by adityathanekar on 28/03/17.
 */

public class TollSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static TollSyncAdapter sSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("Aditya", "onCreate - SunshineSyncService");
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null)
                sSyncAdapter = new TollSyncAdapter(getApplicationContext(), true);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}