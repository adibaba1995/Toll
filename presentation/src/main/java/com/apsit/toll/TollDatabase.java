package com.apsit.toll;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by adityathanekar on 18/03/17.
 */

public class TollDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "toll.db";
    private static final int DATABASE_VERSION = 1;

    public TollDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}