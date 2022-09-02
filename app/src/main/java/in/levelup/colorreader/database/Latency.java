package in.levelup.colorreader.database;

import android.provider.BaseColumns;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class Latency {


    String condtion, value;

    public Latency(String condtion, String value) {
        this.condtion = condtion;
        this.value = value;
    }
    @Obfuscate
    public static class LatencyEntry implements BaseColumns {
        public static final String TABLE_NAME = "Latency";

        public static final String COLUMN_NAME_CONDITION = "condition";
        public static final String COLUMN_NAME_VALUE = "value";


    }


    public static final String SQL_CREATE_LATENCY_DATA_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + LatencyEntry.TABLE_NAME + " (" +
                    LatencyEntry.COLUMN_NAME_CONDITION + " TEXT PRIMARY KEY," +
                    LatencyEntry.COLUMN_NAME_VALUE + " TEXT" + ")";
}
