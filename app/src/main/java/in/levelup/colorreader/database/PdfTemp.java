package in.levelup.colorreader.database;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class PdfTemp {
    int Page;
    int currentPage;
    int tempCurrentPage;

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTempCurrentPage() {
        return tempCurrentPage;
    }

    public void setTempCurrentPage(int tempCurrentPage) {
        this.tempCurrentPage = tempCurrentPage;
    }
}
