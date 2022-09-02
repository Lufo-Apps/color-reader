#include <android/log.h>
#include <string>
#include <string.h>
#include <jni.h>

#define LOGINFO(...) ((void)__android_log_print(ANDROID_LOG_INFO, "security", __VA_ARGS__))
#define LOGERROR(...) ((void)__android_log_print(ANDROID_LOG_ERROR, "security", __VA_ARGS__))

static int verifySign(JNIEnv *env);

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return JNI_ERR;
    }
    if (verifySign(env) == JNI_OK) {
        return JNI_VERSION_1_4;
    }
    //LOGERROR ( "The signature is inconsistent! " );
    return JNI_ERR;
}

static jobject getApplication(JNIEnv *env) {
    jobject application = NULL;
    jclass activity_thread_clz = env->FindClass("android/app/ActivityThread");
    if (activity_thread_clz != NULL) {
        jmethodID currentApplication = env->GetStaticMethodID(
                activity_thread_clz, "currentApplication", "()Landroid/app/Application;");
        if (currentApplication != NULL) {
            application = env->CallStaticObjectMethod(activity_thread_clz, currentApplication);
        } else {
            LOGERROR("Cannot init method: opencvhandler in ActivityThread.");
        }
        env->DeleteLocalRef(activity_thread_clz);
    } else {
        LOGERROR("Cannot find class: opencv.org.opencvhandler");
    }

    return application;
}
static const char *SIGN = "308201f930820162a00302010202045491e01f300d06092a864886f70d0101050500304131133011060355040a130a47696e6765724d696e6431133011060355040b130a47696e6765724d696e64311530130603550403130c4761757261764d697474616c301e170d3134313231373139353731395a170d3339313231313139353731395a304131133011060355040a130a47696e6765724d696e6431133011060355040b130a47696e6765724d696e64311530130603550403130c4761757261764d697474616c30819f300d06092a864886f70d010101050003818d0030818902818100a25fed7de688d5e63fa028d053a63ec3d0ae0eac4b94bab3433d16bce16039b50ed62d9289e1e5c773d7e44b24c053b1665c77466956a36dec7f39c7a29acbcd74600dcd904a6fd808d04b90c25ce1375d9896e6c9f557e861984105c4faf39f17ad7a1642c9be506f1e458c12ad0f5e8ecad6fbf4512f97a59da5b227e9b4f30203010001300d06092a864886f70d0101050500038181001eca9d5b65fb12e525b9521ec4eefec7dd136e74c5896637a31144038f95178916494b8b1dd9822f0a3d4e9997c92a4c54ef1a19805a8dfafb3815d17119b3097c3f6aef2fd06265eaf636f00e280c29f9a2bc4b279ad955b341e329e16ac182c558343e430fc27c0cb988e19620545c5bb2ea5a93b9b68a695063eb00709080";

static int verifySign(JNIEnv *env) {
    // Application object
    jobject application = getApplication(env);
    if (application == NULL) {
        return JNI_ERR;
    }
    // Context(ContextWrapper) class
    jclass context_clz = env->GetObjectClass(application);
    // getPackageManager()
    jmethodID getPackageManager = env->GetMethodID(context_clz, "getPackageManager",
                                                   "()Landroid/content/pm/PackageManager;");
    // android.content.pm.PackageManager object
    jobject package_manager = env->CallObjectMethod(application, getPackageManager);
    // PackageManager class
    jclass package_manager_clz = env->GetObjectClass(package_manager);
    // getPackageInfo()
    jmethodID getPackageInfo = env->GetMethodID(package_manager_clz, "getPackageInfo",
                                                "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    // context.getPackageName()
    jmethodID getPackageName = env->GetMethodID(context_clz, "getPackageName",
                                                "()Ljava/lang/String;");
    // call getPackageName() and cast from jobject to jstring
    jstring package_name = (jstring) (env-> CallObjectMethod (application, getPackageName));
    // PackageInfo object
    jobject package_info = env->CallObjectMethod(package_manager, getPackageInfo, package_name, 64);
    // class PackageInfo
    jclass package_info_clz = env->GetObjectClass(package_info);
    // field signatures
    jfieldID signatures_field = env->GetFieldID(package_info_clz, "signatures",
                                                "[Landroid/content/pm/Signature;");
    jobject signatures = env->GetObjectField(package_info, signatures_field);
    jobjectArray signatures_array = (jobjectArray) signatures;
    jobject signature0 = env->GetObjectArrayElement(signatures_array, 0);
    jclass signature_clz = env->GetObjectClass(signature0);

    jmethodID toCharsString = env->GetMethodID(signature_clz, "toCharsString",
                                               "()Ljava/lang/String;");
    // call toCharsString()
    jstring signature_str = (jstring) (env->CallObjectMethod(signature0, toCharsString));

    // release
    env->DeleteLocalRef(application);
    env->DeleteLocalRef(context_clz);
    env->DeleteLocalRef(package_manager);
    env->DeleteLocalRef(package_manager_clz);
    env->DeleteLocalRef(package_name);
    env->DeleteLocalRef(package_info);
    env->DeleteLocalRef(package_info_clz);
    env->DeleteLocalRef(signatures);
    env->DeleteLocalRef(signature0);
    env->DeleteLocalRef(signature_clz);

    const char *sign = env->GetStringUTFChars(signature_str, NULL);
    if (sign == NULL) {
        LOGERROR ( " Failed to allocate memory " );
        return JNI_ERR;
    }
// Remember to remove the log when publishing! ! ! ! ! ! !
    //LOGERROR("The signature read in the application is: %s", sign);
    //LOGERROR("The preset signature in nativeR is: %s", SIGN);
    int result = strcmp(sign, SIGN);
    //LOGERROR("strcmpd：%d", result);
    // Release this memory after use
    env->ReleaseStringUTFChars(signature_str, sign);
    env->DeleteLocalRef(signature_str);
    if (result == 0 ) { //The signatures are consistent
        return JNI_OK;
    }
    return JNI_ERR;
}
