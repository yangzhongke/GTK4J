#include <stdlib.h>//避免“warning: incompatible implicit declaration of built-in function 'malloc' ”警告
#include <stdio.h> 
#include <string.h>//避免“warning: incompatible implicit declaration of built-in function 'strlen' ”警告
#include <jni.h> 
#include <gtk/gtk.h>
#include <gdk/gdk.h>
#include <windows.h>

#include "com_rupeng_gtk4j_Cairo.h"

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

int jniThrowRuntimeException(JNIEnv* env, const char* msg)
{
    return jniThrowException(env, "java/lang/RuntimeException", msg);
}

int jniThrowNullPointerException(JNIEnv* env, const char* msg)
{
    return jniThrowException(env, "java/lang/NullPointerException", msg);
}

//-g -w $(FileName) -shared -Wl,--kill-at -s -o cairo4j.dll   -ID:\gtk\include -ID:\gtk\include\gtk-3.0 
//-ID:\gtk\include\cairo -ID:\gtk\include\gdk -ID:\gtk\include\glib-2.0 -ID:\gtk\lib\glib-2.0\include -ID:\gtk\include\pango-1.0 
//-ID:\gtk\include\atk-1.0 -ID:\gtk\include\gdk-pixbuf-2.0  -I"C:\Program Files\Java\jdk1.8.0_11\include" 
//-I"C:\Program Files\Java\jdk1.8.0_11\include\win32" -LD:\gtk\lib  -lgtk-win32-3.0 -lgobject-2.0 -lglib-2.0 -lgdk-win32-3.0 -lwinmm 
//-lgdk_pixbuf-2.0 -lcairo  -mwindows
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_findConst
  (JNIEnv * env, jclass cls, jstring name)
  {
	  char* pConstName = (char*)(*env)->GetStringUTFChars(env, name, NULL);
	  if(strcmp("CAIRO_STATUS_SUCCESS",pConstName)==0)
	  {
		 return CAIRO_STATUS_SUCCESS;
	  }	
	 else if(strcmp("CAIRO_STATUS_NO_MEMORY",pConstName)==0)
	  {
		 return CAIRO_STATUS_NO_MEMORY;
	  }	
	  else if(strcmp("CAIRO_STATUS_INVALID_RESTORE",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_RESTORE;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_POP_GROUP",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_POP_GROUP;
	  }
	  else if(strcmp("CAIRO_STATUS_NO_CURRENT_POINT",pConstName)==0)
	  {
		 return CAIRO_STATUS_NO_CURRENT_POINT;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_MATRIX",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_MATRIX;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_STATUS",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_STATUS;
	  }
	  else if(strcmp("CAIRO_STATUS_NULL_POINTER",pConstName)==0)
	  {
		 return CAIRO_STATUS_NULL_POINTER;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_STRING",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_STRING;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_PATH_DATA",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_PATH_DATA;
	  }
	  else if(strcmp("CAIRO_STATUS_READ_ERROR",pConstName)==0)
	  {
		 return CAIRO_STATUS_READ_ERROR;
	  }
	  else if(strcmp("CAIRO_STATUS_WRITE_ERROR",pConstName)==0)
	  {
		 return CAIRO_STATUS_WRITE_ERROR;
	  }
	  else if(strcmp("CAIRO_STATUS_SURFACE_FINISHED",pConstName)==0)
	  {
		 return CAIRO_STATUS_SURFACE_FINISHED;
	  }
	  else if(strcmp("CAIRO_STATUS_SURFACE_TYPE_MISMATCH",pConstName)==0)
	  {
		 return CAIRO_STATUS_SURFACE_TYPE_MISMATCH;
	  }
	  else if(strcmp("CAIRO_STATUS_PATTERN_TYPE_MISMATCH",pConstName)==0)
	  {
		 return CAIRO_STATUS_PATTERN_TYPE_MISMATCH;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_CONTENT",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_CONTENT;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_FORMAT",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_FORMAT;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_VISUAL",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_VISUAL;
	  }
	  else if(strcmp("CAIRO_STATUS_FILE_NOT_FOUND",pConstName)==0)
	  {
		 return CAIRO_STATUS_FILE_NOT_FOUND;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_DASH",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_DASH;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_DSC_COMMENT",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_DSC_COMMENT;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_INDEX",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_INDEX;
	  }
	  else if(strcmp("CAIRO_STATUS_CLIP_NOT_REPRESENTABLE",pConstName)==0)
	  {
		 return CAIRO_STATUS_CLIP_NOT_REPRESENTABLE;
	  }
	  else if(strcmp("CAIRO_STATUS_TEMP_FILE_ERROR",pConstName)==0)
	  {
		 return CAIRO_STATUS_TEMP_FILE_ERROR;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_STRIDE",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_STRIDE;
	  }
	  else if(strcmp("CAIRO_STATUS_FONT_TYPE_MISMATCH",pConstName)==0)
	  {
		 return CAIRO_STATUS_FONT_TYPE_MISMATCH;
	  }
	  else if(strcmp("CAIRO_STATUS_USER_FONT_IMMUTABLE",pConstName)==0)
	  {
		 return CAIRO_STATUS_USER_FONT_IMMUTABLE;
	  }
	  else if(strcmp("CAIRO_STATUS_USER_FONT_ERROR",pConstName)==0)
	  {
		 return CAIRO_STATUS_USER_FONT_ERROR;
	  }
	  else if(strcmp("CAIRO_STATUS_NEGATIVE_COUNT",pConstName)==0)
	  {
		 return CAIRO_STATUS_NEGATIVE_COUNT;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_CLUSTERS",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_CLUSTERS;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_SLANT",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_SLANT;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_WEIGHT",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_WEIGHT;
	  }
	  else if(strcmp("CAIRO_STATUS_INVALID_SIZE",pConstName)==0)
	  {
		 return CAIRO_STATUS_INVALID_SIZE;
	  }
	  else if(strcmp("CAIRO_STATUS_USER_FONT_NOT_IMPLEMENTED",pConstName)==0)
	  {
		 return CAIRO_STATUS_USER_FONT_NOT_IMPLEMENTED;
	  }
	  else if(strcmp("CAIRO_STATUS_DEVICE_TYPE_MISMATCH",pConstName)==0)
	  {
		 return CAIRO_STATUS_DEVICE_TYPE_MISMATCH;
	  }
	  else if(strcmp("CAIRO_STATUS_DEVICE_ERROR",pConstName)==0)
	  {
		 return CAIRO_STATUS_DEVICE_ERROR;
	  }
	  else if(strcmp("CAIRO_STATUS_LAST_STATUS",pConstName)==0)
	  {
		 return CAIRO_STATUS_LAST_STATUS;
	  }
	  else if(strcmp("CAIRO_LINE_CAP_BUTT",pConstName)==0)
	  {
		 return CAIRO_LINE_CAP_BUTT;
	  }
	  else if(strcmp("CAIRO_LINE_CAP_ROUND",pConstName)==0)
	  {
		 return CAIRO_LINE_CAP_ROUND;
	  }
	  //todo：把else if ...{}定义成一个宏会更加好看、好维护：DEFINECONST(CAIRO_LINE_CAP_SQUARE)
	  else if(strcmp("CAIRO_LINE_CAP_SQUARE",pConstName)==0)
	  {
		 return CAIRO_LINE_CAP_SQUARE;
	  }
	  else if(strcmp("CAIRO_FORMAT_INVALID",pConstName)==0)
	  {
		 return CAIRO_FORMAT_INVALID;
	  }
	  else if(strcmp("CAIRO_FORMAT_ARGB32",pConstName)==0)
	  {
		 return CAIRO_FORMAT_ARGB32;
	  }
	  else if(strcmp("CAIRO_FORMAT_RGB24",pConstName)==0)
	  {
		 return CAIRO_FORMAT_RGB24;
	  }
	  else if(strcmp("CAIRO_FORMAT_A8",pConstName)==0)
	  {
		 return CAIRO_FORMAT_A8;
	  }
	  else if(strcmp("CAIRO_FORMAT_A1",pConstName)==0)
	  {
		 return CAIRO_FORMAT_A1;
	  }
	  else if(strcmp("CAIRO_FORMAT_RGB16_565",pConstName)==0)
	  {
		 return CAIRO_FORMAT_RGB16_565;
	  }
	  else if(strcmp("CAIRO_FONT_SLANT_NORMAL",pConstName)==0)
	  {
		 return CAIRO_FONT_SLANT_NORMAL;
	  }
	  else if(strcmp("CAIRO_FONT_SLANT_ITALIC",pConstName)==0)
	  {
		 return CAIRO_FONT_SLANT_ITALIC;
	  }
	  else if(strcmp("CAIRO_FONT_SLANT_OBLIQUE",pConstName)==0)
	  {
		 return CAIRO_FONT_SLANT_OBLIQUE;
	  }
	  else if(strcmp("CAIRO_FONT_WEIGHT_NORMAL",pConstName)==0)
	  {
		 return CAIRO_FONT_WEIGHT_NORMAL;
	  }
	  else if(strcmp("CAIRO_FONT_WEIGHT_BOLD",pConstName)==0)
	  {
		 return CAIRO_FONT_WEIGHT_BOLD;
	  }
	  else
	  {
		  char errorMsg[512];
		  sprintf(errorMsg,"cannot find const %s",pConstName);
		  g_error(errorMsg);
	  }
	  (*env)->ReleaseStringUTFChars(env, name, pConstName);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1create
  (JNIEnv * env, jclass cls, jint target)
  {
	  return (int)cairo_create((cairo_surface_t*)target);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1reference
  (JNIEnv * env, jclass cls, jint cr)
  {
	  return cairo_reference((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1destroy
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_destroy((cairo_t *)cr);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1get_1reference_1count
  (JNIEnv * env, jclass cls, jint cr)
  {
	  return cairo_get_reference_count((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1save
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_save((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1restore
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_restore((cairo_t *)cr);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1source_1rgb
  (JNIEnv * env, jclass cls, jint cr, jdouble red, jdouble green, jdouble blue)
  {
	  cairo_set_source_rgb((cairo_t *)cr,red,green,blue);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1source_1rgba
  (JNIEnv * env, jclass cls, jint cr, jdouble red, jdouble green, jdouble blue, jdouble alpha)
  {
	 cairo_set_source_rgba((cairo_t *)cr,red,green,blue,alpha);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1line_1width
  (JNIEnv * env, jclass cls, jint cr, jdouble width)
  {
	  cairo_set_line_width((cairo_t *)cr,width);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1line_1cap
  (JNIEnv * env, jclass cls, jint cr, jint line_cap)
  {
	  cairo_set_line_cap((cairo_t *)cr,(cairo_line_cap_t)line_cap);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1translate
  (JNIEnv * env, jclass cls, jint cr, jdouble tx, jdouble ty)
  {
	cairo_translate((cairo_t *)cr,tx,ty);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1scale
  (JNIEnv * env, jclass cls, jint cr, jdouble sx, jdouble sy)
  {
	  cairo_scale((cairo_t *)cr,sx,sy);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rotate
  (JNIEnv * env, jclass cls, jint cr, jdouble angle)
  {
     cairo_rotate((cairo_t *)cr,angle);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1new_1path
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_new_path((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1move_1to
  (JNIEnv * env, jclass cls, jint cr, jdouble x, jdouble y)
  {
	  cairo_move_to((cairo_t *)cr,x,y);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1new_1sub_1path
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_new_sub_path((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1line_1to
  (JNIEnv * env, jclass cls, jint cr, jdouble x, jdouble y)
  {
	  cairo_line_to((cairo_t *)cr,x,y);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1curve_1to
  (JNIEnv * env, jclass cls, jint cr , jdouble x1, jdouble y1, jdouble x2, jdouble y2, jdouble x3, jdouble y3)
  {
	  cairo_curve_to((cairo_t *)cr,x1,y1,x2,y2,x3,y3);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1arc
  (JNIEnv * env, jclass cls, jint cr, jdouble xc, jdouble yc, jdouble radius, jdouble angle1, jdouble angle2)
  {
	  cairo_arc((cairo_t *)cr,xc,yc,radius,angle1,angle2);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1arc_1negative
  (JNIEnv * env, jclass cls, jint cr, jdouble xc, jdouble yc, jdouble radius, jdouble angle1, jdouble angle2)
  {
	  cairo_arc_negative((cairo_t *)cr,xc,yc,radius,angle1,angle2);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rel_1move_1to
  (JNIEnv * env, jclass cls, jint cr, jdouble dx, jdouble dy)
  {
	  cairo_rel_move_to((cairo_t *)cr,dx,dy);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rel_1line_1to
  (JNIEnv * env, jclass cls, jint cr, jdouble dx, jdouble dy)
  {
	  cairo_rel_line_to((cairo_t *)cr,dx,dy);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rel_1curve_1to
  (JNIEnv * env, jclass cls, jint cr, jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2, jdouble dx3, jdouble dy3)
  {
	  cairo_rel_curve_to((cairo_t *)cr,dx1,dy1,dx2,dy2,dx3,dy3);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rectangle
  (JNIEnv * env, jclass cls, jint cr, jdouble x, jdouble y, jdouble width, jdouble height)
  {
	  cairo_rectangle((cairo_t *)cr,x,y,width,height);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1close_1path
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_close_path((cairo_t *)cr);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1paint
  (JNIEnv * env, jclass cls, jint cr)
  {
	 cairo_paint((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1paint_1with_1alpha
  (JNIEnv * env, jclass cls, jint cr , jdouble alpha)
  {
	  cairo_paint_with_alpha((cairo_t *)cr,alpha);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1stroke
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_stroke((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1fill
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_fill((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1copy_1page
  (JNIEnv * env, jclass cls, jint cr)
  {
	 cairo_copy_page((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1show_1page
  (JNIEnv * env, jclass cls, jint cr)
  {
	 cairo_show_page((cairo_t *)cr);
  }


JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1create
  (JNIEnv * env, jclass cls, jint format, jint width, jint height)
  {
	  return cairo_image_surface_create((cairo_format_t)format,width,height);
  }


JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1format
  (JNIEnv * env, jclass cls, jint surface)
  {
	  return cairo_image_surface_get_format((cairo_surface_t *)surface);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1width
  (JNIEnv * env, jclass cls, jint surface)
  {
	  return cairo_image_surface_get_width((cairo_surface_t *)surface);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1height
  (JNIEnv * env, jclass cls, jint surface)
  {
	  return cairo_image_surface_get_height((cairo_surface_t *)surface);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1stride
  (JNIEnv * env, jclass cls, jint surface)
  {
	  return cairo_image_surface_get_stride((cairo_surface_t *)surface);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1create_1from_1png
  (JNIEnv * env, jclass cls, jstring filename)
  {
	  if(filename==NULL)
	  {
		 jniThrowRuntimeException(env,"filename不能为空");  
		 return;
	  }
	  char* pFileName = (char*)(*env)->GetStringUTFChars(env, filename, NULL);  
	  cairo_surface_t * surface = cairo_image_surface_create_from_png(pFileName);
	   (*env)->ReleaseStringUTFChars(env, filename, pFileName);
	   return (int)surface;
  }


JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1in_1stroke
  (JNIEnv * env, jclass cls, jint cr, jdouble x, jdouble y)
  {
	  return cairo_in_stroke((cairo_t *)cr,x,y);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1in_1fill
  (JNIEnv * env, jclass cls, jint cr, jdouble x, jdouble y)
  {
	  return cairo_in_fill((cairo_t *)cr,x,y);
  }

JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1in_1clip
  (JNIEnv * env, jclass cls, jint cr, jdouble x, jdouble y)
  {
	  return cairo_in_clip((cairo_t *)cr,x,y);
  }


JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1reset_1clip
  (JNIEnv * env, jclass cls, jint cr)
  {
	cairo_reset_clip((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1clip
  (JNIEnv * env, jclass cls, jint cr)
  {
       cairo_clip((cairo_t *)cr);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1select_1font_1face
  (JNIEnv * env, jclass cls, jint cr, jstring family, jint slant, jint weight)
  {
	  if(family==NULL)
	  {
		 jniThrowRuntimeException(env,"family不能为空");  
		 return;
	  }
	  char* pfamily = (char*)(*env)->GetStringUTFChars(env, family, NULL);  
	   cairo_select_font_face((cairo_t *)cr,pfamily,(cairo_font_slant_t)slant,(cairo_font_weight_t)weight);
	   (*env)->ReleaseStringUTFChars(env, family, pfamily);
      
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1font_1size
  (JNIEnv * env, jclass cls, jint cr, jdouble size)
  {
	  cairo_set_font_size((cairo_t *)cr,size);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1show_1text
  (JNIEnv * env, jclass cls, jint cr, jstring utf8)
  {
	  if(utf8==NULL)
	  {
		 jniThrowRuntimeException(env,"utf8字符串不能为空");  
		 return;
	  }
	  char* pUtf8 = (char*)(*env)->GetStringUTFChars(env, utf8, NULL);  
	   cairo_show_text((cairo_t *)cr,pUtf8);
	   (*env)->ReleaseStringUTFChars(env, utf8, pUtf8);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1text_1path
  (JNIEnv * env, jclass cls, jint cr, jstring utf8)
  {
	  if(utf8==NULL)
	  {
		 jniThrowRuntimeException(env,"utf8字符串不能为空");  
		 return;
	  }
	  char* pUtf8 = (char*)(*env)->GetStringUTFChars(env, utf8, NULL);  
	   cairo_text_path((cairo_t *)cr,pUtf8);
	   (*env)->ReleaseStringUTFChars(env, utf8, pUtf8);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1get_1target
  (JNIEnv * env, jclass cls, jint cr)
  {
	  cairo_surface_t * surface = cairo_get_target((cairo_t *)cr);
	  return surface;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1status
  (JNIEnv * env, jclass cls, jint cr)
  {
     cairo_status_t ret  = cairo_status((cairo_t *)cr);
	 return ret;
  }


JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1status_1to_1string
  (JNIEnv * env, jclass cls, jint status)
  {
	   const char * pChar = cairo_status_to_string((cairo_status_t)status);
	   return (*env)->NewStringUTF(env, pChar);
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1create_1for_1rectangle
  (JNIEnv * env, jclass cls, jint target, jdouble x, jdouble y, jdouble width, jdouble height)
  {
	  cairo_surface_t * surface = cairo_surface_create_for_rectangle((cairo_surface_t *)target,x,y,width,height);
	  return surface;
  }

JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1reference
  (JNIEnv * env, jclass cls, jint surface)
  {
       cairo_surface_t * newsurface = cairo_surface_reference((cairo_surface_t *)surface);
	   return newsurface;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1finish
  (JNIEnv * env, jclass cls, jint surface)
  {
	  cairo_surface_finish((cairo_surface_t *)surface);
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1destroy
  (JNIEnv * env, jclass cls, jint surface)
  {
		cairo_surface_destroy((cairo_surface_t *)surface);
  }


JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1write_1to_1png
  (JNIEnv * env, jclass cls, jint surface, jstring filename)
  {
	  if(filename==NULL)
	  {
		 jniThrowRuntimeException(env,"filename不能为空");  
		 return;
	  }
	  char* pFilename = (char*)(*env)->GetStringUTFChars(env, filename, NULL);  
	  cairo_status_t ret = cairo_surface_write_to_png((cairo_surface_t *)surface,pFilename);
	   (*env)->ReleaseStringUTFChars(env, filename, pFilename);
	   return ret;
  }

JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1flush
  (JNIEnv * env, jclass cls, jint surface)
  {
       cairo_surface_flush((cairo_surface_t *)surface);
  }