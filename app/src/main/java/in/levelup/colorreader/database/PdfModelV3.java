package in.levelup.colorreader.database;

import java.util.List;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class PdfModelV3 {

    private String pdfName, pdfPath;
    private List<String> pageDataList;
    private int totalPages;
    private int executedTillPage;
    private String langCode;

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

    public List<String> getPageDataList() {
        return pageDataList;
    }

    public void setPageDataList(List<String> pageDataList) {
        this.pageDataList = pageDataList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getExecutedTillPage() {
        return executedTillPage;
    }

    public void setExecutedTillPage(int executedTillPage) {
        this.executedTillPage = executedTillPage;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public PdfModelV3() {
    }
}
