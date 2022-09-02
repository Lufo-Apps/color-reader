package in.levelup.colorreader;

import static android.content.Context.LOCATION_SERVICE;
import static in.levelup.colorreader.util.Constants.H_LOCATION_CHANGED;
import static in.levelup.colorreader.util.Constants.H_LOCATION_MODE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.json.JSONException;
import org.json.JSONObject;

import in.levelup.colorreader.util.Constants;
import io.michaelrocks.paranoid.Obfuscate;

/**
 * Created by gauravmittal on 26/05/16.
 */
@Obfuscate
public class LocationApiClient implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Handler handler = null;
    private Context context;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    public Location mLastLocation = null;
    private Location mCurrentLocation = null;
    //private boolean isGPSEnabled=false;
    //private boolean isNetworkEnabled=false;
    private LocationManager locationManager;
    private static String TAG = "LocationApiClient";
    private LocationSettingsRequest.Builder builder;
    private boolean googleApiClientConnected = false;
    int locationMode = 0;

    public boolean isLocationUpdateStarted() {
        return locationUpdateStarted;
    }

    private void setLocationUpdateStarted(boolean locationUpdateStarted) {
        this.locationUpdateStarted = locationUpdateStarted;
    }

    private boolean locationUpdateStarted = false;

    public LocationApiClient(Context context) {

        this.context = context;
        buildGoogleApiClient();
        Log.d(TAG, "LocationApiClient: object created, calling googleApiClient.connect....");
        mGoogleApiClient.connect();
    }

    public LocationApiClient(Context context, Handler handler) {
        this.handler = handler;
        this.context = context;
        buildGoogleApiClient();
        Log.d(TAG, "LocationApiClient: object created, calling googleApiClient.connect....");
        mGoogleApiClient.connect();

    }


    private void buildGoogleApiClient() {
        Log.d(TAG, "buildGoogleApiClient: Creating googleApiClient...");
//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    public GoogleApiClient getClient() {
        return this.mGoogleApiClient;
    }

    public boolean isGoogleApiClientConnected() {
        return googleApiClientConnected;
    }

    @Override
    public void onConnected(Bundle bundle) {
        googleApiClientConnected = true;
        Log.d(TAG, "GoogleApiClient: onConnected: Connected now.");

        JSONObject jsonObject = new JSONObject();



        try {
            jsonObject.put(Constants.CALLING_ACTIVITY_NAME, Constants.MAIN_ACTIVITY);
            jsonObject.put(Constants.EVENT_ID_KEY, Constants.GOOGLE_API_CLIENT_CONNECTED);
        } catch (JSONException e) {
            e.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(e);
        }
        //App.bus.post(jsonObject);
        //startLocationUpdates();
    }

    protected void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
        setLocationUpdateStarted(false);
    }

    protected boolean checkLocMode(int locationMode) {
        int newMode = -1;
        try {
            newMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            Log.e(TAG, "Location mode is " + locationMode);

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return newMode != locationMode;


    }

    @SuppressLint("MissingPermission") //TODO: Add Permission Check
    protected void startLocationUpdates() {


        if (mGoogleApiClient.isConnected()) {
            //#fix: crash #207
            Log.d(TAG, "startLocationUpdates: googleApiClient connected...starting locationReqeust now...");
            if (mLocationRequest == null || checkLocMode(locationMode)) {
                mLocationRequest = LocationRequest.create();

                try {
                    locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (locationMode == Settings.Secure.LOCATION_MODE_BATTERY_SAVING)
                    mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                else mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                sendMessageToHandler(H_LOCATION_MODE, locationMode);
                mLocationRequest.setInterval(1000);
                mLocationRequest.setFastestInterval(1000);

                builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);

                //**************************
                builder.setAlwaysShow(true); //this is the key ingredient
                //**************************
            }

            Log.d(TAG, "startLocationUpdates():locationreq=" + mLocationRequest.toString());

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            setLocationUpdateStarted(true);
        } else {
            Log.e(TAG, "startLocationUpdates: googleApiClient NOT connected...NOT starting locationReqeust now.");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        Log.d(TAG, "onLocationChanged: Location accuracy: " + location.getAccuracy());
        updateLocations(location);
        //setLastKnownLocation();

        sendMessageToHandler(H_LOCATION_CHANGED, null);

        /*JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put(Constants.CALLING_ACTIVITY_NAME,Constants.MAIN_ACTIVITY);
            jsonObject.put(Constants.EVENT_ID_KEY,Constants.LOCATION_CHANGED);
        } catch (JSONException e) {
            e.printStackTrace(); if(Fabric.isInitialized()){ Crashlytics.logException(e);}
        }
        App.bus.post(jsonObject);*/
    }

    private void updateLocations(Location location) {
        //FIX START for https://console.firebase.google.com/project/eye-d-pro/crashlytics/app/android:in.gingermind.eyedpro/issues/d1d7883f809de0521995170a9ebef068?time=last-seven-days&sessionId=5D582D7601B400012CEC8B874FEA6172_DNE_0_v2
        if (mCurrentLocation == null) {
            mCurrentLocation = location;
        }
        //FIX END

        mLastLocation = mCurrentLocation;
        mCurrentLocation = location;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        googleApiClientConnected = false;
        Log.e(TAG, "onConnectionFailed: connectionResult: " + connectionResult.getErrorCode());
        if (handler != null) {
            Message msg = handler.obtainMessage();
            msg.what = Constants.GOOGLE_API_CONNECTION_FAILED;
            msg.obj = connectionResult;
            handler.sendMessage(msg);
        }

        FirebaseCrashlytics.getInstance().log("LocationApiClient Error: " + "LocationApiClient on connectionFailed called");
    }

    public PendingResult<LocationSettingsResult> checkLocationSettings() {

        if (mLocationRequest == null) {
            mLocationRequest = LocationRequest.create();


            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);


            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            if (locationMode == Settings.Secure.LOCATION_MODE_BATTERY_SAVING) {
                Log.d(TAG, "User has Battery saving mode on ");
                mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                sendMessageToHandler(H_LOCATION_MODE, 2);
            } else if (locationMode == Settings.Secure.LOCATION_MODE_SENSORS_ONLY) {
                Log.d(TAG, "High accuracy location request in Device Only Mode ");
                sendMessageToHandler(H_LOCATION_MODE, 1);
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            } else {
                Log.d(TAG, "High accuracy location request ");
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            }
            mLocationRequest.setInterval(5 * 1000);
            mLocationRequest.setFastestInterval(5 * 1000);

            builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);

            //**************************
            builder.setAlwaysShow(true); //this is the key ingredient
            //***************************/
        }

        Log.d(TAG, "checkLocationSettings():locationreq=" + mLocationRequest.toString());

        return LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
    }

    public boolean isLocationInitComplete() {
        return mLastLocation != null;
    }

    public Location getLastLocation() {
        return mLastLocation;
    }

    public double getLastlatitude() {
        return mLastLocation != null ? mLastLocation.getLatitude() : -1;
    }

    public double getLastLongitude() {
        return mLastLocation != null ? mLastLocation.getLongitude() : -1;
    }

    public Location getCurrentLocation() {
        return mCurrentLocation;
    }

    public double getCurrentlatitude() {
        if (mCurrentLocation != null) {
            return mCurrentLocation.getLatitude(); //#266 fabric crash
        } else if (mLastLocation != null) {
            return mLastLocation.getLatitude();
        } else return -1;
    }

    public double getCurrentLongitude() {
        if (mCurrentLocation != null) {
            return mCurrentLocation.getLongitude();
        } else {
            return mLastLocation.getLongitude();
        }
    }

    @SuppressLint("MissingPermission") //TODO: Add permission check
    private void setLastKnownLocation() {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation == null) {
            mLastLocation = getLastKnownLocationOldMethod(context);
        }

        if (mLastLocation != null) {
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put(Constants.CALLING_ACTIVITY_NAME, Constants.MAIN_ACTIVITY);
                jsonObject.put(Constants.EVENT_ID_KEY, Constants.LAST_KNOWN_LOCATION_FETCHED);
            } catch (JSONException e) {
                e.printStackTrace();
                FirebaseCrashlytics.getInstance().recordException(e);
            }
            App.bus.post(jsonObject);
        }
    }

    public Boolean isLocationActuallyChanged() {
        //fix LocationApiClient.java line 355
        //https://fabric.io/eyed/android/apps/in.gingermind.eyedpro/issues/8611eed46948406e8f38a22094b012a7?time=last-seven-days
        if (mCurrentLocation == null && mLastLocation == null) {
            return false;
        }
        //fix end
        if (mCurrentLocation != null && mLastLocation == null) {
            return true;
        } else if (mCurrentLocation == null && mLastLocation != null) {
            return true;
        } else
            return mCurrentLocation.getLatitude() != mLastLocation.getLatitude() || mCurrentLocation.getLongitude() != mLastLocation.getLongitude();
    }

    public Boolean getIsGPSEnabledOld() {
        Log.d(TAG, "getIsGPSEnabledOld:Checking GPS from old method now...");

        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        }


        // getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return isGPSEnabled;

    }

    @SuppressLint("MissingPermission") //TODO: Fix this?
    public Location getLastKnownLocationOldMethod(Context context) {
        Location location = null;

        try {
            if (locationManager == null) {
                locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            }

            double latitude, longitude;


            // getting GPS status
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                //this.canGetLocation = true;
                if (isNetworkEnabled) {
                    /*locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            (long) 2000,
                            (float) 20.0, new android.location.LocationListener() {
                                @Override
                                public void onLocationChanged(android.location.Location location) {

                                }

                                @Override
                                public void onStatusChanged(String provider, int status, Bundle extras) {

                                }

                                @Override
                                public void onProviderEnabled(String provider) {

                                }

                                @Override
                                public void onProviderDisabled(String provider) {

                                }
                            });*/
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        /*locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                (long) 2000,
                                (float) 20.0, new android.location.LocationListener() {
                                    @Override
                                    public void onLocationChanged(android.location.Location location) {

                                    }

                                    @Override
                                    public void onStatusChanged(String provider, int status, Bundle extras) {

                                    }

                                    @Override
                                    public void onProviderEnabled(String provider) {

                                    }

                                    @Override
                                    public void onProviderDisabled(String provider) {

                                    }
                                });*/
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    private void sendMessageToHandler(final Integer result, Object obj) {
        if (handler != null) {
            Message message = handler.obtainMessage();
            message.what = result;
            message.obj = obj;
            handler.sendMessage(message);
        }
    }


    public void disconnectGoogleApiClient() {
        mGoogleApiClient.disconnect();
    }
}
