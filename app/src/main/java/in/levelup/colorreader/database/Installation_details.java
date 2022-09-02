package in.levelup.colorreader.database;

import com.google.gson.annotations.SerializedName;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class Installation_details {

    @SerializedName("app_version_name")
    private String app_version_name;

    @SerializedName("app_version_code")
    private int app_version_code;

    @SerializedName("package_name")
    private String package_name;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("phone_manufacturer")
    private String phone_manufacturer;

    @SerializedName("phone_model")
    private String phone_model;

    @SerializedName("android_os_version")
    private int android_os_version;

    @SerializedName("bulk_sales_code")
    private String bulk_sales_code;

    @SerializedName("bulk_sales_license_id")
    private String bulk_sales_license_id;


    public Installation_details(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getApp_version_name() {
        return app_version_name;
    }

    public void setApp_version_name(String app_version_name) {
        this.app_version_name = app_version_name;
    }

    public int getApp_version_code() {
        return app_version_code;
    }

    public void setApp_version_code(int app_version_code) {
        this.app_version_code = app_version_code;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPhone_manufacturer() {
        return phone_manufacturer;
    }

    public void setPhone_manufacturer(String phone_manufacturer) {
        this.phone_manufacturer = phone_manufacturer;
    }

    public String getPhone_model() {
        return phone_model;
    }

    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }

    public int getAndroid_os_version() {
        return android_os_version;
    }

    public void setAndroid_os_version(int android_os_version) {
        this.android_os_version = android_os_version;
    }
}
