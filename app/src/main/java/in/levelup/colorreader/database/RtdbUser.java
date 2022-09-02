package in.levelup.colorreader.database;

import android.os.Build;

import androidx.annotation.Keep;

//import com.google.firebase.BuildConfig;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.levelup.colorreader.BuildConfig;
import io.michaelrocks.paranoid.Obfuscate;

/*
This object is used to handle all analytics in Firebase RTDB.
Whatever additional attribute is needed, add it to this class to get it reflected on Firebase RTDB
 */

@Keep
@Obfuscate
@IgnoreExtraProperties
public class RtdbUser implements Serializable {

    @SerializedName("email")
    private String email;

    @SerializedName("deviceID")
    private String deviceID;

    @SerializedName("appDetails")
    private AppDetails appDetails;

    @SerializedName("deviceDetails")
    private DeviceDetails deviceDetails;

    @SerializedName("user_stats")
    private User_stats user_stats;

    public RtdbUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public RtdbUser(String email, String deviceID) {

        this.email = email;
        this.deviceID = deviceID;
        this.user_stats = new User_stats();
        this.appDetails = new AppDetails();
        this.deviceDetails = new DeviceDetails();
    }

    public User_stats getUser_stats(){

        if(this.user_stats == null)
        {
            this.user_stats = new User_stats();
        }

        return this.user_stats;
    }

    public void setUser_stats(User_stats user_stats){
        this.user_stats=user_stats;
    }

    public void incrementTotal_shared_to_vision_obj_reqs() {

        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_shared_to_vision_obj_reqs();
    }

    public void incrementTotal_article_open_errors(){

        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_article_open_errors();
    }

    public void incrementTotal_article_open_reqs(){

        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_article_open_reqs();
    }

    public void incrementTotal_news_list_reqs(){

        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_news_list_reqs();
    }

    public void incrementTotal_shared_to_vision_read_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_shared_to_vision_read_reqs();
    }

    public void incrementTotal_aroundme_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_aroundme_reqs();
    }

    public void incrementTotal_see_object_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_see_object_reqs();
    }

    public void incrementTotal_read_text_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_read_text_reqs();
    }

    public void incrementTotal_whereami_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_whereami_reqs();
    }

    public void incrementTotal_adv_ocr_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_adv_ocr_reqs();
    }

    public void incrementTotal_pdf_plus_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_pdf_plus_reqs();
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppDetails getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }

    public DeviceDetails getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }
}

@Keep
@Obfuscate
@IgnoreExtraProperties
class AppDetails implements Serializable{

    @SerializedName("versionName")
    private String versionName;

    @SerializedName("versionCode")
    private int versionCode;

    public AppDetails(){

        versionCode = BuildConfig.VERSION_CODE;
        versionName = BuildConfig.VERSION_NAME;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}

@Keep
@Obfuscate
@IgnoreExtraProperties
class DeviceDetails implements Serializable{

    @SerializedName("androidVersionCode")
    private int androidVersionCode;

    @SerializedName("phoneManufacturer")
    private String phoneManufacturer;

    @SerializedName("phoneModel")
    private String phoneModel;

    public DeviceDetails(){

        androidVersionCode = Build.VERSION.SDK_INT;
        phoneManufacturer = Build.MANUFACTURER;
        phoneModel = Build.MODEL;
    }

    public int getAndroidVersionCode() {
        return androidVersionCode;
    }

    public void setAndroidVersionCode(int androidVersionCode) {
        this.androidVersionCode = androidVersionCode;
    }

    public String getPhoneManufacturer() {
        return phoneManufacturer;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneManufacturer(String phoneManufacturer) {
        this.phoneManufacturer = phoneManufacturer;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }
}