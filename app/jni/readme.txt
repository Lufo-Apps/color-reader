
Created Gaurav 20.10.2021
Readme for dex2c
==================

24.11.2021
- to integrate camera.cpp, follow all the process on github and then right click on Android.mk and add CMakeLists.txt using "link c++ files"
- go to terminal in android studio, got /Users/gaurav/StudioProjects/eyedpro/app and type "ndk-build" which will automatically generate so files (http://web.guohuiwang.com/technical-notes/androidndk1)
20.10.2021
- Notes to compile the app using Dex2C
- multidex is enabled by default on api level 21 and above, so need to use dcc multidex branch else it wont work
- switch to master and merge multidex. then use either filter or dex2c notation
- on release, no compile methods found when using dex2c notation
- ndk filters are used in build.gradle
- add ndkbuild to path in mac 'sudo nano /etc/paths' before running dcc
- go to venv in pyprojects by source /bin/activate
- (venv) gaurav@Gauravs-MBP dcc % python3 dcc.py /Users/gaurav/StudioProjects/eyedpro/app/release/app-universal-release.apk -o out.apk
use filters.txt
.*MainActivity.*onCreate.*
.*ApiRequester.*
.*ApiService.*
.*ForceUpdate.*
.*SaveUserCalls.*
.*SessionKeeper.*
.*App.*
.*MyPiracyChecked.*
.*javiersantos.*
- also, proguard/r8 may need to be disabled else oncreate methods which are made native
may not be found on runtime with out.apk
- change this line in dcc.py

        #subprocess.check_call(['java', '-jar', APKTOOL, 'd', '-r', '-f', '-o', outdir, apk])
        subprocess.check_call(['java', '-jar', APKTOOL, 'd', '-f', '-o', outdir, apk])
- after building apk, zip align and sign using:
        rm zipaligned_signed.apk
        python3 zipalign_and_sign.py out.apk
        adb install zipaligned_signed.apk
        or upload it to playstore
-Useful links
https://github-com.translate.goog/amimo/dcc?_x_tr_sl=zh-CN&_x_tr_tl=en&_x_tr_hl=en-US&_x_tr_pto=nui
https://github.com/amimo/dcc/tree/multidex


USEFUL COMMANDS:
cat ~/.zsh_history | grep 308
adb shell am start -a android.intent.action.VIEW -d  https://play.google.com/apps/test/in.gingermind.eyedpro/308





