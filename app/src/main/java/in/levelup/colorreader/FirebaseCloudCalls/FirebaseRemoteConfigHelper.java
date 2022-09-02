package in.levelup.colorreader.FirebaseCloudCalls;

import android.app.Activity;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import in.levelup.colorreader.R;
import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class FirebaseRemoteConfigHelper {

    public static FirebaseRemoteConfig mFirebaseRemoteConfig;
    private static final String TAG = "FBRemoteConfigHelper";

    public static void setFirebaseRemoteConfig(FirebaseRemoteConfig firebaseRemoteConfig) {
        mFirebaseRemoteConfig = firebaseRemoteConfig;
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);

        // set in-app defaults manually
        /*Map<String, Object> remoteConfigDefaults = new HashMap();
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_REQUIRED, false);
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_CURRENT_VERSION, "1.0.0");
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_URL,
                "https://play.google.com/store/apps/details?id=com.sembozdemir.renstagram");
                        firebaseRemoteConfig.setDefaults(remoteConfigDefaults);
*/
    }

    public static void fetchAndActivate(Activity activity){

    }
}
