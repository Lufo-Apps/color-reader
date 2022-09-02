package in.levelup.colorreader.FirebaseCloudCalls;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.HashMap;
import java.util.Map;

public class FirebaseCloudFunctions {

    private FirebaseFunctions mFunctions;

    public FirebaseCloudFunctions(){
        this.mFunctions = FirebaseFunctions.getInstance();
    }

    private Task<String> addMessage(String text) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("text", text);
        //data.put("push", true);

        return mFunctions
                .getHttpsCallable("addMessage")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });
    }

    private Task<String> checkX(String text) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("xyz", "xxx");
        //data.put("push", true);

        return mFunctions
                .getHttpsCallable("checkX")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });
    }

    public Task<String> checkX2(String text) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("xyz", text);
        //data.put("push", true);

        return mFunctions
                .getHttpsCallable("checkX2")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });
    }

    private void sendMessageToMainHandler(int what, Object obj) {
       /* Message msg = MainActivity.mHandler.obtainMessage();
        msg.what = what;
        msg.obj = obj;
        MainActivity.mHandler.sendMessage(msg); */
    }
}
