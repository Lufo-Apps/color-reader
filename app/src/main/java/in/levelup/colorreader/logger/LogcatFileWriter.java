package in.levelup.colorreader.logger;

import android.app.ActivityManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.levelup.colorreader.App;

/**
 * Created by gauravmittal on 07/10/17.
 */
public class LogcatFileWriter {

    private static final String TAG = "LogcatFileWriter";
    private static String lastLogDateTime = "";
    private static Pattern dateTimePattern, datePattern;
    private static File logFile;
    private static FileOutputStream oFile = null;
    private static boolean initDone = false;
    private static PackageManager packageManager;
    private static ActivityManager activityManager;

    public static void init(PackageManager m, ActivityManager a) {

        packageManager = m;
        activityManager = a;

        //fetch last log time
        lastLogDateTime = App.mUserSettings.getLastLogDateTime();

        //create new directory if not exists
        String s = "in.gingermind.eyedpro";
        PackageInfo p = null;
        try {
            p = m.getPackageInfo(s, 0);
        } catch (PackageManager.NameNotFoundException e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
        }
        s = p.applicationInfo.dataDir;
        File directory = new File(s + File.separator + "logs");
        directory.mkdirs();
        Log.i(TAG, "LogDir:" + directory.getAbsolutePath());

        //create today's log file if not exist
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        SimpleDateFormat dfCurrent = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentSystemDateTime = dfCurrent.format(c.getTime());

        logFile = new File(directory, formattedDate + ".log");

        try {
            logFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
        }

        setStartOfLogFile();

        //create pattern to pull logdatetime from https://regexr.com/
        dateTimePattern = Pattern.compile(".*\\.[0-9]{3}");
        datePattern = Pattern.compile("[0-9]*-[0-9]{2}");

        initDone = true;
    }

    public static boolean isInitDone() {
        return initDone;
    }

    public static void setStartOfLogFile() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dfCurrent = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentSystemDateTime = dfCurrent.format(c.getTime());

        Log.d(TAG, "===============NEW SESSION STARTED AT " + currentSystemDateTime + "===========");

        PackageInfo pInfo = null;
        String versionName = "";
        int verCode = -1;

        try {
            pInfo = packageManager.getPackageInfo("in.gingermind.eyedpro", 0);
            versionName = pInfo.versionName;
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
        }

        try {
            oFile = new FileOutputStream(logFile, true);
            String start = "===============NEW SESSION STARTED AT " + currentSystemDateTime + "===========" + "\n\n\n";
            oFile.write(start.getBytes());

            String metaData = "EMAIL: " + App.mUserSettings.getUserEmail() + "\n" +
                    "ANDROID_ID: " + App.mUserSettings.getUuid() + "\n" +
                    "IS LICENSED: " + App.mUserSettings.getLicensedJS() + "\n" +
                    "APP VERSION NAME: " + versionName + "\n" +
                    "APP VERSION CODE: " + verCode + "\n" +
                    "ANDROID VERSION: " + Build.VERSION.SDK_INT + "\n" +
                    "MANUFACTURER: " + Build.MANUFACTURER + "\n" +
                    "MODEL: " + Build.MODEL + "\n";

            //Write free memory in log
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(mi);
            long availableMegs = mi.availMem / 1048576L;
            long percentAvail = mi.availMem / mi.totalMem;

            metaData += "Available Memory: " + availableMegs + " MB" + "\n" +
                    "Available Memory percentage: " + percentAvail + "%" + "\n";

            oFile.write(metaData.getBytes());
            oFile.close();

        } catch (FileNotFoundException e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
        } catch (IOException e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
        }
    }


    public static void saveLogToFile() {
        try {
            //log.wtf(TAG,"saveLogToFile:oFile = new FileOutputStream(logFile, true)");
            oFile = new FileOutputStream(logFile, true);
            //log.wtf(TAG,"saveLogToFile:oFile.write(getLog().getBytes())");
            String log = getLog();
            if(log!=null) {
                oFile.write(log.getBytes());
                //log.wtf(TAG,"saveLogToFile:oFile.close();");
                oFile.close();
            }
            //log.wtf(TAG,"saveLogToFile:done");
        } catch (FileNotFoundException e) {
            //log.wtf(TAG,"saveLogToFile:"+e.getMessage());
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
        } catch (IOException e) {
            //log.wtf(TAG,"saveLogToFile:"+e.getMessage());
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
        }
    }


    private static String getLog() {
        try {
            Process process;

            /*if(lastLogDateTime.matches("")) {
                process = Runtime.getRuntime().exec("logcat -d");
            }
            else {
                //String command = "logcat -t '"+lastLogDateTime.replaceAll("\\s","")+"'";
                //process = Runtime.getRuntime().exec(command);

                ArrayList<String> commandLine = new ArrayList<String>();
                commandLine.add("logcat");
                commandLine.add("-d");
                commandLine.add("-t '"+lastLogDateTime.replaceAll("\\s","")+"'");
                process = Runtime.getRuntime().exec(commandLine.toArray(new String[0]));
            }*/
            //log.wtf(TAG,"getLog:");
            process = Runtime.getRuntime().exec("logcat -d");

            //log.wtf(TAG,"getLog:BufferedReader bufferedReader = new");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //log.wtf(TAG,"getLog:StringBuilder log = new StringBuilder()");
            StringBuilder log = new StringBuilder();
            //log.wtf(TAG,"getLog:String currentLine, lastLine=\"\"");
            String currentLine, lastLine = "";
            //log.wtf(TAG,"getLog:while ((currentLine = bufferedReader.readLine()) != null");
            while ((currentLine = bufferedReader.readLine()) != null) {
                //log.wtf(TAG,"getLog:log.append(currentLine);");
                log.append(currentLine);
                //log.wtf(TAG,"getLog:log.append(\"\\n\");");
                log.append("\n");
                //log.wtf(TAG,"getLog:lastLine=currentLine;");
                lastLine = currentLine;
            }
            //log.wtf(TAG,"getLog:Runtime.getRuntime().exec(\"logcat -c\");");
            Runtime.getRuntime().exec("logcat -c");

            //log.wtf(TAG,"getLog:Matcher dateTimeMatcher = dateTimePattern.matcher(lastLine);");
            Matcher dateTimeMatcher = dateTimePattern.matcher(lastLine);

            //log.wtf(TAG,"getLog:if (dateTimeMatcher.find()) {");
            if (dateTimeMatcher.find()) {
                //log.wtf(TAG,"getLog:setLastLogTime(dateTimeMatcher.group(0));");
                setLastLogTime(dateTimeMatcher.group(0));
            }

            //log.wtf(TAG,"getLog:Matcher dateMatcher = datePattern.matcher(lastLine);");
            Matcher dateMatcher = datePattern.matcher(lastLine);

            //log.wtf(TAG,"getLog:if(dateMatcher.find()){");
            if (dateMatcher.find()) {
                //log.wtf(TAG,"getLog:setLastDate(dateMatcher.group(0));");
                setLastDate(dateMatcher.group(0));
            }

            //log.wtf(TAG,"getLog:return log.toString();");
            return log.toString();

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            FirebaseCrashlytics.getInstance().recordException(e);
            e.printStackTrace();
            return null;
        }
    }


    private static void setLastLogTime(String group) {
        lastLogDateTime = group;
        App.mUserSettings.setLastLogDateTime(group);
    }

    private static void setLastDate(String group) {
        String lastLogDate = group;
        App.mUserSettings.setLastLogDate(group);
    }

    public static ArrayList<File> getLast30DayFileList() {
        if (initDone) {
            ArrayList<File> fileList = new ArrayList<>();

            //create new directory if not exists
            String s = "in.gingermind.eyedpro";
            PackageInfo p = null;
            try {
                p = packageManager.getPackageInfo(s, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                FirebaseCrashlytics.getInstance().recordException(e);
            }
            s = p.applicationInfo.dataDir;
            File directory = new File(s + File.separator + "logs");
            directory.mkdirs();
            Log.i(TAG, "LogDir:" + directory.getAbsolutePath());

            //create today's log file if not exist
            Calendar c = Calendar.getInstance();

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date todayDate = c.getTime();
            String formattedTodayDateString = df.format(todayDate);

            File[] files = directory.listFiles();

            if (files == null) {
                return null;
            }

            Log.d("Files", "Size: " + files.length);
            for (int i = 0; i < files.length; i++) {
                Log.d("Files", "FileName:" + files[i].getName());
                String[] splitted = files[i].getName().split("\\.");
                Date fileModifiedDate = null;
                try {
                    fileModifiedDate = df.parse(splitted[0]);
                } catch (ParseException e) {
                    FirebaseCrashlytics.getInstance().recordException(e);
                    e.printStackTrace();
                }
                //Date fileModifiedDate = new Date(files[i].lastModified());
                String fileModifiedDateString = df.format(fileModifiedDate);

                long diff = todayDate.getTime() - fileModifiedDate.getTime();
                long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                System.out.println("Days from today: " + days);

                if (days == 0) {
                    if (!formattedTodayDateString.matches(fileModifiedDateString)) {
                        //If log file is yesterday 24 hours have not passed
                        fileList.add(files[i]);
                    }
                } else if (days <= 30) {
                    fileList.add(files[i]);
                }
            }

            return fileList;
        } else return null;
    }
}
