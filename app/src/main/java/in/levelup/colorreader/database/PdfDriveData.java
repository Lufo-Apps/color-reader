package in.levelup.colorreader.database;

import android.provider.BaseColumns;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class PdfDriveData {
    public String pdfName, pdfPath, pageData;
    public int pageNumber;

    public PdfDriveData() {
    }

    public PdfDriveData(String pdfName, String pdfPath, String pageData, int pageNumber) {
        this.pdfName = pdfName;
        this.pdfPath = pdfPath;
        this.pageData = pageData;
        this.pageNumber = pageNumber;
    }


    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
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
    public static class PdfDriveDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "pdDriveCacheData";


        public static final String COLUMN_NAME_PDF_NAME = "pdf_name";
        public static final String COLUMN_NAME_PDF_PATH = "pdf_path";
        public static final String COLUMN_NAME_PAGE_NUMBER = "page_number";
        public static final String COLUMN_NAME_PAGE_DATA = "text";
    }

    public static final String SQL_CREATE_PDF_DRIVE_CACHE_DATA_ENTRIES = "create table IF NOT EXISTS "
            + PdfDriveDataEntry.TABLE_NAME + " (" +
            PdfDriveDataEntry.COLUMN_NAME_PDF_NAME + " TEXT," +
            PdfDriveDataEntry.COLUMN_NAME_PDF_PATH + " TEXT," +
            PdfDriveDataEntry.COLUMN_NAME_PAGE_NUMBER + " INTEGER," +
            PdfDriveDataEntry.COLUMN_NAME_PAGE_DATA + " TEXT,PRIMARY KEY(" + PdfDriveDataEntry.COLUMN_NAME_PDF_PATH + "," + PdfDriveDataEntry.COLUMN_NAME_PAGE_NUMBER + "))";
    /*
    * "CREATE TABLE IF NOT EXISTS " + TABLE_ATTENDEE +
" (" + COLUMN_Att_Event_ID + " TEXT," +
COLUMN_Att_Email + " TEXT, PRIMARY KEY(" + COLUMN_Att_Event_ID + "," + COLUMN_Att_Email + ")
    * */


}
