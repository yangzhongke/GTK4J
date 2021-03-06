/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_rupeng_gtk4j_Cairo */

#ifndef _Included_com_rupeng_gtk4j_Cairo
#define _Included_com_rupeng_gtk4j_Cairo
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    findConst
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_findConst
  (JNIEnv *, jclass, jstring);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_create
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1create
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_reference
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1reference
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_destroy
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1destroy
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_get_reference_count
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1get_1reference_1count
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_save
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1save
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_restore
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1restore
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_set_source_rgb
 * Signature: (IDDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1source_1rgb
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_set_source_rgba
 * Signature: (IDDDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1source_1rgba
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_set_line_width
 * Signature: (ID)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1line_1width
  (JNIEnv *, jclass, jint, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_set_line_cap
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1line_1cap
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_translate
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1translate
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_scale
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1scale
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_rotate
 * Signature: (ID)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rotate
  (JNIEnv *, jclass, jint, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_new_path
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1new_1path
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_move_to
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1move_1to
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_new_sub_path
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1new_1sub_1path
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_line_to
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1line_1to
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_curve_to
 * Signature: (IDDDDDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1curve_1to
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_arc
 * Signature: (IDDDDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1arc
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_arc_negative
 * Signature: (IDDDDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1arc_1negative
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_rel_move_to
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rel_1move_1to
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_rel_line_to
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rel_1line_1to
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_rel_curve_to
 * Signature: (IDDDDDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rel_1curve_1to
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_rectangle
 * Signature: (IDDDD)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1rectangle
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_close_path
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1close_1path
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_paint
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1paint
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_paint_with_alpha
 * Signature: (ID)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1paint_1with_1alpha
  (JNIEnv *, jclass, jint, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_stroke
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1stroke
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_fill
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1fill
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_copy_page
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1copy_1page
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_show_page
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1show_1page
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_image_surface_create
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1create
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_image_surface_get_format
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1format
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_image_surface_get_width
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1width
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_image_surface_get_height
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1height
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_image_surface_get_stride
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1get_1stride
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_image_surface_create_from_png
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1image_1surface_1create_1from_1png
  (JNIEnv *, jclass, jstring);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_in_stroke
 * Signature: (IDD)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1in_1stroke
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_in_fill
 * Signature: (IDD)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1in_1fill
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_in_clip
 * Signature: (IDD)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1in_1clip
  (JNIEnv *, jclass, jint, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_reset_clip
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1reset_1clip
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_clip
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1clip
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_select_font_face
 * Signature: (ILjava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1select_1font_1face
  (JNIEnv *, jclass, jint, jstring, jint, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_set_font_size
 * Signature: (ID)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1set_1font_1size
  (JNIEnv *, jclass, jint, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_show_text
 * Signature: (ILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1show_1text
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_text_path
 * Signature: (ILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1text_1path
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_get_target
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1get_1target
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_status
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1status
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_status_to_string
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1status_1to_1string
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_surface_create_for_rectangle
 * Signature: (IDDDD)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1create_1for_1rectangle
  (JNIEnv *, jclass, jint, jdouble, jdouble, jdouble, jdouble);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_surface_reference
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1reference
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_surface_finish
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1finish
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_surface_destroy
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1destroy
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_surface_write_to_png
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1write_1to_1png
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     com_rupeng_gtk4j_Cairo
 * Method:    cairo_surface_flush
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_rupeng_gtk4j_Cairo_cairo_1surface_1flush
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif
