package in.levelup.colorreader.database;

import android.provider.BaseColumns;

import io.michaelrocks.paranoid.Obfuscate;

/**
 * Created by gauravmittal on 11/10/17.
 */
@Obfuscate
public final class Notification {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Notification() {
    }
    @Obfuscate
    /* Inner class that defines the trial module table contents */
    public static class NotificationEntry implements BaseColumns {
        public static final String TABLE_NAME = "notifications";
        public static final String COLUMN_NAME_MESSAGE_ID = "message_id";
        public static final String COLUMN_NAME_FROM = "from_id";
        public static final String COLUMN_NAME_TO = "to_id";
        public static final String COLUMN_NAME_SENT_TIME = "sent_time";
        public static final String COLUMN_NAME_RECEIVED_TIME = "received_time";
        public static final String COLUMN_NAME_MESSAGE_TYPE = "message_type";
        public static final String COLUMN_NAME_MESSAGE_CUSTOM_TYPE = "message_custom_type";
        public static final String COLUMN_NAME_ANDROID_CHANNEL_ID = "android_channel_id";
        public static final String COLUMN_NAME_LABEL = "label";
        public static final String COLUMN_NAME_NOTIFICATION_DATA = "notification_data";
        public static final String COLUMN_NAME_NOTIFICATION_TITLE = "notification_title";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_EXPIRY = "expiry";

    }

    public static final String SQL_CREATE_NOTIFICATION_ENTRIES =
            "CREATE TABLE " + NotificationEntry.TABLE_NAME + " (" +
                    NotificationEntry._ID + " INTEGER PRIMARY KEY," +
                    NotificationEntry.COLUMN_NAME_MESSAGE_ID + " TEXT," +
                    NotificationEntry.COLUMN_NAME_FROM + " TEXT," +
                    NotificationEntry.COLUMN_NAME_TO + " TEXT," +
                    NotificationEntry.COLUMN_NAME_SENT_TIME + " TEXT," +
                    NotificationEntry.COLUMN_NAME_RECEIVED_TIME + " TEXT," +
                    NotificationEntry.COLUMN_NAME_MESSAGE_TYPE + " TEXT," +
                    NotificationEntry.COLUMN_NAME_MESSAGE_CUSTOM_TYPE + " TEXT," +
                    NotificationEntry.COLUMN_NAME_ANDROID_CHANNEL_ID + " TEXT," +
                    NotificationEntry.COLUMN_NAME_LABEL + " TEXT," +
                    NotificationEntry.COLUMN_NAME_NOTIFICATION_DATA + " TEXT," +
                    NotificationEntry.COLUMN_NAME_NOTIFICATION_TITLE + " TEXT," +
                    NotificationEntry.COLUMN_NAME_DATA + " TEXT," +
                    NotificationEntry.COLUMN_NAME_EXPIRY + " INTEGER," +

                    "unique (" + NotificationEntry.COLUMN_NAME_MESSAGE_ID + "))";

}
