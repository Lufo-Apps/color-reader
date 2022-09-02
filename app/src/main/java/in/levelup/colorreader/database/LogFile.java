package in.levelup.colorreader.database;

import android.provider.BaseColumns;

import io.michaelrocks.paranoid.Obfuscate;

/**
 * Created by gauravmittal on 27/03/18.
 */
@Obfuscate
public class LogFile {

    private LogFile() {
    }

    @Obfuscate
    /* Inner class that defines the trial module table contents */
    public static class LogFileEntry implements BaseColumns {
        public static final String TABLE_NAME = "log_files";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_FILE_NAME = "file_name";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_SIZE = "size";
        public static final String COLUMN_NAME_UPLOADED = "uploaded";

    }

    public static final String SQL_CREATE_LOG_FILE_ENTRIES =
            "CREATE TABLE " + LogFileEntry.TABLE_NAME + " (" +
                    LogFileEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    LogFileEntry.COLUMN_NAME_FILE_NAME + " TEXT," +
                    LogFileEntry.COLUMN_NAME_DATE + " TEXT," +
                    LogFileEntry.COLUMN_NAME_SIZE + " INTEGER," +
                    LogFileEntry.COLUMN_NAME_UPLOADED + " INTEGER," +
                    "unique (" + LogFileEntry.COLUMN_NAME_FILE_NAME + "))";
}
