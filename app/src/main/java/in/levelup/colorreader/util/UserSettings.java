package in.levelup.colorreader.util;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import io.michaelrocks.paranoid.Obfuscate;


/**
 * Created by gauravmittal on 02/10/16.
 */
@Obfuscate
public class UserSettings {

    private static final String TAG = "Ustgs";
    private final Context context;
    private String version="-1";
    private SharedPreferences spref;
    private SharedPreferences.Editor editor;

    private String distanceUnit;
    /* public String currentLatinEngineName;
     public String currentHindiEngineName;
     public String currentBengaliEngineName;
     public String currentNepaliEngineName;
     public String currentVietnameseEngineName;
     public String currentChineseEngineName;
     public String currentRussianEngineName;
     public String currentNorwegianEngineName;
     public String currentMarathiEngineName;
     public String currentTamilEngineName;
     public String currentArabicEngineName; */
    public String currentEnginename;
    private Boolean numberingEnabled;
    /*  private Boolean isLatinEngineSelect;
      private Boolean isAnyEngineSelected;
      private Boolean isHindiEngineSelect;
      private Boolean isBengaliEngineSelect;
      private Boolean isNepaliEngineSelect;
      private Boolean isVietnameseEngineSelect;
      private Boolean isChineseEngineSelect;
      private Boolean isRussianEngineSelect;
      private Boolean isNorwegianEngineSelect;
      private Boolean isMarathiEngineSelect;
      private Boolean isTamilEngineSelect;
      private Boolean isArabicEngineSelect; */
    private Boolean debuggingEnabled;
    private String userEmail;
    private Boolean isSystemLangUsed;
    private Boolean textShareCautionAgreed;
    private Boolean isDonorMessageShown;
    private Boolean isLoggedIn;
    private String userName;
    private String userPhone;
    private Boolean isUserNonAndroid;
    private String gcmToken;
    private String uuid;
    private Boolean isOwnerInfoSent;
    private String lastLatitude;
    private String lastLongitude;
    private String lastChannelURL;
    private boolean licensed;
    private boolean licensedJS;
    private Boolean oneTimeInfoOCRShown;
    private Boolean oneTimeInfoPDFReader;
    private Boolean oneTimeInfoPDFReaderPlus;
    private Boolean firstTimeChat;
    private Boolean isPirated_1_7 = false;
    private Boolean isVideoUploaded;
    private String securityLog;
    private Set<String> activatedModules;
    private String ocrLangName;
    private String ocrEngine;
    private String ocrLangCode;
    private String licenseFailReason;
    private String preferredTTSEngine;
    private String lastLogDateTime;
    private String lastLogDate;
    private String weather;
    private String latency;
    private Boolean refundPolicyAug_219_agreed;
    private String appLanguage;
    private Boolean isBlocked;
    private Set<String> purchasedModulenameStringSet;
    private String certificate;
    private String bulklicenseID;


    public String getBulklicenseID() {
        return bulklicenseID;
    }

    public void setBulklicenseID(String bulklicenseID) {
        this.bulklicenseID = bulklicenseID;
        editor = spref.edit();
        editor.putString(Constants.KEY_BULK_LICENSE_ID, bulklicenseID);
        editor.apply();
    }

    public boolean isBulkLicensed() {
        return isBulkLicensed;
    }

    public void setBulkLicensed(boolean bulkLicensed) {
        isBulkLicensed = bulkLicensed;
        editor = spref.edit();
        editor.putBoolean(Constants.KEY_IS_BULK_LICENSED,bulkLicensed);
        editor.apply();
    }

    private boolean isBulkLicensed;

    public String getFirebaseAnalyticsUidKey() {
        return firebaseAnalyticsUidKey;
    }

    public void setFirebaseAnalyticsUidKey(String firebaseAnalyticsUidKey) {
        this.firebaseAnalyticsUidKey = firebaseAnalyticsUidKey;
        editor = spref.edit();
        editor.putString(Constants.FIREBASE_ANALYTICS_UID_KEY, this.firebaseAnalyticsUidKey);
        editor.apply();
    }

    private String firebaseAnalyticsUidKey;


    public UserSettings(SharedPreferences pref, Context context) {
        this.spref = pref;
        this.context = context;
        initAllValues();

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo("in.gingermind.eyedpro", 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean getBlocked() {
        this.isBlocked = spref.getBoolean(Constants.IS_USER_ACCESS_BLOCKED, false);
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        this.isBlocked = blocked;
        editor = spref.edit();
        editor.putString(Constants.IS_USER_ACCESS_BLOCKED, String.valueOf(this.isBlocked));
        editor.apply();
    }

    public String getAppLanguage() {
        return appLanguage;
    }

    public void setAppLanguage(String appLanguage) {
        this.appLanguage = appLanguage;
        editor = spref.edit();
        editor.putString(Constants.APPLANGUAGE, this.appLanguage);
        editor.apply();

        //TODO: Quick fix for  if (App.mUserSettings != null) { in mainactivity
        SharedPreferences prefs = context.getSharedPreferences("language_setting",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("langcode", appLanguage);
        editor.commit();
    }

    public int getLatency() {
        return Integer.parseInt(latency);
    }

    public void setLatency(int latency) {
        this.latency = Integer.toString(latency);
        editor = spref.edit();
        editor.putString(Constants.LATENCY, this.latency);
        editor.apply();
    }


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
        editor = spref.edit();
        editor.putString(Constants.WEATHER_UPDATE, weather);
        editor.apply();
    }

    public String getLastLogDateTime() {
        return lastLogDateTime;
    }

    public void setLastLogDateTime(String lastLogDateTime) {
        editor = spref.edit();
        editor.putString(Constants.KEY_LAST_LOG_DATETIME, lastLogDateTime);
        editor.apply();
        this.lastLogDateTime = lastLogDateTime;
    }

    public String getLastLogDate() {
        return lastLogDate;
    }

    public void setLastLogDate(String lastLogDate) {
        editor = spref.edit();
        editor.putString(Constants.KEY_LAST_LOG_DATE, lastLogDate);
        editor.apply();
        this.lastLogDate = lastLogDate;
    }


    public Boolean getOneTimeOcrOutputTutorialShown() {
        return oneTimeOcrOutputTutorialShown;
    }

    public Boolean getOneTimeBlobInfoShown() {
        return oneTimeBlobInfo;
    }

    public void setOneTimeOcrOutputTutorialShown(Boolean oneTimeOcrOutputTutorialShown) {
        editor = spref.edit();
        editor.putBoolean(Constants.KEY_OCR_TUTORIAL_SHOWN, oneTimeOcrOutputTutorialShown);
        editor.apply();
        this.oneTimeOcrOutputTutorialShown = oneTimeOcrOutputTutorialShown;
    }

    public void setOneTimeBlobInfoShown(Boolean oneTimeBlobInfo) {
        editor = spref.edit();
        editor.putBoolean(Constants.KEY_BLOB_INFO_SHOWN, oneTimeBlobInfo);
        editor.apply();
        this.oneTimeBlobInfo = oneTimeBlobInfo;
    }

    public Boolean getOneTimeGuidanceInfoShown() {
        return oneTimeGuidanceInfo;
    }

    public void setOneTimeGuidnaceInfoShown(Boolean oneTimeGuidanceInfo) {
        editor = spref.edit();
        editor.putBoolean(Constants.KEY_GUIDANCE_INFO_SHOWN, oneTimeGuidanceInfo);
        editor.apply();
        this.oneTimeGuidanceInfo = oneTimeGuidanceInfo;
    }

    private Boolean oneTimeOcrOutputTutorialShown;
    private Boolean oneTimeBlobInfo;
    private Boolean oneTimeGuidanceInfo;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        editor = spref.edit();
        editor.putString(Constants.KEY_COUNTRY_CODE, countryCode);
        editor.apply();
        this.countryCode = countryCode;
    }

    private String countryCode;

    public String getIdhash() {
        return idhash;
    }

    public void setIdhash(String idhash) {
        this.idhash = idhash;
        editor = spref.edit();
        editor.putString(Constants.KEY_IDHASH, idhash);
        editor.apply();
    }

    private String idhash = "";

    public String getOcrLangName() {
        return ocrLangName;
    }

    public void setOcrLangName(String ocrLangName) {
        this.ocrLangName = ocrLangName;
        editor = spref.edit();
        editor.putString(Constants.KEY_OCR_LANG_NAME, ocrLangName);
        editor.apply();
    }

    public String getOcrEngine() {
        return ocrEngine;
    }

    public void setOcrEngine(String ocrEngine) {
        this.ocrEngine = ocrEngine;
        editor = spref.edit();
        editor.putString(Constants.KEY_OCR_ENGINE_NAME, ocrEngine);
        editor.apply();
    }


    public void setEngineByCode(String ocrEngineneme, String ocrLangCode) {
        editor = spref.edit();
        this.currentEnginename = ocrEngineneme;
        editor.putString(Constants.currentEngineName, ocrEngineneme);

      /*  if(ocrLangCode.equals(Constants.LATIN_CODE))
        {
            this.currentLatinEngineName=ocrEngineneme;
            editor.putString(Constants.currentLatinEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.HINDI_CODE))
        {
            this.currentHindiEngineName=ocrEngineneme;
            editor.putString(Constants.currentHindiEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.BENGALI_CODE))
        {
            this.currentBengaliEngineName=ocrEngineneme;
            editor.putString(Constants.currentBengaliEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.NEPALI_CODE))
        {
            this.currentNepaliEngineName=ocrEngineneme;
            editor.putString(Constants.currentNepaliEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.VIETNAMESE_CODE))
        {
            this.currentVietnameseEngineName=ocrEngineneme;
            editor.putString(Constants.currentVietnameseEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.CHINESE_CODE))
        {
            this.currentChineseEngineName=ocrEngineneme;
            editor.putString(Constants.currentChineseEngineName, ocrEngineneme);
        }
        if(ocrLangCode.equals(Constants.RUSSIAN_CODE))
        {
            this.currentRussianEngineName=ocrEngineneme;
            editor.putString(Constants.currentRussianEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.NORWEGIAN_CODE))
        {
            this.currentNorwegianEngineName=ocrEngineneme;
            editor.putString(Constants.currentNorwegianEngineName, ocrEngineneme);
        }
        if(ocrLangCode.equals(Constants.MARATHI_CODE))
        {
            this.currentMarathiEngineName=ocrEngineneme;
            editor.putString(Constants.currentMarathiEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.TAMIL_CODE))
        {
            this.currentTamilEngineName=ocrEngineneme;
            editor.putString(Constants.currentTamiliEngineName, ocrEngineneme);
        }
        else if(ocrLangCode.equals(Constants.ARABIC_CODE))
        {
            this.currentArabicEngineName=ocrEngineneme;
            editor.putString(Constants.currentArabiciEngineName, ocrEngineneme);
        }*/
        editor.apply();
    }

    public String getEngineByCode() {
        if (null == currentEnginename)
            currentEnginename = "com.google.android.tts"; // when no tts is selected we will use google default tts

        return currentEnginename;
    /*    if(ocrLangCode.equals(Constants.LATIN_CODE))
        {
            return currentLatinEngineName;
        }
        else if(ocrLangCode.equals(Constants.HINDI_CODE))
        {
            return currentHindiEngineName;
        }
        else if(ocrLangCode.equals(Constants.BENGALI_CODE))
        {
            return currentBengaliEngineName;
        }
        else if(ocrLangCode.equals(Constants.NEPALI_CODE))
        {
            return currentNepaliEngineName;
        }
        else if(ocrLangCode.equals(Constants.VIETNAMESE_CODE))
        {
            return currentVietnameseEngineName;
        }
        else if(ocrLangCode.equals(Constants.CHINESE_CODE))
        {
            return currentChineseEngineName;
        }
        else if(ocrLangCode.equals(Constants.RUSSIAN_CODE))
        {
            return currentRussianEngineName;
        }
        else if(ocrLangCode.equals(Constants.NORWEGIAN_CODE))
        {
            return currentNorwegianEngineName;
        }
        else if(ocrLangCode.equals(Constants.MARATHI_CODE))
        {
            return currentMarathiEngineName;
        }
        else if(ocrLangCode.equals(Constants.TAMIL_CODE))
        {
            return currentTamilEngineName;
        }
        else if(ocrLangCode.equals(Constants.ARABIC_CODE))
        {
            return currentArabicEngineName;
        }
        else
        {
            return currentLatinEngineName;
        }
*/
    }

    public String getOcrLangCode() {
        return ocrLangCode;
    }

    public void setOcrLangCode(String ocrLangCode) {
        this.ocrLangCode = ocrLangCode;
        editor = spref.edit();
        editor.putString(Constants.KEY_OCR_LANG_CODE, ocrLangCode);
        editor.apply();
    }

    public Boolean getServerOverride() {
        return serverOverride;
    }

    public void setServerOverride(Boolean serverOverride) {
        editor = spref.edit();
        editor.putBoolean(Constants.SERVER_OVERRIDE_KEY, serverOverride);
        editor.apply();
        this.serverOverride = serverOverride;
    }

    private Boolean serverOverride;

    public Boolean getVideoUploaded() {
        return isVideoUploaded;
    }

    public void setVideoUploaded(Boolean videoUploaded) {
        editor = spref.edit();
        editor.putBoolean(Constants.ISVIDEO_UPLOADED_KEY, videoUploaded);
        editor.apply();
        isVideoUploaded = videoUploaded;
    }


    private String petitionVideoUrl;

    public String getPetitionVideoUrl() {
        return petitionVideoUrl;
    }

    public void setPetitionVideoUrl(String petitionVideoUrl) {
        editor = spref.edit();
        editor.putString(Constants.PETITION_VIDEO_URL_KEY, petitionVideoUrl);
        editor.apply();
        this.petitionVideoUrl = petitionVideoUrl;
    }

    public Boolean getPiracyCheck_1_7() {
        return piracyCheck_1_7;
    }

    public Boolean getPiracyCheck_1_7_done() {
        return piracyCheck_1_7_done;
    }

    public void setPiracyCheck_1_7_done(Boolean piracyCheck_1_7_done) {
        this.piracyCheck_1_7_done = piracyCheck_1_7_done;
        editor = spref.edit();
        editor.putBoolean(Constants.SETPIRACYCHECK_1_7, piracyCheck_1_7_done);
        editor.apply();
    }

    private Boolean piracyCheck_1_7;
    private String petitionVideoFilePath;

    public String getPetitionVideoFilePath() {
        return petitionVideoFilePath;
    }

    public void setPetitionVideoFilePath(String petitionVideoFilePath) {
        editor = spref.edit();
        editor.putString(Constants.PETITION_VIDEO_PATH_KEY, petitionVideoFilePath);
        editor.apply();
        this.petitionVideoFilePath = petitionVideoFilePath;
    }

    private Boolean piracyCheck_1_7_done;


    public String getMinTimeTravelModeUnits() {
        return minTimeTravelModeUnits;
    }

    public void setMinTimeTravelModeUnits(String minTimeTravelModeUnits) {
        this.minTimeTravelModeUnits = minTimeTravelModeUnits;
        editor = spref.edit();
        editor.putString(Constants.KEY_TRAV_MODE_TIME, minTimeTravelModeUnits);
        editor.apply();
    }

    public String getMinDistanceTravelModeUnits() {
        return minDistanceTravelModeUnits;
    }

    public void setMinDistanceTravelModeUnits(String minDistanceTravelModeUnits) {
        this.minDistanceTravelModeUnits = minDistanceTravelModeUnits;
        editor = spref.edit();
        editor.putString(Constants.KEY_TRAV_MODE_DIST, minDistanceTravelModeUnits);
        editor.apply();
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
        editor = spref.edit();
        editor.putString(Constants.KEY_TRAV_MODE_ADD, currentAddress);
        editor.apply();
    }

    private String minTimeTravelModeUnits;
    private String minDistanceTravelModeUnits;
    private String currentAddress;

    public Boolean getGenuineInfoSent() {
        return genuineInfoSent;
    }

    public void setGenuineInfoSent(Boolean genuineInfoSent) {
        editor = spref.edit();
        editor.putBoolean(Constants.GENUINE_INFO_SENT_KEY, genuineInfoSent);
        editor.apply();
        this.genuineInfoSent = genuineInfoSent;
    }

    public Boolean getGenuine() {
        return isGenuine;
    }

    public void setGenuine(Boolean genuine) {
        editor = spref.edit();
        editor.putBoolean(Constants.GENUINE_KEY, genuine);
        editor.apply();
        isGenuine = genuine;
    }

    private Boolean genuineInfoSent;
    private Boolean isGenuine;


    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String val) {
        editor = spref.edit();
        editor.putString(Constants.USERPHONE_KEY, val);
        editor.apply();
        this.userPhone = val;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String val) {
        editor = spref.edit();
        editor.putString(Constants.USERNAME_KEY, val);
        editor.apply();
        this.userName = val;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean val) {
        editor = spref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN, val);
        editor.apply();
        this.isLoggedIn = val;
    }

    private void initAllValues() {
        this.isBlocked = spref.getBoolean(Constants.IS_USER_ACCESS_BLOCKED, false);
        //   this.isAnyEngineSelected= spref.getBoolean(Constants.isAnyEngSelected,false);
        this.distanceUnit = spref.getString(Constants.DISTANCE_METRIC_PREF, Constants.KM_METRIC);
        this.numberingEnabled = spref.getBoolean(Constants.NUMBERING_PREF, true);
        this.debuggingEnabled = spref.getBoolean(Constants.DEBUGGING_PREF, true);
       /*  this.isLatinEngineSelect=spref.getBoolean(Constants.isLatinSelect,false);
        this.isHindiEngineSelect=spref.getBoolean(Constants.isHindiSelect,false);
        this.isBengaliEngineSelect=spref.getBoolean(Constants.isBengaliSelect,false);
        this.isNepaliEngineSelect=spref.getBoolean(Constants.isNepaliSelect,false);
        this.isVietnameseEngineSelect=spref.getBoolean(Constants.isVietnameseSelect,false);
        this.isChineseEngineSelect=spref.getBoolean(Constants.isChineseSelect,false);
        this.isRussianEngineSelect=spref.getBoolean(Constants.isRussianSelect,false);
        this.isNorwegianEngineSelect=spref.getBoolean(Constants.isNorwegianSelect,false);
        this.isMarathiEngineSelect=spref.getBoolean(Constants.isMarathiSelect,false);
        this.isTamilEngineSelect=spref.getBoolean(Constants.isTamiliSelect,false);
        this.isArabicEngineSelect=spref.getBoolean(Constants.isArabiciSelect,false);
        this.currentLatinEngineName=spref.getString(Constants.currentLatinEngineName,null);
        this.currentHindiEngineName=spref.getString(Constants.currentHindiEngineName,null);
        this.currentBengaliEngineName=spref.getString(Constants.currentBengaliEngineName,null);
        this.currentNepaliEngineName=spref.getString(Constants.currentNepaliEngineName,null);
        this.currentVietnameseEngineName=spref.getString(Constants.currentVietnameseEngineName,null);
        this.currentChineseEngineName=spref.getString(Constants.currentChineseEngineName,null);
        this.currentRussianEngineName=spref.getString(Constants.currentRussianEngineName,null);
        this.currentNorwegianEngineName=spref.getString(Constants.currentNorwegianEngineName,null);
        this.currentMarathiEngineName=spref.getString(Constants.currentMarathiEngineName,null);
        this.currentTamilEngineName=spref.getString(Constants.currentTamiliEngineName,null);
        this.currentArabicEngineName=spref.getString(Constants.currentArabiciEngineName,null);*/
        this.currentEnginename = spref.getString(Constants.currentEngineName, null);
        // Log.e("Debugger","Current engine name is " + this.currentEnginename);
        this.userEmail = spref.getString(Constants.USEREMAIL_KEY, "");
        this.isSystemLangUsed = spref.getBoolean(Constants.LANG_KEY, true);
        this.textShareCautionAgreed = spref.getBoolean(Constants.OCR_SHARE_CAUTION_KEY, false);
        this.isLoggedIn = spref.getBoolean(Constants.IS_LOGGED_IN, false);
        this.userName = spref.getString(Constants.USERNAME_KEY, "noname");
        this.userPhone = spref.getString(Constants.USERPHONE_KEY, "000");
        this.isDonorMessageShown = spref.getBoolean(Constants.DONOR_MESSAGE_KEY, false);
        this.isUserNonAndroid = spref.getBoolean(Constants.IS_MODEL_NON_ANDROID, false);
        this.gcmToken = spref.getString(Constants.TOKEN_KEY, "");
        this.uuid = spref.getString(Constants.UUID_KEY, "");
        this.isOwnerInfoSent = spref.getBoolean(Constants.IS_OW_INF_SNT, false);
        this.lastLatitude = spref.getString(Constants.LAST_LNG, null);
        this.lastLongitude = spref.getString(Constants.LAST_LAT, null);
        this.lastChannelURL = spref.getString(Constants.LAST_CHANNEL_URL, null);
        this.licensed = spref.getBoolean(Constants.LICENSE_KEY, false);
        this.licensedJS = spref.getBoolean(Constants.LICENSE_KEY_JS, false);
        this.oneTimeInfoOCRShown = spref.getBoolean(Constants.OCR_ONE_TIME_INFO, false);
        this.oneTimeInfoPDFReader = spref.getBoolean(Constants.PDF_ONE_TIME_INFO, false);
        this.oneTimeInfoPDFReaderPlus = spref.getBoolean(Constants.PDFPLUS_ONE_TIME_INFO, false);
        this.firstTimeChat = spref.getBoolean(Constants.IS_FIRST_CHAT, true);
        this.genuineInfoSent = spref.getBoolean(Constants.GENUINE_INFO_SENT_KEY, false);
        this.isGenuine = spref.getBoolean(Constants.GENUINE_KEY, false);
        this.minTimeTravelModeUnits = spref.getString(Constants.KEY_TRAV_MODE_TIME, "");
        this.minDistanceTravelModeUnits = spref.getString(Constants.KEY_TRAV_MODE_DIST, "");
        this.currentAddress = spref.getString(Constants.KEY_TRAV_MODE_ADD, "");
        this.piracyCheck_1_7 = spref.getBoolean(Constants.SETPIRACYCHECK_1_7, false);
        this.petitionVideoFilePath = spref.getString(Constants.PETITION_VIDEO_PATH_KEY, "");
        this.petitionVideoUrl = spref.getString(Constants.PETITION_VIDEO_URL_KEY, "");
        this.isVideoUploaded = spref.getBoolean(Constants.ISVIDEO_UPLOADED_KEY, false);
        this.piracyCheck_1_7_done = spref.getBoolean(Constants.SETPIRACYCHECK_1_7, false);
        this.isPirated_1_7 = spref.getBoolean(Constants.KEY_IS_PIRATED_1_7, false);
        this.serverOverride = spref.getBoolean(Constants.SERVER_OVERRIDE_KEY, false);
        this.securityLog = spref.getString(Constants.SECURITY_LOG_KEY, "");
        this.activatedModules = spref.getStringSet(Constants.ACT_MOD_KEY, null);
        this.ocrLangName = spref.getString(Constants.KEY_OCR_LANG_NAME, Constants.LATIN_NAME);
        this.ocrLangCode = spref.getString(Constants.KEY_OCR_LANG_CODE, Constants.LATIN_CODE);
        this.idhash = spref.getString(Constants.KEY_IDHASH, "");
        this.countryCode = spref.getString(Constants.KEY_COUNTRY_CODE, "");
        this.oneTimeOcrOutputTutorialShown = spref.getBoolean(Constants.KEY_OCR_TUTORIAL_SHOWN, false);
        this.oneTimeBlobInfo = spref.getBoolean(Constants.KEY_BLOB_INFO_SHOWN, false);
        this.oneTimeGuidanceInfo = spref.getBoolean(Constants.KEY_GUIDANCE_INFO_SHOWN, false);
        this.licenseFailReason = spref.getString(Constants.KEY_LICENSE_FAIL_REASON, "-1");
        this.preferredTTSEngine = spref.getString(Constants.KEY_PREF_TTS, Constants.DEFAULT_TTS);
        this.lastLogDateTime = spref.getString(Constants.KEY_LAST_LOG_DATETIME, "");
        this.lastLogDate = spref.getString(Constants.KEY_LAST_LOG_DATE, "");
        this.weather = spref.getString(Constants.WEATHER_UPDATE, "");
        //FIX START https://console.firebase.google.com/project/eye-d-pro/crashlytics/app/android:in.gingermind.eyedpro/issues/46b4485745e8663967740ae381930fc1?time=last-seven-days&sessionId=5D58D054006E0001781A21B075D7F40F_DNE_0_v2
        //if (Constants.LATENCY != null && spref.contains(Constants.LATENCY)) // Obfuscation Error?
          //  this.latency = spref.getInt(Constants.LATENCY, -1);
        if (spref.contains(Constants.LATENCY)) {
            this.latency = spref.getString(Constants.LATENCY, "-1");
        }
        //FIX END
            this.appLanguage = spref.getString(Constants.APPLANGUAGE, "");
            if (this.appLanguage.matches("")) {
                try {
                    SharedPreferences prefs = context.getSharedPreferences("language_setting", MODE_PRIVATE);
                    String lang = prefs.getString("langcode", null);
                    if (lang == null) {
                        lang = "en";
                        Log.d(TAG, "langcode is null");
                    }
                    this.appLanguage = lang;
                }
                catch(Exception e){
                    this.appLanguage = "en";
                }
            }
        this.refundPolicyAug_219_agreed = spref.getBoolean(Constants.KEY_REFAUG2019, false);
        this.firebaseAnalyticsUidKey = spref.getString(Constants.FIREBASE_ANALYTICS_UID_KEY,null);
        this.purchasedModulenameStringSet = spref.getStringSet(Constants.CONSTANTS_SPREF_KEY_PURCHASEDMODULE,null);
        this.isBulkLicensed = spref.getBoolean(Constants.KEY_IS_BULK_LICENSED, false);
        this.bulklicenseID = spref.getString(Constants.KEY_BULK_LICENSE_ID,null);
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }


    public Boolean getDebuggingEnabled() {
        return debuggingEnabled;
    }

    public Boolean getNumberingEnabled() {
        return numberingEnabled;
    }

  /*  public Boolean getIsEngineSelect(String langcode) {
        if(langcode.equals(Constants.LATIN_CODE)) {
            return isLatinEngineSelect;
        }
        else if(langcode.equals(Constants.HINDI_CODE)) {
            return isHindiEngineSelect;
        }
        else if(langcode.equals(Constants.BENGALI_CODE)) {
            return isBengaliEngineSelect;
        }
        else if(langcode.equals(Constants.NEPALI_CODE)) {
            return isNepaliEngineSelect;
        }
        else if(langcode.equals(Constants.VIETNAMESE_CODE)) {
            return isVietnameseEngineSelect;
        }
        else if(langcode.equals(Constants.CHINESE_CODE)) {
            return isChineseEngineSelect;
        }
        else if(langcode.equals(Constants.RUSSIAN_CODE)) {
            return isRussianEngineSelect;
        }
        else if(langcode.equals(Constants.NORWEGIAN_CODE)) {
            return isNorwegianEngineSelect;
        }
        else if(langcode.equals(Constants.MARATHI_CODE)) {
            return isMarathiEngineSelect;
        }
        else if(langcode.equals(Constants.TAMIL_CODE)) {
            return isTamilEngineSelect;
        }
        else if(langcode.equals(Constants.ARABIC_CODE)) {
            return isArabicEngineSelect;
        }
        else {
            return isLatinEngineSelect;
        }

    }*/
   /* public void setIsEngineSelect(String ocrLangCode,Boolean isEngineSelect) {
        editor=spref.edit();
        if(ocrLangCode.equals(Constants.LATIN_CODE)) {
            editor.putBoolean(Constants.isLatinSelect, isEngineSelect);
            this.isLatinEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.HINDI_CODE)) {
            editor.putBoolean(Constants.isHindiSelect, isEngineSelect);
            this.isHindiEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.BENGALI_CODE)) {
            editor.putBoolean(Constants.isBengaliSelect, isEngineSelect);
            this.isBengaliEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.NEPALI_CODE)) {
            editor.putBoolean(Constants.isNepaliSelect, isEngineSelect);
            this.isNepaliEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.VIETNAMESE_CODE)) {
            editor.putBoolean(Constants.isVietnameseSelect, isEngineSelect);
            this.isVietnameseEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.CHINESE_CODE)) {
            editor.putBoolean(Constants.isChineseSelect, isEngineSelect);
            this.isChineseEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.RUSSIAN_CODE)) {
            editor.putBoolean(Constants.isRussianSelect, isEngineSelect);
            this.isRussianEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.NORWEGIAN_CODE)) {
            editor.putBoolean(Constants.isNorwegianSelect, isEngineSelect);
            this.isNorwegianEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.MARATHI_CODE)) {
            editor.putBoolean(Constants.isMarathiSelect, isEngineSelect);
            this.isMarathiEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.TAMIL_CODE)) {
            editor.putBoolean(Constants.isTamiliSelect, isEngineSelect);
            this.isTamilEngineSelect = isEngineSelect;
        }
        else if(ocrLangCode.equals(Constants.ARABIC_CODE)) {
            editor.putBoolean(Constants.isArabiciSelect, isEngineSelect);
            this.isArabicEngineSelect = isEngineSelect;
        }

        editor.apply();

    } */


    public void setDistanceUnit(String distanceUnit) {
        editor = spref.edit();
        if (distanceUnit.matches(Constants.KM_METRIC)) {
            editor.putString(Constants.DISTANCE_METRIC_PREF, Constants.KM_METRIC);
        } else if (distanceUnit.matches(Constants.MI_METRIC)) {
            editor.putString(Constants.DISTANCE_METRIC_PREF, Constants.MI_METRIC);
        }
        editor.apply();
        this.distanceUnit = distanceUnit;
    }

    public void setNumberingEnabled(Boolean numberingEnabled) {
        editor = spref.edit();
        editor.putBoolean(Constants.NUMBERING_PREF, numberingEnabled);
        editor.apply();
        this.numberingEnabled = numberingEnabled;
    }

    public void setDebuggingEnabled(Boolean debuggingEnabled) {
        editor = spref.edit();
        editor.putBoolean(Constants.DEBUGGING_PREF, debuggingEnabled);
        editor.apply();
        this.debuggingEnabled = debuggingEnabled;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        editor = spref.edit();
        editor.putString(Constants.USEREMAIL_KEY, userEmail);
        editor.apply();
        this.userEmail = userEmail;
    }

    public void setSystemLang(Boolean isSystemLangUsed) {
        editor = spref.edit();
        editor.putBoolean(Constants.LANG_KEY, isSystemLangUsed);
        editor.apply();
        this.isSystemLangUsed = isSystemLangUsed;
    }

    public Boolean getSystemLang() {
        return isSystemLangUsed;
    }


    public Boolean getTextShareCautionAgreed() {
        return textShareCautionAgreed;
    }

    public void setTextShareCautionAgreed(Boolean textShareCautionAgreed) {
        editor = spref.edit();
        editor.putBoolean(Constants.OCR_SHARE_CAUTION_KEY, textShareCautionAgreed);
        editor.apply();
        this.textShareCautionAgreed = textShareCautionAgreed;
    }


    public Boolean getOneTimeInfoOCRShown() {
        return oneTimeInfoOCRShown;
    }

    public void setOneTimeInfoOCRShown(Boolean oneTimeInfoOCRShown) {
        editor = spref.edit();
        editor.putBoolean(Constants.OCR_ONE_TIME_INFO, oneTimeInfoOCRShown);
        editor.apply();
        this.oneTimeInfoOCRShown = oneTimeInfoOCRShown;
    }

    public Boolean getOneTimeInfoPDFReaderShown() {
        return oneTimeInfoPDFReader;
    }

    public void setOneTimeInfoPDFReaderShown(Boolean oneTimeInfoOCRShown) {
        editor = spref.edit();
        editor.putBoolean(Constants.PDF_ONE_TIME_INFO, oneTimeInfoOCRShown);
        editor.apply();
        this.oneTimeInfoPDFReader = oneTimeInfoPDFReader;
    }

    public Boolean getOneTimeInfoPDFReaderPlusShown() {
        return oneTimeInfoPDFReaderPlus;
    }

    public void setOneTimeInfoPDFReaderPlusShown(Boolean oneTimeInfoOCRShown) {
        editor = spref.edit();
        editor.putBoolean(Constants.PDFPLUS_ONE_TIME_INFO, oneTimeInfoOCRShown);
        editor.apply();
        this.oneTimeInfoPDFReaderPlus = oneTimeInfoPDFReaderPlus;
    }

    public Boolean getDonorMessageShown() {
        return isDonorMessageShown;
    }

    public void setDonorMessageShown(Boolean donorMessageShown) {
        editor = spref.edit();
        editor.putBoolean(Constants.DONOR_MESSAGE_KEY, donorMessageShown);
        editor.apply();
        isDonorMessageShown = donorMessageShown;
    }

    public Boolean getUserNonAndroid() {
        return isUserNonAndroid;
    }

    public void setUserNonAndroid(Boolean userNonAndroid) {
        editor = spref.edit();
        editor.putBoolean(Constants.IS_MODEL_NON_ANDROID, userNonAndroid);
        editor.apply();
        isUserNonAndroid = userNonAndroid;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        editor = spref.edit();
        editor.putString(Constants.TOKEN_KEY, gcmToken);
        editor.apply();
        this.gcmToken = gcmToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        editor = spref.edit();
        editor.putString(Constants.UUID_KEY, uuid);
        editor.apply();
        this.uuid = uuid;
    }

    public Boolean getOwnerInfoSent() {
        return isOwnerInfoSent;
    }

    public void setOwnerInfoSent(Boolean ownerInfoSent) {
        editor = spref.edit();
        editor.putBoolean(Constants.IS_OW_INF_SNT, ownerInfoSent);
        editor.apply();
        isOwnerInfoSent = ownerInfoSent;
    }

    public String getLastLatitude() {
        return lastLatitude;
    }

    public void setLastLatitude(String lastLatitude) {
        editor = spref.edit();
        editor.putString(Constants.LAST_LAT, lastLatitude);
        editor.apply();
        this.lastLatitude = lastLatitude;
    }

    public String getLastLongitude() {
        return lastLongitude;
    }

    public void setLastLongitude(String lastLongitude) {
        editor = spref.edit();
        editor.putString(Constants.LAST_LNG, lastLongitude);
        editor.apply();
        this.lastLongitude = lastLongitude;
    }

    public String getLastChannelURL() {
        return lastChannelURL;
    }

    public void setLastChannelURL(String lastChannelURL) {
        editor = spref.edit();
        editor.putString(Constants.LAST_CHANNEL_URL, lastChannelURL);
        editor.apply();
        this.lastChannelURL = lastChannelURL;
    }

    public boolean getLicensed() {
        return licensed;
    }

    public void setLicensed(boolean licensed) {
        editor = spref.edit();
        editor.putBoolean(Constants.LICENSE_KEY, licensed);
        editor.apply();
        this.licensed = licensed;
    }

    public boolean getLicensedJS() {
        return licensed;
    }

    public void setLicensedJS(boolean licensedJS) {
        editor = spref.edit();
        editor.putBoolean(Constants.LICENSE_KEY_JS, licensedJS);
        editor.apply();
        this.licensedJS = licensedJS;
    }


    public Boolean getFirstTimeChat() {
        return firstTimeChat;
    }

    public void setFirstTimeChat(Boolean isFirstChat) {
        editor = spref.edit();
        editor.putBoolean(Constants.IS_FIRST_CHAT, isFirstChat);
        editor.apply();
        this.firstTimeChat = isFirstChat;
    }

    public Boolean getPirated_1_7() {
        return isPirated_1_7;
    }

    public void setPirated_1_7(Boolean pirated_1_7) {
        isPirated_1_7 = pirated_1_7;
        editor = spref.edit();
        editor.putBoolean(Constants.KEY_IS_PIRATED_1_7, pirated_1_7);
        editor.apply();
    }

    public String getSecurityLog() {
        return securityLog;
    }

    public void setSecurityLog(String securityLog) {
        this.securityLog = securityLog;
        editor = spref.edit();
        editor.putString(Constants.SECURITY_LOG_KEY, securityLog);
        editor.apply();
    }

    public Set<String> getActivatedModules() {
        return activatedModules;
    }

    public void addActivatedModule(String moduleName) {
        if (activatedModules == null) {
            activatedModules = new HashSet<>();
        }
        Boolean moduleMatched = false;
        for (String modName : activatedModules) {
            if (modName.matches(moduleName)) {
                moduleMatched = true;
            }
        }
        if (!moduleMatched) {
            this.activatedModules.add(moduleName);
            editor = spref.edit();
            editor.putStringSet(Constants.ACT_MOD_KEY, activatedModules);
            editor.apply();
        }
    }

    public void setActivatedModules(Set<String> moduleSet) {
        this.activatedModules = moduleSet;
        editor = spref.edit();
        editor.putStringSet(Constants.ACT_MOD_KEY, activatedModules);
        editor.apply();
    }

    public int getLicenseFailReason() {
        return Integer.parseInt(licenseFailReason);
    }

    public void setLicenseFailReason(int licenseFailReason) {
        this.licenseFailReason = Integer.toString(licenseFailReason);
        editor = spref.edit();
        editor.putInt(Constants.KEY_LICENSE_FAIL_REASON, licenseFailReason);
        editor.apply();
    }

    public String getPreferredTTSEngine() {
        return preferredTTSEngine;
    }

    // Crash fix fabric 458 by Gurleen
    public boolean checkPreferredTTSEngine() {
        return (null != preferredTTSEngine);
    }

    public void setPreferredTTSEngine(String ttsEngine) {
        this.preferredTTSEngine = ttsEngine;
        editor = spref.edit();
        editor.putString(Constants.KEY_PREF_TTS, ttsEngine);
        editor.apply();
    }

    public Boolean getRefundPolicyAug_219_agreed() {
        return refundPolicyAug_219_agreed;
    }

    public void setRefundPolicyAug_219_agreed(Boolean refundPolicyAug_219_agreed) {
        this.refundPolicyAug_219_agreed = refundPolicyAug_219_agreed;
        editor = spref.edit();
        editor.putBoolean(Constants.KEY_REFAUG2019, refundPolicyAug_219_agreed);
        editor.apply();
    }

    public Set<String> getPurchasedModulenameStringSet() {
        return purchasedModulenameStringSet;
    }

    public void setPurchasedModulenameStringSet(ArrayList<String> purchasedModulenameArrayList) {
        Set<String> set = new HashSet<String>();
        set.addAll(purchasedModulenameArrayList);
        editor = spref.edit();
        editor.putStringSet(Constants.CONSTANTS_SPREF_KEY_PURCHASEDMODULE, set);
        editor.commit();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

}
