#HOW TO BUILD
#First learn about android.mk and application.mk from google android sources
# Then, first build libraries by running >ndk-build in terminal from the root directory of app.
# Remember to have latest ndk-build added to path of terminal. it will generate lins in app/libs/arm<> folders.
# Then copy these to v8a, v7a folder manually. and then run and install. do this for each time cpp are changed.
#

LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := nc
LOCAL_LDLIBS    := -llog
SOURCES := $(wildcard $(LOCAL_PATH)/nc/*.cpp)
LOCAL_C_INCLUDES := $(LOCAL_PATH)/nc
LOCAL_SRC_FILES := $(SOURCES:$(LOCAL_PATH)/%=%)
include $(BUILD_SHARED_LIBRARY)

# Build library 2
#https://stackoverflow.com/questions/14516268/ndk-compiling-multiple-libraries
include $(CLEAR_VARS)
LOCAL_MODULE := opencvhandler
LOCAL_LDLIBS    := -llog
LOCAL_C_INCLUDES := $(LOCAL_PATH)/opencvhandler
LOCAL_SRC_FILES := opencvhandler/opencvhandler.cpp
include $(BUILD_SHARED_LIBRARY)


