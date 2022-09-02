package in.levelup.colorreader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.bugfender.sdk.Bugfender;
import com.bugfender.android.BuildConfig;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.securepreferences.SecurePreferences;
import com.squareup.otto.Bus;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.levelup.colorreader.Analytics.FirebaseAnalyticsHelper;
import in.levelup.colorreader.FirebaseCloudCalls.FirebaseCloudFunctions;
import in.levelup.colorreader.database.FirebaseRTDBHelper;
import in.levelup.colorreader.logger.LogcatFileWriter;
import in.levelup.colorreader.util.Constants;
import in.levelup.colorreader.util.UserSettings;
import io.michaelrocks.paranoid.Obfuscate;

//
//import com.crashlytics.android.answers.Answers;

/**
 * Created by hariharan on 24/6/15.
 */
@Obfuscate
public class App extends MultiDexApplication {

    static {
        //System.loadLibrary("nc");
    }

    public static Constants constants;
    public static String TAG = "APPLICATION";
    public static String inFocusActivity = null;
    public static Bus bus;
    public static Boolean noConnectivity = false;

    int locationMode;
    public LocationApiClient mLAC;

    private Tracker mTracker;
    public static boolean isDebugVersion;
    public boolean isRemoteDebuggingOn = false;
    public static UserSettings mUserSettings;
    public static boolean appsignvalidfrmapp = true;
    public static boolean plststyinstldfrmapp = true;

    public static FirebaseCloudFunctions firebaseCloudFunctions = null;
    private ArrayList<String> codes = new ArrayList<>();

    AlertDialog alertDialogNotLicensed;
    public static String installer = new String("");
    //public MyBillingImpl mMybillingImpl;
    ArrayList<String> classchild = new ArrayList<>();
    int classchildframe = 0;
    android.os.ParcelFormatException p=null;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                //checkP();
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(r, 1000);
        PackageManager pm = this.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Signature[] signatures = pi.signatures;
        Signature signature0 = signatures[0];
        //System.out.println("--."+signature0.toCharsString());

        super.onCreate();

        Bugfender.init(this, "fWakBYgYQxDQNE3nxSfrd5jftMX7mlqa", BuildConfig.DEBUG);
        Bugfender.enableCrashReporting();
        Bugfender.enableUIEventLogging(this);
        Bugfender.enableLogcatLogging(); // optional, if you want logs automatically collected from logcat

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //context = this;

        App.isDebugVersion = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
        //Log.d(TAG, "App oncreate() called");
        int i=-1;
        /*try {
            i= activityCallback();
        } catch (JSONException e) {
            e.printStackTrace();
            Object p=null;
            p.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/

        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
        boolean b = listAssetFiles("");
        int s= (i==0)? initEssentials():-1;
        //Log.d(TAG, "App oncreate() finished");
        //Stetho.initializeWithDefaults(this);
    }

    private boolean listAssetFiles(String path) {

        String [] list;
        CustomStringList3 sd = new CustomStringList3();
        Collections.addAll(sd,"Hook_apk", "libjiagu.so","libjiagu_x64.so",
                "libjiagu_x86.so","arm","hook.apk","Arm_Epic","App_dex","Hook_so","Modex.txt",
                "liblOHook.so", "libmockls.so","libsandhook.so","package$Info");

        try {
            list = getAssets().list(path);
            if (list.length > 0) {
                // This is a folder
                for (String file : list) {
                    android.os.ParcelFormatException f=null;
                    //Log.d(TAG,"..-"+file);
                    Throwable d = (!sd.contains(file)) ? new Throwable() : f.fillInStackTrace();
                    Throwable dd = (!sd.contains(path)) ? new Throwable() : f.fillInStackTrace();
                    if (!listAssetFiles(file))
                        return false;
                    else {
                        // This is a file
                        // TODO: add file name to an array list
                        classchild.add(file);
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }

        //Log.d(TAG,"..-"+classchild);
        return true;
    }

    public class CustomStringList3 extends ArrayList<String> {
        @Override
        public boolean contains(Object o) {
            String paramStr = (String)o;
            for (String s : this) {
                if (paramStr.equalsIgnoreCase(s)) return true;
            }
            return false;
        }
    }

    private int activityCallback() throws JSONException, PackageManager.NameNotFoundException {
        //check lib path
        String path = this.getApplicationInfo().nativeLibraryDir;
        //String libraryPath = this.getApplicationInfo().dataDir + "/lib";
        //Log.d(TAG,"--."+libraryPath);
        Log.d(TAG,"--."+path);
        int count = getFile(path);
        //Log.d(TAG, "--.count is: "+count);

        //apk size
        final PackageManager pm = this.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = pm.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        File file = new File(applicationInfo.publicSourceDir);
        int size = (int) file.length();

        //Log.d(TAG,"--."+ String.valueOf(size));

        //TODO: USE https://developer.android.com/topic/security/data
        String s = "{ \"305\":{\"l8\": 15, \"l7\":16, \"s\":7506523} " +
                ","+
                "\"308\":{\"l8\": 16, \"l7\":16, \"s\":76221312}" +
                ","+
                "\"310\":{\"l8\": 16, \"l7\":16, \"s\":76221312}" +
                ","+
                "\"325\":{\"l8\": 16, \"l7\":16, \"s\":76221312}" +
                ","+
                "\"326\":{\"l8\": 17, \"l7\":17, \"s\":76221312}" +
                "}";
        JSONObject j = new JSONObject(s);
        PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        int verCode = pInfo.versionCode;
        String type = path.contains("arm64")? "l8":"l7";
//        Throwable d = (count == j.getJSONObject(String.valueOf(verCode)).getInt(type)) ? new Throwable() : p.fillInStackTrace();
        //Log.d("--.j", j.toString());

        Pattern pt = Pattern.compile(".*/");
        Matcher m = pt.matcher(path);
        while (m.find()) {
            String payload = m.group(0);
            Log.d(TAG,"--."+payload);

            File f = new File(payload+"arm64");
          //  if(f.exists()) d = (count == j.getJSONObject(String.valueOf(verCode)).getInt("l8")) ? new Throwable() : p.fillInStackTrace();
            f = new File(payload+"arm");
           // if(f.exists()) d = (count == j.getJSONObject(String.valueOf(verCode)).getInt("l7")) ? new Throwable() : p.fillInStackTrace();
        }
        return 0;
    }

    public static void checkP(){
        try {
            //Log.d(" i am in","checklibrary");
            Set<String> libs = new HashSet<String>();
            String mapsFile = "/proc/" + android.os.Process.myPid() + "/status";
            //Log.d(TAG,"--."+mapsFile);
            BufferedReader reader = new BufferedReader(new FileReader(mapsFile));
            String line;
            String tracer="TracerPid:   0";
            while ((line = reader.readLine()) != null) {
                if(line.contains("Tracer")) {
                    //Log.d(TAG, "--." + line);
                    tracer = line;
                    break;
                }
            }
            if(!tracer.matches("TracerPid:\\s*0")){
                int a=0; int b=1; int c=b/a;
            }
        } catch (FileNotFoundException e) {
            // Do some error handling...
        } catch (IOException e) {
            // Do some error handling...
        }
    }


    private int getFile(String dirPath) {
        int count = 0;
        File f = new File(dirPath);
        File[] files = f.listFiles();

        //File ff = new File(getFilesDir(), "lib");
        String[] ff = fileList();
        CustomStringList3 sd = new CustomStringList3();
        Collections.addAll(sd,"libfuck.so", "libarm.so","libarm_signer.so",
                "libsandhook.so","libEpic.so","libcnfix.so","libfrida-gadget.so");
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                count++;
                File file = files[i];

                Log.d(TAG,"..-"+file.getName());
                if(sd.contains(file.getName())){
                    count = -1;
                    return count;
                }

                if (file.isDirectory()) {
                    getFile(file.getAbsolutePath());
                }
            }
            return count;
        }
        else {
            return -1;
        }
    }


    private int initEssentials() {

        FirebaseOptions.Builder builder = new FirebaseOptions.Builder()
                .setApplicationId("1:327467773696:android:54da25094222e31a")
                .setApiKey(Constants.EYEDPRO_ANDROID_CLOUDVISION_KEY)
                .setDatabaseUrl("https://eye-d-pro.firebaseio.com/")
                .setProjectId("eye-d-pro")
                .setStorageBucket("eye-d-pro.appspot.com");
        FirebaseApp.initializeApp(this, builder.build());

        //Registering notification channel (Required for Android 8.0+)
        createNotificationChannel();

        //Init mUserSettings
        App.mUserSettings = new UserSettings(new SecurePreferences(getApplicationContext()),
                getApplicationContext());

        //Init bugfender user key
        Bugfender.setDeviceString("com.google", App.mUserSettings.getUserEmail());
        if(App.mUserSettings.getUserEmail().contains("gauravm") ||
                App.mUserSettings.getUserEmail().contains("mijh073975")
                || App.mUserSettings.getUserEmail().contains("kanaibhandari87")
                || App.mUserSettings.getUserEmail().contains("sujay.kumar31113")
                ||App.mUserSettings.getUserEmail().contains("mahmood.hojabri")
                ||App.mUserSettings.getUserEmail().contains("dnyaneshwarbhosale9595")
                ||App.mUserSettings.getUserEmail().contains("mayur08.dhupad")||
                App.mUserSettings.getUserEmail().contains("shyamkamble850")){
            Bugfender.setForceEnabled(true);
        }

        try {
            Class<?> act = Class.forName("com.frida.injector");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }

        try {
            Class<?> act = Class.forName("bin.mt.apksignaturekillerplus.HookApplication");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }
        try {
            Class<?> act = Class.forName("cc.binmt.signature.PmsHookApplication");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }
        try {
            Class<?> act = Class.forName("cc.binmt.signature.Hook");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }
        try {
            Class<?> act = Class.forName("np.manager.FuckSign");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }
        try {
            Class<?> act = Class.forName("arm.ArmKill");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }
        try {
            Class<?> act = Class.forName("np.manager.signature.PmsHookApplication");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }
        try {
            Class<?> act = Class.forName("com.swift.sandhook.SandHook");
            p.fillInStackTrace();
        } catch (ClassNotFoundException e) {
        }

        FirebaseCrashlytics.getInstance().setCustomKey(App.mUserSettings.getUserEmail(),
                "com.google");

        //Init firebaseAnalytics
        if (FirebaseAnalyticsHelper.mFirebaseAnalytics == null) {
            FirebaseAnalyticsHelper.setFirebaseAnalytics(FirebaseAnalytics.getInstance(this));
        }

        FirebaseRTDBHelper helper = new FirebaseRTDBHelper(this);
        helper.fetchUser(App.mUserSettings.getUserEmail(), App.mUserSettings.getUuid());

        //Setting Firebase cloud
        if (firebaseCloudFunctions == null) {
            firebaseCloudFunctions = new FirebaseCloudFunctions();
        }

        //Get current FCM Token ID (If any)
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        //Log.e(TAG, "FCM TOKEN ID: " + token);
                    }
                });

        //Subscribing the app to topic "all"
        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "subscription successful.";
                        if (!task.isSuccessful()) {
                            msg = "FCM Subscription failed";
                        }
                        //Log.d(TAG, msg);
                    }
                });

        //Test topic (only for debug)
        if (isDebugVersion) {
            FirebaseMessaging.getInstance().subscribeToTopic("debug")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "subscription successful.";
                            if (!task.isSuccessful()) {
                                msg = "FCM Subscription failed";
                            }
                            //Log.d(TAG, msg);
                        }
                    });
        }

        //mMybillingImpl = new MyBillingImpl();
        //mMybillingImpl.initialize(this);

        return 0;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Constants.EYEDPRO_NOTIFICATION_CHANNEL_ID, Constants.EYEDPRO_NOTIFICATION_CHANNEL_NAME, importance);
            channel.setDescription(Constants.EYEDPRO_NOTIFICATION_CHANNEL_DESCRIPTION);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }

    public ArrayList<String> passSecReqs() {
        boolean secPass = true;

        /*if (App.mUserSettings.getLicensed() == false) {
            //throw not licensed dialog
            if (!codes.contains(-794)) {
                codes.add(-794);
            }
            secPass = false;
        }*/

        /*if (!firebaseDBHelperDone) {
            if(!codes.contains("-792")) {
                codes.add("-792");
            }
            //secPass=false;
        }*/
        if (App.mUserSettings.getServerOverride() == true) {
            codes = new ArrayList<>();
            return codes;
        }

        if (App.mUserSettings.getLicensed() == false && App.mUserSettings.getServerOverride() != true
                && !App.mUserSettings.isBulkLicensed()) {

            if (!codes.contains("-8966")) {
                codes.add("-8966");
            }
        }

        if (!App.plststyinstldfrmapp) {
            if (!codes.contains("-795")) {
                codes.add("-795");
            }
        }

        if (!App.appsignvalidfrmapp) {
            if (!codes.contains("-796")) {
                codes.add("-796");
            }
        }


        /*if (isRooted){
            codes.add("-796");
            //TO REMOVE ROOT CHECK COMMENT BELOW TWO LINES
            //showRootDialogCancellable();
            //return false;
        }*/

        return codes;

    }

    //

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        //if(isDebugVersion){
        //  return null;
        //}

        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }

    public boolean checkLicense(Activity activity) {

        if (!MainActivity.gzzv) {
            return true;
        }
        ArrayList<String> codes = passSecReqs();

        if (codes.size() != 0) {
            showNotLicensedDialog(codes, activity);
            return false;
        }
        return true;
    }

    private void showNotLicensedDialog(ArrayList<String> codes, Activity activity) {

        String s = codes.toString();

        if (alertDialogNotLicensed != null) {
            if (alertDialogNotLicensed.isShowing()) {
                alertDialogNotLicensed.setMessage(getResources().getString(R.string.app_not_licensed_message) + " errorcode:" + s);
                return;
            }
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                activity);

        // set title
        alertDialogBuilder.setTitle(getResources().getString(R.string.app_not_licensed));

        // set dialog message
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.app_not_licensed_message) + " errorcode:" + s)
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.goto_playstore), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            Intent goToPlayStore = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                            goToPlayStore.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(goToPlayStore);
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                })
                .setNegativeButton(getResources().getString(R.string.later), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        alertDialogNotLicensed = alertDialogBuilder.create();
        alertDialogNotLicensed.show();
    }

    @Obfuscate
    private final class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {

        @SuppressLint("SourceLockedOrientationActivity")
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Log.i(TAG, "onActivityCreated:" + activity.getLocalClassName());
            //fix https://fabric.io/eyed/android/apps/in.gingermind.eyedpro/issues/184d67dd56ffb71e5e159674297105a9?time=last-seven-days
            if (android.os.Build.VERSION.SDK_INT != 26) {
                if (activity.getPackageName().contains("eyedpro"))
                    activity.setRequestedOrientation(
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                Log.e(TAG, "not setting activity.setRequestedOrientation " +
                        "ActivityInfo.SCREEN_ORIENTATION_PORTRAIT since API level 26 crashes.");
            }
        }

        public void onActivityDestroyed(Activity activity) {
            Log.i(TAG, "onActivityDestroyed:" + activity.getLocalClassName());
        }

        public void onActivityPaused(Activity activity) {
            Log.e(TAG, "onActivityPaused:" + activity.getLocalClassName());
            if (LogcatFileWriter.isInitDone()) {
                LogcatFileWriter.saveLogToFile();
            }
            inFocusActivity = null;
        }

        public void onActivityResumed(Activity activity) {
            Log.i(TAG, "onActivityResumed:" + activity.getLocalClassName());
            inFocusActivity = activity.getLocalClassName();
            if (MainActivity.gzzv) {
                activityCallbackLifecycle();
                activityLifecyleCallback();
            }
        }

        public void onActivitySaveInstanceState(Activity activity,
                                                Bundle outState) {
            Log.i(TAG, "onActivitySaveInstanceState:" + activity.getLocalClassName());
        }

        public void onActivityStarted(Activity activity) {
            Log.i(TAG, "onActivityStarted:" + activity.getLocalClassName());
            inFocusActivity = activity.getLocalClassName();
        }

        public void onActivityStopped(Activity activity) {
            Log.i(TAG, "onActivityStopped:" + activity.getLocalClassName());
        }
    }

    private boolean isCertificateValid() {

        String[] keys = new String[2];
        keys[0] = "XRy2K4ejglgkCfoQ5p6rpi3vfvA=";//release key of eye-d pro - INDEX 0
        keys[1] = "U4B2oeyvWkuvFdhKhH1jC4YCCVI=";//debug key of gaurav - INDEX 1


        try {
            //Log.wtf("REMOVE_ME", "Now in checkAppSignature " );

            PackageInfo packageInfo = this.getPackageManager()

                    .getPackageInfo(this.getPackageName(),

                            PackageManager.GET_SIGNATURES);
            //Log.wtf("REMOVE_ME", "Now obtained signatures " );

            int s = packageInfo.signatures.length;
            //Log.wtf("REMOVE_ME", "Obtained signatures length="+s);

            for (Signature signature : packageInfo.signatures) {

                byte[] signatureBytes = signature.toByteArray();
                MessageDigest md = MessageDigest.getInstance("SHA1");
                md.update(signatureBytes);
                final String currentSignature = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                String currentSignTrim = currentSignature.trim();
                //todo: remove signature printing
                //Log.d(TAG, "currentSignTrim=" + currentSignature);
                for (String key : keys) {
                    if (currentSignTrim.matches(key)) {
                        return true;
                    }
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void activityCallbackLifecycle() {
        if (!isCertificateValid()) {
            appsignvalidfrmapp = false;
            //throw new RuntimeException("Unhandled exception x");
        } else {
            System.out.println("ddx");
        }
    }

    public void activityLifecyleCallback() {
        //REMOVED SINCE THIS CAUSES FALSE -VE ALARM AND SHOWS NOT LCIENSED SINCE ANDROID 10
        //https://www.yellowduck.be/posts/checking-if-android-app-installed-google-play/
        //THIS WORKS FINE : MYPIRACYCHECKEr.piracyCheckerNotPlaystoreJS()
        /*if (MainActivity.pstr) {
            //if (!checkPlaystoreInstalled()) {
            if (!checkPlaystoreInstalled2()) {
                    plststyinstldfrmapp = false;
            }
            //throw new RuntimeException("Unhandled exception y");
            else {
                System.out.println("ddy");
            }
        }*/
    }
/*
    private boolean checkPlaystoreInstalled() {
        if (android.os.Build.VERSION.SDK_INT <= 28) {
            if (MainActivity.pstr) {
                // A list with valid installers package name
                List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));

                // The package name of the app that has installed your app
                if (installer == "")
                    installer = this.getPackageManager().getInstallerPackageName(this.getPackageName());

                // true if your app has been downloaded from Play Store
                return installer != null && validInstallers.contains(installer);
            }
            return true;
        }

        else if (android.os.Build.VERSION.SDK_INT >= 29)//https://www.yellowduck.be/posts/checking-if-android-app-installed-google-play/
          {
            if (MainActivity.pstr) {
                String packageName = this.getPackageName();
                PackageManager pm = this.getPackageManager();
                InstallSourceInfo info = null;
                try {
                    info = pm.getInstallSourceInfo(packageName);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String installerPackageName = "";
                if (info != null) {
                    installerPackageName = info.getInstallingPackageName();
                }
                if ("com.android.vending".equals(installerPackageName)){
                    return true;
                }
                else return false;
            }
        }
        return true;
    }

    private boolean checkPlaystoreInstalled2() {

        try {
            if (MainActivity.pstr) {
                String packageName = this.getPackageName();
                PackageManager pm = this.getPackageManager();
                InstallSourceInfo info = null;
                try {
                    info = pm.getInstallSourceInfo(packageName);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String installerPackageName = "";
                if (info != null) {
                    installerPackageName = info.getInstallingPackageName();
                }
                if ("com.android.vending".equals(installerPackageName)) {
                    return true;
                } else return false;
            }
        }
        catch (NoSuchMethodError e){
            if (MainActivity.pstr) {
                // A list with valid installers package name
                List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));

                // The package name of the app that has installed your app
                if (installer == "")
                    installer = this.getPackageManager().getInstallerPackageName(this.getPackageName());
                //Log.d(TAG,"cpstr2: ins:"+installer);
                // true if your app has been downloaded from Play Store
                return installer != null && validInstallers.contains(installer);
            }
            return true;
        }

        return true;
    }
*/
}


