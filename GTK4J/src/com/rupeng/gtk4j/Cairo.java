package com.rupeng.gtk4j;

public class Cairo
{

	static
	{
		Utils.loadGtkLibs();
		Utils.loadDll("cairo4j.dll");
	}

	private static native int findConst(String name);

	// cairo_status_t start
	public final static int CAIRO_STATUS_SUCCESS = findConst("CAIRO_STATUS_SUCCESS");
	public final static int CAIRO_STATUS_NO_MEMORY = findConst("CAIRO_STATUS_NO_MEMORY");
	public final static int CAIRO_STATUS_INVALID_RESTORE = findConst("CAIRO_STATUS_INVALID_RESTORE");
	public final static int CAIRO_STATUS_INVALID_POP_GROUP = findConst("CAIRO_STATUS_INVALID_POP_GROUP");
	public final static int CAIRO_STATUS_NO_CURRENT_POINT = findConst("CAIRO_STATUS_NO_CURRENT_POINT");
	public final static int CAIRO_STATUS_INVALID_MATRIX = findConst("CAIRO_STATUS_INVALID_MATRIX");
	public final static int CAIRO_STATUS_INVALID_STATUS = findConst("CAIRO_STATUS_INVALID_STATUS");
	public final static int CAIRO_STATUS_NULL_POINTER = findConst("CAIRO_STATUS_NULL_POINTER");
	public final static int CAIRO_STATUS_INVALID_STRING = findConst("CAIRO_STATUS_INVALID_STRING");
	public final static int CAIRO_STATUS_INVALID_PATH_DATA = findConst("CAIRO_STATUS_INVALID_PATH_DATA");
	public final static int CAIRO_STATUS_READ_ERROR = findConst("CAIRO_STATUS_READ_ERROR");
	public final static int CAIRO_STATUS_WRITE_ERROR = findConst("CAIRO_STATUS_WRITE_ERROR");
	public final static int CAIRO_STATUS_SURFACE_FINISHED = findConst("CAIRO_STATUS_SURFACE_FINISHED");
	public final static int CAIRO_STATUS_SURFACE_TYPE_MISMATCH = findConst("CAIRO_STATUS_SURFACE_TYPE_MISMATCH");
	public final static int CAIRO_STATUS_PATTERN_TYPE_MISMATCH = findConst("CAIRO_STATUS_PATTERN_TYPE_MISMATCH");
	public final static int CAIRO_STATUS_INVALID_CONTENT = findConst("CAIRO_STATUS_INVALID_CONTENT");
	public final static int CAIRO_STATUS_INVALID_FORMAT = findConst("CAIRO_STATUS_INVALID_FORMAT");
	public final static int CAIRO_STATUS_INVALID_VISUAL = findConst("CAIRO_STATUS_INVALID_VISUAL");
	public final static int CAIRO_STATUS_FILE_NOT_FOUND = findConst("CAIRO_STATUS_FILE_NOT_FOUND");
	public final static int CAIRO_STATUS_INVALID_DASH = findConst("CAIRO_STATUS_INVALID_DASH");
	public final static int CAIRO_STATUS_INVALID_DSC_COMMENT = findConst("CAIRO_STATUS_INVALID_DSC_COMMENT");
	public final static int CAIRO_STATUS_INVALID_INDEX = findConst("CAIRO_STATUS_INVALID_INDEX");
	public final static int CAIRO_STATUS_CLIP_NOT_REPRESENTABLE = findConst("CAIRO_STATUS_CLIP_NOT_REPRESENTABLE");
	public final static int CAIRO_STATUS_TEMP_FILE_ERROR = findConst("CAIRO_STATUS_TEMP_FILE_ERROR");
	public final static int CAIRO_STATUS_INVALID_STRIDE = findConst("CAIRO_STATUS_INVALID_STRIDE");
	public final static int CAIRO_STATUS_FONT_TYPE_MISMATCH = findConst("CAIRO_STATUS_FONT_TYPE_MISMATCH");
	public final static int CAIRO_STATUS_USER_FONT_IMMUTABLE = findConst("CAIRO_STATUS_USER_FONT_IMMUTABLE");
	public final static int CAIRO_STATUS_USER_FONT_ERROR = findConst("CAIRO_STATUS_USER_FONT_ERROR");
	public final static int CAIRO_STATUS_NEGATIVE_COUNT = findConst("CAIRO_STATUS_NEGATIVE_COUNT");
	public final static int CAIRO_STATUS_INVALID_CLUSTERS = findConst("CAIRO_STATUS_INVALID_CLUSTERS");
	public final static int CAIRO_STATUS_INVALID_SLANT = findConst("CAIRO_STATUS_INVALID_SLANT");
	public final static int CAIRO_STATUS_INVALID_WEIGHT = findConst("CAIRO_STATUS_INVALID_WEIGHT");
	public final static int CAIRO_STATUS_INVALID_SIZE = findConst("CAIRO_STATUS_INVALID_SIZE");
	public final static int CAIRO_STATUS_USER_FONT_NOT_IMPLEMENTED = findConst("CAIRO_STATUS_USER_FONT_NOT_IMPLEMENTED");
	public final static int CAIRO_STATUS_DEVICE_TYPE_MISMATCH = findConst("CAIRO_STATUS_DEVICE_TYPE_MISMATCH");
	public final static int CAIRO_STATUS_DEVICE_ERROR = findConst("CAIRO_STATUS_DEVICE_ERROR");
	public final static int CAIRO_STATUS_LAST_STATUS = findConst("CAIRO_STATUS_LAST_STATUS");

	// cairo_status_t end

	/**
	 * 
	 * @param target
	 *            cairo_surface_t*
	 * @return cairo_t *
	 */
	public static native int cairo_create(int target);

	/**
	 * 
	 * @param cr
	 *            cairo_t *
	 * @return cairo_t *
	 */
	public static native int cairo_reference(int cr);

	/**
	 * 
	 * @param cr
	 *            cairo_t *
	 */
	public static native void cairo_destroy(int cr);

	public static native int cairo_get_reference_count(int cr);

	public static native void cairo_save(int cr);

	public static native void cairo_restore(int cr);

	public static native void cairo_set_source_rgb(int cr, double red,
			double green, double blue);

	public static native void cairo_set_source_rgba(int cr, double red,
			double green, double blue, double alpha);

	public static native void cairo_set_line_width(int cr, double width);

	// cairo_line_cap_t start
	public final static int CAIRO_LINE_CAP_BUTT = findConst("CAIRO_LINE_CAP_BUTT");
	public final static int CAIRO_LINE_CAP_ROUND = findConst("CAIRO_LINE_CAP_ROUND");
	public final static int CAIRO_LINE_CAP_SQUARE = findConst("CAIRO_LINE_CAP_SQUARE");

	// cairo_line_cap_t end
	/**
	 * 设置线的两个端点
	 * 
	 * @param cr
	 * @param line_cap
	 *            {@link cairo_line_cap_t}
	 */
	public static native void cairo_set_line_cap(int cr, int line_cap);

	public static native void cairo_translate(int cr, double tx, double ty);

	public static native void cairo_scale(int cr, double sx, double sy);

	public static native void cairo_rotate(int cr, double angle);

	public static native void cairo_new_path(int cr);

	public static native void cairo_move_to(int cr, double x, double y);

	public static native void cairo_new_sub_path(int cr);

	public static native void cairo_line_to(int cr, double x, double y);

	// 通过三点定义一道弧线
	public static native void cairo_curve_to(int cr, double x1, double y1,
			double x2, double y2, double x3, double y3);

	// 顺时针画一个圆弧，圆心xc,yc，半径radius，起始弧度angle1，终止弧度angle2.
	public static native void cairo_arc(int cr, double xc, double yc,
			double radius, double angle1, double angle2);

	public static native void cairo_arc_negative(int cr, double xc, double yc,
			double radius, double angle1, double angle2);

	public static native void cairo_rel_move_to(int cr, double dx, double dy);

	public static native void cairo_rel_line_to(int cr, double dx, double dy);

	public static native void cairo_rel_curve_to(int cr, double dx1,
			double dy1, double dx2, double dy2, double dx3, double dy3);

	public static native void cairo_rectangle(int cr, double x, double y,
			double width, double height);

	/*
	 * XXX: NYI public static native void cairo_stroke_to_path (int cr);
	 */

	public static native void cairo_close_path(int cr);

	public static native void cairo_paint(int cr);

	public static native void cairo_paint_with_alpha(int cr, double alpha);

	public static native void cairo_stroke(int cr);

	public static native void cairo_fill(int cr);

	public static native void cairo_copy_page(int cr);

	public static native void cairo_show_page(int cr);

	// cairo_format_t start
	public final static int CAIRO_FORMAT_INVALID = findConst("CAIRO_FORMAT_INVALID");
	public final static int CAIRO_FORMAT_ARGB32 = findConst("CAIRO_FORMAT_ARGB32");
	public final static int CAIRO_FORMAT_RGB24 = findConst("CAIRO_FORMAT_RGB24");
	public final static int CAIRO_FORMAT_A8 = findConst("CAIRO_FORMAT_A8");
	public final static int CAIRO_FORMAT_A1 = findConst("CAIRO_FORMAT_A1");
	public final static int CAIRO_FORMAT_RGB16_565 = findConst("CAIRO_FORMAT_RGB16_565");

	// cairo_format_t end

	/**
	 * 
	 * @param format
	 *            cairo_format_t
	 * @param width
	 * @param height
	 * @return cairo_surface_t *
	 */
	public static native int cairo_image_surface_create(int format, int width,
			int height);

	/**
	 * 
	 * @param surface
	 *            cairo_surface_t *
	 * @return cairo_format_t
	 */
	public static native int cairo_image_surface_get_format(int surface);

	public static native int cairo_image_surface_get_width(int surface);

	public static native int cairo_image_surface_get_height(int surface);

	public static native int cairo_image_surface_get_stride(int surface);

	/**
	 * 
	 * @param filename
	 * @return cairo_surface_t *
	 */
	public static native int cairo_image_surface_create_from_png(String filename);

	/* Insideness testing */
	public static native boolean cairo_in_stroke(int cr, double x, double y);

	public static native boolean cairo_in_fill(int cr, double x, double y);

	public static native boolean cairo_in_clip(int cr, double x, double y);

	public static native void cairo_reset_clip(int cr);

	public static native void cairo_clip(int cr);

	// cairo_font_slant_t start
	public final static int CAIRO_FONT_SLANT_NORMAL = findConst("CAIRO_FONT_SLANT_NORMAL");
	public final static int CAIRO_FONT_SLANT_ITALIC = findConst("CAIRO_FONT_SLANT_ITALIC");
	public final static int CAIRO_FONT_SLANT_OBLIQUE = findConst("CAIRO_FONT_SLANT_OBLIQUE");
	// cairo_font_slant_t end

	// cairo_font_weight_t start
	public final static int CAIRO_FONT_WEIGHT_NORMAL = findConst("CAIRO_FONT_WEIGHT_NORMAL");
	public final static int CAIRO_FONT_WEIGHT_BOLD = findConst("CAIRO_FONT_WEIGHT_BOLD");

	// cairo_font_weight_t end

	/**
	 * 
	 * @param cr
	 * @param family
	 *            字体名
	 * @param slant
	 *            cairo_font_slant_t
	 * @param weight
	 *            cairo_font_weight_t
	 */
	public static native void cairo_select_font_face(int cr, String family,
			int slant, int weight);

	public static native void cairo_set_font_size(int cr, double size);

	public static native void cairo_show_text(int cr, String utf8);

	public static native void cairo_text_path(int cr, String utf8);

	/**
	 * 从cairo_t *获得cairo_surface_t *
	 * 
	 * @param cr
	 * @return cairo_surface_t *
	 */
	public static native int cairo_get_target(int cr);

	/**
	 * 得到cairo_t *的cairo_status_t
	 * 
	 * @param cr
	 * @return cairo_status_t
	 */
	public static native int cairo_status(int cr);

	/**
	 * 
	 * @param status
	 *            cairo_status_t
	 * @return
	 */
	public static native String cairo_status_to_string(int status);

	/**
	 * 
	 * @param target
	 *            cairo_surface_t *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return cairo_surface_t *
	 */
	public static native int cairo_surface_create_for_rectangle(int target,
			double x, double y, double width, double height);

	/**
	 * 
	 * @param surface
	 *            cairo_surface_t *
	 * @return cairo_surface_t *
	 */
	public static native int cairo_surface_reference(int surface);

	/**
	 * 
	 * @param surface
	 *            cairo_surface_t *
	 */
	public static native void cairo_surface_finish(int surface);

	public static native void cairo_surface_destroy(int surface);

	/**
	 * 
	 * @param surface
	 * @param filename
	 * @return cairo_status_t
	 */
	public static native int cairo_surface_write_to_png(int surface,
			String filename);

	public static native void cairo_surface_flush(int surface);

}
