package in.levelup.colorreader.database;

import android.provider.BaseColumns;

import io.michaelrocks.paranoid.Obfuscate;

/**
 * Created by gauravmittal on 12/09/17.
 */
@Obfuscate
public final class MarketModules {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MarketModules() {
    }
    @Obfuscate
    /* Inner class that defines the trial module table contents */
    public static class TrialModuleEntry implements BaseColumns {
        public static final String TABLE_NAME = "market_modules";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_MOD_ID = "mod_id";
        public static final String COLUMN_NAME_MOD_NAME = "mod_name";
        public static final String COLUMN_NAME_ENABLED = "enabled";
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_END_DATE = "end_date";
        public static final String COLUMN_NAME_SUBS_TYPE = "subs_type";
        public static final String COLUMN_NAME_ACC_STATUS = "acc_status";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_UPDATED_AT = "updated_at";
        public static final String COLUMN_NAME_USER_AUTH_ID = "user_auth_id";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_REFNAME = "ref_name";

    }
    @Obfuscate
    /* Inner class that defines the trial module table contents */
    public static class PurchasedModuleEntry implements BaseColumns {
        public static final String TABLE_NAME = "purchased_modules";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_MOD_ID = "mod_id";
        public static final String COLUMN_NAME_MOD_NAME = "mod_name";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_UPDATED_AT = "updated_at";
        public static final String COLUMN_NAME_USER_AUTH_ID = "user_auth_id";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_REFNAME = "ref_name";

    }
    @Obfuscate
    /* Inner class that defines the trial module table contents */
    public static class RetrialModuleEntry implements BaseColumns {
        public static final String TABLE_NAME = "retrial_modules";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_MOD_ID = "mod_id";
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_TRIALS = "trials";
        public static final String COLUMN_NAME_CODE = "code";

    }


    public static final String SQL_DELETE_MARKET_MODULE_ENTRIES =
            "DROP TABLE IF EXISTS " + TrialModuleEntry.TABLE_NAME;

    public static final String SQL_DELETE_PURCHASED_MODULES_ENTRIES =
            "DELETE TABLE " + PurchasedModuleEntry.TABLE_NAME;

    public static final String SQL_DROP_TRIAL_MODULES_ENTRIES =
            "DROP TABLE IF EXISTS " + TrialModuleEntry.TABLE_NAME;

    public static final String SQL_DROP_PURCHASED_MODULES_ENTRIES =
            "DROP TABLE IF EXISTS " + PurchasedModuleEntry.TABLE_NAME;

    public static final String SQL_CREATE_TRIAL_MODULE_ENTRIES =
            "CREATE TABLE " + TrialModuleEntry.TABLE_NAME + " (" +
                    TrialModuleEntry._ID + " INTEGER PRIMARY KEY," +
                    TrialModuleEntry.COLUMN_NAME_ID + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_MOD_ID + " INTEGER," +
                    TrialModuleEntry.COLUMN_NAME_MOD_NAME + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_ENABLED + " INTEGER," +
                    TrialModuleEntry.COLUMN_NAME_START_DATE + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_END_DATE + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_SUBS_TYPE + " INTEGER," +
                    TrialModuleEntry.COLUMN_NAME_ACC_STATUS + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_CREATED_AT + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_UPDATED_AT + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_USER_AUTH_ID + " INTEGER," +
                    TrialModuleEntry.COLUMN_NAME_STATUS + " TEXT," +
                    TrialModuleEntry.COLUMN_NAME_REFNAME + " TEXT," +

                    "unique (" + TrialModuleEntry.COLUMN_NAME_MOD_ID + "))";

    public static final String SQL_CREATE_PURCHASED_MODULE_ENTRIES =
            "CREATE TABLE " + PurchasedModuleEntry.TABLE_NAME + " (" +
                    PurchasedModuleEntry._ID + " INTEGER PRIMARY KEY," +
                    PurchasedModuleEntry.COLUMN_NAME_ID + " TEXT," +
                    PurchasedModuleEntry.COLUMN_NAME_MOD_ID + " INTEGER," +
                    PurchasedModuleEntry.COLUMN_NAME_MOD_NAME + " TEXT," +
                    PurchasedModuleEntry.COLUMN_NAME_CREATED_AT + " TEXT," +
                    PurchasedModuleEntry.COLUMN_NAME_UPDATED_AT + " TEXT," +
                    PurchasedModuleEntry.COLUMN_NAME_USER_AUTH_ID + " INTEGER," +
                    PurchasedModuleEntry.COLUMN_NAME_STATUS + " TEXT," +
                    PurchasedModuleEntry.COLUMN_NAME_REFNAME + " TEXT," +

                    "unique (" + PurchasedModuleEntry.COLUMN_NAME_MOD_ID + "))";

    public static final String SQL_CREATE_RETRIAL_MODULE_ENTRIES =
            "CREATE TABLE " + RetrialModuleEntry.TABLE_NAME + " (" +
                    RetrialModuleEntry._ID + " INTEGER PRIMARY KEY," +
                    RetrialModuleEntry.COLUMN_NAME_ID + " INTEGER," +
                    RetrialModuleEntry.COLUMN_NAME_MOD_ID + " INTEGER," +
                    RetrialModuleEntry.COLUMN_NAME_START_DATE + " TEXT," +
                    RetrialModuleEntry.COLUMN_NAME_TRIALS + " INTEGER," +
                    RetrialModuleEntry.COLUMN_NAME_CODE + " INTEGER," +
                    "unique (" + RetrialModuleEntry.COLUMN_NAME_MOD_ID + "))";


}
