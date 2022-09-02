package in.levelup.colorreader.database;

import com.google.gson.annotations.SerializedName;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class Sec_details {

    @SerializedName("crtfct_b64")
    private String crtfct_b64;

    @SerializedName("lcnse_lvl")
    private String lcnse_lvl;

    @SerializedName("lcnse_js")
    private String lcnse_js;

    @SerializedName("lcnse_gm")
    private String lcnse_gm;

    @SerializedName("rt_check")
    private String rt_check;

    @SerializedName("frm_ply_str")
    private String frm_ply_str;

    @SerializedName("prt_app_instlld")
    private String prt_app_instlld;

    @SerializedName("crtfct_vld")
    private String crtfct_vld;

    @SerializedName("is_bulk_license")
    private boolean is_bulk_license;

    public Sec_details(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public String getCrtfct_b64() {
        return crtfct_b64;
    }

    public void setCrtfct_b64(String crtfct_b64) {
        this.crtfct_b64 = crtfct_b64;
    }

    public String getLcnse_lvl() {
        return lcnse_lvl;
    }

    public void setLcnse_lvl(String lcnse_lvl) {
        this.lcnse_lvl = lcnse_lvl;
    }

    public String getLcnse_js() {
        return lcnse_js;
    }

    public void setLcnse_js(String lcnse_js) {
        this.lcnse_js = lcnse_js;
    }

    public void setLcnse_gm(String lcnse_gm) {
        this.lcnse_gm = lcnse_gm;
    }

    public String getRt_check() {
        return rt_check;
    }

    public void setRt_check(String rt_check) {
        this.rt_check = rt_check;
    }

    public String getFrm_ply_str() {
        return frm_ply_str;
    }

    public void setFrm_ply_str(String frm_ply_str) {
        this.frm_ply_str = frm_ply_str;
    }

    public String getPrt_app_instlld() {
        return prt_app_instlld;
    }

    public void setPrt_app_instlld(String prt_app_instlld) {
        this.prt_app_instlld = prt_app_instlld;
    }

    public String getCrtfct_vld() {
        return crtfct_vld;
    }

    public void setCrtfct_vld(String crtfct_vld) {
        this.crtfct_vld = crtfct_vld;
    }
}
