package com.apsit.toll.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.apsit.toll.data.database.TollContract.TollEntry;

/**
 * Created by adityathanekar on 18/07/15.
 */
public class TollDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "toll.db";

    public TollDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TOLL_TABLE = "CREATE TABLE " + TollEntry.TABLE_NAME + " (" +
                TollEntry._ID + " INTEGER PRIMARY KEY, " +
                TollEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                TollEntry.COLUMN_STATE + " TEXT, " +
                TollEntry.COLUMN_COUNTRY + " TEXT, " +
                TollEntry.COLUMN_ADDRESS + " TEXT, " +
                TollEntry.COLUMN_PLACE_ID + " TEXT, " +
                TollEntry.COLUMN_TWO_AXLE + " REAL, " +
                TollEntry.COLUMN_LCV + " REAL, " +
                TollEntry.COLUMN_TWO_AXLE_HEAVY + " REAL, " +
                TollEntry.COLUMN_UPTO_THREE_AXLE + " REAL, " +
                TollEntry.COLUMN_FOUR_AXLE_MORE + " REAL," +
                TollEntry.COLUMN_LATITUDE + " REAL, " +
                TollEntry.COLUMN_LONGITUDE + " REAL" +
                " );";

        db.execSQL(SQL_CREATE_TOLL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TollEntry.TABLE_NAME);
        onCreate(db);
    }
}
