package in.levelup.colorreader;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import in.levelup.colorreader.util.Constants;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ColorReadingActivity";
    private static final int ONE_PLUS_CENTER_X = 960;
    private static final int ONE_PLUS_CENTER_Y = 540;
    public static boolean gzzv;
    private Camera mCamera;
    private int Storage_permission_code = 1;
    private static Bitmap bitmap;
    App app;
    private Tracker mTracker;
    private SurfaceView preview = null;
    private SurfaceHolder previewHolder = null;
    private boolean inPreview = false;
    private String ActivityType = "dot";
    private ImageView imageView, myImageView2;
    private boolean mIsColorSelected = false;
    private Scalar mBlobColorRgba;
    private Scalar CONTOUR_COLOR;
    private int framskip;

    //UserSettings mUserSettings;
    TextView lumaTextView;
    Handler mHandler;
    List<Triplet<Integer, Integer, Integer, String>> colorList;
    private TextToSpeech tts;
    private boolean isActivityPaused = false;
    HashMap<String, String> map = new HashMap<String, String>();
    String colprev = "";
    LinearLayout bottomLinLayout, actColorLinLayout, mtchColorLinLayout;
    ColorClass curColorObj = new ColorClass();
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_reading);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        app = (App) getApplication();
        //mUserSettings=new UserSettings(PreferenceManager.getDefaultSharedPreferences(this));
        imageView = findViewById(R.id.myImageView);
        myImageView2 = findViewById(R.id.myImageView2);
        createToast(getResources().getString(R.string.message_color_reader_init));
        Log.d(TAG, "Initialized");
        lumaTextView = findViewById(R.id.luma_textview);
        bottomLinLayout = findViewById(R.id.bottomLinLayout);
        actColorLinLayout = findViewById(R.id.actColorLinLayout);
        mtchColorLinLayout = findViewById(R.id.mtchColorLinLayout);
        mTracker = app.getDefaultTracker();
        sendAnalytics("Color Reading");
        if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            //performAction(...);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            //showInContextUI(...);
            // Toast.makeText(app, "Success", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    Storage_permission_code);
            //prepareCamera();
        } else {
            // You can directly ask for the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    Storage_permission_code);
        }
        prepareCamera();
        CONTOUR_COLOR = new Scalar(255, 0, 0, 255);
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                ColorClass colnew = (ColorClass) inputMessage.obj;
                if (!colnew.getApproxColorName().matches(colprev)) {
                    colprev = colnew.getApproxColorName();
                    lumaTextView.setText("Matched:" + colprev);
                    actColorLinLayout.setBackgroundColor(Color.parseColor(colnew.getActualColorHexCode()));
                    mtchColorLinLayout.setBackgroundColor(Color.parseColor(colnew.getApproxColorHexCode()));
                    tts.speak(colprev, TextToSpeech.QUEUE_FLUSH, map);
                }
            }
        };

        setColorMap();
        startTTS();
    }

    private void startTTS() {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status != TextToSpeech.ERROR) {
                    Locale locale = Locale.getDefault();
                    tts.setLanguage(locale);
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, Constants.TTS_ID);
                    tts.speak(getResources().getString(R.string.market_item_color_reader),
                            TextToSpeech.QUEUE_FLUSH, map);
                    Log.d(TAG, "onInit: Page load complete. Starting first time...");
                }
            }
        }
        );

        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                Log.d(TAG, "setOnUtteranceProgressListener: TTS start called..");
            }

            @Override
            public void onDone(String utteranceId) {
                if (!isActivityPaused) {
                    Log.d(TAG, "setOnUtteranceProgressListener: onDone called");
                    tts.playSilence(3000, TextToSpeech.QUEUE_ADD, null);
                    tts.speak(colprev, TextToSpeech.QUEUE_ADD, map);
                }
            }

            @Override
            public void onError(String utteranceId) {
                Log.d(TAG, "setOnUtteranceProgressListener: TTS error.");
            }

            //Below works only on API 23 and above.
            @Override
            public void onStop(String utteranceId, boolean interrupted) {
                if (interrupted == true) {
                    Log.d(TAG, "setOnUtteranceProgressListener: TTS interrupted.");
                }
            }
        });
    }

    private void setColorMap() {
        colorList = new ArrayList<>();

        colorList.add(0, new Triplet(148, 0, 211, getString(R.string.color_Violet)));
        colorList.add(1, new Triplet(75, 0, 130, getString(R.string.color_Indigo)));
        colorList.add(2, new Triplet(0, 0, 255, getString(R.string.color_Blue)));
        colorList.add(3, new Triplet(0, 255, 0, getString(R.string.color_Green)));
        colorList.add(4, new Triplet(255, 255, 0, getString(R.string.color_Yellow)));
        colorList.add(5, new Triplet(255, 127, 0, getString(R.string.color_Orange)));
        colorList.add(6, new Triplet(220, 20, 60, getString(R.string.color_Red)));

        //More major colors
        colorList.add(7, new Triplet(255, 255, 255, getString(R.string.color_White)));
        colorList.add(8, new Triplet(0, 0, 0, getString(R.string.color_Black)));
        colorList.add(9, new Triplet(128, 128, 128, getString(R.string.color_Gray)));
        colorList.add(10, new Triplet(165, 42, 42, getString(R.string.color_Brown)));


        //shades of white
        colorList.add(11, new Triplet(238, 238, 238, getString(R.string.color_White)));
        colorList.add(12, new Triplet(240, 240, 240, getString(R.string.color_White)));
        colorList.add(13, new Triplet(242, 242, 242, getString(R.string.color_White)));
        colorList.add(14, new Triplet(245, 245, 245, getString(R.string.color_White)));
        colorList.add(15, new Triplet(247, 247, 247, getString(R.string.color_White)));
        colorList.add(16, new Triplet(250, 250, 250, getString(R.string.color_White)));
        colorList.add(17, new Triplet(252, 252, 252, getString(R.string.color_White)));

        //shades of gray
        colorList.add(18, new Triplet(211, 211, 211, getString(R.string.light_color_Gray)));
        colorList.add(19, new Triplet(119, 136, 153, getString(R.string.color_Gray)));
        colorList.add(20, new Triplet(193, 205, 205, getString(R.string.color_Gray)));
        colorList.add(21, new Triplet(94, 94, 94, getString(R.string.color_Gray)));
        colorList.add(22, new Triplet(120, 120, 120, getString(R.string.color_Gray)));
        colorList.add(23, new Triplet(173, 173, 173, getString(R.string.color_Gray)));
        colorList.add(24, new Triplet(191, 191, 191, getString(R.string.color_Gray)));
        colorList.add(25, new Triplet(173, 173, 173, getString(R.string.color_Gray)));

        //shades of Green
        colorList.add(26, new Triplet(127, 255, 212, getString(R.string.color_Green)));
        colorList.add(27, new Triplet(127, 255, 0, getString(R.string.color_Green)));
        colorList.add(28, new Triplet(179, 238, 58, getString(R.string.color_Green)));
        colorList.add(29, new Triplet(109, 125, 71, getString(R.string.color_Green)));
        colorList.add(30, new Triplet(47, 79, 47, getString(R.string.color_Green)));
        colorList.add(31, new Triplet(34, 139, 34, getString(R.string.color_Green)));
        colorList.add(32, new Triplet(50, 205, 50, getString(R.string.color_Green)));
        colorList.add(33, new Triplet(127, 255, 0, getString(R.string.color_Green)));
        colorList.add(34, new Triplet(102, 205, 0, getString(R.string.color_Green)));

        // shades of blue
        colorList.add(35, new Triplet(135, 206, 235, getString(R.string.color_SkyBlue)));
        colorList.add(36, new Triplet(67, 110, 238, getString(R.string.color_Blue)));
        colorList.add(37, new Triplet(31, 74, 64, getString(R.string.color_Blue)));
        colorList.add(38, new Triplet(0, 255, 255, getString(R.string.color_Cyan)));
        // colorList.add(39, new Triplet(0, 128, 128, getString(R.string.color_seaGreen)));
        colorList.add(39, new Triplet(0, 0, 128, getString(R.string.color_navyBlue)));

        //shades of yellow
        colorList.add(40, new Triplet(255, 246, 143, getString(R.string.color_Yellow)));
        colorList.add(41, new Triplet(255, 255, 0, getString(R.string.color_Yellow)));
        colorList.add(42, new Triplet(238, 238, 0, getString(R.string.color_Yellow)));
        colorList.add(43, new Triplet(205, 205, 0, getString(R.string.color_Yellow)));
        colorList.add(44, new Triplet(139, 139, 0, getString(R.string.color_Olive)));

        colorList.add(45, new Triplet(255, 215, 0, getString(R.string.color_Golden)));
        colorList.add(46, new Triplet(238, 201, 0, getString(R.string.color_Golden)));
        colorList.add(47, new Triplet(205, 173, 0, getString(R.string.color_Golden)));
        colorList.add(48, new Triplet(218, 165, 32, getString(R.string.color_Brown)));
        colorList.add(49, new Triplet(199, 97, 20, getString(R.string.color_Brown)));
        colorList.add(50, new Triplet(67, 35, 5, getString(R.string.color_Brown)));
        colorList.add(51, new Triplet(128, 0, 0, getString(R.string.color_Maroon)));

        //shades of violet
        colorList.add(52, new Triplet(255, 0, 255, getString(R.string.color_Magenta)));
        colorList.add(53, new Triplet(255, 192, 203, getString(R.string.color_Pink)));
        colorList.add(54, new Triplet(128, 0, 128, getString(R.string.color_Purple)));
        colorList.add(55, new Triplet(221, 160, 221, getString(R.string.color_Violet)));
        colorList.add(56, new Triplet(255, 187, 255, getString(R.string.color_Violet)));
        colorList.add(57, new Triplet(204, 50, 153, getString(R.string.color_Violet)));

        // shades of Orange
        colorList.add(58, new Triplet(237, 145, 33, getString(R.string.color_Orange)));
        colorList.add(59, new Triplet(168, 121, 85, getString(R.string.color_Brown)));
        colorList.add(60, new Triplet(255, 140, 0, getString(R.string.color_Orange)));
        colorList.add(61, new Triplet(238, 118, 0, getString(R.string.color_Orange)));
        colorList.add(62, new Triplet(255, 127, 0, getString(R.string.color_Orange)));
        colorList.add(63, new Triplet(205, 233, 0, getString(R.string.color_Yellow)));

        //shades of Red
        colorList.add(64, new Triplet(238, 59, 59, getString(R.string.color_Red)));
        colorList.add(65, new Triplet(139, 0, 0, getString(R.string.color_Red)));  // dark red
        colorList.add(66, new Triplet(178, 34, 34, getString(R.string.color_Red)));// firebrick
        colorList.add(66, new Triplet(220, 20, 60, getString(R.string.color_Red)));// crimson
        colorList.add(67, new Triplet(255, 0, 0, getString(R.string.color_Red))); //red
        colorList.add(68, new Triplet(255, 99, 71, getString(R.string.color_Red))); //tomato
        colorList.add(69, new Triplet(255, 127, 80, getString(R.string.color_Red))); //coral
        colorList.add(70, new Triplet(205, 92, 92, getString(R.string.color_Red))); //indian red
        colorList.add(71, new Triplet(240, 128, 128, getString(R.string.color_Red))); //light coral
        colorList.add(72, new Triplet(233, 150, 122, getString(R.string.color_Red))); //dark salomon
        colorList.add(73, new Triplet(250, 128, 114, getString(R.string.color_Red))); //salomon
        colorList.add(74, new Triplet(255, 160, 122, getString(R.string.color_Red))); //light salomon
        colorList.add(75, new Triplet(255, 69, 0, getString(R.string.color_Red))); //orange red
        colorList.add(76, new Triplet(255, 99, 71, getString(R.string.color_Orange)));
        colorList.add(77, new Triplet(233, 150, 122, getString(R.string.color_Skin)));


        // shades of orange contd..

        colorList.add(78, new Triplet(255, 144, 0, getString(R.string.color_Orange))); // orange
        colorList.add(79, new Triplet(255, 165, 0, getString(R.string.color_Orange))); // orange

        // shades of green contd...
        colorList.add(80, new Triplet(189, 183, 107, getString(R.string.color_Green))); // dark khaki
        colorList.add(81, new Triplet(240, 230, 140, getString(R.string.color_Green)));  // khaki
        colorList.add(82, new Triplet(128, 128, 0, getString(R.string.color_Green)));  // olive
        colorList.add(83, new Triplet(154, 205, 50, getString(R.string.color_Green)));  // yellow green
        colorList.add(84, new Triplet(85, 107, 47, getString(R.string.color_Green)));  // dark olive  green
        colorList.add(85, new Triplet(107, 142, 35, getString(R.string.color_Green)));  // olive drab
        colorList.add(86, new Triplet(124, 252, 0, getString(R.string.color_Green)));  // lawn green
        colorList.add(87, new Triplet(127, 255, 0, getString(R.string.color_Green)));  // chart reuse
        colorList.add(88, new Triplet(173, 255, 47, getString(R.string.color_Green)));  // green yellow
        colorList.add(89, new Triplet(0, 100, 0, getString(R.string.color_Green)));  // dark green
        colorList.add(90, new Triplet(0, 128, 0, getString(R.string.color_Green)));  // green
        colorList.add(91, new Triplet(34, 139, 34, getString(R.string.color_Green)));  // forest green
        colorList.add(92, new Triplet(0, 255, 0, getString(R.string.color_Green)));  // lime
        colorList.add(93, new Triplet(50, 205, 50, getString(R.string.color_Green)));  // lime green
        colorList.add(94, new Triplet(144, 238, 144, getString(R.string.color_Green)));  // light green
        colorList.add(95, new Triplet(152, 251, 152, getString(R.string.color_Green)));  // pale green
        colorList.add(96, new Triplet(143, 188, 143, getString(R.string.color_Green)));  // dark sea green
        colorList.add(97, new Triplet(0, 250, 154, getString(R.string.color_Green)));  // medium spring green
        colorList.add(98, new Triplet(0, 255, 127, getString(R.string.color_Green)));  // spring green
        colorList.add(99, new Triplet(46, 139, 87, getString(R.string.color_Green)));  // sea green


        // shades of blue contd....
        colorList.add(100, new Triplet(0, 255, 255, getString(R.string.color_Blue)));  // aqua
        colorList.add(101, new Triplet(0, 206, 209, getString(R.string.color_Blue)));  // dark turquoise
        colorList.add(102, new Triplet(64, 224, 208, getString(R.string.color_Blue)));  // turqoise
        colorList.add(103, new Triplet(127, 255, 212, getString(R.string.color_Blue)));  // aqua marine
        colorList.add(104, new Triplet(100, 149, 237, getString(R.string.color_Blue)));  // corn flower blue
        colorList.add(105, new Triplet(0, 191, 255, getString(R.string.color_Blue)));  // deep sky blue
        colorList.add(106, new Triplet(30, 144, 255, getString(R.string.color_Blue)));  // dodger blue
        colorList.add(107, new Triplet(173, 216, 230, getString(R.string.color_Blue)));  // light blue
        colorList.add(108, new Triplet(25, 25, 112, getString(R.string.color_Blue)));  // midnight blue
        colorList.add(109, new Triplet(0, 0, 204, getString(R.string.color_Blue)));  // medium blue
        colorList.add(110, new Triplet(65, 105, 225, getString(R.string.color_Blue)));  // royal blue


        // shades of indigo
        colorList.add(111, new Triplet(147, 112, 219, getString(R.string.color_Indigo)));  // medium purple
        colorList.add(112, new Triplet(139, 0, 139, getString(R.string.color_Indigo)));  // dark magenta
        colorList.add(113, new Triplet(148, 0, 211, getString(R.string.color_Indigo)));  // dark violet
        colorList.add(114, new Triplet(153, 50, 204, getString(R.string.color_Indigo)));  // dark orchid
        colorList.add(115, new Triplet(128, 0, 128, getString(R.string.color_Indigo)));  // purple
        colorList.add(116, new Triplet(218, 112, 214, getString(R.string.color_Indigo)));  // orchid
        colorList.add(116, new Triplet(114, 88, 139, getString(R.string.color_Indigo)));  // orchid


        //other
        // colorList.add(67, new Triplet(192, 192, 192, getString(R.string.color_Silver)));
        //colorList.add(68, new Triplet(128, 128, 0, getString(R.string.color_Olive)));


    }

    public String getApproxColor(int r, int g, int b) {

        int oldred = r;
        int oldgreen = g;
        int oldblue = b;
        double dmin = 2147483646, dcur; //big number
        String color = "not known";

        for (MainActivity.Triplet t : colorList) {
            dcur = Math.sqrt(Math.pow((int) t.getRed() - r, 2) +
                    Math.pow((int) t.getGreen() - g, 2) +
                    Math.pow((int) t.getBlue() - b, 2));
            if (dcur < dmin) {
                dmin = dcur;
                color = t.getName().toString();
            }
        }
        Log.d(TAG, "Color Approx :" + color);
        return color;
    }

    Camera.PreviewCallback colorPreviewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] bytes, Camera camera) {
            if (framskip == 10) {

                framskip = 0;
                final int w = camera.getParameters().getPreviewSize().width;

                final int h = camera.getParameters().getPreviewSize().height;

                Log.d(TAG, "onPreviewFrame: frame width=" + w + ", frame height=" + h);
                // number of pixels//transforms NV21 pixel data into RGB pixels
                int[] rgb = new int[w * h];
                decodeYUV420SP(rgb, bytes, w, h);
                int red = 0;
                int green = 0;
                int blue = 0;
                String approxColorName = "";
                if (ActivityType.equalsIgnoreCase("dot")) {
                    red = (rgb[w * (h + 1) / 2] & 0x00ff0000) >> 16;
                    green = (rgb[w * (h + 1) / 2] & 0x0000ff00) >> 8;
                    blue = rgb[w * (h + 1) / 2] & 0x000000ff;


                    approxColorName = getApproxColor(red, green, blue);
                    Log.d(TAG, "Point Mode : Color Detected - " + approxColorName);
                } else {
                    ColorBlobDetector mDetector = new ColorBlobDetector();
                    Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    bitmap.setPixels(rgb, 0, w, 0, 0, w, h);

                    Mat mRgba = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC3);
                    Log.d(TAG, "onPreviewFrame: bitmap width=" + bitmap.getWidth() +
                            ", bitmap height=" + bitmap.getHeight());
                    Utils.bitmapToMat(bitmap, mRgba);
                    mDetector.process(mRgba);
                    List<MatOfPoint> contours = mDetector.getContours();
                    Log.e(TAG, "Contours count: " + contours.size());
                    Imgproc.drawContours(mRgba, contours, -1, CONTOUR_COLOR);
                    Utils.matToBitmap(mRgba, bitmap);
                    myImageView2.setImageBitmap(bitmap);
                    int cols = mRgba.cols();
                    int rows = mRgba.rows();
                    Log.d(TAG, "onPreviewFrame: mRgba cols=" + mRgba.cols() +
                            ", mRgba rows=" + mRgba.rows());
                    int xOffset = (w - cols) / 2;
                    int yOffset = ((h) - rows) / 2;
                    int x = (h / 2) - xOffset;
                    int y = (w / 2) - yOffset;

                    Rect touchedRect = new Rect();


                    touchedRect.x = (x > 4) ? x - 4 : 0;
                    touchedRect.y = (y > 4) ? y - 4 : 0;

                    touchedRect.width = (x + 4 < cols) ? x + 4 - touchedRect.x : cols - touchedRect.x;
                    touchedRect.height = (y + 4 < rows) ? y + 4 - touchedRect.y : rows - touchedRect.y;


                    Mat touchedRegionRgba = mRgba.submat(touchedRect);

                    Mat touchedRegionHsv = new Mat();
                    Imgproc.cvtColor(touchedRegionRgba, touchedRegionHsv, Imgproc.COLOR_RGB2HSV_FULL);

                    // Calculate average color of touched region
                    Scalar mBlobColorHsv = Core.sumElems(touchedRegionHsv);
                    int pointCount = touchedRect.width * touchedRect.height;
                    for (int i = 0; i < mBlobColorHsv.val.length; i++)
                        mBlobColorHsv.val[i] /= pointCount;

                    mBlobColorRgba = converScalarHsv2Rgba(mBlobColorHsv);
                    approxColorName = getApproxColor((int) mBlobColorRgba.val[0], (int) mBlobColorRgba.val[1], (int) mBlobColorRgba.val[2]);
                    Log.d(TAG, "Object Mode : Color Detected - " + approxColorName);

                }


                if (!approxColorName.matches(colprev)) {
                    int[] approxColorRGBValue = getTripletRGB(approxColorName);
                    String actualColorHexCode;
                    if (ActivityType.equalsIgnoreCase("dot")) {
                        actualColorHexCode = String.format("#%02x%02x%02x", red, green, blue);
                    } else {
                        actualColorHexCode = String.format("#%02x%02x%02x", (int) mBlobColorRgba.val[0], (int) mBlobColorRgba.val[1], (int) mBlobColorRgba.val[2]);
                    }
                    String approxColorHexCode = String.format("#%02x%02x%02x",
                            approxColorRGBValue[0], approxColorRGBValue[1], approxColorRGBValue[2]);
                    System.out.println("RGB=(" + red + "," + green + "," + blue + ")"
                            + ", ActualColorHexCode=" + actualColorHexCode
                            + ", ApproxColorName=" + approxColorName + ",");
                    curColorObj.setApproxColorName(approxColorName);
                    curColorObj.setApproxColorHexCode(approxColorHexCode);
                    curColorObj.setActualColorHexCode(actualColorHexCode);
                    Message message = mHandler.obtainMessage();
                    message.obj = curColorObj;
                    mHandler.sendMessage(message);
                }
            } else {
                framskip += 1;
            }
        }


    };

    private Scalar converScalarHsv2Rgba(Scalar hsvColor) {
        Mat pointMatRgba = new Mat();
        Mat pointMatHsv = new Mat(1, 1, CvType.CV_8UC3, hsvColor);
        Imgproc.cvtColor(pointMatHsv, pointMatRgba, Imgproc.COLOR_HSV2RGB_FULL, 4);

        return new Scalar(pointMatRgba.get(0, 0));
    }

    private int[] getTripletRGB(String name) {
        Triplet curTriplet = null;
        for (Triplet t : colorList) {
            curTriplet = t;
            if (t.getName().toString().matches(name)) {
                break;
            }
        }
        int[] rgb = new int[3];
        rgb[0] = (int) (curTriplet.getRed());
        rgb[1] = (int) (curTriplet.getGreen());
        rgb[2] = (int) (curTriplet.getBlue());
        return rgb;
    }

    //  Byte decoder : ---------------------------------------------------------------------
    void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height) {
        // Pulled directly from:
        // http://ketai.googlecode.com/svn/trunk/ketai/src/edu/uic/ketai/inputService/KetaiCamera.java
        final int frameSize = width * height;

        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0)
                    y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }

                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0)
                    r = 0;
                else if (r > 262143)
                    r = 262143;
                if (g < 0)
                    g = 0;
                else if (g > 262143)
                    g = 262143;
                if (b < 0)
                    b = 0;
                else if (b > 262143)
                    b = 262143;

                rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
            }
        }
    }

    private Camera.Size pictureSize;

    private int getCameraRotation() {
        //STEP #1: Get rotation degrees
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, info);
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break; //Natural orientation
            case Surface.ROTATION_90:
                degrees = 90;
                break; //Landscape left
            case Surface.ROTATION_180:
                degrees = 180;
                break;//Upside down
            case Surface.ROTATION_270:
                degrees = 270;
                break;//Landscape right
        }
        int rotate = (info.orientation - degrees + 360) % 360;

        return rotate;
    }


    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea > resultArea) {
                        result = size;
                    }
                }
            }
        }
        return (result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_13, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        isActivityPaused = false;
        if (app.mLAC != null) {//#269 crashfix
            app.mLAC.stopLocationUpdates();
        }

        if (mCamera != null) {
            mCamera.release();
        }

        try {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            //mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);

            if (mCamera == null) {
                //findFrontFacingCamera();
                mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);// FIX: 26/10/16 #303 crash

                if (mCamera == null) {

                    mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);

                    //#interim fix213 for xiomi and micromax samsung phones fail to open camera
                    if (mCamera == null) {
                        createToast(getResources().getString(R.string.error_camera_open_failed));

                        // throw new Exception("SeeObjectActivity:Camera is null onResume");
                    }
                }
            }

            if (mCamera != null) {
                // Create an instance of Camera
                preview.setVisibility(View.VISIBLE);
                prepareCamera();
            } else {
                createToast(getResources().getString(R.string.error_camera_open_failed));
            }
        } catch (Exception e) {
            //
            //e.printStackTrace();
            createToast(getResources().getString(R.string.error_camera_open_failed));
            FirebaseCrashlytics.getInstance().recordException(e);
        }

    }

    @Override
    protected void onPause() {

        super.onPause();              // release the camera immediately on pause event
        isActivityPaused = true;
        if (inPreview) {
            mCamera.stopPreview();
        }

        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.release();
            preview.setVisibility(View.GONE);
        }

        mCamera = null;
        inPreview = false;
        Log.d(TAG, "onPause");

        tts.playSilence(2000, TextToSpeech.QUEUE_FLUSH, null);
        tts.stop();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    public void createToast(String toastMessage) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);
        text.setText(toastMessage);

        Toast toast = new Toast(getApplicationContext());

        toast.setDuration(Toast.LENGTH_LONG);

        toast.setView(layout);
        toast.show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        }
    }

    // Google Analytics - Method to send Click event hits
    public void sendAnalytics(String data) {
        if (!App.isDebugVersion) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setLabel(data)
                    .setCategory("Button Click")
                    .setAction("Vision Activity3 Menu")
                    .build());
        }
    }

    public void sendAnalytics(String action, String label, String category) {
        if (!App.isDebugVersion) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setLabel(label)
                    .setCategory(category)
                    .setAction(action)
                    .build());
        }
    }

    // Google Analytics - Method to send Click event hits
    public void sendAnalytics(String labelData, String categoryData) {
        mTracker.send(new HitBuilders.EventBuilder()
                .setLabel(labelData)
                .setCategory(categoryData)
                .setAction("Vision Activity3 Menu")
                .build());
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {

            case R.id.radio_button_dot:
                if (checked)
                    ActivityType = "dot";
                sendAnalytics(Constants.COLOR_READER, Constants.COLOR_READER_POINT,
                        Constants.BUTTON_CLICK);
                imageView.setVisibility(View.VISIBLE);
                tts.speak("Point Color Mode On",
                        TextToSpeech.QUEUE_FLUSH, map);

                break;

            case R.id.radio_button_blob:
                if (checked)
                    ActivityType = "blob";
                sendAnalytics(Constants.COLOR_READER, Constants.COLOR_READER_OBJECT,
                        Constants.BUTTON_CLICK);
                imageView.setVisibility(View.INVISIBLE);
                tts.speak("Object Color Mode On",
                        TextToSpeech.QUEUE_FLUSH, map);

                break;

            default:
                ActivityType = "dot";
                break;

        }

    }

    private Camera.Size getBiggestPictureSize(Camera.Parameters parameters) {
        Camera.Size result = null;

        for (Camera.Size size : parameters.getSupportedPictureSizes()) {
            if (result == null) {
                result = size;
                break; //We are giving away the biggest size as of now
            } else {
                int resultArea = result.width * result.height;
                int newArea = size.width * size.height;

                if (newArea > resultArea) {
                    result = size;
                }
            }
        }
        return (result);
    }

    public class Triplet<T, U, V, N> {

        private final T red;
        private final U green;
        private final V blue;
        private final N name;

        public Triplet(T red, U green, V blue, N name) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }

        public T getRed() {
            return red;
        }

        public U getGreen() {
            return green;
        }

        public V getBlue() {
            return blue;
        }

        public N getName() {
            return name;
        }
    }

    private class ColorClass {
        String approxColorName;
        String approxColorHexCode;
        String actualColorHexCode;

        public String getApproxColorName() {
            return this.approxColorName;
        }

        public void setApproxColorName(String approxColorName) {
            this.approxColorName = approxColorName;
        }

        public String getApproxColorHexCode() {
            return this.approxColorHexCode;
        }

        public void setApproxColorHexCode(String approxColorHexCode) {
            this.approxColorHexCode = approxColorHexCode;
        }

        public String getActualColorHexCode() {
            return this.actualColorHexCode;
        }

        public void setActualColorHexCode(String actualColorHexCode) {
            this.actualColorHexCode = actualColorHexCode;
        }
    }

    private void prepareCamera() {

        final SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mCamera.setPreviewDisplay(previewHolder);
                    mCamera.getParameters().setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                } catch (Throwable t) {
                    Log.e("Surrface",
                            "Exception in setPreviewDisplay()", t);
                    //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    if (!App.isDebugVersion) {
                        FirebaseCrashlytics.getInstance().recordException(t);
                    }
                    sendAnalytics("SeeObjectActivity failed creating surface", Constants.ERROR);
                }
            }

            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width,
                                       int height) {
                //fix #327 non-fatal. onResume and surfaceChanged might be having race conditions
                if (mCamera == null) {
                    try {
                        mCamera = Camera.open();

                        //throw new Exception("Camera null onSurfaceChanged() in SeeObjectActivity"); // issue: 26/10/16 #327 non-fatal

                    } catch (Exception e) {
                        e.printStackTrace();
                        //Fix #200,#296 crash when camera is null on mobiles like Xiomi
                        createToast(getResources().getString(R.string.error_camera_open_failed));
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }
                }

                //If camera was already opened or opened here just now, do below
                if (mCamera != null) {
                    {
                        Camera.Parameters parameters = mCamera.getParameters();
                        Camera.Size size = getBestPreviewSize(width, height,
                                parameters);

                        if (size != null) {
                            //parameters.setPreviewSize(size.width, size.height);//MALLARD FIX: http://stackoverflow.com/questions/19400978/surfaceview-displays-the-noise-at-the-camera-image
                            pictureSize = getBiggestPictureSize(parameters);

                            parameters.setPictureSize(pictureSize.width,
                                    pictureSize.height); // fixme: make sure resolution is supported by the camera - Fatal Exception: java.lang.RuntimeException: setParameters failed
                            List<Integer> s = parameters.getSupportedPreviewFormats();
                            parameters.setPictureFormat(ImageFormat.JPEG);
                            List<String> focusModes = parameters.getSupportedFocusModes();
                            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

                            }
                         /*   List<String> flashModes = parameters.getSupportedFlashModes();
                            if (flashModes != null) {
                                if (flashModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                                }
                            }*/
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            int rotate = getCameraRotation();
                            parameters.setRotation(rotate);
                            mCamera.setParameters(parameters);
                            mCamera.setDisplayOrientation(90);
                            mCamera.setPreviewCallback(colorPreviewCallback);
                            mCamera.startPreview();
                            inPreview = true;
                        }
                    }
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                // no-op
            }
        };

        preview = findViewById(R.id.surface);

        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        previewHolder.setFixedSize(getWindow().getWindowManager()
                .getDefaultDisplay().getWidth(), getWindow().getWindowManager()
                .getDefaultDisplay().getHeight());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        // If request is cancelled, the result arrays are empty.
        if (requestCode == Storage_permission_code) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(app, "permission granted", Toast.LENGTH_SHORT).show();
                // Permission is granted. Continue the action or workflow
                // in your app.
            } else {
                // Explain to the user that the feature is unavailable because
                // the features requires a permission that the user has denied.
                // At the same time, respect the user's decision. Don't link to
                // system settings in an effort to convince the user to change
                // their decision.
                Toast.makeText(app, "permission not granted", Toast.LENGTH_SHORT).show();
            }
            return;
        }

    }

    // Other 'case' lines to check for other
    // permissions this app might request.
    public void request_permission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            //showInContextUI(...);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    Storage_permission_code);
        } else {
            // You can directly ask for the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    Storage_permission_code);
        }
    }
}

