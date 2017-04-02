package com.apsit.toll.service;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class Constant {
    public interface ACTION {
        public static String MAIN_ACTION = "com.apsit.toll.action.main";
        public static String ON_THE_GO_ACTION = "com.apsit.toll.action.prev";
//        public static String PLAY_ACTION = "com.apsit.toll.action.play";
//        public static String NEXT_ACTION = "com.apsit.toll.action.next";
        public static String STARTFOREGROUND_ACTION = "com.apsit.toll.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.apsit.toll.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }

    public interface GEOFENCE {
        /**
         * Used to set an expiration time for a geofence. After this amount of time Location Services
         * stops tracking the geofence.
         */
        public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

        /**
         * For this sample, geofences expire after twelve hours.
         */
        public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
                GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
        public static final float GEOFENCE_RADIUS_IN_METERS = 3000; // 1 mile, 1.6 km
    }
}
