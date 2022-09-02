package in.levelup.colorreader.database;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.michaelrocks.paranoid.Obfuscate;

/**
 * Use RtdbUser for any reference to user model
 */

@Deprecated
@Obfuscate
@IgnoreExtraProperties
public class User {

    @SerializedName("email")
    private String email;

    @SerializedName("deviceID")
    private String deviceID;

    @SerializedName("user_stats")
    private User_stats user_stats;

    @SerializedName("purchased_modules")
    public ArrayList<String> purchased_modules;

    @SerializedName("installation_details")
    private Installation_details installation_details;

    @SerializedName("sec_details")
    private Sec_details sec_details;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String deviceID) {
        this.email = email;
        this.deviceID = deviceID;
        this.user_stats = new User_stats();
        this.installation_details = new Installation_details();
        this.sec_details = new Sec_details();
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

    public void addPurchasedModule(String item_name) {

        if(purchased_modules==null || purchased_modules.size()==0){
            purchased_modules = new ArrayList<String>();
            purchased_modules.add(item_name);
        }
        else {
            if(!purchased_modules.contains(item_name)){
                purchased_modules.add(item_name);
            }
        }
    }

    public void set_crtfct_b64(String resIndex) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setCrtfct_b64(resIndex);
    }

    public void set_crtfct_vld(String status) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setCrtfct_vld(status);
    }

    public void set_lcnse_js(String status) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setLcnse_js(status);
    }

    public void set_lcnse_gm(String status) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setLcnse_gm(status);
    }

    public void set_frm_ply_str(String status) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setFrm_ply_str(status);
    }

    public void set_rt_check(String status) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setRt_check(status);
    }

    public void set_prt_app_instlld(String status) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setPrt_app_instlld(status);
    }

    public void set_lcnse_lvl(String status) {
        if(sec_details==null){
            sec_details = new Sec_details();
        }
        sec_details.setLcnse_lvl(status);
    }

    public Sec_details getSecDetails() {
        if(this.sec_details==null){
            this.sec_details=new Sec_details();
        }
        return this.sec_details;
    }

    public Installation_details getInstallation_details() {
        if(this.installation_details==null){
            this.installation_details=new Installation_details();
        }
        return this.installation_details;
    }

    public void updateInstallationDetails(String app_version_name, int app_version_code, String package_name,
                                          String device_id, String phone_manufacturer, String phone_model,
                                          int android_os_version) {
        if(this.installation_details==null){
            this.installation_details=new Installation_details();
        }

        installation_details.setApp_version_name(app_version_name);
        installation_details.setApp_version_code(app_version_code);
        installation_details.setPackage_name(package_name);
        installation_details.setDevice_id(device_id);
        installation_details.setPhone_manufacturer(phone_manufacturer);
        installation_details.setPhone_model(phone_model);
        installation_details.setAndroid_os_version(android_os_version);
    }

    public void incrementTotal_shared_to_vision_obj_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_shared_to_vision_obj_reqs();
    }

    public void incrementTotal_shared_to_vision_read_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_shared_to_vision_read_reqs();
    }

    /*public void incrementTotal_pdf_reader_reqs() {
        if(this.user_stats==null){
            this.user_stats = new User_stats();
        }
        this.user_stats.incrementTotal_pdf_reader_reqs();
    }*/

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
}