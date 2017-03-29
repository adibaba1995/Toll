package com.apsit.toll.data.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by adityathanekar on 18/07/15.
 */
public class TollContract {

    public static final String CONTENT_AUTHORITY = "com.apsit.toll";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TOLLS = "tolls";

    public static long getTollIdFromUri(Uri uri) {
        return Long.valueOf(uri.getPathSegments().get(1));
    }

    public static final class TollEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TOLLS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOLLS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOLLS;

        public static final String TABLE_NAME = "tolls";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PLACE_ID = "place_id";
        public static final String COLUMN_TWO_AXLE = "two_axle";
        public static final String COLUMN_LCV = "lcv";
        public static final String COLUMN_TWO_AXLE_HEAVY = "two_axle_heavy";
        public static final String COLUMN_UPTO_THREE_AXLE = "upto_three_axle";
        public static final String COLUMN_FOUR_AXLE_MORE = "four_axle_more";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";

        public static Uri buildTollsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
