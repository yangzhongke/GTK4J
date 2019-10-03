#include <stdlib.h>//避免“warning: incompatible implicit declaration of built-in function 'malloc' ”警告
#include <stdio.h> 
#include <string.h>//避免“warning: incompatible implicit declaration of built-in function 'strlen' ”警告
#include <jni.h> 
#include <gtk/gtk.h>
#include <gdk/gdk.h>
#include <windows.h>
#include <MMSystem.h>

#include "com_rupeng_gtk4j_GTK.h"

//-g  $(FileName) -shared -Wl,--kill-at -s -o gtk4j.dll   -ID:\gtk\include -ID:\gtk\include\gtk-3.0 
//-ID:\gtk\include\cairo -ID:\gtk\include\gdk -ID:\gtk\include\glib-2.0 -ID:\gtk\lib\glib-2.0\include 
//-ID:\gtk\include\pango-1.0 -ID:\gtk\include\atk-1.0 -ID:\gtk\include\gdk-pixbuf-2.0  -I"C:\Program Files\Java\jdk1.8.0_05\include" 
//-I"C:\Program Files\Java\jdk1.8.0_05\include\win32" -LD:\gtk\lib  -lgtk-win32-3.0 -lgobject-2.0 -lglib-2.0   -mwindows

int jniThrowException(JNIEnv* env, const char* className, const char* msg)
{
    jclass exceptionClass;

    if ((*env)->ExceptionCheck(env)) {
        jthrowable exception = (*env)->ExceptionOccurred(env);
        (*env)->ExceptionClear(env);

        if (exception != NULL) {
            
            (*env)->DeleteLocalRef(env, exception);
        }
    }

    exceptionClass = (*env)->FindClass(env, className);
    if (exceptionClass == NULL) {
        return -1;
    }

    int result = 0;
    if ((*env)->ThrowNew(env, exceptionClass, msg) != JNI_OK) {
        result = -1;
    }

    (*env)->DeleteLocalRef(env, exceptionClass);
    return result;
}


int jniThrowNullPointerException(JNIEnv* env, const char* msg)
{
    return jniThrowException(env, "java/lang/NullPointerException", msg);
}


int jniThrowRuntimeException(JNIEnv* env, const char* msg)
{
    return jniThrowException(env, "java/lang/RuntimeException", msg);
}

//C字符串转java字符串
jstring strToJstring(JNIEnv* env, const char* pStr)
{
    int        strLen    = strlen(pStr);
    jclass     jstrObj   = (*env)->FindClass(env, "java/lang/String");
    jmethodID  methodId  = (*env)->GetMethodID(env, jstrObj, "<init>", "([BLjava/lang/String;)V");
    jbyteArray byteArray = (*env)->NewByteArray(env, strLen);
    jstring    encode    = (*env)->NewStringUTF(env, "utf-8");

    (*env)->SetByteArrayRegion(env, byteArray, 0, strLen, (jbyte*)pStr);
    
    return (jstring)(*env)->NewObject(env, jstrObj, methodId, byteArray, encode);
} 

//把jstring转换为Win32 API用的Unicode编码
char* jstringToWindows(JNIEnv* env, jstring jstr)
 {
	 int length = (*env)->GetStringLength(env,jstr);
	 const jchar* jcstr = (*env)->GetStringChars(env,jstr, 0);
	 char* rtn = (char*)malloc(length * 2 + 1);
	 int size = 0;
	 size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, rtn, (length*2+1), NULL, NULL);
	 if(size <= 0)
	 return NULL;
	 (*env)->ReleaseStringChars(env,jstr, jcstr);
	 rtn[size] = 0;
	 return rtn;
 }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1init
  (JNIEnv * env, jclass cls)
  {
	  gtk_init(NULL,NULL);
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1new
  (JNIEnv * env, jclass cls, jint style)
  {
	  return (int)gtk_window_new(style);
  }

void assertType(JNIEnv* env,int condition,char* paramName,char * typeName)
{
	  if(!condition)
	  {
		  char msg[256];
		  sprintf(msg,"%s参数传递的 不是%s",paramName,typeName);
		 jniThrowRuntimeException(env,msg);
	  }
}

  JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_findConst
  (JNIEnv * env, jclass cls, jstring name)
  {
	 char* pConstName = (char*)(*env)->GetStringUTFChars(env, name, NULL);
	 if(strcmp("GTK_WINDOW_POPUP",pConstName)==0)
	  {
		 return GTK_WINDOW_POPUP;
	  }	
	  else if(strcmp("GTK_WINDOW_TOPLEVEL",pConstName)==0)
	  {
		 return GTK_WINDOW_TOPLEVEL;
	  }	
	  else if(strcmp("GTK_WIN_POS_NONE",pConstName)==0)
	  {
		 return GTK_WIN_POS_NONE;
	  }	
	  else if(strcmp("GTK_WIN_POS_CENTER",pConstName)==0)
	  {
		 return GTK_WIN_POS_CENTER;
	  }	
	  else if(strcmp("GTK_WIN_POS_MOUSE",pConstName)==0)
	  {
		 return GTK_WIN_POS_MOUSE;
	  }	
	  else if(strcmp("GTK_WIN_POS_CENTER_ALWAYS",pConstName)==0)
	  {
		 return GTK_WIN_POS_CENTER_ALWAYS;
	  }	
	  else if(strcmp("GTK_WIN_POS_CENTER_ON_PARENT",pConstName)==0)
	  {
		 return GTK_WIN_POS_CENTER_ON_PARENT;
	  }	
 
	  else if(strcmp("GTK_DIALOG_MODAL",pConstName)==0)
	  {
		 return GTK_DIALOG_MODAL;
	  }	 
	  else if(strcmp("GTK_DIALOG_DESTROY_WITH_PARENT",pConstName)==0)
	  {
		 return GTK_DIALOG_DESTROY_WITH_PARENT;
	  }	  
	  else if(strcmp("GTK_MESSAGE_INFO",pConstName)==0)
	  {
		 return GTK_MESSAGE_INFO;
	  }	 
	  else if(strcmp("GTK_MESSAGE_WARNING",pConstName)==0)
	  {
		 return GTK_MESSAGE_WARNING;
	  }	 
	  else if(strcmp("GTK_MESSAGE_QUESTION",pConstName)==0)
	  {
		 return GTK_MESSAGE_QUESTION;
	  }	 
	  else if(strcmp("GTK_MESSAGE_ERROR",pConstName)==0)
	  {
		 return GTK_MESSAGE_ERROR;
	  }	 
	  else if(strcmp("GTK_MESSAGE_OTHER",pConstName)==0)
	  {
		 return GTK_MESSAGE_OTHER;
	  }	 
	  else if(strcmp("GTK_BUTTONS_NONE",pConstName)==0)
	  {
		 return GTK_BUTTONS_NONE;
	  }	 
	  else if(strcmp("GTK_BUTTONS_OK",pConstName)==0)
	  {
		 return GTK_BUTTONS_OK;
	  }	 
	  else if(strcmp("GTK_BUTTONS_CLOSE",pConstName)==0)
	  {
		 return GTK_BUTTONS_CLOSE;
	  }	 
	  else if(strcmp("GTK_BUTTONS_CANCEL",pConstName)==0)
	  {
		 return GTK_BUTTONS_CANCEL;
	  }	 
	  else if(strcmp("GTK_BUTTONS_YES_NO",pConstName)==0)
	  {
		 return GTK_BUTTONS_YES_NO;
	  }	 
	  else if(strcmp("GTK_BUTTONS_OK_CANCEL",pConstName)==0)
	  {
		 return GTK_BUTTONS_OK_CANCEL;
	  }	
	  else if(strcmp("GTK_RESPONSE_NONE",pConstName)==0)
	  {
		 return GTK_RESPONSE_NONE;
	  }	 
	  else if(strcmp("GTK_RESPONSE_REJECT",pConstName)==0)
	  {
		 return GTK_RESPONSE_REJECT;
	  }	 
	  else if(strcmp("GTK_RESPONSE_ACCEPT",pConstName)==0)
	  {
		 return GTK_RESPONSE_ACCEPT;
	  }	 
	  else if(strcmp("GTK_RESPONSE_DELETE_EVENT",pConstName)==0)
	  {
		 return GTK_RESPONSE_DELETE_EVENT;
	  }	 
	  else if(strcmp("GTK_RESPONSE_OK",pConstName)==0)
	  {
		 return GTK_RESPONSE_OK;
	  }	
	  else if(strcmp("GTK_RESPONSE_CANCEL",pConstName)==0)
	  {
		 return GTK_RESPONSE_CANCEL;
	  }	
	  else if(strcmp("GTK_RESPONSE_CLOSE",pConstName)==0)
	  {
		 return GTK_RESPONSE_CLOSE;
	  }	
	  else if(strcmp("GTK_RESPONSE_YES",pConstName)==0)
	  {
		 return GTK_RESPONSE_YES;
	  }	
	   else if(strcmp("GTK_RESPONSE_NO",pConstName)==0)
	  {
		 return GTK_RESPONSE_NO;
	  }	
	  else if(strcmp("GTK_RESPONSE_APPLY",pConstName)==0)
	  {
		 return GTK_RESPONSE_APPLY;
	  }	
	  else if(strcmp("GTK_RESPONSE_HELP",pConstName)==0)
	  {
		 return GTK_RESPONSE_HELP;
	  }	
	  else if(strcmp("GTK_ICON_SIZE_INVALID",pConstName)==0)
	  {
		 return GTK_ICON_SIZE_INVALID;
	  }
	   else if(strcmp("GTK_ICON_SIZE_MENU",pConstName)==0)
	  {
		 return GTK_ICON_SIZE_MENU;
	  }	
	   else if(strcmp("GTK_ICON_SIZE_SMALL_TOOLBAR",pConstName)==0)
	  {
		 return GTK_ICON_SIZE_SMALL_TOOLBAR;
	  }	
	    else if(strcmp("GTK_ICON_SIZE_LARGE_TOOLBAR",pConstName)==0)
	  {
		 return GTK_ICON_SIZE_LARGE_TOOLBAR;
	  }
	   else if(strcmp("GTK_ICON_SIZE_BUTTON",pConstName)==0)
	  {
		 return GTK_ICON_SIZE_BUTTON;
	  }	
	   else if(strcmp("GTK_ICON_SIZE_DND",pConstName)==0)
	  {
		 return GTK_ICON_SIZE_DND;
	  }	
	  else if(strcmp("GTK_ICON_SIZE_DIALOG",pConstName)==0)
	  {
		 return GTK_ICON_SIZE_DIALOG;
	  }	
	  else if(strcmp("GTK_POS_TOP",pConstName)==0)
	  {
		 return GTK_POS_TOP;
	  }		  
	  else if(strcmp("GTK_POS_BOTTOM",pConstName)==0)
	  {
		 return GTK_POS_BOTTOM;
	  }		  
	  else if(strcmp("GTK_POS_LEFT",pConstName)==0)
	  {
		 return GTK_POS_LEFT;
	  }		  
	  else if(strcmp("GTK_POS_RIGHT",pConstName)==0)
	  {
		 return GTK_POS_RIGHT;
	  }	

	  else if(strcmp("GTK_STATE_NORMAL",pConstName)==0)
	  {
		 return GTK_STATE_NORMAL;
	  }
	  else if(strcmp("GTK_STATE_ACTIVE",pConstName)==0)
	  {
		 return GTK_STATE_ACTIVE;
	  }
	  else if(strcmp("GTK_STATE_PRELIGHT",pConstName)==0)
	  {
		 return GTK_STATE_PRELIGHT;
	  }
	  else if(strcmp("GTK_STATE_SELECTED",pConstName)==0)
	  {
		 return GTK_STATE_SELECTED;
	  }
	  else if(strcmp("GTK_STATE_INSENSITIVE",pConstName)==0)
	  {
		 return GTK_STATE_INSENSITIVE;
	  }
	  else if(strcmp("GTK_ORIENTATION_HORIZONTAL",pConstName)==0)
	  {
		 return GTK_ORIENTATION_HORIZONTAL;
	  }
	  else if(strcmp("GTK_ORIENTATION_VERTICAL",pConstName)==0)
	  {
		 return GTK_ORIENTATION_VERTICAL;
	  }
	  else if(strcmp("GTK_FILE_CHOOSER_ACTION_OPEN",pConstName)==0)
	  {
		 return GTK_FILE_CHOOSER_ACTION_OPEN;
	  }
	  else if(strcmp("GTK_FILE_CHOOSER_ACTION_SAVE",pConstName)==0)
	  {
		 return GTK_FILE_CHOOSER_ACTION_SAVE;
	  } 
	  else if(strcmp("GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER",pConstName)==0)
	  {
		 return GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER;
	  }	  
	  else if(strcmp("GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER",pConstName)==0)
	  {
		 return GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER;
	  }
	  else if(strcmp("GDK_NOTHING",pConstName)==0)
	  {
		 return GDK_NOTHING;
	  }
	  else if(strcmp("GTK_WRAP_NONE",pConstName)==0)
	  {
		 return GTK_WRAP_NONE;
	  }
	   else if(strcmp("GTK_WRAP_CHAR",pConstName)==0)
	  {
		 return GTK_WRAP_CHAR;
	  }
	   else if(strcmp("GTK_WRAP_WORD",pConstName)==0)
	  {
		 return GTK_WRAP_WORD;
	  }
	   else if(strcmp("GTK_WRAP_WORD_CHAR",pConstName)==0)
	  {
		 return GTK_WRAP_WORD_CHAR;
	  }


	  else if(strcmp("GDK_SHIFT_MASK",pConstName)==0)
	  {
		 return GDK_SHIFT_MASK;
	  }
	  else if(strcmp("GDK_CONTROL_MASK",pConstName)==0)
	  {
		 return GDK_CONTROL_MASK;
	  }
	  else if(strcmp("GDK_DELETE",pConstName)==0)
	  {
		 return GDK_DELETE;
	  }
	  else if(strcmp("GDK_DESTROY",pConstName)==0)
	  {
		 return GDK_DESTROY;
	  }
	  else if(strcmp("GDK_EXPOSE",pConstName)==0)
	  {
		 return GDK_EXPOSE;
	  }
	  else if(strcmp("GDK_MOTION_NOTIFY",pConstName)==0)
	  {
		 return GDK_MOTION_NOTIFY;
	  }
	  else if(strcmp("GDK_BUTTON_PRESS",pConstName)==0)
	  {
		 return GDK_BUTTON_PRESS;
	  }
	  else if(strcmp("GDK_2BUTTON_PRESS",pConstName)==0)
	  {
		 return GDK_2BUTTON_PRESS;
	  }
	  else if(strcmp("GDK_DOUBLE_BUTTON_PRESS",pConstName)==0)
	  {
		 return GDK_DOUBLE_BUTTON_PRESS;
	  }
	  else if(strcmp("GDK_3BUTTON_PRESS",pConstName)==0)
	  {
		 return GDK_3BUTTON_PRESS;
	  }
	  else if(strcmp("GDK_TRIPLE_BUTTON_PRESS",pConstName)==0)
	  {
		 return GDK_TRIPLE_BUTTON_PRESS;
	  }
	  else if(strcmp("GDK_BUTTON_RELEASE",pConstName)==0)
	  {
		 return GDK_BUTTON_RELEASE;
	  }
	  else if(strcmp("GDK_KEY_PRESS",pConstName)==0)
	  {
		 return GDK_KEY_PRESS;
	  }
	  else if(strcmp("GDK_KEY_RELEASE",pConstName)==0)
	  {
		 return GDK_KEY_RELEASE;
	  }
	  else if(strcmp("GDK_ENTER_NOTIFY",pConstName)==0)
	  {
		 return GDK_ENTER_NOTIFY;
	  }
	  else if(strcmp("GDK_LEAVE_NOTIFY",pConstName)==0)
	  {
		 return GDK_LEAVE_NOTIFY;
	  }
	  else if(strcmp("GDK_FOCUS_CHANGE",pConstName)==0)
	  {
		 return GDK_FOCUS_CHANGE;
	  }
	  else if(strcmp("GDK_CONFIGURE",pConstName)==0)
	  {
		 return GDK_CONFIGURE;
	  }
	  else if(strcmp("GDK_MAP",pConstName)==0)
	  {
		 return GDK_MAP;
	  }
	  else if(strcmp("GDK_UNMAP",pConstName)==0)
	  {
		 return GDK_UNMAP;
	  }
	  else if(strcmp("GDK_PROPERTY_NOTIFY",pConstName)==0)
	  {
		 return GDK_PROPERTY_NOTIFY;
	  }
	  else if(strcmp("GDK_SELECTION_CLEAR",pConstName)==0)
	  {
		 return GDK_SELECTION_CLEAR;
	  }
	  else if(strcmp("GDK_SELECTION_REQUEST",pConstName)==0)
	  {
		 return GDK_SELECTION_REQUEST;
	  }
	  else if(strcmp("GDK_SELECTION_NOTIFY",pConstName)==0)
	  {
		 return GDK_SELECTION_NOTIFY;
	  }
	  else if(strcmp("GDK_PROXIMITY_IN",pConstName)==0)
	  {
		 return GDK_PROXIMITY_IN;
	  }
	  else if(strcmp("GDK_PROXIMITY_OUT",pConstName)==0)
	  {
		 return GDK_PROXIMITY_OUT;
	  }
	  else if(strcmp("GDK_DRAG_ENTER",pConstName)==0)
	  {
		 return GDK_DRAG_ENTER;
	  }
	  else if(strcmp("GDK_DRAG_LEAVE",pConstName)==0)
	  {
		 return GDK_DRAG_LEAVE;
	  }
	  else if(strcmp("GDK_DRAG_MOTION",pConstName)==0)
	  {
		 return GDK_DRAG_MOTION;
	  }
	  else if(strcmp("GDK_DRAG_STATUS",pConstName)==0)
	  {
		 return GDK_DRAG_STATUS;
	  }
	  else if(strcmp("GDK_DROP_START",pConstName)==0)
	  {
		 return GDK_DROP_START;
	  }
	  else if(strcmp("GDK_DROP_FINISHED",pConstName)==0)
	  {
		 return GDK_DROP_FINISHED;
	  }
	  else if(strcmp("GDK_CLIENT_EVENT",pConstName)==0)
	  {
		 return GDK_CLIENT_EVENT;
	  }
	  else if(strcmp("GDK_VISIBILITY_NOTIFY",pConstName)==0)
	  {
		 return GDK_VISIBILITY_NOTIFY;
	  }
	  else if(strcmp("GDK_SCROLL",pConstName)==0)
	  {
		 return GDK_SCROLL;
	  }
	  else if(strcmp("GDK_WINDOW_STATE",pConstName)==0)
	  {
		 return GDK_WINDOW_STATE;
	  }
	  else if(strcmp("GDK_SETTING",pConstName)==0)
	  {
		 return GDK_SETTING;
	  }
	  else if(strcmp("GDK_OWNER_CHANGE",pConstName)==0)
	  {
		 return GDK_OWNER_CHANGE;
	  }
	  else if(strcmp("GDK_GRAB_BROKEN",pConstName)==0)
	  {
		 return GDK_GRAB_BROKEN;
	  }
	  else if(strcmp("GDK_DAMAGE",pConstName)==0)
	  {
		 return GDK_DAMAGE;
	  }
	  else if(strcmp("GDK_TOUCH_BEGIN",pConstName)==0)
	  {
		 return GDK_TOUCH_BEGIN;
	  }
	  else if(strcmp("GDK_TOUCH_UPDATE",pConstName)==0)
	  {
		 return GDK_TOUCH_UPDATE;
	  }
	  else if(strcmp("GDK_TOUCH_END",pConstName)==0)
	  {
		 return GDK_TOUCH_END;
	  }
	  else if(strcmp("GDK_TOUCH_CANCEL",pConstName)==0)
	  {
		 return GDK_TOUCH_CANCEL;
	  }
	  else if(strcmp("MCI_MODE_NOT_READY",pConstName)==0)
	  {
		 return MCI_MODE_NOT_READY;
	  }
	  else if(strcmp("MCI_MODE_STOP",pConstName)==0)
	  {
		 return MCI_MODE_STOP;
	  }
	  else if(strcmp("MCI_MODE_PLAY",pConstName)==0)
	  {
		 return MCI_MODE_PLAY;
	  }
	  else if(strcmp("MCI_MODE_RECORD",pConstName)==0)
	  {
		 return MCI_MODE_RECORD;
	  }
	  else if(strcmp("MCI_MODE_SEEK",pConstName)==0)
	  {
		 return MCI_MODE_SEEK;
	  }
	  else if(strcmp("MCI_MODE_PAUSE",pConstName)==0)
	  {
		 return MCI_MODE_PAUSE;
	  }
	  else if(strcmp("MCI_MODE_OPEN",pConstName)==0)
	  {
		 return MCI_MODE_OPEN;
	  }
	  else if(strcmp("MCI_STATUS_ITEM",pConstName)==0)
	  {
		 return MCI_STATUS_ITEM;
	  }
	  else if(strcmp("MCI_STATUS_START",pConstName)==0)
	  {
		 return MCI_STATUS_START;
	  }
	  else if(strcmp("MCI_STATUS_LENGTH",pConstName)==0)
	  {
		 return MCI_STATUS_LENGTH;
	  }
	  else if(strcmp("MCI_STATUS_POSITION",pConstName)==0)
	  {
		 return MCI_STATUS_POSITION;
	  }
	  else if(strcmp("MCI_STATUS_NUMBER_OF_TRACKS",pConstName)==0)
	  {
		 return MCI_STATUS_NUMBER_OF_TRACKS;
	  }
	  else if(strcmp("MCI_STATUS_MODE",pConstName)==0)
	  {
		 return MCI_STATUS_MODE;
	  }
	  else if(strcmp("MCI_STATUS_MEDIA_PRESENT",pConstName)==0)
	  {
		 return MCI_STATUS_MEDIA_PRESENT;
	  }
	  else if(strcmp("MCI_STATUS_TIME_FORMAT",pConstName)==0)
	  {
		 return MCI_STATUS_TIME_FORMAT;
	  }
	  else if(strcmp("MCI_STATUS_READY",pConstName)==0)
	  {
		 return MCI_STATUS_READY;
	  }
	  else if(strcmp("MCI_STATUS_CURRENT_TRACK",pConstName)==0)
	  {
		 return MCI_STATUS_CURRENT_TRACK;
	  }
	  else if(strcmp("GTK_SELECTION_NONE",pConstName)==0)
	  {
		 return GTK_SELECTION_NONE;
	  }
	  else if(strcmp("GTK_SELECTION_SINGLE",pConstName)==0)
	  {
		 return GTK_SELECTION_SINGLE;
	  }
	  else if(strcmp("GTK_SELECTION_BROWSE",pConstName)==0)
	  {
		 return GTK_SELECTION_BROWSE;
	  }
	  else if(strcmp("GTK_SELECTION_MULTIPLE",pConstName)==0)
	  {
		 return GTK_SELECTION_MULTIPLE;
	  }
	  else if(strcmp("GDK_EXPOSURE_MASK",pConstName)==0)
	  {
		 return GDK_EXPOSURE_MASK;
	  }
	  else if(strcmp("GDK_POINTER_MOTION_MASK",pConstName)==0)
	  {
		 return GDK_POINTER_MOTION_MASK;
	  }
	  else if(strcmp("GDK_POINTER_MOTION_HINT_MASK",pConstName)==0)
	  {
		 return GDK_POINTER_MOTION_HINT_MASK;
	  }
	  else if(strcmp("GDK_BUTTON_MOTION_MASK",pConstName)==0)
	  {
		 return GDK_BUTTON_MOTION_MASK;
	  }
	  else if(strcmp("GDK_BUTTON1_MOTION_MASK",pConstName)==0)
	  {
		 return GDK_BUTTON1_MOTION_MASK;
	  }
	  else if(strcmp("GDK_BUTTON2_MOTION_MASK",pConstName)==0)
	  {
		 return GDK_BUTTON2_MOTION_MASK;
	  }
	  else if(strcmp("GDK_BUTTON3_MOTION_MASK",pConstName)==0)
	  {
		 return GDK_BUTTON3_MOTION_MASK;
	  }
	  else if(strcmp("GDK_BUTTON_PRESS_MASK",pConstName)==0)
	  {
		 return GDK_BUTTON_PRESS_MASK;
	  }
	  else if(strcmp("GDK_BUTTON_RELEASE_MASK",pConstName)==0)
	  {
		 return GDK_BUTTON_RELEASE_MASK;
	  }
	  else if(strcmp("GDK_KEY_PRESS_MASK",pConstName)==0)
	  {
		 return GDK_KEY_PRESS_MASK;
	  }
	  else if(strcmp("GDK_KEY_RELEASE_MASK",pConstName)==0)
	  {
		 return GDK_KEY_RELEASE_MASK;
	  }
	  else if(strcmp("GDK_ENTER_NOTIFY_MASK",pConstName)==0)
	  {
		 return GDK_ENTER_NOTIFY_MASK;
	  }
	  else if(strcmp("GDK_LEAVE_NOTIFY_MASK",pConstName)==0)
	  {
		 return GDK_LEAVE_NOTIFY_MASK;
	  }
	  else if(strcmp("GDK_FOCUS_CHANGE_MASK",pConstName)==0)
	  {
		 return GDK_FOCUS_CHANGE_MASK;
	  }
	  else if(strcmp("GDK_STRUCTURE_MASK",pConstName)==0)
	  {
		 return GDK_STRUCTURE_MASK;
	  }
	  else if(strcmp("GDK_PROPERTY_CHANGE_MASK",pConstName)==0)
	  {
		 return GDK_PROPERTY_CHANGE_MASK;
	  }
	  else if(strcmp("GDK_VISIBILITY_NOTIFY_MASK",pConstName)==0)
	  {
		 return GDK_VISIBILITY_NOTIFY_MASK;
	  }
	  else if(strcmp("GDK_PROXIMITY_IN_MASK",pConstName)==0)
	  {
		 return GDK_PROXIMITY_IN_MASK;
	  }
	  else if(strcmp("GDK_PROXIMITY_OUT_MASK",pConstName)==0)
	  {
		 return GDK_PROXIMITY_OUT_MASK;
	  }
	  else if(strcmp("GDK_SUBSTRUCTURE_MASK",pConstName)==0)
	  {
		 return GDK_SUBSTRUCTURE_MASK;
	  }
	  else if(strcmp("GDK_SCROLL_MASK",pConstName)==0)
	  {
		 return GDK_SCROLL_MASK;
	  }
	  else if(strcmp("GDK_TOUCH_MASK",pConstName)==0)
	  {
		 return GDK_TOUCH_MASK;
	  }
	  else if(strcmp("GDK_SMOOTH_SCROLL_MASK",pConstName)==0)
	  {
		 return GDK_SMOOTH_SCROLL_MASK;
	  }
	  else if(strcmp("GDK_ALL_EVENTS_MASK",pConstName)==0)
	  {
		 return GDK_ALL_EVENTS_MASK;
	  }
	  else
	  {
		  char errorMsg[512];
		  sprintf(errorMsg,"cannot find const %s",pConstName);
		  g_error(errorMsg);
	  }
	  (*env)->ReleaseStringUTFChars(env, name, pConstName);
  }

//判断str是否以substr结尾
int endsWith(char str[],char substr[] )
{
	//如果字串长过str，则肯定是FALSE
	if(strlen(substr)>strlen(str))
	{
		return FALSE;
	}
	//把主串的起始指针向后移动到和substr右对齐的位置
    str=str+(strlen(str)-strlen(substr));
    return strcmp(str,substr)==0;
}

struct CallBackInfo
{
	JNIEnv *env;
	jint signalId;
};

int onEventCallBack(GtkWidget *widget,GdkEvent *event,gpointer data)
{
	struct CallBackInfo * callbackInfo=(struct CallBackInfo *)data;
	JNIEnv *env = callbackInfo->env;
	int signalId = callbackInfo->signalId;

	//调用GCallBackManager的triggerSignal方法，把回调相关的widget和signalId发回去
	jclass cls = (*env)->FindClass(env,"com/rupeng/gtk4j/GCallBackManager");  
	jmethodID triggerSignal =  (*env)->GetStaticMethodID(env,cls,"triggerSignal","(III)V");
	(*env)->CallStaticIntMethod(env,cls,triggerSignal,(int)widget,(int)event,signalId);

	return 0;//后续事件继续处理，否则以TreeView举例：一旦监听双击事件，鼠标点击选择就不起作用了
}

void onCallBack(GtkWidget *widget,gpointer data)
{
	onEventCallBack(widget,NULL,data);//信号不传递event
}

gboolean draw_callback(GtkWidget *widget, cairo_t *cr, gpointer user_data)
{
	struct CallBackInfo * callbackInfo=(struct CallBackInfo *)user_data;
	JNIEnv *env = callbackInfo->env;
	int signalId = callbackInfo->signalId;

	//调用GCallBackManager的triggerSignal方法，把回调相关的widget和signalId发回去
	jclass cls = (*env)->FindClass(env,"com/rupeng/gtk4j/GCallBackManager");  
	jmethodID triggerSignal =  (*env)->GetStaticMethodID(env,cls,"triggerSignal","(III)V");
	(*env)->CallStaticIntMethod(env,cls,triggerSignal,(int)widget,(int)cr,signalId);
	return FALSE;
}

  JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1main
  (JNIEnv * env, jclass cls)
  {
	  gtk_main();
  }

  JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_g_1signal_1connect
  (JNIEnv * env, jclass cls, jint instance, jstring signalName,jint signalId)
  {
	  if(signalName==NULL)
	  {
		 jniThrowRuntimeException(env,"signalName不能为空");  
		 return;
	  }
	  char* pSignalName = (char*)(*env)->GetStringUTFChars(env, signalName, NULL);
	  struct CallBackInfo *callbackInfo=(struct CallBackInfo *)malloc(sizeof(struct CallBackInfo));
	  callbackInfo->env=env;
	  callbackInfo->signalId= signalId;

	  //把env和signalId保存到CallBackInfo中做为最后一个参数传递给g_signal_connect
	  //在onCallBack中就可以取出来env和signalId使用了

	  //按照GTK的约定，如果信号名字以event结尾，就是事件而不是信号
	  if(endsWith(pSignalName,"event"))
	  {
		  g_signal_connect(G_OBJECT(instance),pSignalName,G_CALLBACK(onEventCallBack),callbackInfo);
	  }
	  //GTK2中的expose_event不能用了，GTK3中要使用draw
	  else if(strcmp(pSignalName,"draw")==0)//shit，忘了==0了，意思全变了
	  {
		   g_signal_connect(G_OBJECT(instance),pSignalName,draw_callback,callbackInfo);
	  }
	  else
	  {
		  
	      g_signal_connect(G_OBJECT(instance),pSignalName,G_CALLBACK(onCallBack),callbackInfo);
	  }
	  (*env)->ReleaseStringUTFChars(env, signalName, pSignalName);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1main_1quit
  (JNIEnv * env, jclass cls)
{
	gtk_main_quit();
}

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1show
  (JNIEnv * env, jclass cls, jint widget)
  {
	 assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  gtk_widget_show(GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1hide
  (JNIEnv * env, jclass cls, jint widget)
  {
	 assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  gtk_widget_hide(GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1set_1can_1focus
  (JNIEnv * env, jclass cls, jint widget, jboolean can_focus)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	 gtk_widget_set_can_focus(GTK_WIDGET(widget),can_focus);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1can_1focus
  (JNIEnv * env, jclass cls, jint widget)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  return gtk_widget_get_can_focus(GTK_WIDGET(widget));
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1has_1focus
  (JNIEnv * env, jclass cls, jint widget)
  {
	 assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  return gtk_widget_has_focus(GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1grab_1focus
  (JNIEnv * env, jclass cls, jint widget)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  gtk_widget_grab_focus(GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1set_1visible
  (JNIEnv * env, jclass cls, jint widget, jboolean visible)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  gtk_widget_set_visible(GTK_WIDGET(widget),visible);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1visible
  (JNIEnv * env, jclass cls, jint widget)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  return gtk_widget_get_visible(GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1set_1sensitive
  (JNIEnv * env, jclass cls, jint widget, jboolean sensitive)
  {
	gtk_widget_set_sensitive(GTK_WIDGET(widget),sensitive);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1sensitive
  (JNIEnv * env, jclass cls, jint widget)
  {
	  return gtk_widget_get_sensitive(GTK_WIDGET(widget));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1parent
  (JNIEnv * env, jclass cls, jint widget)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  return (jint)gtk_widget_get_parent(GTK_WIDGET(widget));
  }
  JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1set_1parent
  (JNIEnv * env, jclass cls, jint widget, jint parent)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  assertType(env,GTK_IS_WIDGET(parent),"parent","GTK_widget");
	  gtk_widget_set_parent(GTK_WIDGET(widget),GTK_WIDGET(parent));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1toplevel
  (JNIEnv * env, jclass cls, jint widget)
  {
    assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	GtkWidget* ret = gtk_widget_get_toplevel(GTK_WIDGET(widget));
	return (int)ret;
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1is_1toplevel
  (JNIEnv * env, jclass cls, jint widget)
  {
	 assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	 return gtk_widget_is_toplevel(GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1set_1app_1paintable
  (JNIEnv * env, jclass cls, jint widget, jboolean paintable)
  {
	 assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
     gtk_widget_set_app_paintable(GTK_WIDGET(widget),paintable);
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1screen
  (JNIEnv * env, jclass cls, jint widget)
  {
     assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	 GdkScreen *screen = gtk_widget_get_screen(GTK_WIDGET(widget));
	 return (int)screen;
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1has_1screen
  (JNIEnv * env, jclass cls, jint widget)
  {
    assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	return gtk_widget_has_screen(GTK_WIDGET(widget));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1screen_1get_1width
  (JNIEnv * env, jclass cls, jint screen)
  {
	  assertType(env,GDK_IS_SCREEN(screen),"screen","GDK_SCREEN");
	 return gdk_screen_get_width(GDK_SCREEN(screen));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1screen_1get_1height
  (JNIEnv * env, jclass cls, jint screen)
  {
	  assertType(env,GDK_IS_SCREEN(screen),"screen","GDK_SCREEN");
	 return gdk_screen_get_height(GDK_SCREEN(screen));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1screen_1get_1default
  (JNIEnv * env, jclass cls)
  {
	  GdkScreen *screen = gdk_screen_get_default();
	  return (int)screen;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1screen_1get_1n_1monitors
  (JNIEnv * env, jclass cls, jint screen)
  {
	assertType(env,GDK_IS_SCREEN(screen),"screen","GDK_SCREEN");
	return gdk_screen_get_n_monitors(GDK_SCREEN(screen));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1screen_1get_1primary_1monitor
  (JNIEnv * env, jclass cls, jint screen)
  {
	  assertType(env,GDK_IS_SCREEN(screen),"screen","GDK_SCREEN");
	  return gdk_screen_get_primary_monitor(GDK_SCREEN(screen));
  }

JNIEXPORT jintArray JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1screen_1get_1monitor_1workarea
  (JNIEnv * env, jclass cls, jint screen, jint monitor_num)
  {
     assertType(env,GDK_IS_SCREEN(screen),"screen","GDK_SCREEN");
	 GdkRectangle rect={0};
	 gdk_screen_get_monitor_workarea(GDK_SCREEN(screen),monitor_num,&rect);
	jintArray result = (*env)->NewIntArray(env,4); // 新建一个jintArray 
	 int x=rect.x;
	 int y=rect.y;
	 int width = rect.width;
	 int height = rect.height;
	 int array[] = {x,y,width,height};
    (*env)->SetIntArrayRegion(env,result, 0, 4, array);
	return result;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1parent_1window
  (JNIEnv * env, jclass cls, jint widget)
  {
	assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	GtkWidget *temp=(GtkWidget *)widget;
	while(temp!=NULL)//避免使用递归
	{
	 if(GTK_IS_WINDOW(temp))
	  {
		 return (int)temp;
	  }
	  else
	  {
		  temp = gtk_widget_get_parent(temp);
	  }
	}
	return NULL;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1set_1size_1request
  (JNIEnv * env, jclass cls, jint widget, jint width, jint height)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  gtk_widget_set_size_request(GTK_WIDGET(widget),width,height);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1destroy
  (JNIEnv * env, jclass cls, jint widget)
  {
	 assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_widget");
	  gtk_widget_destroy(GTK_WIDGET(widget));
  }

  JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1set_1title
  (JNIEnv * env, jclass cls, jint window, jstring title)
  {
	  if(title==NULL)
	  {
		 jniThrowRuntimeException(env,"title不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  char* pTitle = (char*)(*env)->GetStringUTFChars(env, title, NULL);
	  gtk_window_set_title(GTK_WINDOW(window),pTitle);
	  (*env)->ReleaseStringUTFChars(env, title, pTitle);
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1get_1title
  (JNIEnv * env, jclass cls, jint window)
  {
	  assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  const char* title= gtk_window_get_title(GTK_WINDOW(window));
	  return (*env)->NewStringUTF(env, title);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1set_1resizable
  (JNIEnv * env, jclass cls, jint window, jboolean resizable)
  {
	  assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  gtk_window_set_resizable(GTK_WINDOW(window),resizable);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1get_1resizable
  (JNIEnv * env, jclass cls, jint window)
  {
	  assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  return gtk_window_get_resizable(GTK_WINDOW(window));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1set_1modal
  (JNIEnv * env, jclass cls, jint window, jboolean modal)
  {
	assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	gtk_window_set_modal(GTK_WINDOW(window),modal);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1set_1default_1size
  (JNIEnv * env, jclass cls, jint window, jint width, jint height)
  {
	 assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  gtk_window_set_default_size(GTK_WINDOW(window),width,height);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1set_1position
  (JNIEnv * env, jclass cls, jint window, jint position)
  {
	 assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	 gtk_window_set_position(GTK_WINDOW(window),position);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1fullscreen
  (JNIEnv * env, jclass cls, jint window)
  {
	  assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  gtk_window_fullscreen(GTK_WINDOW(window));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1unfullscreen
  (JNIEnv * env, jclass cls, jint window)
  {
	  assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  gtk_window_unfullscreen(GTK_WINDOW(window));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1window_1maximize
  (JNIEnv * env, jclass cls, jint window)
  {
	  assertType(env,GTK_IS_WINDOW(window),"window","GTK_WINDOW");
	  gtk_window_maximize(GTK_WINDOW(window));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1fixed_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_fixed_new();
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1fixed_1put
  (JNIEnv * env, jclass cls, jint fixed, jint widget, jint x, jint y)
  {
	  assertType(env,GTK_IS_FIXED(fixed),"fixed","GTK_FIXED");
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	  gtk_fixed_put(GTK_FIXED(fixed),GTK_WIDGET(widget),x,y);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1container_1add
  (JNIEnv * env, jclass cls, jint container, jint widget)
  {
	  assertType(env,GTK_IS_CONTAINER(container),"container","GTK_container");
	  gtk_container_add(GTK_CONTAINER(container),GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1container_1remove
  (JNIEnv * env, jclass cls, jint container, jint widget)
{
	assertType(env,GTK_IS_CONTAINER(container),"container","GTK_container");
	gtk_container_remove(GTK_CONTAINER(container),GTK_WIDGET(widget));
}


JNIEXPORT jintArray JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1container_1get_1children
  (JNIEnv * env, jclass cls, jint container)
  {
		assertType(env,GTK_IS_CONTAINER(container),"container","GTK_container");
		GList * children =gtk_container_get_children(GTK_CONTAINER(container));
		int count = g_list_length(children);

		jintArray result = (*env)->NewIntArray(env,count); // 新建一个jintArray 
		int i=0;
		for(;children;children=children->next)
		{
			GtkWidget *child = children->data;
			(*env)->SetIntArrayRegion(env,result, i, 1, (int)child);
			i++;
		}
		return result;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1label_1new
  (JNIEnv * env, jclass cls, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	 char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);
	 GtkWidget *newButton = gtk_label_new(pLabel);
	 (*env)->ReleaseStringUTFChars(env, label, pLabel);
	 return (int)newButton;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1label_1set_1text
  (JNIEnv * env, jclass cls, jint label, jstring str)
  {
	  if(str==NULL)
	  {
		 jniThrowRuntimeException(env,"str不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_LABEL(label),"label","GTK_LABEL");
	char* pLabel = (char*)(*env)->GetStringUTFChars(env, str, NULL);
	 gtk_label_set_text(GTK_LABEL(label),pLabel);
	 (*env)->ReleaseStringUTFChars(env, str, pLabel);
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1label_1get_1text
  (JNIEnv * env, jclass cls, jint label)
  {
	assertType(env,GTK_IS_LABEL(label),"label","GTK_LABEL");
	char* txt = gtk_label_get_text(GTK_LABEL(label));
	return strToJstring(env,txt);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1button_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkWidget *newLabel = gtk_button_new();
	  return (int)newLabel;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1button_1new_1with_1label
  (JNIEnv * env, jclass cls, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);
	  GtkWidget *newButton = gtk_button_new_with_label(pLabel);
	  (*env)->ReleaseStringUTFChars(env, label, pLabel);
	  return (int)newButton;
  }
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1button_1set_1label
  (JNIEnv * env, jclass cls, jint button, jstring label)
  {
	  assertType(env,GTK_IS_BUTTON(button),"button","GTK_button");
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);
	  gtk_button_set_label(GTK_BUTTON(button),pLabel);
	  (*env)->ReleaseStringUTFChars(env, label, pLabel);
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1button_1get_1label
  (JNIEnv * env, jclass cls, jint button)
  {
		assertType(env,GTK_IS_BUTTON(button),"button","GTK_button");
		char *label = gtk_button_get_label(GTK_BUTTON(button));
		return strToJstring(env,label);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1button_1set_1image
  (JNIEnv * env, jclass cls, jint button, jint widget)
  {
	assertType(env,GTK_IS_BUTTON(button),"button","GTK_button");
	assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");

	gtk_button_set_image(GTK_BUTTON(button),GTK_WIDGET(widget));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1button_1set_1image_1position
  (JNIEnv * env, jclass cls, jint button, jint position)
   {
	 assertType(env,GTK_IS_BUTTON(button),"button","GTK_button");
	 gtk_button_set_image_position(GTK_BUTTON(button),(GtkPositionType)position);
   }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1calendar_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_calendar_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1calendar_1select_1month
  (JNIEnv * env, jclass cls, jint calendar , jint month, jint year)
  {
	 assertType(env,GTK_IS_CALENDAR(calendar),"calendar","GTK_CALENDAR");
	 gtk_calendar_select_month(GTK_CALENDAR(calendar),month,year);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1calendar_1select_1day
  (JNIEnv * env, jclass cls, jint calendar, jint day)
  {
	assertType(env,GTK_IS_CALENDAR(calendar),"calendar","GTK_CALENDAR");
	 gtk_calendar_select_day(GTK_CALENDAR(calendar),day);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1calendar_1get_1year
  (JNIEnv * env, jclass cls, jint calendar)
  {
	  assertType(env,GTK_IS_CALENDAR(calendar),"calendar","GTK_CALENDAR");
	  int year,month,day;
	 gtk_calendar_get_date(GTK_CALENDAR(calendar),&year,&month,&day);
	 return year;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1calendar_1get_1month
  (JNIEnv * env, jclass cls, jint calendar)
  {
	  assertType(env,GTK_IS_CALENDAR(calendar),"calendar","GTK_CALENDAR");
	  int year,month,day;
	 gtk_calendar_get_date(GTK_CALENDAR(calendar),&year,&month,&day);
	 return month;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1calendar_1get_1day
  (JNIEnv * env, jclass cls, jint calendar)
  {
	  assertType(env,GTK_IS_CALENDAR(calendar),"calendar","GTK_CALENDAR");
	  int year,month,day;
	 gtk_calendar_get_date(GTK_CALENDAR(calendar),&year,&month,&day);
	 return day;
  }

  JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1box_1new
  (JNIEnv * env, jclass cls, jint orientation, jint spacing)
  {
	  return (int)gtk_box_new(orientation,spacing);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1box_1pack_1start
  (JNIEnv * env, jclass cls, jint box, jint child, jboolean expand, jboolean fill, jint padding)
  {
	  assertType(env,GTK_IS_BOX(box),"box","GTK_box");
	  gtk_box_pack_start(GTK_BOX(box),GTK_WIDGET(child),expand,fill,padding);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1box_1pack_1end
  (JNIEnv * env, jclass cls, jint box, jint child, jboolean expand, jboolean fill, jint padding)
  {
	  assertType(env,GTK_IS_BOX(box),"box","GTK_box");
	  gtk_box_pack_end(GTK_BOX(box),GTK_WIDGET(child),expand,fill,padding);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1box_1set_1homogeneous
  (JNIEnv * env, jclass cls, jint box, jboolean homogeneous)
  {
	  assertType(env,GTK_IS_BOX(box),"box","GTK_box");
	  gtk_box_set_homogeneous(GTK_BOX(box),homogeneous);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_grid_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1insert_1row
  (JNIEnv * env, jclass cls, jint grid, jint position)
  {
	  assertType(env,GTK_IS_GRID(grid),"grid","GTK_GRID");
	  gtk_grid_insert_row(GTK_GRID(grid),position);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1insert_1column
  (JNIEnv * env, jclass cls, jint grid, jint position)
  {
	  assertType(env,GTK_IS_GRID(grid),"grid","GTK_GRID");
	  gtk_grid_insert_column(GTK_GRID(grid),position);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1attach
  (JNIEnv * env, jclass cls, jint grid,jint child,jint left,jint top,jint width,jint height)
  {
	  assertType(env,GTK_IS_GRID(grid),"grid","GTK_GRID");
	  gtk_grid_attach(GTK_GRID(grid),GTK_WIDGET(child),left,top,width,height);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1set_1row_1spacing
  (JNIEnv * env, jclass cls, jint grid, jint spacing)
  {
	  assertType(env,GTK_IS_GRID(grid),"grid","GTK_GRID");
	  gtk_grid_set_row_spacing(GTK_GRID(grid),spacing);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1set_1column_1spacing
  (JNIEnv * env, jclass cls, jint grid, jint spacing)
  {
	  assertType(env,GTK_IS_GRID(grid),"grid","GTK_GRID");
	  gtk_grid_set_column_spacing(GTK_GRID(grid),spacing);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1set_1row_1homogeneous
  (JNIEnv * env, jclass cls, jint grid, jboolean homogeneous)
  {
	  assertType(env,GTK_IS_GRID(grid),"grid","GTK_GRID");
	 gtk_grid_set_row_homogeneous(GTK_GRID(grid),homogeneous);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1grid_1set_1column_1homogeneous
  (JNIEnv * env, jclass cls, jint grid, jboolean homogeneous)
  {
	 assertType(env,GTK_IS_GRID(grid),"grid","GTK_GRID");
	  gtk_grid_set_column_homogeneous(GTK_GRID(grid),homogeneous);
  }

  JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1message_1dialog_1new
  (JNIEnv * env, jclass cls, jint parent, jint flags, jint type, jint buttons, jstring message)
  {
	  if(parent!=NULL)
	  {
		assertType(env,GTK_IS_WINDOW(parent),"parent","GTK_WINDOW");
	  }
	  if(message==NULL)
	  {
		 jniThrowRuntimeException(env,"message不能为空");  
		 return;
	  }
	  char* pMessage = (char*)(*env)->GetStringUTFChars(env, message, NULL);  
	  GtkWidget *dialog = gtk_message_dialog_new(GTK_WINDOW(parent),flags,type,buttons,"%s",pMessage);
	  //因为gtk的gtk_message_dialog_new 的message_format参数是可以带占位符的，但是为了简单，java传递的是message
	  //因此为了避免麻烦，用%s进行格式化，避免了特殊字符转义的问题
	  (*env)->ReleaseStringUTFChars(env, message, pMessage);
	  return (int)dialog;
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1dialog_1run
  (JNIEnv * env, jclass cls, jint dialog)
  {
	  assertType(env,GTK_IS_DIALOG(dialog),"dialog","GTK_DIALOG");
	 gtk_dialog_run(GTK_DIALOG(dialog));
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1dialog_1response
  (JNIEnv * env, jclass cls, jint dialog, jint response_id)
  {
	  assertType(env,GTK_IS_DIALOG(dialog),"dialog","GTK_DIALOG");
	 gtk_dialog_response(GTK_DIALOG(dialog),response_id);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1dialog_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkDialog* dialog = gtk_dialog_new();
	  return (int)dialog;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1dialog_1new_1with_1buttons
  (JNIEnv * env, jclass cls, jstring title, jint parent, jint flags)
  {
	if(parent!=NULL)
	{
		assertType(env,GTK_IS_WINDOW(parent),"parent","GTK_WINDOW");
	}
	  if(title==NULL)
	  {
		 jniThrowRuntimeException(env,"title不能为空");  
		 return;
	  }
	char* pTitle = (char*)(*env)->GetStringUTFChars(env, title, NULL);  
	GtkDialog *dialog = gtk_dialog_new_with_buttons(pTitle,GTK_WINDOW(parent),(GtkDialogFlags)flags,NULL);
	(*env)->ReleaseStringUTFChars(env, title, pTitle);
	return (int)dialog;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1dialog_1get_1action_1area
  (JNIEnv * env, jclass cls, jint dialog)
  {
	   assertType(env,GTK_IS_DIALOG(dialog),"dialog","GTK_Dialog");
	  GtkWidget* actionArea = gtk_dialog_get_action_area(GTK_DIALOG(dialog));
	  return (int)actionArea;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1dialog_1get_1content_1area
  (JNIEnv * env, jclass cls, jint dialog)
  {
	   assertType(env,GTK_IS_DIALOG(dialog),"dialog","GTK_Dialog");
	  GtkWidget* contentArea = gtk_dialog_get_content_area(GTK_DIALOG(dialog));
	  return (int)contentArea;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1dialog_1add_1button
  (JNIEnv * env, jclass cls, jint dialog, jstring button_text, jint response_id)
  {
	  if(button_text==NULL)
	  {
		 jniThrowRuntimeException(env,"button_text不能为空");  
		 return;
	  }
	   assertType(env,GTK_IS_DIALOG(dialog),"dialog","GTK_Dialog");
	  char* pBtnText = (char*)(*env)->GetStringUTFChars(env, button_text, NULL);  
	  gtk_dialog_add_button(GTK_WIDGET(dialog),	pBtnText,response_id);
	  (*env)->ReleaseStringUTFChars(env, button_text, pBtnText);
	  return (int)dialog;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1check_1button_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_check_button_new();
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1check_1button_1new_1with_1label
  (JNIEnv * env, jclass cls, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	  GtkWidget *btn = gtk_check_button_new_with_label(pLabel);
	   (*env)->ReleaseStringUTFChars(env, label, pLabel);
	   return (int)btn;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1check_1button_1new_1with_1mnemonic
  (JNIEnv * env, jclass cls, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	  GtkWidget *btn = gtk_check_button_new_with_mnemonic(pLabel);
	   (*env)->ReleaseStringUTFChars(env, label, pLabel);
	   return (int)btn;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_combo_box_new();
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1get_1active
  (JNIEnv * env, jclass cls, jint combo_box)
  {
	  assertType(env,GTK_IS_COMBO_BOX(combo_box),"combo_box","GTK_COMBO_BOX");
	 return gtk_combo_box_get_active(GTK_COMBO_BOX(combo_box));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1set_1active
  (JNIEnv * env, jclass cls, jint combo_box, jint index)
  {
		assertType(env,GTK_IS_COMBO_BOX(combo_box),"combo_box","GTK_COMBO_BOX");
		gtk_combo_box_set_active(GTK_COMBO_BOX(combo_box),index);
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1get_1active_1id
  (JNIEnv * env, jclass cls, jint combo_box)
  {
	  assertType(env,GTK_IS_COMBO_BOX(combo_box),"combo_box","GTK_COMBO_BOX");
      const char * id = gtk_combo_box_get_active_id(GTK_COMBO_BOX(combo_box));
	  return (*env)->NewStringUTF(env, id);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1set_1active_1id
  (JNIEnv * env, jclass cls, jint combo_box, jstring active_id)
  {
	  if(active_id==NULL)
	  {
		 jniThrowRuntimeException(env,"active_id不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_COMBO_BOX(combo_box),"combo_box","GTK_COMBO_BOX");
	   char* pActive_id = (char*)(*env)->GetStringUTFChars(env, active_id, NULL);  
	  gtk_combo_box_set_active_id(GTK_COMBO_BOX(combo_box),pActive_id);
	   (*env)->ReleaseStringUTFChars(env, active_id, pActive_id);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1toggle_1button_1set_1active
  (JNIEnv * env, jclass cls, jint toggle_button, jboolean is_active)
  {
	  assertType(env,GTK_IS_TOGGLE_BUTTON(toggle_button),"toggle_button","GTK_TOGGLE_BUTTON");
	  gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(toggle_button),is_active);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1toggle_1button_1get_1active
  (JNIEnv * env, jclass cls, jint toggle_button)
  {
	  assertType(env,GTK_IS_TOGGLE_BUTTON(toggle_button),"toggle_button","GTK_TOGGLE_BUTTON");
	  return gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(toggle_button));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1toggle_1button_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkWidget *btn = gtk_toggle_button_new();
	  return (int)btn;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1toggle_1button_1new_1with_1label
  (JNIEnv * env, jclass cls, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	 char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	  GtkWidget *btn = gtk_toggle_button_new_with_label(pLabel);
	   (*env)->ReleaseStringUTFChars(env, label, pLabel);
	   return (int)btn;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1toggle_1button_1new_1with_1mnemonic
  (JNIEnv * env, jclass cls, jstring label)
    {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	 char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	  GtkWidget *btn = gtk_toggle_button_new_with_mnemonic(pLabel);
	   (*env)->ReleaseStringUTFChars(env, label, pLabel);
	   return (int)btn;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1text_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_combo_box_text_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1text_1append_1text
  (JNIEnv * env, jclass cls, jint combo_box, jstring text)
  {
	  if(text==NULL)
	  {
		 jniThrowRuntimeException(env,"text不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_COMBO_BOX_TEXT(combo_box),"combo_box","GTK_COMBO_BOX_TEXT");

	  char* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL);  
	  gtk_combo_box_text_append_text(GTK_COMBO_BOX_TEXT(combo_box),pText);
	  (*env)->ReleaseStringUTFChars(env, text, pText);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1text_1remove
  (JNIEnv * env, jclass cls, jint combo_box, jint position)
  {
	  assertType(env,GTK_IS_COMBO_BOX_TEXT(combo_box),"combo_box","GTK_COMBO_BOX_TEXT");
	  gtk_combo_box_text_remove(GTK_COMBO_BOX_TEXT(combo_box),position);
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1text_1get_1active_1text
  (JNIEnv * env, jclass cls, jint combo_box)
  {
	  assertType(env,GTK_IS_COMBO_BOX_TEXT(combo_box),"combo_box","GTK_COMBO_BOX_TEXT");
		const gchar *text = gtk_combo_box_text_get_active_text(GTK_COMBO_BOX_TEXT(combo_box));
		jstring str =   strToJstring(env,text);
		return str;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1text_1remove_1all
  (JNIEnv * env, jclass cls, jint combo_box)
  {
	  assertType(env,GTK_IS_COMBO_BOX_TEXT(combo_box),"combo_box","GTK_COMBO_BOX_TEXT");
	  gtk_combo_box_text_remove_all(GTK_COMBO_BOX_TEXT(combo_box));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1combo_1box_1text_1append
  (JNIEnv * env, jclass cls, jint combo_box, jstring id, jstring text)
  {
	  if(id==NULL)
	  {
		 jniThrowRuntimeException(env,"id不能为空");  
		 return;
	  }
	  if(text==NULL)
	  {
		 jniThrowRuntimeException(env,"text不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_COMBO_BOX_TEXT(combo_box),"combo_box","GTK_COMBO_BOX_TEXT");
	  char* pId = (char*)(*env)->GetStringUTFChars(env, id, NULL); 
	  char* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL); 
	  gtk_combo_box_text_append(GTK_COMBO_BOX_TEXT(combo_box),pId,pText);
	  (*env)->ReleaseStringUTFChars(env, id, pId);
      (*env)->ReleaseStringUTFChars(env, text, pText);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_entry_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1set_1max_1length
  (JNIEnv * env, jclass cls, jint entry, jint max)
  {
	  assertType(env,GTK_IS_ENTRY(entry),"entry","GTK_ENTRY");
	  gtk_entry_set_max_length(GTK_ENTRY(entry),max);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1get_1max_1length
  (JNIEnv * env, jclass cls, jint entry)
  {
	   assertType(env,GTK_IS_ENTRY(entry),"entry","GTK_ENTRY");
	  return  gtk_entry_get_max_length(GTK_ENTRY(entry));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1get_1text_1length
  (JNIEnv * env, jclass cls, jint entry)
  {
	  assertType(env,GTK_IS_ENTRY(entry),"entry","GTK_ENTRY");
	  return gtk_entry_get_text_length(GTK_ENTRY(entry));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1set_1text
  (JNIEnv * env, jclass cls, jint entry, jstring text)
  {
	  assertType(env,GTK_IS_ENTRY(entry),"entry","GTK_ENTRY");
	  if(text==NULL)
	  {
		  jniThrowNullPointerException(env,"text不能为NULL");
		   return;
	  }
	  gchar* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL);  
	  gtk_entry_set_text(GTK_ENTRY(entry),pText);
	  (*env)->ReleaseStringUTFChars(env, text, pText);	 
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1get_1text
  (JNIEnv * env, jclass cls, jint entry)
  {
	  assertType(env,GTK_IS_ENTRY(entry),"entry","GTK_ENTRY");
	 const gchar *text = gtk_entry_get_text(GTK_ENTRY(entry));
	 jstring str =  strToJstring(env,text);
	 return str;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1set_1invisible_1char
  (JNIEnv * env, jclass cls, jint entry, jchar inv_char)
  {
	  assertType(env,GTK_IS_ENTRY(entry),"entry","GTK_ENTRY");
	  gtk_entry_set_invisible_char(GTK_ENTRY(entry),inv_char);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1entry_1set_1visibility
  (JNIEnv * env, jclass cls, jint entry, jboolean visible)
  {
	  assertType(env,GTK_IS_ENTRY(entry),"entry","GTK_ENTRY");
	  gtk_entry_set_visibility(GTK_ENTRY(entry),visible);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1select_1region
  (JNIEnv * env, jclass cls, jint editable, jint start_pos, jint end_pos)
  {
		assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
		gtk_editable_select_region(GTK_EDITABLE(editable),start_pos,end_pos);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1insert_1text
  (JNIEnv * env, jclass cls, jint editable, jstring new_text,jint new_text_length)
  {
	  if(new_text==NULL)
	  {
		 jniThrowRuntimeException(env,"new_text不能为空");  
		 return;
	  }
	assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	gchar* pText = (char*)(*env)->GetStringUTFChars(env, new_text, NULL);  
	int position;
	gtk_editable_insert_text(GTK_EDITABLE(editable),pText,new_text_length,&position);
	(*env)->ReleaseStringUTFChars(env, new_text, pText);	 
	return position;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1delete_1text
  (JNIEnv * env, jclass cls, jint editable, jint start_pos, jint end_pos)
  {
	  assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	  gtk_editable_delete_text(GTK_EDITABLE(editable),start_pos,end_pos);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1cut_1clipboard
  (JNIEnv * env, jclass cls, jint editable)
  {
	  assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	  gtk_editable_cut_clipboard(GTK_EDITABLE(editable));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1copy_1clipboard
  (JNIEnv * env, jclass cls, jint editable)
  {
	  assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	  gtk_editable_copy_clipboard(GTK_EDITABLE(editable));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1paste_1clipboard
  (JNIEnv * env, jclass cls, jint editable)
  {
	  assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	  gtk_editable_paste_clipboard(GTK_EDITABLE(editable));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1delete_1selection
  (JNIEnv * env, jclass cls, jint editable)
  {
	  assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	  gtk_editable_delete_selection(GTK_EDITABLE(editable));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1set_1position
  (JNIEnv * env, jclass cls, jint editable, jint position)
  {
	assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	gtk_editable_set_position(GTK_EDITABLE(editable),position);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1get_1position
  (JNIEnv * env, jclass cls, jint editable)
  {
	  assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	  return gtk_editable_get_position(GTK_EDITABLE(editable));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1set_1editable
  (JNIEnv * env, jclass cls, jint editable, jboolean is_editable)
  {
	assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	gtk_editable_set_editable(GTK_EDITABLE(editable),is_editable);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1editable_1get_1editable
  (JNIEnv * env, jclass cls, jint editable)
  {
	 assertType(env,GTK_IS_EDITABLE(editable),"editable","GTK_EDITABLE");
	 return gtk_editable_get_editable(GTK_EDITABLE(editable));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1spin_1button_1new_1with_1range
  (JNIEnv * env, jclass cls, jdouble min, jdouble max, jdouble step)
  {
	  return gtk_spin_button_new_with_range(min,max,step);
  }

JNIEXPORT jdouble JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1spin_1button_1get_1value
  (JNIEnv * env, jclass cls, jint spin_button)
  {
     assertType(env,GTK_IS_SPIN_BUTTON(spin_button),"spin_button","GTK_SPIN_BUTTON");
	 return gtk_spin_button_get_value(GTK_SPIN_BUTTON(spin_button));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1spin_1button_1get_1value_1as_1int
  (JNIEnv * env, jclass cls, jint spin_button)
  {
	  assertType(env,GTK_IS_SPIN_BUTTON(spin_button),"spin_button","GTK_SPIN_BUTTON");
	  return gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(spin_button));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1spin_1button_1set_1value
  (JNIEnv * env, jclass cls, jint spin_button, jdouble value)
  {
      assertType(env,GTK_IS_SPIN_BUTTON(spin_button),"spin_button","GTK_SPIN_BUTTON");
	  gtk_spin_button_set_value(GTK_SPIN_BUTTON(spin_button),value);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1spin_1button_1set_1digits
  (JNIEnv * env, jclass cls, jint spin_button, jbyte digits)
  {
       assertType(env,GTK_IS_SPIN_BUTTON(spin_button),"spin_button","GTK_SPIN_BUTTON");
	   gtk_spin_button_set_digits(GTK_SPIN_BUTTON(spin_button),digits);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1image_1new
  (JNIEnv * env, jclass cls)
  {
	  return gtk_image_new();
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1image_1new_1from_1file
  (JNIEnv * env, jclass cls, jstring filename)
{
	  if(filename==NULL)
	  {
		 jniThrowRuntimeException(env,"filename不能为空");  
		 return;
	  }
	char* pFileName = (char*)(*env)->GetStringUTFChars(env, filename, NULL);  
	GtkWidget *img = gtk_image_new_from_file(pFileName);
	(*env)->ReleaseStringUTFChars(env, filename, pFileName);
	return (int)img;
}
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1image_1new_1from_1stock
  (JNIEnv * env, jclass cls, jstring stock_id, jint size)
{
	  if(stock_id==NULL)
	  {
		 jniThrowRuntimeException(env,"stock_id不能为空");  
		 return;
	  }
	char* pStock_id = (char*)(*env)->GetStringUTFChars(env, stock_id, NULL);  
	GtkWidget *img = gtk_image_new_from_stock(pStock_id,size);
	(*env)->ReleaseStringUTFChars(env, stock_id, pStock_id);
	return (int)img;
}

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1image_1clear
  (JNIEnv * env, jclass cls, jint image)
  {
	assertType(env,GTK_IS_IMAGE(image),"image","GTK_IMAGE");
	gtk_image_clear(GTK_IMAGE(image));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1image_1set_1from_1stock
  (JNIEnv * env, jclass cls, jint image, jstring stock_id,jint size)
  {
	  if(stock_id==NULL)
	  {
		 jniThrowRuntimeException(env,"stock_id不能为空");  
		 return;
	  }
	assertType(env,GTK_IS_IMAGE(image),"image","GTK_IMAGE");

	char* pStock_id = (char*)(*env)->GetStringUTFChars(env, stock_id, NULL);  
	gtk_image_set_from_stock(GTK_IMAGE(image),pStock_id,size);
	(*env)->ReleaseStringUTFChars(env, stock_id, pStock_id);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1image_1set_1from_1file
  (JNIEnv * env, jclass cls, jint image, jstring filename)
  {
	  if(filename==NULL)
	  {
		 jniThrowRuntimeException(env,"filename不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_IMAGE(image),"image","GTK_IMAGE");
	char* pFileName = (char*)(*env)->GetStringUTFChars(env, filename, NULL);  
	gtk_image_set_from_file(GTK_IMAGE(image),pFileName);
	(*env)->ReleaseStringUTFChars(env, filename, pFileName);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1radio_1button_1new
  (JNIEnv * env, jclass cls, jint group)
  {
	 return (int)gtk_radio_button_new((GSList*) group);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1radio_1button_1new_1with_1label
  (JNIEnv * env, jclass cls, jint group, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	GtkWidget *btn = gtk_radio_button_new_with_label((GSList*) group,pLabel);
	(*env)->ReleaseStringUTFChars(env, label, pLabel);
	return (int)btn;
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1radio_1button_1new_1with_1label_1from_1widget
  (JNIEnv * env, jclass cls, jint radio_group_member, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	GtkWidget *btn = gtk_radio_button_new_with_label_from_widget(GTK_RADIO_BUTTON(radio_group_member),pLabel);
	(*env)->ReleaseStringUTFChars(env, label, pLabel);
	return (int)btn;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1scale_1new_1with_1range
  (JNIEnv * env, jclass cls,jint orientation, jdouble min, jdouble max, jdouble step)
  {
	  return (int)gtk_scale_new_with_range(orientation,min,max,step);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1scale_1set_1draw_1value
  (JNIEnv * env, jclass cls, jint scale, jboolean draw_value)
  {
	  assertType(env,GTK_IS_SCALE(scale),"scale","GTK_SCALE");
		gtk_scale_set_draw_value(GTK_SCALE(scale),draw_value);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1scale_1get_1draw_1value
  (JNIEnv * env, jclass cls, jint scale)
  {
	  assertType(env,GTK_IS_SCALE(scale),"scale","GTK_SCALE");
	  return gtk_scale_get_draw_value(GTK_SCALE(scale));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1range_1set_1value
  (JNIEnv * env, jclass cls, jint scale, jdouble value)
  {
	  assertType(env,GTK_IS_SCALE(scale),"scale","GTK_SCALE");
	  gtk_range_set_value(GTK_SCALE(scale),value);
  }

JNIEXPORT jdouble JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1range_1get_1value
  (JNIEnv * env, jclass cls, jint scale)
  {
	  assertType(env,GTK_IS_RANGE(scale),"scale","GTK_RANGE");
	  return gtk_range_get_value(GTK_RANGE(scale));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1progress_1bar_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_progress_bar_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1progress_1bar_1set_1fraction
  (JNIEnv * env, jclass cls, jint pbar, jdouble fraction)
  {
	  assertType(env,GTK_IS_PROGRESS_BAR(pbar),"pbar","GTK_PROGRESS_BAR");
	  gtk_progress_bar_set_fraction(GTK_PROGRESS_BAR(pbar),fraction);
  }

JNIEXPORT jdouble JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1progress_1bar_1get_1fraction
  (JNIEnv * env, jclass cls, jint pbar)
  {
	assertType(env,GTK_IS_PROGRESS_BAR(pbar),"pbar","GTK_PROGRESS_BAR");
	return gtk_progress_bar_get_fraction(GTK_PROGRESS_BAR(pbar));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1progress_1bar_1set_1text
  (JNIEnv * env, jclass cls, jint pbar, jstring text)
  {
	  if(text==NULL)
	  {
		 jniThrowRuntimeException(env,"text不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_PROGRESS_BAR(pbar),"pbar","GTK_PROGRESS_BAR");
	  char* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL);  
	gtk_progress_bar_set_text(GTK_PROGRESS_BAR(pbar),pText);
	(*env)->ReleaseStringUTFChars(env, text, pText);
	  
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1progress_1bar_1get_1text
  (JNIEnv * env, jclass cls, jint pbar)
  {
	assertType(env,GTK_IS_PROGRESS_BAR(pbar),"pbar","GTK_PROGRESS_BAR");
	char *text = gtk_progress_bar_get_text(GTK_PROGRESS_BAR(pbar));
	return (*env)->NewStringUTF(env, text);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1progress_1bar_1set_1show_1text
  (JNIEnv * env, jclass cls, jint pbar, jboolean show_text)
  {
     assertType(env,GTK_IS_PROGRESS_BAR(pbar),"pbar","GTK_PROGRESS_BAR");
	gtk_progress_bar_set_show_text(GTK_PROGRESS_BAR(pbar),show_text);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1progress_1bar_1get_1show_1text
  (JNIEnv * env, jclass cls, jint pbar)
  {
	  assertType(env,GTK_IS_PROGRESS_BAR(pbar),"pbar","GTK_PROGRESS_BAR");
	return gtk_progress_bar_get_show_text(GTK_PROGRESS_BAR(pbar));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1notebook_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_notebook_new();
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1notebook_1append_1page
  (JNIEnv * env, jclass cls, jint notebook, jint child, jint tab_label)
  {
	  assertType(env,GTK_IS_NOTEBOOK(notebook),"notebook","GTK_NOTEBOOK");
	  assertType(env,GTK_IS_WIDGET(child),"child","GTK_WIDGET");
	  return gtk_notebook_append_page(GTK_NOTEBOOK(notebook),GTK_WIDGET(child),GTK_WIDGET(tab_label));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1notebook_1set_1tab_1pos
  (JNIEnv * env, jclass cls, jint notebook, jint position)
  {
	  assertType(env,GTK_IS_NOTEBOOK(notebook),"notebook","GTK_NOTEBOOK");
	  gtk_notebook_set_tab_pos(GTK_NOTEBOOK(notebook),position);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1bar_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_menu_bar_new();
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1item_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_menu_item_new();
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1item_1new_1with_1label
  (JNIEnv * env, jclass cls, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	GtkWidget *menuitem = gtk_menu_item_new_with_label(pLabel);
	(*env)->ReleaseStringUTFChars(env, label, pLabel);
	return (int)menuitem;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1item_1new_1with_1mnemonic
  (JNIEnv * env, jclass cls, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);  
	GtkWidget *menuitem = gtk_menu_item_new_with_mnemonic(pLabel);
	(*env)->ReleaseStringUTFChars(env, label, pLabel);
	return (int)menuitem;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1item_1set_1submenu
  (JNIEnv * env, jclass cls, jint menu_item, jint submenu)
  {
	  assertType(env,GTK_IS_MENU_ITEM(menu_item),"menu_item","GTK_MENU_ITEM");
	  assertType(env,GTK_IS_WIDGET(submenu),"submenu","GTK_WIDGET");
	  gtk_menu_item_set_submenu(GTK_MENU_ITEM(menu_item),GTK_WIDGET(submenu));
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_menu_new();
  }
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1shell_1append
  (JNIEnv * env, jclass cls, jint menu, jint child)
  {
	  assertType(env,GTK_IS_MENU_SHELL(menu),"menu","GTK_MENU_SHELL");
	  assertType(env,GTK_IS_WIDGET(child),"child","GTK_WIDGET");
	 gtk_menu_shell_append(GTK_MENU_SHELL(menu),GTK_WIDGET(child));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1attach_1to_1widget
  (JNIEnv * env, jclass cls, jint menu, jint attach_widget)
  {
	  assertType(env,GTK_IS_MENU(menu),"menu","GTK_MENU");
	  assertType(env,GTK_IS_WIDGET(attach_widget),"attach_widget","GTK_WIDGET");
	  gtk_menu_attach_to_widget(GTK_MENU(menu),GTK_WIDGET(attach_widget),NULL);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1popup
  (JNIEnv * env, jclass cls, jint menu)
  {
	   assertType(env,GTK_IS_MENU(menu),"menu","GTK_MENU");
	  gtk_menu_popup (GTK_MENU (menu), NULL, NULL, NULL, NULL,0, 0);
  }
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1override_1background_1color
  (JNIEnv * env, jclass cls, jint widget, jint state, jint red, jint green, jint blue)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	  GdkRGBA color={0};
	  color.red= red;
	  color.green = green;
	  color.blue = blue;
	  color.alpha=255;//要设置，如果用默认值0的话，就不会有效果
	  gtk_widget_override_background_color(GTK_WIDGET(widget),state,&color);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1override_1color
  (JNIEnv * env, jclass cls, jint widget, jint state, jint red, jint green, jint blue)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");

	  GdkRGBA color={0};
	  color.red= red;
	  color.green = green;
	  color.blue = blue;
	  color.alpha=255;//要设置，如果用默认值0的话，就不会有效果
	  gtk_widget_override_color(GTK_WIDGET(widget),state,&color);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1scrolled_1window_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkWidget * scrolled_win = gtk_scrolled_window_new(NULL,NULL);
	  return (int)scrolled_win;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1scrolled_1window_1add_1with_1viewport
  (JNIEnv * env, jclass cls, jint scrolled_window, jint child)
  {
	  assertType(env,GTK_IS_SCROLLED_WINDOW(scrolled_window),"scrolled_window","GTK_SCROLLED_WINDOW");
	  gtk_scrolled_window_add_with_viewport(GTK_SCROLLED_WINDOW(scrolled_window),GTK_WIDGET(child));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1iter_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkTreeIter *iter = (GtkTreeIter *)malloc(sizeof(GtkTreeIter));
	  memset(iter,0,sizeof(GtkTreeIter));
	  return (int)iter;
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1iter_1free
  (JNIEnv * env, jclass cls,jint iter)
  {
       gtk_tree_iter_free((GtkTreeIter *)iter);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1model_1get_1iter_1first
  (JNIEnv * env, jclass cls, jint tree_model, jint iter)
  {
	  assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	  gtk_tree_model_get_iter_first(GTK_TREE_MODEL(tree_model),(GtkTreeIter *)iter);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1model_1iter_1previous
  (JNIEnv * env, jclass cls, jint tree_model, jint iter)
  {
	  assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	  gtk_tree_model_iter_previous(GTK_TREE_MODEL(tree_model),(GtkTreeIter *)iter);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1model_1iter_1next
  (JNIEnv * env, jclass cls, jint tree_model, jint iter)
  {
	  assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	  gtk_tree_model_iter_next(GTK_TREE_MODEL(tree_model),(GtkTreeIter *)iter);
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1model_1get_1value
  (JNIEnv * env, jclass cls, jint tree_model, jint iter, jint column)
  {
      assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	  GValue value = {0};
	  gtk_tree_model_get_value(GTK_TREE_MODEL(tree_model),(GtkTreeIter *)iter,column,&value);
	  const char * buffer = g_value_get_string(&value);
	  return (*env)->NewStringUTF(env, buffer);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1list_1store_1iter_1is_1valid
  (JNIEnv * env, jclass cls, jint tree_model, jint iter)
  {
    assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	return gtk_tree_store_iter_is_valid(GTK_TREE_MODEL(tree_model),(GtkTreeIter *)iter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1store_1append
  (JNIEnv * env, jclass cls, jint tree_model, jint iter, jint parentIter)
  {
	  assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	  gtk_tree_store_append(GTK_TREE_MODEL(tree_model),(GtkTreeIter *)iter,(GtkTreeIter *)parentIter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1store_1set_1value
  (JNIEnv * env, jclass cls, jint tree_model, jint iter, jint column, jstring value)
  {
	  if(value==NULL)
	  {
		 jniThrowRuntimeException(env,"value不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	char* pValue = (char*)(*env)->GetStringUTFChars(env, value, NULL);  
	GValue gvalue = {0};
	g_value_init(&gvalue, G_TYPE_STRING);
	g_value_set_string(&gvalue, pValue);

	gtk_tree_store_set_value(GTK_TREE_STORE(tree_model),(GtkTreeIter  *)iter,column,&gvalue);
	(*env)->ReleaseStringUTFChars(env, value, pValue);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1store_1clear
  (JNIEnv * env, jclass cls, jint tree_model)
  {
	  assertType(env,GTK_IS_TREE_MODEL(tree_model),"tree_model","GTK_TREE_MODEL");
	  gtk_tree_store_clear(GTK_TREE_MODEL(tree_model));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1list_1store_1new
  (JNIEnv * env, jclass cls, jint n_columns)
  {
	  GType *colTypes = (GType *)malloc(sizeof(GType)*n_columns);
	  int i;
	  for(i=0;i<n_columns;i++)
	  {
		colTypes[i] = G_TYPE_STRING;
	  }
	  return (int)gtk_list_store_newv(n_columns,colTypes);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1store_1new
  (JNIEnv * env, jclass cls, jint n_columns)
  {
	  GType *colTypes = (GType *)malloc(sizeof(GType)*n_columns);
	  int i;
	  for(i=0;i<n_columns;i++)
	  {
		colTypes[i] = G_TYPE_STRING;
	  }
	  return (int)gtk_tree_store_newv(n_columns,colTypes);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1list_1store_1append
  (JNIEnv * env, jclass cls, jint list_store, jint iter)
  {
	  assertType(env,GTK_IS_LIST_STORE(list_store),"list_store","GTK_LIST_STORE");
	 gtk_list_store_append(GTK_LIST_STORE(list_store),(GtkTreeIter  *)iter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1list_1store_1set_1value
  (JNIEnv * env, jclass cls, jint list_store, jint iter, jint column, jstring value)
  {
	  if(value==NULL)
	  {
		 jniThrowRuntimeException(env,"value不能为空");  
		 return;
	  }
	assertType(env,GTK_IS_LIST_STORE(list_store),"list_store","GTK_LIST_STORE");
	char* pValue = (char*)(*env)->GetStringUTFChars(env, value, NULL);  
	GValue gvalue = {0};
	g_value_init(&gvalue, G_TYPE_STRING);
	g_value_set_string(&gvalue, pValue);

	gtk_list_store_set_value(GTK_LIST_STORE(list_store),(GtkTreeIter  *)iter,column,&gvalue);
	(*env)->ReleaseStringUTFChars(env, value, pValue);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1list_1store_1clear
  (JNIEnv * env, jclass cls, jint list_store)
  {
	  assertType(env,GTK_IS_LIST_STORE(list_store),"list_store","GTK_LIST_STORE");
	  gtk_list_store_clear(GTK_LIST_STORE(list_store));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1cell_1renderer_1text_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_cell_renderer_text_new();
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1new_1with_1attributes
  (JNIEnv * env, jclass cls, jstring title , jint cellrender,jint columnIndex)
  {
	  if(title==NULL)
	  {
		 jniThrowRuntimeException(env,"title不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_CELL_RENDERER(cellrender),"cellrender","GTK_CELL_RENDERER");

	char* pTitle = (char*)(*env)->GetStringUTFChars(env, title, NULL);  
	//不设定"text",0,NULL  会显示不出来
	GtkTreeViewColumn * col= gtk_tree_view_column_new_with_attributes(pTitle,GTK_CELL_RENDERER(cellrender),
		"text",columnIndex,NULL);
	(*env)->ReleaseStringUTFChars(env, title, pTitle);	 
	return (int)col;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1set_1visible
  (JNIEnv * env, jclass cls, jint tree_column, jboolean visible)
  {
	assertType(env,GTK_IS_TREE_VIEW_COLUMN(tree_column),"tree_column","GTK_TREE_VIEW_COLUMN");
	gtk_tree_view_column_set_visible(GTK_TREE_VIEW_COLUMN(tree_column),visible);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1get_1visible
  (JNIEnv * env, jclass cls, jint tree_column)
  {
	  assertType(env,GTK_IS_TREE_VIEW_COLUMN(tree_column),"tree_column","GTK_TREE_VIEW_COLUMN");
	  return gtk_tree_view_column_get_visible(GTK_TREE_VIEW_COLUMN(tree_column));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1set_1resizable
  (JNIEnv * env, jclass cls, jint tree_column, jboolean resizable)
  {
	  assertType(env,GTK_IS_TREE_VIEW_COLUMN(tree_column),"tree_column","GTK_TREE_VIEW_COLUMN");
	  return gtk_tree_view_column_set_resizable(GTK_TREE_VIEW_COLUMN(tree_column),resizable);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1get_1resizable
  (JNIEnv * env, jclass cls, jint tree_column)
  {
	 assertType(env,GTK_IS_TREE_VIEW_COLUMN(tree_column),"tree_column","GTK_TREE_VIEW_COLUMN");
	 return gtk_tree_view_column_get_resizable(GTK_TREE_VIEW_COLUMN(tree_column));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1set_1reorderable
  (JNIEnv * env, jclass cls, jint tree_column, jboolean reorderable)
  {
	  assertType(env,GTK_IS_TREE_VIEW_COLUMN(tree_column),"tree_column","GTK_TREE_VIEW_COLUMN");
	  gtk_tree_view_column_set_reorderable(GTK_TREE_VIEW_COLUMN(tree_column),reorderable);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1get_1reorderable
  (JNIEnv * env, jclass cls, jint tree_column)
  {
	  assertType(env,GTK_IS_TREE_VIEW_COLUMN(tree_column),"tree_column","GTK_TREE_VIEW_COLUMN");
	  return gtk_tree_view_column_get_reorderable(GTK_TREE_VIEW_COLUMN(tree_column));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1column_1set_1sort_1column_1id
  (JNIEnv * env, jclass cls, jint tree_column, jint sort_column_id)
  {
	  assertType(env,GTK_IS_TREE_VIEW_COLUMN(tree_column),"tree_column","GTK_TREE_VIEW_COLUMN");
	  gtk_tree_view_column_set_sort_column_id(GTK_TREE_VIEW_COLUMN(tree_column),sort_column_id);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1append_1column
  (JNIEnv * env, jclass cls, jint tree_view, jint column)
  {
	  assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");
	  assertType(env,GTK_IS_TREE_VIEW_COLUMN(column),"column","GTK_TREE_VIEW_COLUMN");
	  return gtk_tree_view_append_column(GTK_TREE_VIEW(tree_view),GTK_TREE_VIEW_COLUMN(column));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1remove_1column
  (JNIEnv * env, jclass cls, jint tree_view, jint column)
  {
	  assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");
	  assertType(env,GTK_IS_TREE_VIEW_COLUMN(column),"column","GTK_TREE_VIEW_COLUMN");
	  //MessageBox(0,G_OBJECT_CLASS_NAME(G_TYPE_FROM_INSTANCE(column)),G_OBJECT_CLASS_NAME(G_TYPE_FROM_INSTANCE(column)),0);
	  gtk_tree_view_remove_column(GTK_TREE_VIEW(tree_view),GTK_TREE_VIEW_COLUMN(column));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1get_1n_1columns
  (JNIEnv * env, jclass cls, jint tree_view)
  {
	  assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");
	  return gtk_tree_view_get_n_columns(GTK_TREE_VIEW(tree_view));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1get_1column
  (JNIEnv * env, jclass cls, jint tree_view, jint n)
  {
	  return (int)gtk_tree_view_get_column(GTK_TREE_VIEW(tree_view),n);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1set_1model
  (JNIEnv * env, jclass cls, jint tree_view, jint model)
  {
	   assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");
	  assertType(env,GTK_IS_TREE_MODEL(model),"model","GTK_TREE_MODEL");
	gtk_tree_view_set_model(GTK_TREE_VIEW(tree_view),GTK_TREE_MODEL(model));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_tree_view_new();
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1get_1headers_1visible
  (JNIEnv * env, jclass cls, jint tree_view)
  {
	  assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");
      return gtk_tree_view_get_headers_visible(GTK_TREE_VIEW(tree_view));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1set_1headers_1visible
  (JNIEnv * env, jclass cls, jint tree_view, jboolean headers_visible)
  {
	  assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");
      gtk_tree_view_set_headers_visible(GTK_TREE_VIEW(tree_view),headers_visible);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1get_1selection
  (JNIEnv * env, jclass cls, jint tree_view)
  {
	  assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");
	  GtkTreeSelection *selection;
	  selection = gtk_tree_view_get_selection(GTK_TREE_VIEW(tree_view));
	  return (int)selection;
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1selection_1set_1mode
  (JNIEnv * env, jclass cls, jint tree_selection, jint mode)
  {
	  assertType(env,GTK_IS_TREE_SELECTION(tree_selection),"tree_selection","GTK_TREE_SELECTION");
      gtk_tree_selection_set_mode(GTK_TREE_SELECTION(tree_selection),mode);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1selection_1get_1mode
  (JNIEnv * env, jclass cls, jint tree_selection)
  {
     assertType(env,GTK_IS_TREE_SELECTION(tree_selection),"tree_selection","GTK_TREE_SELECTION");
	 return gtk_tree_selection_get_mode(GTK_TREE_SELECTION(tree_selection));
  }

JNIEXPORT jintArray JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1get_1selection_1indices
  (JNIEnv * env, jclass cls, jint tree_view)
  {
	assertType(env,GTK_IS_TREE_VIEW(tree_view),"tree_view","GTK_TREE_VIEW");

	GtkTreeSelection *tsel = gtk_tree_view_get_selection (GTK_TREE_VIEW(tree_view));

	/*
	GtkTreeIter iter ;
	GtkTreeModel * tm ;
	GtkTreePath * path ;

	int selectCount = gtk_tree_selection_count_selected_rows(tsel);
	jintArray result = (*env)->NewIntArray(env,selectCount); // 新建一个jintArray 

	jint * buf ;

	//根据gtktreeselection.h中对gtk_tree_selection_get_selected的描述： Only meaningful if GTK_SELECTION_SINGLE or GTK_SELECTION_BROWSE is set
	//因此如果是GTK_SELECTION_MULTIPLE执行gtk_tree_selection_get_selected就会报错，因此换用下面的gtk_tree_selection_get_selected_rows
	//这样就可以同时支持Single和Multiple了
	if ( gtk_tree_selection_get_selected ( tsel , &tm , &iter ) )
	{
		path = gtk_tree_model_get_path ( tm , &iter ) ;
		buf = gtk_tree_path_get_indices ( path ) ;
		
		(*env)->SetIntArrayRegion(env,result, 0, selectCount, buf); // 将buf转存入result
	}
	return result;*/

	int selectCount = gtk_tree_selection_count_selected_rows(tsel);//获得选择的行数
	jintArray result = (*env)->NewIntArray(env,selectCount); // 新建一个jintArray 

	GList * list = gtk_tree_selection_get_selected_rows(tsel,NULL);
	int i=0;
	for(; list; list = list->next) 
	{
	  GtkTreePath * path = ( GtkTreePath *)list->data;
	  jint * buf ;//选中行的行号
	  buf = gtk_tree_path_get_indices ( path ) ;//这种方法只适合普通的ListView风格的，这种风格下一个TreePath只有一个选择项，buf的长度一直只有一个元素
	  (*env)->SetIntArrayRegion(env,result, i, 1, buf); // 将buf转存入result
	  i++;
	}
	return result;
  }
 
 JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1dialog_1new
  (JNIEnv * env, jclass cls, jstring title, jint parentWindow, jint action, jstring first_button_text)
  {
	  if(title==NULL)
	  {
		 jniThrowRuntimeException(env,"title不能为空");  
		 return;
	  }
	  if(first_button_text==NULL)
	  {
		 jniThrowRuntimeException(env,"first_button_text不能为空");  
		 return;
	  }
	  if(parentWindow!=NULL)
	  {
		assertType(env,GTK_IS_WINDOW(parentWindow),"parentWindow","GTK_WINDOW");
	  }

	 char* pTitle = (char*)(*env)->GetStringUTFChars(env, title, NULL);  
	 char* pFirst_button_text = (char*)(*env)->GetStringUTFChars(env, first_button_text, NULL);  
	 //pFirst_button_text, GTK_RESPONSE_OK是一组，"取消",GTK_RESPONSE_CANCEL是一组
	 //点击前一个按钮对话框的返回值就是第二个值
	 //必须以一个NULL结尾，否则会出来一堆按钮，或者报错
	 GtkWidget *dialog =gtk_file_chooser_dialog_new(pTitle,GTK_WINDOW(parentWindow),action,pFirst_button_text, GTK_RESPONSE_OK,
		 "取消",GTK_RESPONSE_CANCEL,NULL);
	(*env)->ReleaseStringUTFChars(env, title, pTitle);	
	(*env)->ReleaseStringUTFChars(env, first_button_text, pFirst_button_text);	  
	return (int)dialog;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1set_1select_1multiple
  (JNIEnv * env, jclass cls, jint chooser, jboolean select_multiple)
  {
	   assertType(env,GTK_IS_FILE_CHOOSER(chooser),"chooser","GTK_FILE_CHOOSER");
		gtk_file_chooser_set_select_multiple(GTK_FILE_CHOOSER(chooser),select_multiple);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1set_1current_1name
  (JNIEnv * env, jclass cls, jint chooser, jstring name)
  {
	  if(name==NULL)
	  {
		 jniThrowRuntimeException(env,"name不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_FILE_CHOOSER(chooser),"chooser","GTK_FILE_CHOOSER");
		char* pName = (char*)(*env)->GetStringUTFChars(env, name, NULL);  
		gtk_file_chooser_set_current_name(GTK_FILE_CHOOSER(chooser),pName);
		(*env)->ReleaseStringUTFChars(env, name, pName);	 
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1get_1filename
  (JNIEnv * env, jclass cls, jint chooser)
  {
	  assertType(env,GTK_IS_FILE_CHOOSER(chooser),"chooser","GTK_FILE_CHOOSER");
	char *filename = gtk_file_chooser_get_filename(GTK_FILE_CHOOSER(chooser));
	return strToJstring(env,filename);
  }

JNIEXPORT jobjectArray JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1get_1filenames
  (JNIEnv * env, jclass cls, jint chooser)
  {
	  assertType(env,GTK_IS_FILE_CHOOSER(chooser),"chooser","GTK_FILE_CHOOSER");

	GList *list = gtk_file_chooser_get_filenames(GTK_FILE_CHOOSER(chooser));
	int len = g_list_length(list);
	jobjectArray result = (*env)->NewObjectArray(env,len,
		(*env)->FindClass(env,"java/lang/String"),
		(*env)->NewStringUTF(env,"")); // 新建一个jintArray 
	int i;
	for(i=0;i<len;i++)
	{
		char *buf = g_list_nth_data(list,i);
		(*env)->SetObjectArrayElement(env,result,i,strToJstring(env,buf));
	}
	return result;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1set_1do_1overwrite_1confirmation
  (JNIEnv * env, jclass cls, jint chooser, jboolean do_overwrite_confirmation)
  {
	  assertType(env,GTK_IS_FILE_CHOOSER(chooser),"chooser","GTK_FILE_CHOOSER");
	 gtk_file_chooser_set_do_overwrite_confirmation(GTK_FILE_CHOOSER(chooser),do_overwrite_confirmation);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1set_1create_1folders
  (JNIEnv * env, jclass cls, jint chooser, jboolean create_folders)
  {
	  assertType(env,GTK_IS_FILE_CHOOSER(chooser),"chooser","GTK_FILE_CHOOSER");
	  gtk_file_chooser_set_create_folders(GTK_FILE_CHOOSER(chooser),create_folders);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1filter_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_file_filter_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1filter_1set_1name
  (JNIEnv * env, jclass cls, jint filter, jstring name)
  {
	  if(name==NULL)
	  {
		 jniThrowRuntimeException(env,"name不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_FILE_FILTER(filter),"filter","GTK_FILE_FILTER");

	char* pName = (char*)(*env)->GetStringUTFChars(env, name, NULL);  
	gtk_file_filter_set_name(GTK_FILE_FILTER(filter),pName);
	(*env)->ReleaseStringUTFChars(env, name, pName);	 
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1filter_1add_1pattern
  (JNIEnv * env, jclass cls, jint filter, jstring pattern)
  {
	  if(pattern==NULL)
	  {
		 jniThrowRuntimeException(env,"pattern不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_FILE_FILTER(filter),"filter","GTK_FILE_FILTER");

	char* pPattern = (char*)(*env)->GetStringUTFChars(env, pattern, NULL);  
	gtk_file_filter_add_pattern(GTK_FILE_FILTER(filter),pPattern);
	(*env)->ReleaseStringUTFChars(env, pattern, pPattern);	 
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1file_1chooser_1add_1filter
  (JNIEnv * env, jclass cls, jint chooser, jint filter)
  {
	  assertType(env,GTK_IS_FILE_CHOOSER(chooser),"chooser","GTK_FILE_CHOOSER");
	gtk_file_chooser_add_filter(GTK_FILE_CHOOSER(chooser),GTK_FILE_FILTER(filter));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1switch_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_switch_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1switch_1set_1active
  (JNIEnv * env, jclass cls, jint sw, jboolean is_active)
  {
	  assertType(env,GTK_IS_SWITCH(sw),"sw","GTK_COLOR_BUTTON");
	  gtk_switch_set_active(GTK_SWITCH(sw),is_active);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1switch_1get_1active
  (JNIEnv * env, jclass cls, jint sw)
  {
	  assertType(env,GTK_IS_SWITCH(sw),"sw","GTK_COLOR_BUTTON");
	  return gtk_switch_get_active(GTK_SWITCH(sw));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1iter_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkTextIter *iter = (GtkTextIter *)malloc(sizeof(GtkTextIter));
	  return (int)iter;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1iter_1free
  (JNIEnv * env, jclass cls, jint iter)
  {
      gtk_text_iter_free((GtkTextIter *)iter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1iter_1forward_1to_1end
  (JNIEnv * env, jclass cls, jint iter)
  {
	  gtk_text_iter_forward_to_end((GtkTextIter *)iter);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1iter_1get_1line
  (JNIEnv * env, jclass cls, jint iter)
  {
      gtk_text_iter_get_line((GtkTextIter *)iter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1iter_1forward_1char
  (JNIEnv * env, jclass cls, jint iter)
  {
	  gtk_text_iter_forward_char((GtkTextIter *)iter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1iter_1backward_1char
  (JNIEnv * env, jclass cls, jint iter)
  {
	   gtk_text_iter_backward_char((GtkTextIter *)iter);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1view_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkWidget* txt = gtk_text_view_new();	  
	  return (int)txt;
  }

/*
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1view_1set_1buffer
  (JNIEnv * env, jclass cls, jint text_view, jint buffer)
  {
	  assertType(env,GTK_IS_TEXT_VIEW(text_view),"text_view","GTK_TEXT_VIEW");
	  assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	  gtk_text_view_set_buffer(GTK_TEXT_VIEW(text_view),GTK_TEXT_BUFFER(buffer));
  }
*/

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1view_1get_1buffer
  (JNIEnv * env, jclass cls, jint text_view)
  {
	  assertType(env,GTK_IS_TEXT_VIEW(text_view),"text_view","GTK_TEXT_VIEW");
	  GtkTextBuffer * buffer= gtk_text_view_get_buffer(GTK_TEXT_VIEW(text_view));
	  return (int)buffer;
  }

JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1get_1text
  (JNIEnv * env, jclass cls, jint buffer)
  {
	  assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");

	  GtkTextBuffer *gtkBuffer = GTK_TEXT_BUFFER(buffer);
	  GtkTextIter start, end;
	  gtk_text_buffer_get_start_iter (gtkBuffer, &start );
	  gtk_text_buffer_get_end_iter (gtkBuffer, &end );
	  char* chars = gtk_text_buffer_get_text(gtkBuffer,&start,&end,TRUE);
	  return strToJstring(env,chars);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1set_1text
  (JNIEnv * env, jclass cls, jint buffer, jstring text)
  {
	assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	if(text==NULL)
	{
	   jniThrowNullPointerException(env,"text不能为NULL");//如果text为NULL，则GetStringUTFChars会报错
	   return;
	}
	char* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL);  
	gtk_text_buffer_set_text(GTK_TEXT_BUFFER(buffer),pText,strlen(pText));
	(*env)->ReleaseStringUTFChars(env, text, pText);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1insert
  (JNIEnv * env, jclass cls, jint buffer, jint iter, jstring text)
  {
	assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	if(text==NULL)
	{
	   jniThrowNullPointerException(env,"text不能为NULL");//如果text为NULL，则GetStringUTFChars会报错
	   return;
	}
	char* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL);  
	gtk_text_buffer_insert(GTK_TEXT_BUFFER(buffer),(GtkTextIter *)iter,pText,strlen(pText));
	(*env)->ReleaseStringUTFChars(env, text, pText);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1get_1iter_1at_1line_1index
  (JNIEnv * env, jclass cls, jint buffer, jint iter, jint line_number, jint byte_index)
  {
	  assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	  gtk_text_buffer_get_iter_at_line_index(GTK_TEXT_BUFFER(buffer),(GtkTextIter *)iter,line_number,byte_index);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1get_1iter_1at_1offset
  (JNIEnv * env, jclass cls, jint buffer, jint iter, jint char_offset)
  {
	  assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	  gtk_text_buffer_get_iter_at_offset(GTK_TEXT_BUFFER(buffer),(GtkTextIter *)iter,char_offset);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1get_1iter_1at_1line
  (JNIEnv * env, jclass cls, jint buffer, jint iter, jint line_number)
  {
	  assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	  gtk_text_buffer_get_iter_at_line(GTK_TEXT_BUFFER(buffer),(GtkTextIter *)iter,line_number);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1get_1start_1iter
  (JNIEnv * env, jclass cls, jint buffer, jint iter)
  {
	assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	gtk_text_buffer_get_start_iter(GTK_TEXT_BUFFER(buffer),(GtkTextIter *)iter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1buffer_1get_1end_1iter
  (JNIEnv * env, jclass cls, jint buffer, jint iter)
  {
	assertType(env,GTK_IS_TEXT_BUFFER(buffer),"buffer","GTK_TEXT_BUFFER");
	gtk_text_buffer_get_end_iter(GTK_TEXT_BUFFER(buffer),(GtkTextIter *)iter);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1view_1set_1wrap_1mode
  (JNIEnv * env, jclass cls, jint text_view, jint wrap_mode)
  {
	  assertType(env,GTK_IS_TEXT_VIEW(text_view),"text_view","GTK_TEXT_VIEW");
	  gtk_text_view_set_wrap_mode(GTK_TEXT_VIEW(text_view),wrap_mode);
  }


JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1view_1get_1wrap_1mode
  (JNIEnv * env, jclass cls, jint text_view)
  {
	   assertType(env,GTK_IS_TEXT_VIEW(text_view),"text_view","GTK_TEXT_VIEW");
	  return gtk_text_view_get_wrap_mode(GTK_TEXT_VIEW(text_view));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1view_1set_1editable
  (JNIEnv * env, jclass cls, jint text_view, jboolean setting)
  {
	  assertType(env,GTK_IS_TEXT_VIEW(text_view),"text_view","GTK_TEXT_VIEW");
	gtk_text_view_set_editable(GTK_TEXT_VIEW(text_view),setting);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1text_1view_1get_1editable
  (JNIEnv * env, jclass cls, jint text_view)
  {
	assertType(env,GTK_IS_TEXT_VIEW(text_view),"text_view","GTK_TEXT_VIEW");
	return gtk_text_view_get_editable(GTK_TEXT_VIEW(text_view));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_mci_1open
  (JNIEnv * env, jclass cls, jstring filename)
  {
	  if(filename==NULL)
	  {
		 jniThrowRuntimeException(env,"filename不能为空");  
		 return -1;
	  }
		char* pFileName = jstringToWindows(env,filename);//传递给Win32 API需要用jstringToWindows转码

		//用mci_send_string 只支持GetShortPathName短路径，用mciSendCommand更底层
		MCI_OPEN_PARMS openParms; 
		openParms.lpstrDeviceType= MCI_ALL_DEVICE_ID;
		openParms.lpstrElementName=(LPCSTR)pFileName; 
		openParms.wDeviceID = 0; 
		DWORD dwResult =mciSendCommand (NULL, MCI_OPEN, MCI_WAIT|MCI_OPEN_ELEMENT, 
			(DWORD)(LPVOID) &openParms);
		free(pFileName);
		if (dwResult != 0)  
		{  
		   jniThrowRuntimeException(env,"Open Media fail");  
		}  
		return openParms.wDeviceID;  
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_mci_1play
  (JNIEnv * env, jclass cls, jint deviceId, jboolean isRepeat)
  {
	  //isRepeat代表是否重复播放
	MCI_PLAY_PARMS mciPlay; 
	int MCI_DGV_PLAY_REPEAT = 0x00010000;
    if(mciSendCommand(deviceId, MCI_PLAY, isRepeat?MCI_DGV_PLAY_REPEAT:0, (DWORD)&mciPlay)!=0)  
    {  
        jniThrowRuntimeException(env,"play  Media fail");  
    }  
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_mci_1close
  (JNIEnv * env, jclass cls, jint deviceId)
  {
	DWORD dwResult = mciSendCommand (deviceId, MCI_CLOSE, NULL, NULL); 
	if (dwResult != 0)  
	{  
		jniThrowRuntimeException(env,"Close Media fail");  
	}  
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_mci_1pause
  (JNIEnv * env, jclass cls, jint deviceId)
  {
	MCI_PLAY_PARMS playParms; 
	DWORD dwResult = mciSendCommand (deviceId, MCI_PAUSE, 0,(DWORD)(LPVOID) &playParms);
	if (dwResult != 0)  
	{  
		jniThrowRuntimeException(env,"pause Media fail");  
	}  
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_mci_1seek
  (JNIEnv * env, jclass cls, jint deviceId, jlong toMS)
  {
	MCI_SEEK_PARMS seekParms; 
	seekParms.dwTo = toMS; 
	//跳转的目标时间，时间单位为毫秒 
	DWORD dwResult = mciSendCommand (deviceId, MCI_SEEK, MCI_TO ,(DWORD)(LPVOID)&seekParms);
	if (dwResult != 0)  
	{  
		jniThrowRuntimeException(env,"seek Media fail");  
	} 
  }

JNIEXPORT jlong JNICALL Java_com_rupeng_gtk4j_GTK_mci_1get_1status
  (JNIEnv * env, jclass cls, jint deviceId,jint status)
  {
	MCI_STATUS_PARMS statusParms; 
	statusParms.dwItem = status; 
	DWORD dwResult = mciSendCommand (deviceId, MCI_STATUS, MCI_WAIT | MCI_STATUS_ITEM, (DWORD)(LPVOID) &statusParms);
	if (dwResult != 0)  
	{  
		jniThrowRuntimeException(env,"getlength Media fail");  
	} 
	return statusParms.dwReturn;
  }


//定时器start
struct TimeoutInfo
{
	JNIEnv *env;
	jint timeoutId;
};

gint timeout_function(gpointer data)
{
	struct TimeoutInfo * timeoutInfo=(struct TimeoutInfo *)data;
	JNIEnv *env = timeoutInfo->env;
	int timeoutId = timeoutInfo->timeoutId;

	jclass cls = (*env)->FindClass(env,"com/rupeng/gtk4j/TimeoutManager");  
	jmethodID trigger =  (*env)->GetStaticMethodID(env,cls,"trigger","(I)Z");
	jboolean ret = (*env)->CallStaticBooleanMethod(env,cls,trigger,timeoutId);
	return ret==JNI_TRUE?G_SOURCE_CONTINUE:G_SOURCE_REMOVE;
}

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK__1g_1timeout_1add
  (JNIEnv * env, jclass cls, jint interval, jint timeoutId)
  {
	  struct TimeoutInfo *timeoutInfo=(struct TimeoutInfo *)malloc(sizeof(struct TimeoutInfo));
	  timeoutInfo->env=env;
	  timeoutInfo->timeoutId= timeoutId;

	  guint timeout = g_timeout_add(interval,timeout_function,timeoutInfo);
	  return timeout;
  }
//定时器 end

struct IdleInfo
{
	JNIEnv *env;
	jint idleId;
};

gint idle_function(gpointer data)
{
	struct IdleInfo * idleInfo=(struct IdleInfo *)data;
	JNIEnv *env = idleInfo->env;
	int idleId = idleInfo->idleId;

	jclass cls = (*env)->FindClass(env,"com/rupeng/gtk4j/GIdleManager");  
	jmethodID trigger =  (*env)->GetStaticMethodID(env,cls,"trigger","(I)Z");
	jboolean ret =  (*env)->CallStaticBooleanMethod(env,cls,trigger,idleId);
	return ret==JNI_TRUE?G_SOURCE_CONTINUE:G_SOURCE_REMOVE;
}
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK__1g_1idle_1add
  (JNIEnv * env, jclass cls, jint idleId)
  {
	  struct IdleInfo *idleInfo=(struct IdleInfo *)malloc(sizeof(struct IdleInfo));
	  idleInfo->env=env;
	  idleInfo->idleId= idleId;

	  guint idle = g_idle_add(idle_function,idleInfo);
	  return idle;
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1event_1get_1state
  (JNIEnv * env, jclass cls, jint event, jint state)
  {
	  const GdkEvent * gdkEvent = (const GdkEvent *)event;
	  switch(gdkEvent->type)
	  {
		case GDK_MOTION_NOTIFY:
			return ((GdkEventMotion*)gdkEvent)->state&state;
		case GDK_BUTTON_PRESS:
		case GDK_2BUTTON_PRESS:
		case GDK_3BUTTON_PRESS:
		case GDK_BUTTON_RELEASE:
			return ((GdkEventButton*)gdkEvent)->state&state;
		case GDK_TOUCH_BEGIN:
		case GDK_TOUCH_UPDATE:
		case GDK_TOUCH_END:
		case GDK_TOUCH_CANCEL:
			return ((GdkEventTouch*)gdkEvent)->state&state;
		case GDK_SCROLL:
			return ((GdkEventScroll*)gdkEvent)->state&state;
		case GDK_KEY_PRESS:
		case GDK_KEY_RELEASE:
			return ((GdkEventKey*)gdkEvent)->state&state;
		default:
			jniThrowRuntimeException(env,"event事件没有state值");
			return FALSE;
	  }
  }

JNIEXPORT jdouble JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1event_1get_1coords_1x
  (JNIEnv * env, jclass cls, jint event)
  {
	  const GdkEvent * gdkEvent = (const GdkEvent *)event;
	  switch(gdkEvent->type)
	  {
		case GDK_MOTION_NOTIFY:
			return ((GdkEventMotion*)gdkEvent)->x;
		case GDK_BUTTON_PRESS:
		case GDK_2BUTTON_PRESS:
		case GDK_3BUTTON_PRESS:
		case GDK_BUTTON_RELEASE:
			return ((GdkEventButton*)gdkEvent)->x;
		case GDK_TOUCH_BEGIN:
		case GDK_TOUCH_UPDATE:
		case GDK_TOUCH_END:
		case GDK_TOUCH_CANCEL:
			return ((GdkEventTouch*)gdkEvent)->x;
		case GDK_SCROLL:
			return ((GdkEventScroll*)gdkEvent)->x;
		default:
			jniThrowRuntimeException(env,"event事件没有x值");
			return -1;
	  }
  }

JNIEXPORT jdouble JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1event_1get_1coords_1y
  (JNIEnv * env, jclass cls, jint event)
  {
	  const GdkEvent * gdkEvent = (const GdkEvent *)event;
	  switch(gdkEvent->type)
	  {
		case GDK_MOTION_NOTIFY:
			return ((GdkEventMotion*)gdkEvent)->y;
		case GDK_BUTTON_PRESS:
		case GDK_2BUTTON_PRESS:
		case GDK_3BUTTON_PRESS:
		case GDK_BUTTON_RELEASE:
			return ((GdkEventButton*)gdkEvent)->y;
		case GDK_TOUCH_BEGIN:
		case GDK_TOUCH_UPDATE:
		case GDK_TOUCH_END:
		case GDK_TOUCH_CANCEL:
			return ((GdkEventTouch*)gdkEvent)->y;
		case GDK_SCROLL:
			return ((GdkEventScroll*)gdkEvent)->y;
		default:
			jniThrowRuntimeException(env,"event事件没有y值");
			return -1;
	  }
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1event_1get_1type
  (JNIEnv * env, jclass cls, jint event)
  {
	  const GdkEvent * gdkEvent = (const GdkEvent *)event;
	  return gdkEvent->type;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1event_1get_1keyval
  (JNIEnv * env, jclass cls, jint event)
  {
	  const GdkEvent * gdkEvent = (const GdkEvent *)event;
	  return gdkEvent->key.keyval;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1event_1get_1keycode
  (JNIEnv * env, jclass cls, jint event)
  {
	  const GdkEvent * gdkEvent = (const GdkEvent *)event;
	  return gdkEvent->key.hardware_keycode;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gdk_1event_1get_1button
  (JNIEnv * env, jclass cls, jint event)
  {
	  const GdkEvent * gdkEvent = (const GdkEvent *)event;
	  return gdkEvent->button.button;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1toolbar_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_toolbar_new();
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1toolbar_1insert
  (JNIEnv * env, jclass cls, jint toolbar, jint item, jint pos)
  {
	   assertType(env,GTK_IS_TOOLBAR(toolbar),"toolbar","GTK_TOOLBAR");
	    assertType(env,GTK_IS_TOOL_ITEM(item),"item","GTK_TOOL_ITEM");
	 gtk_toolbar_insert(GTK_TOOLBAR(toolbar),GTK_TOOL_ITEM(item),pos);
  }


JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tool_1item_1new
  (JNIEnv * env, jclass cls)
  {
	  return (int)gtk_tool_item_new();
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tool_1item_1set_1tooltip_1text
  (JNIEnv * env, jclass cls, jint toolbar, jstring text)
  {
	  if(text==NULL)
	  {
		 jniThrowRuntimeException(env,"text不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_TOOLBAR(toolbar),"toolbar","GTK_TOOLBAR");
	  char* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL);	
	  gtk_tool_item_set_tooltip_text(GTK_TOOLBAR(toolbar),pText);
	  (*env)->ReleaseStringUTFChars(env, text, pText);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tool_1button_1new
  (JNIEnv * env, jclass cls,  jint icon_widget,jstring label)
  {
	  //icon_widget可以为NULL，所以不进行类型检查
	  //assertType(env,GTK_IS_WIDGET(icon_widget),"icon_widget","GTK_WIDGET");
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);	
	  GtkToolButton *btn = gtk_tool_button_new(GTK_WIDGET(icon_widget),pLabel);
	  (*env)->ReleaseStringUTFChars(env, label, pLabel);
	  return (int)btn;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tool_1button_1set_1label
  (JNIEnv * env, jclass cls, jint button, jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  assertType(env,GTK_IS_TOOL_BUTTON(button),"button","GTK_TOOL_BUTTON");
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);	
	  gtk_tool_button_set_label(GTK_TOOL_BUTTON(button),pLabel);
	  (*env)->ReleaseStringUTFChars(env, label, pLabel);     
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tool_1button_1set_1stock_1id
  (JNIEnv * env, jclass cls, jint button, jstring stock_id)
  {
	  if(stock_id==NULL)
	  {
		 jniThrowRuntimeException(env,"stock_id不能为空");  
		 return;
	  }
	assertType(env,GTK_IS_TOOL_BUTTON(button),"button","GTK_TOOL_BUTTON");
	  char* pstock_id = (char*)(*env)->GetStringUTFChars(env, stock_id, NULL);	
	  gtk_tool_button_set_stock_id(GTK_TOOL_BUTTON(button),pstock_id);
	  (*env)->ReleaseStringUTFChars(env, stock_id, pstock_id);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1tool_1button_1set_1icon_1widget
  (JNIEnv * env, jclass cls, jint button, jint widget)
  {
	assertType(env,GTK_IS_TOOL_BUTTON(button),"button","GTK_TOOL_BUTTON");
	assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	gtk_tool_button_set_icon_widget(GTK_BUTTON(button),GTK_WIDGET(widget));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1tool_1button_1new
  (JNIEnv * env, jclass cls, jint icon_widget,jstring label)
  {
	  if(label==NULL)
	  {
		 jniThrowRuntimeException(env,"label不能为空");  
		 return;
	  }
	  char* pLabel = (char*)(*env)->GetStringUTFChars(env, label, NULL);
	  if(icon_widget!=NULL)//icon_widget可以为空
	  {
         assertType(env,GTK_IS_WIDGET(icon_widget),"icon_widget","GTK_WIDGET");
	  }
	  GtkToolItem  *item = gtk_menu_tool_button_new(icon_widget,pLabel);
	  (*env)->ReleaseStringUTFChars(env, label, pLabel);
	  return (int)item;
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1tool_1button_1new_1from_1stock
  (JNIEnv * env, jclass cls, jstring stock_id)
  {
	  if(stock_id==NULL)
	  {
		 jniThrowRuntimeException(env,"stock_id不能为空");  
		 return;
	  }
	  char* pStock_id = (char*)(*env)->GetStringUTFChars(env, stock_id, NULL);  
	  GtkToolItem *item = gtk_menu_tool_button_new_from_stock(pStock_id);
	  (*env)->ReleaseStringUTFChars(env, stock_id, pStock_id);
	  return (int)item;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1menu_1tool_1button_1set_1menu
  (JNIEnv * env, jclass cls, jint button, jint menu)
  {
	 assertType(env,GTK_IS_MENU_TOOL_BUTTON(button),"button","GTK_MENU_TOOL_BUTTON");
	 assertType(env,GTK_IS_MENU(menu),"menu","GTK_MENU");
	 gtk_menu_tool_button_set_menu(GTK_MENU_TOOL_BUTTON(button),GTK_MENU(menu));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1event_1box_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkEventBox *box = gtk_event_box_new();
	  return (int)box;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1add_1events
  (JNIEnv * env, jclass cls, jint widget, jint events)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	  gtk_widget_add_events(GTK_WIDGET(widget),events);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1draw
  (JNIEnv * env, jclass cls, jint widget, jint cr)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	  gtk_widget_draw(GTK_WIDGET(widget),(cairo_t *)cr);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1queue_1draw
  (JNIEnv * env, jclass cls, jint widget)
  {
	assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	  gtk_widget_queue_draw(GTK_WIDGET(widget));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1allocated_1width
  (JNIEnv * env, jclass cls, jint widget)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	  return gtk_widget_get_allocated_width(GTK_WIDGET(widget));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1widget_1get_1allocated_1height
  (JNIEnv * env, jclass cls, jint widget)
  {
	  assertType(env,GTK_IS_WIDGET(widget),"widget","GTK_WIDGET");
	  return gtk_widget_get_allocated_height(GTK_WIDGET(widget));
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1status_1icon_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkStatusIcon *icon = gtk_status_icon_new();
	  return (int)icon;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1status_1icon_1set_1from_1file
  (JNIEnv * env, jclass cls, jint status_icon, jstring filename)
  {
	  if(filename==NULL)
	  {
		 jniThrowRuntimeException(env,"filename不能为空");  
		 return;
	  }
	assertType(env,GTK_IS_STATUS_ICON(status_icon),"status_icon","GTK_STATUS_ICON");
	char* pFileName = (char*)(*env)->GetStringUTFChars(env, filename, NULL);	
	gtk_status_icon_set_from_file(GTK_STATUS_ICON(status_icon),pFileName);
	(*env)->ReleaseStringUTFChars(env, filename, pFileName);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1status_1icon_1set_1from_1stock
  (JNIEnv * env, jclass cls, jint status_icon, jstring stock_id)
  {
	  if(stock_id==NULL)
	  {
		 jniThrowRuntimeException(env,"stock_id不能为空");  
		 return;
	  }
	assertType(env,GTK_IS_STATUS_ICON(status_icon),"status_icon","GTK_STATUS_ICON");
	char* pstock_id = (char*)(*env)->GetStringUTFChars(env, stock_id, NULL);	
	gtk_status_icon_set_from_stock(GTK_STATUS_ICON(status_icon),pstock_id);
	(*env)->ReleaseStringUTFChars(env, stock_id, pstock_id);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1status_1icon_1set_1tooltip_1text
  (JNIEnv * env, jclass cls, jint status_icon, jstring text)
  {
	  if(text==NULL)
	  {
		 jniThrowRuntimeException(env,"text不能为空");  
		 return;
	  }
	assertType(env,GTK_IS_STATUS_ICON(status_icon),"status_icon","GTK_STATUS_ICON");
	char* pText = (char*)(*env)->GetStringUTFChars(env, text, NULL);	
	gtk_status_icon_set_tooltip_text(GTK_STATUS_ICON(status_icon),pText);
	(*env)->ReleaseStringUTFChars(env, text, pText);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1status_1icon_1set_1visible
  (JNIEnv * env, jclass cls, jint status_icon, jboolean visible)
  {
	assertType(env,GTK_IS_STATUS_ICON(status_icon),"status_icon","GTK_STATUS_ICON");
	gtk_status_icon_set_visible(GTK_STATUS_ICON(status_icon),visible);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1status_1icon_1get_1visible
  (JNIEnv * env, jclass cls, jint status_icon)
  {
	 assertType(env,GTK_IS_STATUS_ICON(status_icon),"status_icon","GTK_STATUS_ICON");
	 return gtk_status_icon_get_visible(GTK_STATUS_ICON(status_icon));
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_GTK_g_1error
  (JNIEnv * env, jclass cls, jstring message)
  {
	  if(message==NULL)
	  {
		 jniThrowRuntimeException(env,"message不能为空");  
		 return;
	  }
	char* pMessage = (char*)(*env)->GetStringUTFChars(env, message, NULL);  
	g_error("%s",pMessage);
	(*env)->ReleaseStringUTFChars(env, message, pMessage);	  
  }
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_GTK_gtk_1drawing_1area_1new
  (JNIEnv * env, jclass cls)
  {
	  GtkWidget* widget = gtk_drawing_area_new();
	  return (int)widget;
  }