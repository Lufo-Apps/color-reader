package in.levelup.colorreader.database;

import android.provider.BaseColumns;

import java.io.Serializable;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class pdfMetaData implements Serializable {

    public String pdfName, pdfPath, dateOpened, totalPages, lastPage;

    //7.12.2021: changed to DB v10, pdf uri for storage access framework
    public String pdfUri;

    public String getPdfUri() {
        return pdfUri;
    }

    public void setPdfUri(String pdfUri) {
        this.pdfUri = pdfUri;
    }


    public pdfMetaData() {
    }

    public pdfMetaData(String pdfName, String pdfPath, String dateOpened, String totalPages, String lastPage) {
        this.pdfName = pdfName;
        this.pdfPath = pdfPath;
        this.dateOpened = dateOpened;
        this.totalPages = totalPages;
        this.lastPage = lastPage;
    }

    public pdfMetaData(String pdfName, String pdfPath, String dateOpened, String totalPages, String lastPage,String pdfUri) {
        this.pdfName = pdfName;
        this.pdfPath = pdfPath;
        this.pdfUri = pdfUri;
        this.dateOpened = dateOpened;
        this.totalPages = totalPages;
        this.lastPage = lastPage;
    }

    public String getPdfName() {
        return pdfName;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public String getDateOpened() {
        return dateOpened;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public void setDateOpened(String dateOpened) {
        this.dateOpened = dateOpened;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }
    @Obfuscate
    public static class pdfMetaDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "pdfMetaData";
        public static final String COLUMN_NAME_PDF_NAME = "pdf_name";
        public static final String COLUMN_NAME_PDF_PATH = "pdf_path";
        public static final String COLUMN_NAME_TOTAL_PAGES = "total_pages";
        public static final String COLUMN_NAME_DATE_OPENED = "date";
        public static final String COLUMN_NAME_LAST_PAGE = "last_page";
        public static final String COLUMN_NAME_PDF_URI = "pdf_uri";
    }


    public static final String SQL_CREATE_PDF_META_DATA_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + pdfMetaDataEntry.TABLE_NAME + " (" +
                    pdfMetaDataEntry.COLUMN_NAME_PDF_NAME + " TEXT," +
                    pdfMetaDataEntry.COLUMN_NAME_PDF_PATH + " TEXT PRIMARY KEY," +
                    pdfMetaDataEntry.COLUMN_NAME_TOTAL_PAGES + " TEXT," +
                    pdfMetaDataEntry.COLUMN_NAME_LAST_PAGE + " TEXT," +
                    pdfMetaDataEntry.COLUMN_NAME_DATE_OPENED + " TEXT," +
                    pdfMetaDataEntry.COLUMN_NAME_PDF_URI + " TEXT" + ")";
}

/*
    public static final String SQL_CREATE_PDF_META_DATA_ENTRIES =
            "CREATE TABLE " + pdfMetaDataEntry .TABLE_NAME + " (" +
                    pdfMetaDataEntry.COLUMN_NAME_PDF_NAME + " TEXT," +
                    pdfMetaDataEntry.COLUMN_NAME_PDF_PATH + " TEXT PRIMARY KEY," +
                    pdfMetaDataEntry.COLUMN_NAME_TOTAL_PAGES + " TEXT," +
                    pdfMetaDataEntry.COLUMN_NAME_LAST_PAGE + " TEXT," +
                    pdfMetaDataEntry.COLUMN_NAME_DATE_OPENED + " TEXT"+")";*/