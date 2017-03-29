package com.apsit.toll.data.network.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.apsit.toll.data.database.TollContract.TollEntry;

/**
 * Created by adityathanekar on 28/03/17.
 */

public class TollSyncAdapter extends AbstractThreadedSyncAdapter {

    public final String LOG_TAG = TollSyncAdapter.class.getSimpleName();
    // Interval at which to sync with the weather, in seconds.
    // 60 seconds (1 minute) * 180 = 3 hours
    public static final int SYNC_INTERVAL = 60 * 180;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    private static final int WEATHER_NOTIFICATION_ID = 3004;

    public TollSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.adisoftwares.com/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Log.d("Aditya", "Hello world synced");

        try {
            TollSync tollService = retrofit.create(TollSync.class);
            Call<List<Toll>> call = tollService.getTolls();
            List<Toll> tolls = call.execute().body();

            ContentValues[] values = new ContentValues[tolls.size()];
            for (int i = 0; i < tolls.size(); i++) {
                Toll toll = tolls.get(i);
                values[i] = new ContentValues();
                ContentValues tempValue = values[i];
                tempValue.put(TollEntry._ID, toll.getId());
                tempValue.put(TollEntry.COLUMN_NAME, toll.getName());
                tempValue.put(TollEntry.COLUMN_STATE, toll.getState());
                tempValue.put(TollEntry.COLUMN_COUNTRY, toll.getCountry());
                tempValue.put(TollEntry.COLUMN_ADDRESS, toll.getAddress());
                tempValue.put(TollEntry.COLUMN_PLACE_ID, toll.getPlace_id());
                tempValue.put(TollEntry.COLUMN_TWO_AXLE, toll.getTwo_axle());
                tempValue.put(TollEntry.COLUMN_LCV, toll.getLCV());
                tempValue.put(TollEntry.COLUMN_TWO_AXLE_HEAVY, toll.getTwo_axle_heavy());
                tempValue.put(TollEntry.COLUMN_UPTO_THREE_AXLE, toll.getUpto_three_axle());
                tempValue.put(TollEntry.COLUMN_FOUR_AXLE_MORE, toll.getFour_axle_more());
                tempValue.put(TollEntry.COLUMN_LATITUDE, toll.getLatitude());
                tempValue.put(TollEntry.COLUMN_LONGITUDE, toll.getLongitude());
            }
            provider.bulkInsert(TollEntry.CONTENT_URI, values);
        } catch (Exception e) {
            Log.d("Aditya", "Sync adapter aai jhavli " + e.getMessage());
        }

    }

    public interface TollSync {
        @GET("/tollsync.php")
        Call<List<com.apsit.toll.data.network.pojo.toll.Toll>> getTolls();
    }


    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     *
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if (null == accountManager.getPassword(newAccount)) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        TollSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }
}
