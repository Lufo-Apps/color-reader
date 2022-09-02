package in.levelup.colorreader.util;

import io.michaelrocks.paranoid.Obfuscate;

/**
 * Created by hariharan on 24/6/15.
 */
@Obfuscate
public class Constants {

    //Keys can be found in eyed.dev and under Eye-D
    //https://console.cloud.google.com/apis/credentials?project=eyedpro-151712&authuser=3
    public static final String EYEDPRO_DEV_BROWSER_KEY_GEOCODER = "AIzaSyBSeTbI33l1EgMsNi0TKe5rqNaNXYF7qP0";
    private static final String Eye_D_Pro_Android_21122021_restricted = "AIzaSyBVpL5Z8zbVuqwWZ4-90rlBWEzp8F2M7kE"; //from 21 dec 2021
    public static final String EYEDPRO_ANDROID_CLOUDVISION_KEY = Eye_D_Pro_Android_21122021_restricted;


    public static final int OUT_OF_SERVICE = 0;
    public static final int TEMPORARILY_UNAVAILABLE = 1;
    public static final int AVAILABLE = 2;
    public static final String currentEngineName = "latin engine";
    public static final String currentLatinEngineName = "latin engine";
    public static final String currentHindiEngineName = "hindi engine";
    public static final String currentBengaliEngineName = "bengali engine";
    public static final String currentNepaliEngineName = "nepali engine";
    public static final String currentVietnameseEngineName = "vietnamese engine";
    public static final String currentChineseEngineName = "chinese engine";
    public static final String currentRussianEngineName = "russian engine";
    public static final String currentNorwegianEngineName = "norwegian engine";
    public static final String currentMarathiEngineName = "marathi engine";
    public static final String currentTamiliEngineName = "tamil engine";
    public static final String currentArabiciEngineName = "arabic engine";
    public static final String PROJECT_NUMBER = "680489653272";
    public static final String PROJECTID = "eye-d-pro";
    public static final String ERROR_LOCATION_NOT_FOUND_ON_CONNECTED = "Location not found on connected";
    public static final String LOCATION_ANALYTICS_CATEGORY = "Location";
    public static final String CALLING_ACTIVITY_NAME = "calling activity";
    public static final String MAIN_ACTIVITY = "MainActivity";
    public static final String WHERE_AM_I = "Where Am I";
    public static final String TRAVEL_MODE = "Travel Mode";
    public static final String MAIN_ACTIVITY_MENU = "Main Activity Menu";
    public static final String BUTTON_CLICK = "Button Click";
    public static final String BUS_MODE = "Bus Mode";
    public static final String AROUND_ME = "Around Me";
    // public static final String IMAGE_ANALYSIS = "Image Analysis";
    public static final String IMAGE_ANALYSIS = "See Object in Image";
    public static final String TEXT_READER = "Text Reader";
    public static final String EXIT = "Exit";
    public static final String NEARBY_PLACES_ACTIVITY = "Around Me Sub-Menu";
    public static final String WHERE_AM_I_ACTIVITY3 = "Where am I Sub-Menu";
    public static final String PLACES_ITEM_SELECTED = "Place Item Selected";
    public static final String DIRECTION_ACTIVITY = "Directions Activity";
    public static final String BUS_MODE_ACTIVITY = "BusModeActivity";
    public static final String URL_KEY = "url";
    public static final String NOTIF_MESSAGE_KEY = "message";
    public static final String NOTIFICATION_ID = "id";
    public static final String NOTIFICATION_TITLE = "title";
    public static final String LIKELYHOOD_PLACES_LIST = "Likelyhood Places list";
    public static final String SHARED_PREF_NAME = "EyeD Shared Preferences";
    public static final String TOKEN_KEY = "token";
    public static final String UUID_KEY = "uuid";
    public static final String TOKEN_RECEIVER = "Token receiver";
    public static final int RECEIVER_RUNNING = 1022;
    public static final int RECEIVER_FINISHED = 1023;
    public static final int RECEIVER_ERROR = -1022;
    public static final int TOKEN_RECEIVED = 2327;
    public static final int GOOGLE_API_CLIENT_CONNECTED = 5567;
    public static final int LOCATION_CHANGED = 6345;
    public static final int TM_LOCATION_CHANGED = 6212;
    public static final int LAST_KNOWN_LOCATION_FETCHED = 6346;
    public static final int LOCATION_INIT_FAILED = -6346;
    public static final String LOCATION_INIT_FAILED_STR = "Location init failed";
    //public static final int SEND_LOCATION_TO_SERVER=6347;
    public static final int RESULT_LOAD_IMG = 6348;
    public static final int RESULT_LOAD_IMG_FROM_OWN_BROWSER = 6349;


    //Warning: Permission codes need to be from 0-255 i.e. 8 bit
    public static final int MY_PERMISSIONS_REQUEST_LOCATION_BILL_RECOGNIZER = 120;
    public static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 121;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 122;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 123;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 124;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 125;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_CONTACTS = 126;
    public static final int MY_PERMISSIONS_LOC_AND_CONTACTS = 127;
    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 128;
    public static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 129;
    //public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE = 130;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA_COLORREADER = 131;
    public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE_SEE_OBJ = 132;
    public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE_RD_TXT = 133;
    public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE_AUDIO = 134;
    public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE_SELFIE = 135;
    public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE_ADV_OCR = 136;
    public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE_RD_OFFLINE = 137;
    public static final int MY_PERMISSIONS_CAMERA_AND_STORAGE_RLTIM_OCR = 138;

    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 120;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 119;
    public static final int GPS_RESOLUTION_RESULT = 1000;
    public static final int VOICE_DATA_INTEGRITY_CHECK = 1001;
    public static final int RESULT_VOICE_DATA_OK = 1;


    public static final int POLYLINE_FETCHED = 7008;
    public static final int DIRECTION_HELPER_CALLBACK = 7012;
    public static final int DIRECTION_TAKE_U_TURN = 7013;
    public static final int DIRECTION_RIGHT_TURN_APPROACHING = 7014;
    public static final int DIRECTION_LEFT_TURN_APPROACHING = 7015;
    public static final int DIRECTION_MOVE_STRAIGHT = 7016;


    public static final String USERNAME_KEY = "username";
    public static final String USERPHONE_KEY = "userphone";
    public static final String USEREMAIL_KEY = "usernemail";
    public static final int USER_EMAIL_AND_NAME_FECTHED = 504;
    public static final String NO_USER_NAME_IN_PREF = "no_user_id_in_pref";
    public static final String NO_USER_MAIL_IN_PREF = "no_user_email_in_pref";
    public static final String GAURAV_CHAT_USER_ID = "gauravm.itbhu@gmail.com";
    public static final String SHASWAT_CHAT_USER_ID = "mailshaswat.jena@gmail.com";
    public static final String KAUSHIK_CHAT_USER_ID = "kaushik.ls1996@gmail.com";
    public static final String EYED_DEV_CHAT_USER_ID = "eyed.test2@gmail.com";

    public static final String EVENT_ID_KEY = "event id key";
    public static final String CHAT_ACTIVITY_NAME = "SendBirdChatActivity";
    public static final String NEW_CHAT_MESSAGE_KEY = "new message";
    public static final String TARGET_USER_IDS = "targetUserIds";
    public static final String LAST_CHANNEL_URL = "last messaging channel";
    public static final String IS_OW_INF_SNT = "is info sent to server";
    public static final String IS_MODEL_NON_ANDROID = "is model non android";
    public static final String SENDBIRD_CHAT_ACTIVITY = "sendbirdchatactivity";
    public static final int NO_NETWORK_ON_CHAT_SEND = 4343;
    public static final String LIVE_CHAT = "Live Chat Support";
    public static final String IS_LOGGED_IN = "is logged in";
    public static final String LAST_LAT = "lastlatitude";
    public static final String LAST_LNG = "lastlongitude";
    public static final int NETWORK_CHANGED = 4434;
    public static final String IS_FIRST_CHAT = "is first chat";
    public static final String LIKELYHOOD_PLACES_DIST = "liekelihood distance";
    public static final String LIKELYHOOD_PLACES_LAT = "likelyhood lat";
    public static final String LIKELYHOOD_PLACES_LNG = "likelyhood lng";
    public static final String WHEREAMI_ACTIVITY = "WhereAmI Activity";
    public static final String LANDMARK_ITEM_SELECTED = "Landmark Item Selected";


    public static final String LABEL_DETECTION = "LABEL_DETECTION";
    public static final int TEXT_DETECTION = 99;
    public static final int DOC_TEXT_DETECTION = 98;
    public static final int DUMMY_DETECTION_NOUSE = 97;
    public static final String DOCUMENT_TEXT_DETECTION = "DOCUMENT_TEXT_DETECTION";
    public static final String CURRENT_LANDMARK_ADDRESS_CS = "current address char sequence";
    public static final String LIKELYHOOD_PLACES_TYPE = "likelyhood places type";
    public static final String POLYLINE = "polyline";
    public static final String DIRECTION_EVENT_ID_KEY = "direction event";

    public static final int ADDRESS_RECEIVER_SUCCESS = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "gingermind.koodoo";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";
    public static final String ADDLIST_DATA_KEY = PACKAGE_NAME +
            ".ADDLIST_DATA_KEY";
    //public static final String sendbird_mAppId = "EE85D5B8-1497-4034-B429-B97EFD6ABDBD"; /* Eye-D Application */
    public static final String sendbird_mAppId = "85AA41B5-3B0A-4468-9974-7C5143909727"; /* Eye-D-Pro Application */

    public static final String LAST_VERSION = "version log";
    public static final String RADIUS_KEY = "radius";
    public static final String ERROR = "ERROR";
    public static final String ERROR_ACCOUNTS_NOT_FOUND = "Accounts length is 0 in OwnerInfo";
    public static final int ERROR_ACCOUNTS_NOT_FOUND_INT = 3434;
    public static final String EXCEPTION_KEY = "exception";
    public static final String DEBUGGING_PREF = "debugging enabled";
    public static final String DEBUG_CHECK_URL = "http://www.eye-d.in/track/";
    public static final String MAIN_CATEGORY_ITEM = "main category item";
    public static final String KEY_PLACE_TYPE = "bundle tag cat";
    public static final String STORE_CATEGORY_ITEM = "store cat";
    public static final String FOOD_CATEGORY_ITEM = "restaurants";
    public static final String[] mainPlaceTypes = {"ATM", "bus_station", "Bank",
            "Cinema", "Restaurant", "Hospital or Healthcare Facility", "Stores", "place_of_worship"};
    public static final String[] herePlaceTypes = {"atm-bank-exchange","transport","atm-bank-exchange","cinema","restaurant","hospital-health-care-facility","shopping","accomodation","petrol-station","toilet-rest-area","places_of_worship"};
    public static final String[] foodPlaceTypes = {"cafe", "Bakery", "Food & Drink", "meal_takeaway", "meal_delivery", "supermarketNe", "night_club", "restaurant"};
    public static final String[] storePlaceTypes = {"PHARMACY", "Department Store", "HOME_GOODS_STORE",
            "LIQUOR_STORE", "Shop", "Clothing & Accessories", "Electronics",
            "FURNITURE_STORE", "HARDWARE_STORE", "PET_STORE", "Book Shop"};
    public static final String KEY_PLACE_CAT = "key place cat";
    public static final String NUMBERING_PREF = "key numb pref";
    public static final String KM_METRIC = "km";
    public static final String DISTANCE_METRIC_PREF = "distmetricpref";
    public static final String MI_METRIC = "milesMetric";
    public static final String CURRENT_LATLNG_ADDRESS_CS = "latlngcurrentadd";
    public static final String LANG_ENGLISH = "en";
    public static final String LANG_KEY = "langpref";
    public static final String INTERNET_AVAILABLE = "is internet";
    public static final int MAX_ADRESS_RESULTS = 5;
    public static final String OCR_RESULT_KEY = "ocr key";
    public static final String OCR_RESULT_PHONE_NUMBERS_KEY = "ocr key numbers";
    public static final String OCR_RESULT_EMAILS_KEY = "ocr key emails";
    public static final String OCR_RESULT_WEB_LINKS_KEY = "ocr key weblinks ";
    public static final String OCR_RESULT_CALLING_ACTIVITY = "ocr calling activity";

    //public static final String LATIN_NAME = "LATIN";
    //public static final String OCRLANG_KEY = "ocr lang";
    public static final String OCR_SHARE_CAUTION_KEY = "ocr share caution";
    public static final String DISABLE_SAVE_KEY = "disabledSave";
    public static final String TTS_ID = "ttsid";
    public static final String DONOR_MESSAGE_KEY = "donoekey";
    public static final String LANG_ENGLISH_FULL = "English";

    //public static final String LANG_CODE_KEY = "langcodekey";
    // Generate 20 random bytes, and put them here.
    public static final byte[] MITSALTZ = new byte[]{
            -46, 65, 39, -128, -103, -57, 65, -64, 51, 88, -95,
            -45, 77, -117, -11, -113, -19, 32, -13, 89
    };
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgwf/gKTUilnZTg8HtiJE+BPEaPiXv9qH80VlkUr0OAErWsqTgXBbzA9K86/v+n2sBS5gXO23/095l+jW7Fzo0K800vQN+s3zG4jtTcDHtk3pX05DdazAnyKwE5+A6ZCitmgXfsjydEeEjSDj7Y3qmi8TaxyMJOA37bDumszhIgMclKEOeyiYXq6kXEhCL+QmA+6NLdUMK0HictT23cXeCX4sy8kKe+Z5O42/2kKbUmUq/OfIlaMa3MFXMVFFy3/l5Cm4A5QEGPn4xQSijkM4ZKFDtLjKUyaasfDBjuaRx1VpwvCHV7fzX25wEgsYa8L43jvRZ+SBLPX4mS9FlWh7owIDAQAB";


    //HANDLER CODES//////////////
    public static final int H_LOCATION_MODE = 7862;
    public static final int H_LOCATION_CHANGED = 7863;
    public static final int H_PLACE_FETCHER_CURRENT_LANDMARK_SUCCESS = 7864;
    public static final int H_PLACE_FETCHER_CURRENT_ADDRESS_SUCCESS = 7865;
    public static final int H_PLACE_FETCHER_GEOCODER_FAIL = 7866;
    public static final int H_PLACE_FETCHER_CURRENT_ADDRESS_FAIL = 7867;
    public static final int H_NO_UPDATE = 7868;
    public static final int H_SAVE_SECURITY_LOG = 7869;
    public static final int H_MODULES_SYNCED = 7870;
    public static final int H_MODULES_SYNCED_FAILED = 7871;
    public static final int H_MODULES_OBTAINED = 7872;
    public static final int H_NOT_PLAYSTORE_INSTALLED = 7873;
    public static final int H_LICENSE_SUCCESS = 8080;
    public static final int H_LICENSE_FAIL = 7874;
    public static final int H_ANDROID_ID_MISMATCH = 7875;
    public static final int H_MODIFIED_SIGN = 7876;
    //public static final int H_NOT_LICENSED_DEEPCHECK = 7877;
    public static final int GOOGLE_API_CONNECTION_FAILED = 7878;
    public static final int H_CREATE_USER_FINISHED = 7879;
    public static final int H_COUNTRY_FETCHED = 7880;
    public static final int NEW_MODULE_ADDED = 7881;
    public static final int H_SESSION_PRIVILEGES_OBTAINED = 7882;
    public static final int H_TTS_INIT_DONE = 7882;
    public static final int H_INIT_USERSETTINGS_DEPENDENCIES = 7883;
    public static final int H_UPDATE_CHECK_FAIL = 7884;
    public static final int H_SHOW_DIALOG_FORCE_UPDATE = 7885;
    public static final int H_SHOW_DIALOG_NORMAL_UPDATE = 7886;
    public static final int H_LOG_PIRATED_APP_CHECK_RESULT = 7887;
    public static final int H_NOT_LICENSED_JS = 7888;
    public static final int H_LOG_LICENSE_JS_RESULT = 7889;
    public static final int H_LOG_LICENSE_LVL_RESULT = 7890;
    public static final int H_LOG_PIRACY_DEEP_CHECK_RESULT = 7891;
    public static final int H_GOOGLE_PLAY_MIN_VER_FAIL = 7892;
    public static final int H_SAVEUSERDETAILS_EMAIL_NULL = 7893;
    public static final int H_SAVEUSERDETAILS_SUCCESS = 7894;
    public static final int H_SAVEUSERDETAILS_FAIL = 7895;
    public static final int H_LICENSE_PIRATE_DETAILS_DONE = 7896;
    public static final int H_NOT_LICENSED_LVL = 7897;
    public static final int H_CREATE_TOAST = 7898;
    public static final int H_DISMISS_PROGRESS = 7899;
    public static final int H_DISMISS_APP_NOT_LICENSED_DIALOG = 7901;
    public static final int H_PDF_PLUS_PURCHASE_STATUS_PULLED = 7900;
    public static final int LATENCY_SUCCESS = 7902;
    public static final int LATENCY_FAIL = 7903;
    public static final int LATENCY_CALL_FAIL = 7904;
    public static final int LATENCY_RESTART = 7905;
    public static final int LATENCY_RETRY = 7906;
    public static final int LATENCY_POOR_CONNECTION = 7907;
    public static final int H_ROOT_FOUND = 7908;
    public static final int H_PURCHASED_MODULE_LIST_FETCHED = 7909;
    public static final int H_FIREBASE_DB_USER_EXISTS_AND_FETCHED = 7910;
    public static final int H_FIREBASE_DB_NEW_USER_CREATED = 7911;
    public static final int H_APP_SIGN_CHECK_FAILED = 7912;
    public static final int H_FIREBASE_CHECKX2_PASS = 7913;
    public static final int H_FIREBASE_CHECKX2_FAIL = 7914;
    public static final int H_FIREBASE_CHECKX2_EXCEPTION = 7915;
    public static final int H_NOT_LICENSED_GM = 7916;
    public static final int H_FIREBASE_DB_USER_EMAIL_UPDATED = 7917;
    public static final int H_MLKIT_PROCESSING_SUCCESS = 7920;
    public static final int H_MLKIT_PROCESSING_ERROR = -7920;
    public static final int H_MLKIT_LABEL_PROCESSING_SUCCESS = 7921;
    public static final int H_MLKIT_LABEL_PROCESSING_ERROR = -7921;
    public static final int H_MLKIT_TEXT_PROCESSING_SUCCESS = 7922;
    public static final int H_MLKIT_TEXT_PROCESSING_ERROR = -7922;
    public static final int H_MLKIT_DOC_PROCESSING_SUCCESS = 7923;
    public static final int H_MLKIT_DOC_PROCESSING_ERROR = -7923;

    /////////////////////////

    //Notification Constants
    public static final String EYEDPRO_NOTIFICATION_CHANNEL_ID = "eyed";
    public static final String EYEDPRO_NOTIFICATION_CHANNEL_NAME = "eyedpro";
    public static final String EYEDPRO_NOTIFICATION_CHANNEL_DESCRIPTION = "Update from team Eye-D.";

    //Here Maps Constants
    public static final String HERE_MAPS_APP_ID = "ht72VN7YtfXmBCxnfS7R";
    public static final String HERE_MAPS_APP_CODE = "OVFYYXRw0DjMw_lLlg8HRg";
    public static final String HERE_MAPS_API_KEY = "tLKsDtBSS0BWyCJAbunZMiHraVimnEDTKVoRLwbDioI"; //from 22 dec 2021


    public static final String SHASWAT_CHAT_USER_ID_2 = "e5036541bce183a8";
    public static final String GAURAV_CHAT_USER_ID_2 = "7c2e4b551aba452c";
    public static final String LICENSE_KEY = "lickey";
    public static final String AUTO_LANG = "auto_auto";
    public static final String OCR_ONE_TIME_INFO = "otocrinfo";
    public static final String PDF_ONE_TIME_INFO = "otpdfinfo";
    public static final String PDFPLUS_ONE_TIME_INFO = "otpdfpinfo";
    //public static final String GLOBAL_TTS_KEY = "globaltts";
    public static final String TTS_INIT_DONE = "tts init done";

    public static final String HINDI_CODE = "hi";
    public static final String ENGLISH_GB_CODE = "en_GB";
    public static final String ENGLISH_US_CODE = "en_US";
    public static final String ENGLISH_CODE = "en";
    public static final String FRENCH_FRANCE_CODE = "fr_FR";
    public static final String FRENCH_CA_CODE = "fr_CA";
    public static final String FRENCH_CODE = "fr";
    public static final String FINNISH_CODE = "fi";
    public static final String FINNISH_FINLAND_CODE = "fi_FI";
    public static final String SPANISH_SPAIN_CODE = "es_ES";
    public static final String GERMAN_CODE = "de";
    public static final String GERMAN_GERMANY_CODE = "de_DE";
    public static final String ITALIAN_CODE = "it";
    public static final String ITALIAN_ITALY_CODE = "it_IT";
    public static final String PORTUGEESE_BRAZIL_CODE = "pt_BR";
    public static final String PORTUGEESE_CODE = "pt";
    public static final String DANISH_CODE = "da";
    public static final String DUTCH_CODE = "nl";
    public static final String VIETNAMESE_CODE = "vi";
    public static final String NEPALI_CODE = "ne";
    public static final String BENGALI_CODE = "bn";
    public static final String TAMIL_CODE = "ta";
    public static final String ARABIC_CODE = "ar";
    public static final String NORWEGIANBOKMAL = "no";
    public static final String TELUGU_CODE = "te";
    public static final String KANNADA_CODE = "kn";
    public static final String CATALAN_CODE = "ca";
    public static final String HUNGARIAN_CODE = "hu";
    public static final String POLISH_CODE = "pl";
    public static final String ROMANIAN_CODE = "ro";
    public static final String SWEDISH_CODE = "sv_SE";
    public static final String NORWEGIAN_CODE = "nb";
    public static final String TURKISH_CODE = "tr";
    public static final String CHINESE_CODE = "zh";
    public static final String RUSSIAN_CODE = "ru";
    public static final String MARATHI_CODE = "mr";
    public static final String ENGLISH_INDIA_CODE = "en_IN";
    public static final String ENGLISH_INDIA_NAME = "English India";
    public static final String HINDI_NAME = "Hindi";
    public static final String TAMIL_NAME = "Tamil";
    public static final String BENGALI_NAME = "Bengali";
    public static final String MARATHI_NAME = "Marathi";
    public static final String ARABIC_NAME = "Arabic";
    public static final String TELUGU_NAME = "Telugu";
    public static final String NEPALI_NAME = "Nepali";
    public static final String VIETNAMESE_NAME = "Vietnamese";
    public static final String CHINESE_NAME = "Chinese";
    public static final String RUSSIAN_NAME = "Russian";
    public static final String KANNADA_NAME = "Kannada";
    public static final String NORWEGIAN_NAME = "Norwegian";
    public static final String LATIN_NAME = "Latin";
    public static final String LATIN_CODE = "Latin";
    public static final String isLatinSelect = "is latin select";
    public static final String isHindiSelect = "is hindi select";
    public static final String isAnyEngSelected = "is hindi select";

    public static final String isBengaliSelect = "is bengali select";
    public static final String isNepaliSelect = "is nepali select";
    public static final String isVietnameseSelect = "is vietnamese select";
    public static final String isChineseSelect = "is chinese select";
    public static final String isRussianSelect = "is russian select";
    public static final String isNorwegianSelect = "is norwegian bokmal select";
    public static final String isMarathiSelect = "is marathi select";
    public static final String isTamiliSelect = "is tamil select";
    public static final String isArabiciSelect = "is arabic select";
    //public static final String OCRLANG_KEY2 = "ocr lang key 2";
    public static final String GOOGLE_TTS_ENGINE_NAME = "com.google.android.tts";
    public static final String ESPEAK_TTS_ENGINE_NAME = "com.reecedunn.espeak";
    public static final String GOOGLE_ENGLISH_INDIA_CODE = "en_IN";
    public static final String GOOGLE_HINDI_CODE = "hi_IN";
    public static final String ESPEAK_HINDI_CODE = "hi";
    public static final String ESPEAK_TAMIL_CODE = "ta";
    public static final String GOOGLE_BENGALI_CODE = "bn_BD";
    public static final String ESPEAK_BENGALI_CODE = "bn";
    public static final String ESPEAK_MARATHI_CODE = "mr";
    public static final String ESPEAK_TELUGU_CODE = "te";
    public static final String ESPEAK_KANNADA_CODE = "kn";
    public static final String LATEST_TEXT_RESULT_FETCHED = "latest text result fetched";
    public static final String KEY_OCR_LANG_NAME = "ocr lang namekey";
    public static final String KEY_OCR_ENGINE_NAME = "ocr Engine namekey";
    public static final String OCR_LANG_CODE_KEY = "ocrlangcode";
    public static final String KEY_PLACE_KEYWORD = "keyplacekeyword";
    public static final String KEY_TRAV_MODE_TIME = "keytravmodetime";
    public static final String KEY_TRAV_MODE_DIST = "keytravmodedist";
    public static final String KEY_TRAV_MODE_ALERT_ON_NEW_LOCATION = "true";
    public static final int REQUEST_CODE_EMAIL = 1002;
    public static final String KEY_TRAV_MODE_ADD = "keytravmodeadd";
    public static final int HTTP_200_OK = 200;
    public static final String KEY_MARKET_ITEM_TITLE = "marketitemtitle";
    public static final String KEY_MARKET_ITEM_DESC = "marketitemdesc";
    public static final String KEY_MARKET_ITEM_PRICE = "marketitemprice";
    public static final String KEY_MARKET_ITEM_SKU = "marketitemsku";
    public static final String KEY_INDEX = "indexkey";
    public static final int AZURE_RESULT_RECEIVED = 1309;
    public static final int AZURE_RESULT_STRING = 1310;
    public static final String VISION_ACTIVITY4 = "visionactivity4";
    public static final String GENUINE_INFO_SENT_KEY = "genuineinfosent";
    public static final String GENUINE_KEY = "genuinekey";
    public static final int USER_GENUINE = 12322;
    public static final int USER_NOT_GENUINE = 12300;
    public static final String TEXT_READER_OFFLINE = "text reader offline";
    public static final String GPS_ACCURACY = "gps accuracy";
    public static final int NOTIFICATION_ID_FOREGROUND_SERVICE = 156;
    public static final String KEY_ANDROID_ID = "androididkey";
    public static final String SETPIRACYCHECK_1_7 = "setpiracycheck_1_7";
    public static final int REQUEST_CODE_EMAIL_PIRACY_1_7 = 1003;

    public static final String RECORD = "record button";
    public static final String SAVED_VIDEO_ACTIVITY = "saved video activity";
    public static final String SELFIE_CAMERA_ACTIVITY = "selfie camera activity";
    public static final String PETITION_VIDEO_PATH_KEY = "filepath video";
    public static final String PETITION_VIDEO_URL_KEY = "petition video key";
    public static final String ISVIDEO_UPLOADED_KEY = "isvideouploaded";

    public static final String KEY_IS_PIRATED_1_7 = "keyispirated17";
    public static final String SERVER_OVERRIDE_KEY = "override";
    public static final String LIB_LVL = "LVL_LIBRARY";
    public static final String LIB_JS = "JS_LIBRARY";
    public static final String LIB_GM = "GM_NOT_PLAYSTORE";
    public static final String LIB_JS_DEEP_CHECK = "JS_DEEP_CHECK_LIBRARY";
    public static final String MODIFIED_APP = "MODIFIED_APP_SIGNATURE";
    public static final String MODIFIED_APP_VERIFICATION_FAILED = "MODIFIED_APP_SIG_VERIFICATION_FAILED";

    public static final String MISMATCH_ANDROID_ID = "MISMATCH_ANDROID_ID";
    public static final String PIRATED_APP_CHECK = "PIRATED_APP_FOUND";
    public static final String LICENSE_KEY_JS = "licensejs";
    public static final String SECURITY_LOG_KEY = "securitylogkey";
    public static final String SECURITY_ROOTED_KEY = "rootedphonelogkey";
    public static final String ACT_MOD_KEY = "activatedModules";
    public static final String KEY_ITEM_NAME = "marketitemname";
    public static final String KEY_ITEM_SERVER_ID = "serverID";
    public static final int UPLOAD_FILE_RESULT_CODE = 776;

    public static final String ADVANCED_OCR = "Advanced OCR Activity";
    public static final String COLOR_READER = "Color Reader Activity";
    public static final String COLOR_READER_BLOB = "Color Reader Blob";
    //new tags for analytics
    public static final String COLOR_READER_POINT = "Color Reader Point Mode";
    public static final String COLOR_READER_OBJECT = "Color Reader Object Mode";
    public static final String MARKET_ACTIVITY_PURCHASE = "Purchasing item ";
    public static final String MARKET_ACTIVITY_TRIAL = "Trial of item ";
    public static final String MARKET_ACTIVITY_ITEM_SELECTED = "Market Item selected";

    // NEW AOCR feature constants
    public static final String AOCR_GUIDANCE = " Guidance in AOCR ";
    public static final String AOCR_PIC_TAKEN_CAMERA = " Pic taken from camera in AOCR ";
    public static final String AOCR_TTS_BUTTON = " TTS Engine button used ";
    public static final String AOCR_SMART_RESULTS_BUTTON = " Smart results viewed in AOCR ";
    public static final String AOCR_SMART_RESULTS_PLAY = "  play button in AOCR ";
    public static final String AOCR_SMART_RESULTS_PAUSE = " pause button in AOCR ";
    public static final String AOCR_SMART_RESULTS_REPLAY = " Replay button in AOCR ";
    public static final String AOCR_SMART_RESULTS_NEXT = " Next button in AOCR ";
    public static final String AOCR_SMART_RESULTS_PREV = " Previous button in AOCR";
    public static final String AOCR_SMART_RESULTS_ACTION = "Action button in AOCR ";
    public static final String AOCR_SMART_RESULTS_SHARE = "Action button in AOCR ";
    public static final String AOCR_PIC_TAKEN_GALLERY = " Pic taken from Gallery in AOCR ";
    public static final String KEY_PLACE_NAME = "keyplacename";
    public static final String KEY_PLACE_DISTANCE = "keyplacedistance";
    public static final String KEY_PLACE_DESCRIPTION = "keyplacedesc";
    public static final String KEY_PLACE_RATING = "keyplacerating";
    public static final String KEY_PLACE_LAT = "keyplacelat";
    public static final String KEY_PLACE_LNG = "keypalcelng";
    public static final String KEY_PLACE_PHONE = "keyplacephone";
    public static final String KEY_PLACE_ID = "keyplaceid";
    public static final String KEY_OCR_LANG_CODE = "keyocrlangcode";
    public static final String KEY_IDHASH = "idhash";
    public static final String MODULE_ADDED_FOR_TRIAL = "MODULE_ADDED_FOR_TRIAL";
    public static final String CURRENCY_RECOGNITION = "Currency Recognition Activity";
    public static final String REALTIME_OCR = "Real-Time OCR Activity";
    public static final String KEY_COUNTRY_CODE = "keycountrycode";
    public static final String KEY_ITEM_MIN_SUP_API = "keyminsupapi";
    public static final String KEY_ITEM_TRIAL_STATE = "keytrialstate";
    public static final String KEY_ITEM_TRIAL_STATUS = "keytrialstatus";
    public static final String KEY_OCR_TUTORIAL_SHOWN = "keyocrtuteshown";
    public static final String KEY_BLOB_INFO_SHOWN = "keyBlobinfoShown";
    public static final String KEY_GUIDANCE_INFO_SHOWN = "keyGuidanceinfoShown";
    public static final String MODIFIED_SIGN_ERROR_CODE = "SIGx007";
    public static final String KEY_LICENSE_FAIL_REASON = "licensefailreason";
    public static final String GOOGLE_VISION_MODE_KEY = "googlevisionmodekey";
    public static final String AMAZON_VISION_MODE_KEY = "amazonvisionmodekey";
    public static final String MLKIT_VISION_MODE_KEY = "mlkitvisionmodekey";
    public static final String INSTALLER_ID = "INSTALLER_ID";
    public static final String DB_KEY = "asfn453nmd";
    public static final String KEY_PREF_TTS = "keypreftts";
    public static final String DEFAULT_TTS = "default tts";
    public static final String KEY_ITEM_PURCHASE_STATE = "purchasekey";
    public static final String PURCHASED = "PURCHASED";
    public static final String KEY_LAST_LOG_DATETIME = "keylastlogdatetime";
    public static final String KEY_LAST_LOG_DATE = "keylastlogdate";
    public static final String WEATHER_UPDATE = "weather_alert";
    public static final String LATENCY = "latency";
    public static final String KEY_ITEM_START_DATE = "start date";
    public static final String KEY_ITEM_TRIALS = "trials done";
    public static final String KEY_ITEM_LAST_DATE = "last date";
    public static final String KEY_ITEM_RETRIAL_STATUS = "retriak_status";
    public static final String item_purchased = "PURCHASED";
    public static final String item_not_purchased = "NOT_PURCHASED";
    public static final String TRIAL_NOT_ACTIVATED = "TRIAL_NOT_ACTIVATED";
    public static final String TRIAL_ACTIVE = "TRIAL_ACTIVE";
    public static final String TRIAL_EXPIRED = "TRIAL_EXPIRED";
    public static final String RETRY_NOW = "RETRY_NOW";

    public static final String PDF_MONTHLY_DEADLINE = "pdf_monthly_deadline";
    public static final String APPLANGUAGE = "applanguage";
    public static final String KEY_REFAUG2019 = "keyrefaug2019";

    public static final String IS_USER_ACCESS_BLOCKED = "false";

    public static final String PDF_READER_PLUS_ACTIVITY = "PDF_Reader_Plus_Activity";
    public static final String PDF_READER_PLUS_NEXT_PAGE_BUTTON = "PDF_Reader_Plus_Next_Page_Button";
    public static final String PDF_READER_PLUS_PREVIOUS_PAGE_BUTTON = "PDF_Reader_Plus_Previous_Page_Button";
    public static final String PDF_READER_PLUS_GO_TO_PAGE_BUTTON = "Pdf_Reader_Plus_Go_To_Page_Button";
    public static final String PDF_READER_PLUS_SHARE_IMAGE_BUTTON = "Pdf_Reader_Plus_Share_Image_Button";

    public static final String PDF_READER_PLUS_RESPONSE_PAGE_RECEIVED = "Pdf_Reader_Plus_Response_Page_Received";
    public static final String FIREBASE_ANALYTICS_UID_KEY = "firebaseanalyticsuidkey";
    public static final String KEY_IS_BULK_LICENSED = "KEY_IS_BULK_LICENSED";
    public static final String KEY_BULK_LICENSE_ID = "KEY_BULK_LICENSE_ID";

    //public static final String LVL_ERROR_CODE = "XVLx003";
    //public static final String JS_ERROR_CODE = "XSx003";
    //public static final String JS_DEEP_CHECK_ERROR_CODE = "XSDPx003";

    public static final String ADVANCED_OCR_CODENAME="ADVANCED_OCR";
    public static final String PDF_RDR_CODENAME="PDF_READER";
    public static final String PDR_RDR_PLUS_CODENAME="PDF_READER_PLUS";

    public static final String CONSTANTS_SPREF_KEY_PURCHASEDMODULE = "CONSTANTS_SPREF_KEY_PURCHASEDMODULE";
    public static final int REQUEST_CODE_STORAGE_FOR_APK_STORAGE = 18976 ;
}