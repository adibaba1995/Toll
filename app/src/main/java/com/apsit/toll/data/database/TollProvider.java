package com.apsit.toll.data.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by adityathanekar on 18/07/15.
 */
public class TollProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private TollDbHelper mOpenHelper;

    static final int TOLLS = 100;
    static final int TOLL_WITH_ID = 101;

    private static final SQLiteQueryBuilder sMovieByIdQueryBuilder;

    private static final String sTollWithId =
            TollContract.TollEntry.TABLE_NAME+
                    "." + TollContract.TollEntry._ID + " = ? ";

    static{
        sMovieByIdQueryBuilder = new SQLiteQueryBuilder();
        sMovieByIdQueryBuilder.setTables(TollContract.TollEntry.TABLE_NAME);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new TollDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case TOLL_WITH_ID: {
                retCursor = getTollsById(uri, projection, sortOrder);
                break;
            }
            case TOLLS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        TollContract.TollEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    private Cursor getTollsById(
            Uri uri, String[] projection, String sortOrder) {
        long movieId = TollContract.getTollIdFromUri(uri);

        return sMovieByIdQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sTollWithId,
                new String[]{String.valueOf(movieId)},
                null,
                null,
                sortOrder
        );
    }

    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case TOLLS:
                return TollContract.TollEntry.CONTENT_TYPE;
            case TOLL_WITH_ID:
                return TollContract.TollEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case TOLLS: {
                long _id = db.insert(TollContract.TollEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = TollContract.TollEntry.buildTollsUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        //Note: we should not return uri instead we should use passed in uri because otherwise it will not correctly notify cursors of the change.
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }


    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int returnCount = 0;
        switch (match) {
            case TOLLS:
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(TollContract.TollEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int noOfRowsDeleted = 0;
        if(selection == null)
            selection = "1";
        long id = TollContract.getTollIdFromUri(uri);
        switch(match) {
            case TOLL_WITH_ID:
                noOfRowsDeleted = db.delete(TollContract.TollEntry.TABLE_NAME, TollContract.TollEntry._ID + "=?", new String[]{String.valueOf(id)});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Student: A null value deletes all rows.  In my implementation of this, I only notified
        // the uri listeners (using the content resolver) if the rowsDeleted != 0 or the selection
        // is null.
        // Oh, and you should notify the listeners here.
        if(noOfRowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        // Student: return the actual rows deleted
        return noOfRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    static UriMatcher buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = TollContract.CONTENT_AUTHORITY;

        // 2) Use the addURI function to match each of the types.  Use the constants from
        // WeatherContract to help define the types to the UriMatcher.
        matcher.addURI(authority, TollContract.PATH_TOLLS, TOLLS);
        matcher.addURI(authority, TollContract.PATH_TOLLS + "/#", TOLL_WITH_ID);

        // 3) Return the new matcher!
        return matcher;
    }
}
