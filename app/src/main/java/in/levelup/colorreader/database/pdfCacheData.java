package in.levelup.colorreader.database;

import android.provider.BaseColumns;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class pdfCacheData {

    public String pdfName, pdfUri, pageData;
    public int pageNumber;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String uri;

    public pdfCacheData(String pdfName, String pdfUri, String pageData, int pageNumber, String uri) {
        this.pdfName = pdfName;
        this.pdfUri = pdfUri;
        this.pageData = pageData;
        this.pageNumber = pageNumber;
        this.uri = uri;
    }

    public pdfCacheData(String pdfName, String pdfUri, String pageData, int pageNumber) {
        this.pdfName = pdfName;
        this.pdfUri = pdfUri;
        this.pageData = pageData;
        this.pageNumber = pageNumber;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfUri() {
        return pdfUri;
    }

    public void setPdfUri(String pdfUri) {
        this.pdfUri = pdfUri;
    }

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    @Obfuscate
    public static class pdfCacheDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "pdfCacheData";


        public static final String COLUMN_NAME_PDF_NAME = "pdf_name";
        public static final String COLUMN_NAME_PDF_URI = "pdf_uri";
        public static final String COLUMN_NAME_PAGE_NUMBER = "page_number";
        public static final String COLUMN_NAME_PAGE_DATA = "text";
    }


    public static final String SQL_CREATE_PDF_CACHE_DATA_ENTRIES = "create table IF NOT EXISTS "
            + pdfCacheDataEntry.TABLE_NAME + " (" +
            pdfCacheDataEntry.COLUMN_NAME_PDF_NAME + " TEXT," +
            pdfCacheDataEntry.COLUMN_NAME_PDF_URI + " TEXT," +
            pdfCacheDataEntry.COLUMN_NAME_PAGE_NUMBER + " INTEGER," +
            pdfCacheDataEntry.COLUMN_NAME_PAGE_DATA + " TEXT,PRIMARY KEY(" + pdfCacheDataEntry.COLUMN_NAME_PDF_URI + "," + pdfCacheDataEntry.COLUMN_NAME_PAGE_NUMBER + "))";
    /*
    * "CREATE TABLE IF NOT EXISTS " + TABLE_ATTENDEE +
" (" + COLUMN_Att_Event_ID + " TEXT," +
COLUMN_Att_Email + " TEXT, PRIMARY KEY(" + COLUMN_Att_Event_ID + "," + COLUMN_Att_Email + ")
    * */

}

/*
*   public static final String SQL_CREATE_PDF_CACHE_DATA_ENTRIES = "create table "
            + pdfCacheDataEntry.TABLE_NAME + " (" +
            pdfCacheDataEntry.COLUMN_NAME_PDF_NAME + " TEXT," +
            pdfCacheDataEntry.COLUMN_NAME_PDF_PATH + " TEXT," +
            pdfCacheDataEntry.COLUMN_NAME_PAGE_NUMBER + " INTEGER," +
            pdfCacheDataEntry.COLUMN_NAME_PAGE_DATA+" TEXT,PRIMARY KEY("+pdfCacheDataEntry.COLUMN_NAME_PDF_PATH+","+pdfCacheDataEntry.COLUMN_NAME_PAGE_NUMBER+"))";
*
* */
