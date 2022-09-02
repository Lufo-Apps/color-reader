package in.levelup.colorreader.database;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class User_stats implements Serializable {

    @SerializedName("total_see_object_reqs")
    private int total_see_object_reqs;
    @SerializedName("total_pdf_plus_reqs")
    private int total_pdf_plus_reqs;
    @SerializedName("total_adv_ocr_reqs")
    private int total_adv_ocr_reqs;
    @SerializedName("total_read_text_reqs")
    private int total_read_text_reqs;
    @SerializedName("total_whereami_reqs")
    private int total_whereami_reqs;
    @SerializedName("total_aroundme_reqs")
    private int total_aroundme_reqs;
    @SerializedName("total_shared_to_vision_obj_reqs")
    private int total_shared_to_vision_obj_reqs;
    @SerializedName("total_shared_to_vision_read_reqs")
    private int total_shared_to_vision_read_reqs;
    @SerializedName("total_article_open_reqs")
    private int total_article_open_reqs;
    @SerializedName("total_news_list_reqs")
    private int total_news_list_reqs;
    @SerializedName("total_article_open_errors")
    private int total_article_open_errors;

    public User_stats() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    private void initAllStatZero(){
        this.total_see_object_reqs=0;
        this.total_pdf_plus_reqs=0;
        this.total_adv_ocr_reqs=0;
        this.total_read_text_reqs=0;
        this.total_whereami_reqs=0;
        this.total_aroundme_reqs=0;
        this.total_news_list_reqs = 0;
        this.total_article_open_errors = 0;
        this.total_article_open_reqs = 0;
    }


    public void incrementTotal_adv_ocr_reqs(){
        this.total_adv_ocr_reqs++;
    }

    public void incrementTotal_pdf_plus_reqs(){
        this.total_pdf_plus_reqs++;
    }

    public void incrementTotal_see_object_reqs(){
        this.total_see_object_reqs++;
    }

    public void incrementTotal_read_text_reqs(){
        this.total_read_text_reqs++;
    }

    public void incrementTotal_whereami_reqs(){
        this.total_whereami_reqs++;
    }

    public void incrementTotal_aroundme_reqs(){
        this.total_aroundme_reqs++;
    }

    public void incrementTotal_shared_to_vision_obj_reqs(){
        this.total_shared_to_vision_obj_reqs++;
    }

    public void incrementTotal_shared_to_vision_read_reqs(){
        this.total_shared_to_vision_read_reqs++;
    }

    public void incrementTotal_article_open_errors(){
        this.total_article_open_errors++;
    }

    public void incrementTotal_article_open_reqs(){
        this.total_article_open_reqs++;
    }

    public void incrementTotal_news_list_reqs(){
        this.total_news_list_reqs++;
    }

    public int getTotal_news_list_reqs() {
        return total_news_list_reqs;
    }

    public int getTotal_article_open_reqs() {
        return total_article_open_reqs;
    }

    public int getTotal_article_open_errors() {
        return total_article_open_errors;
    }

    public int getTotal_adv_ocr_reqs() {
        return total_adv_ocr_reqs;
    }

    public void setTotal_adv_ocr_reqs(int total_adv_ocr_reqs) {
        this.total_adv_ocr_reqs = total_adv_ocr_reqs;
    }


    public int getTotal_read_text_reqs() {
        return total_read_text_reqs;
    }

    public void setTotal_read_text_reqs(int total_read_text_reqs) {
        this.total_read_text_reqs = total_read_text_reqs;
    }


    public int getTotal_see_object_reqs() {
        return total_see_object_reqs;
    }

    public void setTotal_see_object_reqs(int total_see_object_reqs) {
        this.total_see_object_reqs = total_see_object_reqs;
    }

    public int getTotal_pdf_plus_reqs() {
        return total_pdf_plus_reqs;
    }

    public void setTotal_pdf_plus_reqs(int total_pdf_plus_reqs) {
        this.total_pdf_plus_reqs = total_pdf_plus_reqs;
    }

    public int getTotal_whereami_reqs() {
        return total_whereami_reqs;
    }

    public void setTotal_whereami_reqs(int total_whereami_reqs) {
        this.total_whereami_reqs = total_whereami_reqs;
    }

    public int getTotal_aroundme_reqs() {
        return total_aroundme_reqs;
    }

    public void setTotal_aroundme_reqs(int total_aroundme_reqs) {
        this.total_aroundme_reqs = total_aroundme_reqs;
    }

    public int getTotal_shared_to_vision_obj_reqs() {
        return total_shared_to_vision_obj_reqs;
    }

    public void setTotal_shared_to_vision_obj_reqs(int total_shared_to_vision_obj_reqs) {
        this.total_shared_to_vision_obj_reqs = total_shared_to_vision_obj_reqs;
    }

    public int getTotal_shared_to_vision_read_reqs() {
        return total_shared_to_vision_read_reqs;
    }

    public void setTotal_shared_to_vision_read_reqs(int total_shared_to_vision_read_reqs) {
        this.total_shared_to_vision_read_reqs = total_shared_to_vision_read_reqs;
    }

    public void setTotal_article_open_errors(int total_article_open_errors) {
        this.total_article_open_errors = total_article_open_errors;
    }

    public void setTotal_article_open_reqs(int total_article_open_reqs) {
        this.total_article_open_reqs = total_article_open_reqs;
    }

    public void setTotal_news_list_reqs(int total_news_list_reqs) {
        this.total_news_list_reqs = total_news_list_reqs;
    }
}