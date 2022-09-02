package in.levelup.colorreader.Analytics;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

//import io.michaelrocks.paranoid.Obfuscate;

//@Obfuscate
public class FirebaseAnalyticsHelper {

    public static FirebaseAnalytics mFirebaseAnalytics;

    //GENERAL
    public static final String EVENT_PARAM_ACTIVITY = "activity";


    //PDF READER PLUS
    public static final int PDF_RDR_PLUS_PDF_OPENED_CODE = 101;
    public static final String PDF_RDR_PLUS_PDF_OPENED_EVENT_NAME = "pdf_rdr_plus_pdf_opened";
    public static final String PDF_RDR_PLUS_PDF_OPENED_EVENT_PARAM_TOT_PAGES = "pdf_tot_pages";
    public static final int PDF_RDR_PLUS_SCAN_SINGLE_CODE = 102;
    public static final String PDF_RDR_PLUS_SCAN_SINGLE_EVENT_NAME = "pdf_rdr_plus_scan_single";
    public static final int PDF_RDR_PLUS_SESSION_EXITED_CODE = 103;
    public static final String PDF_RDR_PLUS_SESSION_EXITED_EVENT_NAME = "pdf_rdr_plus_session_exited";
    public static final String PDF_RDR_PLUS_SESSION_EXITED_EVENT_PARAM_TOT_PAGES = "session_tot_pages";

    //PDF READER
    public static final int PDF_RDR_PDF_OPENED_CODE = 111;
    public static final String PDF_RDR_PDF_OPENED_EVENT_NAME = "pdf_rdr_pdf_opened";
    public static final String PDF_RDR_PDF_OPENED_EVENT_PARAM_TOT_PAGES = "pdf_tot_pages";
    public static final int PDF_RDR_SCAN_SINGLE_CODE = 112;
    public static final String PDF_RDR_SCAN_SINGLE_EVENT_NAME = "pdf_rdr_scan_single";
    public static final int PDF_RDR_SESSION_EXITED_CODE = 113;
    public static final String PDF_RDR_SESSION_EXITED_EVENT_NAME = "pdf_rdr_session_exited";
    public static final String PDF_RDR_SESSION_EXITED_EVENT_PARAM_TOT_PAGES = "session_tot_pages_new_uncached";

    //ADVANCED OCR
    public static final int ADV_OCR_SCAN_SINGLE_CODE = 201;
    public static final String ADV_OCR_SCAN_SINGLE_EVENT_NAME = "adv_ocr_scan_single";

    //ADVANCED OCR
    public static final int SEE_OBJECT_SCAN_SINGLE_CODE = 301;
    public static final String SEE_OBJECT_SCAN_SINGLE_EVENT_NAME = "see_object_scan_single";

    //ADVANCED OCR
    public static final int READ_TEXT_SCAN_SINGLE_CODE = 401;
    public static final String READ_TEXT_SCAN_SINGLE_EVENT_NAME = "read_text_scan_single";

    //WHERE AMI
    public static final int WHEREAMI_SINGLE_CODE = 501;
    public static final String WHEREAMI_SINGLE_EVENT_NAME = "whereami_single";

    //AROUNDME
    public static final int AROUNDME_SINGLE_CODE = 601;
    public static final String AROUNDME_SINGLE_EVENT_NAME = "aroundme_single";

    //SHAREDTOVISION
    public static final int SHARED_TO_VISION_OBJ_SINGLE_CODE = 701;
    public static final String SHARED_TO_VISION_OBJ_SINGLE_EVENT_NAME = "shared_to_vision_obj_single";

    public static final int SHARED_TO_VISION_READ_SINGLE_CODE = 702;
    public static final String SHARED_TO_VISION_READ_SINGLE_EVENT_NAME = "shared_to_vision_read_single";

    public static void setFirebaseAnalytics(FirebaseAnalytics firebaseAnalytics){
        mFirebaseAnalytics = firebaseAnalytics;
    }

    public static void sendFirebaseAnalytics(int code, ArrayList<Object> metadataList){
        Bundle bundle = new Bundle();

        Log.e("Analytics", "Processing");

        switch (code){

            //PDF READER PLUS
            case PDF_RDR_PLUS_PDF_OPENED_CODE:
                int total_pages_pdf_file = (Integer) metadataList.get(0);
                bundle.putString(PDF_RDR_PLUS_PDF_OPENED_EVENT_PARAM_TOT_PAGES, Integer.toString(total_pages_pdf_file));
                bundle.putString(EVENT_PARAM_ACTIVITY, "PdfPlusOutputActivity");
                if(mFirebaseAnalytics==null){
                    Log.e("switch","NULL");
                }
                mFirebaseAnalytics.logEvent(PDF_RDR_PLUS_PDF_OPENED_EVENT_NAME, bundle);
                break;
            case PDF_RDR_PLUS_SCAN_SINGLE_CODE:

                bundle.putString(EVENT_PARAM_ACTIVITY, "PdfPlusHelper");
                mFirebaseAnalytics.logEvent(PDF_RDR_PLUS_SCAN_SINGLE_EVENT_NAME, bundle);

                Log.e("PDF Reader Analytics", "Called");

                break;
            case PDF_RDR_PLUS_SESSION_EXITED_CODE:
                total_pages_pdf_file = (Integer) metadataList.get(0);
                int session_tot_pages_new_uncached = (Integer) metadataList.get(1);
                bundle.putString(PDF_RDR_PLUS_PDF_OPENED_EVENT_PARAM_TOT_PAGES, Integer.toString(total_pages_pdf_file));
                bundle.putString(EVENT_PARAM_ACTIVITY, "PdfPlusOutputActivity");
                bundle.putString(PDF_RDR_PLUS_SESSION_EXITED_EVENT_PARAM_TOT_PAGES, Integer.toString(session_tot_pages_new_uncached));
                mFirebaseAnalytics.logEvent(PDF_RDR_PLUS_SESSION_EXITED_EVENT_NAME, bundle);
                break;

             //PDR READER
            case PDF_RDR_PDF_OPENED_CODE:
                total_pages_pdf_file = (Integer) metadataList.get(0);
                bundle.putString(PDF_RDR_PDF_OPENED_EVENT_PARAM_TOT_PAGES, Integer.toString(total_pages_pdf_file));
                bundle.putString(EVENT_PARAM_ACTIVITY, "PdfReaderOutputActivity");
                mFirebaseAnalytics.logEvent(PDF_RDR_PDF_OPENED_EVENT_NAME, bundle);
                break;
            case PDF_RDR_SCAN_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "PdfReaderOutputActivity");
                mFirebaseAnalytics.logEvent(PDF_RDR_SCAN_SINGLE_EVENT_NAME, bundle);
                break;
            case PDF_RDR_SESSION_EXITED_CODE:
                total_pages_pdf_file = (Integer) metadataList.get(0);
                bundle.putString(EVENT_PARAM_ACTIVITY, "PdfReaderOutputActivity");
                bundle.putString(PDF_RDR_SESSION_EXITED_EVENT_PARAM_TOT_PAGES, Integer.toString(total_pages_pdf_file));
                mFirebaseAnalytics.logEvent(PDF_RDR_SESSION_EXITED_EVENT_NAME, bundle);
                break;

            //ADVANCED OCR
            case ADV_OCR_SCAN_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "OcrCaptureActivity6");
                mFirebaseAnalytics.logEvent(ADV_OCR_SCAN_SINGLE_EVENT_NAME, bundle);

                Log.e("OCR Scan Analytics", "Called");

                break;

            //ADVANCED OCR
            case SEE_OBJECT_SCAN_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "VisionActivity5");
                mFirebaseAnalytics.logEvent(SEE_OBJECT_SCAN_SINGLE_EVENT_NAME, bundle);
                break;

            //ADVANCED OCR
            case READ_TEXT_SCAN_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "VisionActivity5");
                mFirebaseAnalytics.logEvent(READ_TEXT_SCAN_SINGLE_EVENT_NAME, bundle);

                Log.e("Read Text Analytics", "Called");

                break;

            //WHEREAMI
            case WHEREAMI_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "WhereAmiActivity3");
                mFirebaseAnalytics.logEvent(WHEREAMI_SINGLE_EVENT_NAME, bundle);
                break;

            //AROUND ME
            case AROUNDME_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "NearByPlacesActivity2");
                mFirebaseAnalytics.logEvent(AROUNDME_SINGLE_EVENT_NAME, bundle);
                break;

            //AROUND ME
            case SHARED_TO_VISION_OBJ_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "SharedToVisionActivity");
                mFirebaseAnalytics.logEvent(SHARED_TO_VISION_OBJ_SINGLE_EVENT_NAME, bundle);

                Log.e("Shared Object Analytics", "Called");

                break;

            //AROUND ME
            case SHARED_TO_VISION_READ_SINGLE_CODE:
                bundle.putString(EVENT_PARAM_ACTIVITY, "SharedToVisionActivity");
                mFirebaseAnalytics.logEvent(SHARED_TO_VISION_READ_SINGLE_EVENT_NAME, bundle);

                Log.e("Shared Read Analytics", "Called");

                break;
        }
    }
}
