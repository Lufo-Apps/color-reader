package in.levelup.colorreader.database;

//import static in.gingermind.eyedpro.MainActivity.isMainActivityRootTask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import in.levelup.colorreader.App;
import in.levelup.colorreader.MainActivity;
import io.michaelrocks.paranoid.Obfuscate;

/**
 *
 * Currently the main DB Helper Class (until further deprecation)
 * This class maintains a separate collections "eyedpro_stats_relase", "independent_stats", "stream_stats" and "daily_stats" in the eye-d-pro database.
 *
 * Each object in the array: "eyedpro_stats" is of type RtdbUser
 * Each object in the array: "independent_stats" is an Integer
 * Each object in the array: "stream_stats" is of type StreamObject
 * Each object in the array: "daily_stats" is of type Integer (grouped on dates)
 *
 * Created by rakshitgl on 26th March, 2020
 *
 */

@Keep
@Obfuscate
public class FirebaseRTDBHelper {

    private static final String TAG = "FirebaseRTDBHelper";
    //Persistent database instance
    private FirebaseDatabase database;

    //context needed to restart app
    private Context context;
    private DatabaseReference reference;

    //PDF READER PLUS
    public static final int PDF_RDR_PLUS_SCAN_SINGLE_CODE = 102;
    private final String PDF_RDR_PLUS_SCAN_SINGLE_EVENT_NAME = "pdf_rdr_plus_scan_single";

    //ADVANCED OCR
    public static final int ADV_OCR_SCAN_SINGLE_CODE = 201;
    private final String ADV_OCR_SCAN_SINGLE_EVENT_NAME = "adv_ocr_scan_single";

    //ADVANCED OCR
    public static final int SEE_OBJECT_SCAN_SINGLE_CODE = 301;
    private final String SEE_OBJECT_SCAN_SINGLE_EVENT_NAME = "see_object_scan_single";

    //ADVANCED OCR
    public static final int READ_TEXT_SCAN_SINGLE_CODE = 401;
    private final String READ_TEXT_SCAN_SINGLE_EVENT_NAME = "read_text_scan_single";

    //WHERE AMI
    public static final int WHEREAMI_SINGLE_CODE = 501;
    private final String WHEREAMI_SINGLE_EVENT_NAME = "whereami_single";

    //AROUNDME
    public static final int AROUNDME_SINGLE_CODE = 601;
    private final String AROUNDME_SINGLE_EVENT_NAME = "aroundme_single";

    //SHAREDTOVISION
    public static final int SHARED_TO_VISION_OBJ_SINGLE_CODE = 701;
    private final String SHARED_TO_VISION_OBJ_SINGLE_EVENT_NAME = "shared_to_vision_obj_single";

    public static final int SHARED_TO_VISION_READ_SINGLE_CODE = 702;
    private final String SHARED_TO_VISION_READ_SINGLE_EVENT_NAME = "shared_to_vision_read_single";

    //NEWS
    public static final int NEWS_LIST_REQ_CODE = 801;
    private final String NEWS_LIST_REQ_NAME = "total_news_list_reqs";

    public static final int ARTICLE_OPEN_REQ_CODE = 802;
    private final String ARTICLE_OPEN_REQ_NAME = "total_article_open_reqs";

    public static final int ARTICLE_ERROR_CODE = 803;
    private final String ARTICLE_ERROR_NAME = "total_article_open_errors";

    //RTDB Properties that can be useful

    //default google library
    public static String licenseLvl = "PASS";
    public static String licenseGm = "PASS";

    //Javier Santos library
    public static String licenseJs = "PASS";

    //Not installed from Play Store
    public static String playStoreInstall = "PASS";

    //Certificate property: B64
    public static String certificateB64;

    //Certification Validation Flag
    public static String certificateValid;

    //Pirated App Installed Flag
    public static String piracyCheck = "PASS";

    //Rooted Phone Flag
    public static String rootCheck = "FAIL";

    //Persistence set flag
    public static boolean isPersistent = false;
    public static String currentEmail;

    /**
     * Basic constructor: initializes database instance and database reference
     *
     * @param context is the context of the calling activity
     */

    public FirebaseRTDBHelper(Context context){

        makeDatabasePersistent();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("eyedpro_stats_release");
        this.context = context;
    }

    /**
     * Helper function that creates a RtdbUser object from UID and email and inserts it to database
     * @param UID deviceID of the user's device
     * @param email user's registered email address
     */
    public void insertUser(String UID, String email){

        RtdbUser user = new RtdbUser(email, UID);
        insertUser(user);
    }

    /**
     * Helper function that inserts a RtdbUser object to eyedpro_stats collection
     * @param user the object that needs to be inserted to the DB
     */
    private void insertUser(RtdbUser user){

        if(!currentEmail.trim().isEmpty() && !user.getDeviceID().isEmpty()) {

            reference.child(currentEmail.concat("_").concat(user.getDeviceID())).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                   /* if(isMainActivityRootTask)
                    {
                        //Let MainActivity know that the user was inserted
                        Message message = MainActivity.mHandler.obtainMessage();
                        message.what = Constants.H_FIREBASE_DB_NEW_USER_CREATED;
                        MainActivity.mHandler.sendMessage(message);
                    } */
                }
            });
        }
        else{

            if(!App.mUserSettings.getIsLoggedIn()){

                return;
            }

            //If user is logged in, and null deviceID is found then restart the app

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }

    /**
     *
     * @param email the user email that'll be used to fetch object. (If email is an empty string then it is safely ignored as we use deviceID as our unique key)
     * @param UID the user deviceID that'll be used to fetch object
     */
    public void fetchUser(String email, String UID){

        final RtdbUser user = new RtdbUser(email,UID);

        if((email == null || email.isEmpty()) && (currentEmail == null || currentEmail.isEmpty())){
            Log.e("fetchUser", "Error in Email");
        }
        else{

            if(email != null && !email.isEmpty() && email.contains("@")){
                currentEmail = email.split("@")[0];
                currentEmail = currentEmail.replaceAll("\\.", "-");
            }
        }

        if(!user.getDeviceID().trim().isEmpty() && !currentEmail.trim().isEmpty()) {

            DatabaseReference ref = reference.child(currentEmail.concat("_").concat(user.getDeviceID()));

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try {
                        RtdbUser userInternal = dataSnapshot.getValue(RtdbUser.class);

                        if (userInternal == null || userInternal.getDeviceID().trim().isEmpty()) {

                            throw new Exception("Error fetching user");

                        } else {
                            if (email != null && !email.trim().isEmpty() && !email.equals(userInternal.getEmail())) {

                                //If a new user email got registered in the same device, update the email in our database
                                //Already checked for null email field. Ignore if email is null.

                                userInternal.setEmail(email);
                                ref.setValue(userInternal);

                                //Let MainActivity know that email was updated

                               // Message message = MainActivity.mHandler.obtainMessage();
                               // message.what = Constants.H_FIREBASE_DB_USER_EMAIL_UPDATED;
                               // MainActivity.mHandler.sendMessage(message);
                                return;
                            }

                            //Let MainActivity know that an existing user was successfully fetched from the DB

                            //Message message = MainActivity.mHandler.obtainMessage();
                            //message.what = Constants.H_FIREBASE_DB_USER_EXISTS_AND_FETCHED;
                            //MainActivity.mHandler.sendMessage(message);
                        }
                    } catch (Exception e) {

                        Log.e("FirebaseRTDBHelper ", "onDataChange: " + e.getMessage());
                        e.printStackTrace();

                        if(e.getMessage() != null && e.getMessage().equalsIgnoreCase("Error fetching user")){

                            insertUser(user);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{

            if(!App.mUserSettings.getIsLoggedIn()){
                return;
            }

            //If user is logged in, and null deviceID is found then restart the app

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * Updates the user stats based on API calls made by user's app
     * @param UID the deviceID of the user's device
     * @param flag the code of API which was used by the user (mentioned as static integers in FirebaseRTDBHelper)
     */
    public void updateUserStat(String UID, int flag){

        if(UID.trim().isEmpty() || currentEmail.trim().isEmpty()){
            pushStat(flag);
            pushDailyStat(flag);
            streamStat(flag);
            return;
        }

        final DatabaseReference ref = reference.child(currentEmail.concat("_").concat(UID));

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try{

                    RtdbUser user = dataSnapshot.getValue(RtdbUser.class);

                    if(user == null || user.getDeviceID().trim().isEmpty()){
                        throw new Exception("Error fetching user");
                    }
                    else{

                        Log.e("RTDBHelper", user.getUser_stats().getTotal_shared_to_vision_obj_reqs() + "");

                        switch(flag){

                            case PDF_RDR_PLUS_SCAN_SINGLE_CODE:
                                user.incrementTotal_pdf_plus_reqs();
                                break;

                            case ADV_OCR_SCAN_SINGLE_CODE:
                                user.incrementTotal_adv_ocr_reqs();
                                break;

                            case SEE_OBJECT_SCAN_SINGLE_CODE:
                                user.incrementTotal_see_object_reqs();
                                break;

                            case READ_TEXT_SCAN_SINGLE_CODE:
                                user.incrementTotal_read_text_reqs();
                                break;

                            case WHEREAMI_SINGLE_CODE:
                                user.incrementTotal_whereami_reqs();
                                break;

                            case AROUNDME_SINGLE_CODE:
                                user.incrementTotal_aroundme_reqs();
                                break;

                            case SHARED_TO_VISION_OBJ_SINGLE_CODE:
                                user.incrementTotal_shared_to_vision_obj_reqs();
                                break;

                            case SHARED_TO_VISION_READ_SINGLE_CODE:
                                user.incrementTotal_shared_to_vision_read_reqs();
                                break;

                            case ARTICLE_ERROR_CODE:
                                user.incrementTotal_article_open_errors();
                                break;

                            case ARTICLE_OPEN_REQ_CODE:
                                user.incrementTotal_article_open_reqs();
                                break;

                            case NEWS_LIST_REQ_CODE:
                                user.incrementTotal_news_list_reqs();
                                break;
                        }

                        pushStat(flag);
                        pushDailyStat(flag);
                        streamStat(flag);

                        ref.setValue(user);
                    }
                }
                catch(Exception e){

                    //Some error.
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

    /**
     * Update the overall stats in collection "independent_stats"
     * @param flag the code of API which was used by the user (mentioned as static integers in FirebaseRTDBHelper)
     */
    private void pushStat(int flag){

        //if an unknown flag is encountered, then increment stats at "unknown" path
        String path = "unknown";

        switch(flag){
            case PDF_RDR_PLUS_SCAN_SINGLE_CODE:
                path = PDF_RDR_PLUS_SCAN_SINGLE_EVENT_NAME;
                break;

            case ADV_OCR_SCAN_SINGLE_CODE:
                path = ADV_OCR_SCAN_SINGLE_EVENT_NAME;
                break;

            case SEE_OBJECT_SCAN_SINGLE_CODE:
                path = SEE_OBJECT_SCAN_SINGLE_EVENT_NAME;
                break;

            case READ_TEXT_SCAN_SINGLE_CODE:
                path = READ_TEXT_SCAN_SINGLE_EVENT_NAME;
                break;

            case WHEREAMI_SINGLE_CODE:
                path = WHEREAMI_SINGLE_EVENT_NAME;
                break;

            case AROUNDME_SINGLE_CODE:
                path = AROUNDME_SINGLE_EVENT_NAME;
                break;

            case SHARED_TO_VISION_OBJ_SINGLE_CODE:
                path = SHARED_TO_VISION_OBJ_SINGLE_EVENT_NAME;
                break;

            case SHARED_TO_VISION_READ_SINGLE_CODE:
                path = SHARED_TO_VISION_READ_SINGLE_EVENT_NAME;
                break;

            case ARTICLE_ERROR_CODE:
                path = ARTICLE_ERROR_NAME;
                break;

            case ARTICLE_OPEN_REQ_CODE:
                path = ARTICLE_OPEN_REQ_NAME;
                break;

            case NEWS_LIST_REQ_CODE:
                path = NEWS_LIST_REQ_NAME;
                break;
        }

        //Access the right collection within "independent_stats"
        DatabaseReference statRef = database.getReference("independent_stats").child(path);

        statRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Integer in = dataSnapshot.getValue(Integer.class);
                if(in != null){

                    statRef.setValue(++in);
                }
                else{

                    //If the stat is not found, then create it and set the value to 1
                    statRef.setValue(1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Maintains a stat that is segregated date-wise
     * @param flag is the API event that was just registered
     */

    private void pushDailyStat(int flag){

        //SNTPClient helps us use Network provided time i.e. the app does not use System Time that is susceptible to changes done by user.

        SNTPClient.getDate(TimeZone.getTimeZone("Asia/Kolkata"), new SNTPClient.Listener() {
            @Override
            public void onTimeReceived(String rawDate) {
                // rawDate -> 2019-11-05T17:51:01+0530
                Log.e(SNTPClient.TAG, rawDate);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

                Date date;

                try{
                    date = sdf.parse(rawDate);

                    if(date == null){
                        date = new Date();
                    }
                }
                catch(ParseException e){

                    date = new Date();
                }

                dailyStatSnippet(date, flag);
            }

            @Override
            public void onError(Exception ex) {

                if(ex != null)
                {
                    ex.printStackTrace();
                }

                dailyStatSnippet(new Date(), flag);

            }
        });
    }

    /**
     * Updates RTDB collection appropriately
     * @param date is the network provided time obtained through SNTPClient
     * @param flag is the API event that was just registered
     */
    private void dailyStatSnippet(@NonNull Date date, int flag){

        String path = "unknown";

        SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH);

        String parent = format.format(date);

        switch(flag){
            case PDF_RDR_PLUS_SCAN_SINGLE_CODE:
                path = PDF_RDR_PLUS_SCAN_SINGLE_EVENT_NAME;
                break;

            case ADV_OCR_SCAN_SINGLE_CODE:
                path = ADV_OCR_SCAN_SINGLE_EVENT_NAME;
                break;

            case SEE_OBJECT_SCAN_SINGLE_CODE:
                path = SEE_OBJECT_SCAN_SINGLE_EVENT_NAME;
                break;

            case READ_TEXT_SCAN_SINGLE_CODE:
                path = READ_TEXT_SCAN_SINGLE_EVENT_NAME;
                break;

            case WHEREAMI_SINGLE_CODE:
                path = WHEREAMI_SINGLE_EVENT_NAME;
                break;

            case AROUNDME_SINGLE_CODE:
                path = AROUNDME_SINGLE_EVENT_NAME;
                break;

            case SHARED_TO_VISION_OBJ_SINGLE_CODE:
                path = SHARED_TO_VISION_OBJ_SINGLE_EVENT_NAME;
                break;

            case SHARED_TO_VISION_READ_SINGLE_CODE:
                path = SHARED_TO_VISION_READ_SINGLE_EVENT_NAME;
                break;

            case ARTICLE_ERROR_CODE:
                path = ARTICLE_ERROR_NAME;
                break;

            case ARTICLE_OPEN_REQ_CODE:
                path = ARTICLE_OPEN_REQ_NAME;
                break;

            case NEWS_LIST_REQ_CODE:
                path = NEWS_LIST_REQ_NAME;
                break;
        }

        //Access the right collection within "independent_stats"
        DatabaseReference statRef = database.getReference("daily_stats").child(parent).child(path);

        statRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Integer in = dataSnapshot.getValue(Integer.class);
                if(in != null){

                    statRef.setValue(++in);
                }
                else{

                    //If the stat is not found, then create it and set the value to 1
                    statRef.setValue(1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Maintains a stat that represents a stream of {timestamp, API event called} shaped objects
     * @param flag is the API event that was just registered
     */
    private void streamStat(int flag){

        //SNTPClient helps us use Network provided time i.e. the app does not use System Time that is susceptible to changes done by user.

        SNTPClient.getDate(TimeZone.getTimeZone("Asia/Mumbai"), new SNTPClient.Listener() {
            @Override
            public void onTimeReceived(String rawDate) {
                // rawDate -> 2019-11-05T17:51:01+0530
                Log.e(SNTPClient.TAG, rawDate);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

                Date date;

                try{
                    date = sdf.parse(rawDate);

                    if(date == null){
                        date = new Date();
                    }
                }
                catch(ParseException e){

                    date = new Date();
                }

                streamStatSnippet(date, flag);
            }

            @Override
            public void onError(Exception ex) {

                if(ex != null)
                {
                    ex.printStackTrace();
                }

                streamStatSnippet(new Date(), flag);
            }
        });

    }

    /**
     * Updates RTDB collection appropriately
     * @param date is the network provided time obtained through SNTPClient
     * @param flag is the API event that was just registered
     */
    private void streamStatSnippet(@NonNull Date date, int flag){

        String api = "unknown";

        switch(flag){
            case PDF_RDR_PLUS_SCAN_SINGLE_CODE:
                api = PDF_RDR_PLUS_SCAN_SINGLE_EVENT_NAME;
                break;

            case ADV_OCR_SCAN_SINGLE_CODE:
                api = ADV_OCR_SCAN_SINGLE_EVENT_NAME;
                break;

            case SEE_OBJECT_SCAN_SINGLE_CODE:
                api = SEE_OBJECT_SCAN_SINGLE_EVENT_NAME;
                break;

            case READ_TEXT_SCAN_SINGLE_CODE:
                api = READ_TEXT_SCAN_SINGLE_EVENT_NAME;
                break;

            case WHEREAMI_SINGLE_CODE:
                api = WHEREAMI_SINGLE_EVENT_NAME;
                break;

            case AROUNDME_SINGLE_CODE:
                api = AROUNDME_SINGLE_EVENT_NAME;
                break;

            case SHARED_TO_VISION_OBJ_SINGLE_CODE:
                api = SHARED_TO_VISION_OBJ_SINGLE_EVENT_NAME;
                break;

            case SHARED_TO_VISION_READ_SINGLE_CODE:
                api = SHARED_TO_VISION_READ_SINGLE_EVENT_NAME;
                break;

            case ARTICLE_ERROR_CODE:
                api = ARTICLE_ERROR_NAME;
                break;

            case ARTICLE_OPEN_REQ_CODE:
                api = ARTICLE_OPEN_REQ_NAME;
                break;

            case NEWS_LIST_REQ_CODE:
                api = NEWS_LIST_REQ_NAME;
                break;
        }

        DatabaseReference streamRef = database.getReference("stream_stats");

        StreamObject object = new StreamObject(date.getTime(), api);
        String key = streamRef.push().getKey();

        streamRef.child(key).setValue(object);
    }

    // static method to call persistence on Database

    private static void makeDatabasePersistent(){

        if(!isPersistent) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            isPersistent = true;
        }
    }
}

//Represents the Stream Stat object

@Keep
@Obfuscate
class StreamObject{

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("api")
    private String api;

    public StreamObject(){
        //empty constructor
    }

    public StreamObject(long t, String a){
        timestamp = t;
        api = a;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}