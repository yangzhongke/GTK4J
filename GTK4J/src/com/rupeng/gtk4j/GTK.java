package com.rupeng.gtk4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * GTK4J版权属于如鹏（www.rupeng.com）
 * @author www.rupeng.com
 *
 */
public class GTK
{

	static
	{
		Utils.unZipShared();
		Utils.loadGtkLibs();
		Utils.loadDll("gtk4j.dll");
	}

	/**
	 * 调用gtk的方法连接信号（内部供GCallBackManager使用，外部不要使用）
	 * 
	 * @param instance
	 *            组件句柄
	 * @param signalName
	 *            信号名
	 * @param signalId
	 *            GCallBackManager分配的信号Id
	 */
	protected static native void g_signal_connect(int instance,
			String signalName, int signalId);

	/**
	 * 连接信号，
	 * 
	 * @param instance
	 * @param signalName
	 * @param callback
	 * @param object
	 *            附加的额外对象，将会传递给IGCallBack的doAction方法的最后一个参数
	 */
	public static void g_signal_connect(int instance, String signalName,
			IGCallBack callback, Object object)
	{

		GCallBackManager.Instance.connect(instance, signalName, object,
				callback);
	}

	private static native int findConst(String name);

	public final static int GTK_WINDOW_TOPLEVEL = findConst("GTK_WINDOW_TOPLEVEL");
	public final static int GTK_WINDOW_POPUP = findConst("GTK_WINDOW_POPUP");

	/**
	 * 初始化gtk环境
	 */
	public static native void gtk_init();

	/**
	 * 启动主循环
	 */
	public static native void gtk_main();

	/**
	 * 退出程序
	 */
	public static native void gtk_main_quit();

	/**
	 * 
	 * @param style
	 *            目前可选值只有GTK_WINDOW_TOPLEVEL
	 * @return
	 */
	public static native int gtk_window_new(int style);

	/**
	 * 显示Widget
	 * @param widget
	 */
	public static native void gtk_widget_show(int widget);

	public static native void gtk_widget_hide(int widget);

	public static native void gtk_widget_set_can_focus(int widget,
			boolean can_focus);

	public static native boolean gtk_widget_get_can_focus(int widget);

	public static native boolean gtk_widget_has_focus(int widget);

	public static native void gtk_widget_grab_focus(int widget);

	public static native void gtk_widget_set_visible(int widget, boolean visible);

	public static native boolean gtk_widget_get_visible(int widget);

	/**
	 * 设置禁用启用
	 * 
	 * @param widget
	 * @param sensitive
	 */
	public static native void gtk_widget_set_sensitive(int widget,
			boolean sensitive);

	public static native boolean gtk_widget_get_sensitive(int widget);

	public static native int gtk_widget_get_parent(int widget);

	public static native void gtk_widget_set_parent(int widget, int parent);

	/**
	 * 得到控件的顶级GtkWindow
	 * 
	 * @param widget
	 * @return
	 */
	// public static native int gtk_widget_get_parent_window(int widget);

	/**
	 * 获得控件的顶级窗口，一般是GtkWindow，但是如果widget没有父对象，则将会把自己返回
	 * 为了确保返回的一定是GtkWindow，可以使用gtk_widget_is_toplevel() 判断 例子： GtkWidget
	 * *toplevel = gtk_widget_get_toplevel (widget); if (gtk_widget_is_toplevel
	 * (toplevel)) {}
	 * 
	 * @param widget
	 * @return
	 */
	public static native int gtk_widget_get_toplevel(int widget);

	public static native boolean gtk_widget_is_toplevel(int widget);

	public static native void gtk_widget_set_app_paintable(int widget,
			boolean paintable);

	/**
	 * 获得widget所在的GdkScreen，如果Widget没有被加入到可视化树中，则无法返回，
	 * 需要调用gtk_widget_has_screen判断是否有GdkScreen关联
	 * 
	 * @param widget
	 * @return
	 */
	public static native int gtk_widget_get_screen(int widget);

	public static native boolean gtk_widget_has_screen(int widget);

	/**
	 * 得到屏幕的宽度
	 * 
	 * @param screen
	 * @return
	 */
	public static native int gdk_screen_get_width(int screen);

	/**
	 * 得到屏幕的高度
	 * 
	 * @param screen
	 * @return
	 */
	public static native int gdk_screen_get_height(int screen);

	/**
	 * 得到默认的屏幕，如果没有屏幕则返回NULL
	 * 
	 * @return
	 */
	public static native int gdk_screen_get_default();

	/**
	 * 得到屏幕由几块显示器组成
	 * 
	 * @param screen
	 * @return
	 */
	public static native int gdk_screen_get_n_monitors(int screen);

	/**
	 * 得到在屏幕中的主显示器的编号，主显示器也就是主桌面所在的
	 * 
	 * @param screen
	 * @return
	 */
	public static native int gdk_screen_get_primary_monitor(int screen);

	/**
	 * 得到第monitor_num块屏幕工作区的矩形区域，排除掉Linux下的Docker、Menu/windows下的任务栏等
	 * 
	 * @param screen
	 * @param monitor_num
	 *            屏幕的序号
	 * @return 长度为4的int数组，依次是：x、y、width、height
	 */
	public static native int[] gdk_screen_get_monitor_workarea(int screen,
			int monitor_num);

	public static native void gtk_widget_set_size_request(int widget,
			int width, int height);

	public static native void gtk_widget_destroy(int widget);

	public static native void gtk_window_set_title(int window, String title);

	public static native String gtk_window_get_title(int window);

	public static native void gtk_window_set_resizable(int window,
			boolean resizable);

	public static native boolean gtk_window_get_resizable(int window);

	public static native void gtk_window_set_modal(int window, boolean modal);

	public static native void gtk_window_set_default_size(int window,
			int width, int height);

	public final static int GTK_WIN_POS_NONE = findConst("GTK_WIN_POS_NONE");
	public final static int GTK_WIN_POS_CENTER = findConst("GTK_WIN_POS_CENTER");
	public final static int GTK_WIN_POS_MOUSE = findConst("GTK_WIN_POS_MOUSE");
	public final static int GTK_WIN_POS_CENTER_ALWAYS = findConst("GTK_WIN_POS_CENTER_ALWAYS");
	public final static int GTK_WIN_POS_CENTER_ON_PARENT = findConst("GTK_WIN_POS_CENTER_ON_PARENT");

	/**
	 * 设定窗口的厨师位置
	 * 
	 * @param window
	 * @param position
	 *            取值 GtkWindowPosition
	 */
	public static native void gtk_window_set_position(int window, int position);

	/**
	 * 全屏
	 * 
	 * @param window
	 */
	public static native void gtk_window_fullscreen(int window);

	/**
	 * 退出全屏
	 * 
	 * @param window
	 */
	public static native void gtk_window_unfullscreen(int window);

	/**
	 * 最大化
	 * 
	 * @param window
	 */
	public static native void gtk_window_maximize(int window);

	public static native int gtk_fixed_new();

	public static native void gtk_fixed_put(int fixed, int widget, int x, int y);

	public static native void gtk_container_add(int container, int widget);

	public static native void gtk_container_remove(int container, int widget);

	public static native int[] gtk_container_get_children(int container);

	public static native int gtk_label_new(String label);

	public static native void gtk_label_set_text(int label, String str);

	public static native String gtk_label_get_text(int label);

	public static native int gtk_button_new();

	public static native int gtk_button_new_with_label(String label);

	public static native void gtk_button_set_label(int button, String label);

	public static native String gtk_button_get_label(int button);

	public static native void gtk_button_set_image(int button, int widget);

	/***
	 * 设定按钮中图片的显示位置
	 * 
	 * @param button
	 * @param position
	 *            GtkPositionType
	 */
	public static native void gtk_button_set_image_position(int button,
			int position);

	public static native int gtk_calendar_new();

	public static native void gtk_calendar_select_month(int calendar,
			int month, int year);

	public static native void gtk_calendar_select_day(int calendar, int day);

	public static native int gtk_calendar_get_year(int calendar);

	public static native int gtk_calendar_get_month(int calendar);

	public static native int gtk_calendar_get_day(int calendar);

	// GtkOrientation start 用来演示枚举的封装
	public static final int GTK_ORIENTATION_HORIZONTAL = findConst("GTK_ORIENTATION_HORIZONTAL");
	public static final int GTK_ORIENTATION_VERTICAL = findConst("GTK_ORIENTATION_VERTICAL");

	// GtkOrientation end

	public static native int gtk_box_new(int orientation, int spacing);

	/**
	 * 
	 * @param box
	 * @param child
	 * @param expand expand参数表示在所有构件加入组装盒以后，构件周围是否还有可扩充的空间。如果用homogenous格式建立组装盒，
	 * 则忽略这一参数。因为homegeneous参数表示构件在组装盒内使用同样大小的空间。
	 * fill参数表示构件是否需要充分利用构件周围空间。将此参数设置为TRUE允许构件稍稍扩大一点，以充分利用组装盒分配给它的空间。
	 * 将此参数设置为FALSE强制构件只使用它需要的空间。多余的空间围绕构件的周围分布。
	 * @param fill
	 * @param padding
	 */
	public static native void gtk_box_pack_start(int box, int child,
			boolean expand, boolean fill, int padding);

	public static native void gtk_box_pack_end(int box, int child,
			boolean expand, boolean fill, int padding);
	
	/**
	 * 各个单元格是否等宽/等高
	 * @param box
	 * @param homogeneous
	 */
	public static native void gtk_box_set_homogeneous(int box,boolean homogeneous);

	public static native int gtk_grid_new();

	public static native void gtk_grid_insert_row(int grid, int position);

	public static native void gtk_grid_insert_column(int grid, int position);

	public static native void gtk_grid_attach(int grid, int child, int left,
			int top, int width, int height);

	public static native void gtk_grid_set_row_spacing(int grid, int spacing);

	public static native void gtk_grid_set_column_spacing(int grid, int spacing);

	public static native void gtk_grid_set_row_homogeneous(int grid,
			boolean homogeneous);

	public static native void gtk_grid_set_column_homogeneous(int grid,
			boolean homogeneous);

	public static native int gtk_message_dialog_new(int parent, int flags,
			int type, int buttons, String message);

	public static native int gtk_dialog_run(int dialog);

	public static native int gtk_dialog_response(int dialog, int response_id);

	public static native int gtk_dialog_new();

	/**
	 * 
	 * @param title
	 * @param parent
	 *            GtkWindow
	 * @param flags
	 *            GtkDialogFlags
	 * @return
	 */
	public static native int gtk_dialog_new_with_buttons(String title,
			int parent, int flags);

	public static native int gtk_dialog_get_action_area(int dialog);

	public static native int gtk_dialog_get_content_area(int dialog);

	/**
	 * 往对话框中增加按钮
	 * 
	 * @param dialog
	 * @param button_text
	 *            按钮文本
	 * @param response_id
	 *            点击按钮后gtk_dialog_run()的返回值，可选值为GTK_RESPONSE_*
	 * @return
	 */
	public static native int gtk_dialog_add_button(int dialog,
			String button_text, int response_id);

	// GtkDialogFlags start 用来演示枚举的封装
	public static final int GTK_DIALOG_MODAL = findConst("GTK_DIALOG_MODAL");
	public static final int GTK_DIALOG_DESTROY_WITH_PARENT = findConst("GTK_DIALOG_DESTROY_WITH_PARENT");
	// GtkDialogFlags end

	// GtkMessageType start
	public static final int GTK_MESSAGE_INFO = findConst("GTK_MESSAGE_INFO");
	public static final int GTK_MESSAGE_WARNING = findConst("GTK_MESSAGE_WARNING");
	public static final int GTK_MESSAGE_QUESTION = findConst("GTK_MESSAGE_QUESTION");
	public static final int GTK_MESSAGE_ERROR = findConst("GTK_MESSAGE_ERROR");
	public static final int GTK_MESSAGE_OTHER = findConst("GTK_MESSAGE_OTHER");
	// GtkMessageType end

	// GtkButtonsType start
	public static final int GTK_BUTTONS_NONE = findConst("GTK_BUTTONS_NONE");
	public static final int GTK_BUTTONS_OK = findConst("GTK_BUTTONS_OK");
	public static final int GTK_BUTTONS_CLOSE = findConst("GTK_BUTTONS_CLOSE");
	public static final int GTK_BUTTONS_CANCEL = findConst("GTK_BUTTONS_CANCEL");
	public static final int GTK_BUTTONS_YES_NO = findConst("GTK_BUTTONS_YES_NO");
	public static final int GTK_BUTTONS_OK_CANCEL = findConst("GTK_BUTTONS_OK_CANCEL");
	// GtkButtonsType end

	// GtkResponseType start
	public static final int GTK_RESPONSE_NONE = findConst("GTK_RESPONSE_NONE");
	public static final int GTK_RESPONSE_REJECT = findConst("GTK_RESPONSE_REJECT");
	public static final int GTK_RESPONSE_ACCEPT = findConst("GTK_RESPONSE_ACCEPT");
	public static final int GTK_RESPONSE_DELETE_EVENT = findConst("GTK_RESPONSE_DELETE_EVENT");
	public static final int GTK_RESPONSE_OK = findConst("GTK_RESPONSE_OK");
	public static final int GTK_RESPONSE_CANCEL = findConst("GTK_RESPONSE_CANCEL");
	public static final int GTK_RESPONSE_CLOSE = findConst("GTK_RESPONSE_CLOSE");
	public static final int GTK_RESPONSE_YES = findConst("GTK_RESPONSE_YES");
	public static final int GTK_RESPONSE_NO = findConst("GTK_RESPONSE_NO");
	public static final int GTK_RESPONSE_APPLY = findConst("GTK_RESPONSE_APPLY");
	public static final int GTK_RESPONSE_HELP = findConst("GTK_RESPONSE_HELP");

	// GtkResponseType end

	public static native int gtk_toggle_button_new();

	public static native int gtk_toggle_button_new_with_label(String label);

	public static native int gtk_toggle_button_new_with_mnemonic(String label);

	public static native void gtk_toggle_button_set_active(int toggle_button,
			boolean is_active);

	public static native boolean gtk_toggle_button_get_active(int toggle_button);

	public static native int gtk_check_button_new();

	public static native int gtk_check_button_new_with_label(String label);

	public static native int gtk_check_button_new_with_mnemonic(String label);

	public static native int gtk_combo_box_new();

	public static native int gtk_combo_box_get_active(int combo_box);

	public static native void gtk_combo_box_set_active(int combo_box, int index);

	public static native String gtk_combo_box_get_active_id(int combo_box);

	public static native boolean gtk_combo_box_set_active_id(int combo_box,
			String active_id);

	public static native int gtk_combo_box_text_new();

	public static native void gtk_combo_box_text_append_text(int combo_box,
			String text);

	public static native void gtk_combo_box_text_remove(int combo_box,
			int position);

	public static native String gtk_combo_box_text_get_active_text(int combo_box);

	public static native void gtk_combo_box_text_remove_all(int combo_box);

	public static native void gtk_combo_box_text_append(int combo_box,
			String id, String text);

	public static native int gtk_entry_new();

	public static native void gtk_entry_set_max_length(int entry, int max);

	public static native int gtk_entry_get_max_length(int entry);

	public static native int gtk_entry_get_text_length(int entry);

	public static native void gtk_entry_set_text(int entry, String text);

	public static native String gtk_entry_get_text(int entry);

	public static native void gtk_entry_set_invisible_char(int entry,
			char inv_char);

	public static native void gtk_entry_set_visibility(int entry,
			boolean visible);

	// gtk_editable_*对实现了GtkEditable 接口的对象进行操作
	// GtkEntry、GtkSpin实现了这个接口
	public static native void gtk_editable_select_region(int editable,
			int start_pos, int end_pos);

	public static native int gtk_editable_insert_text(int editable,
			String new_text, int new_text_length);

	public static native void gtk_editable_delete_text(int editable,
			int start_pos, int end_pos);

	public static native void gtk_editable_cut_clipboard(int editable);

	public static native void gtk_editable_copy_clipboard(int editable);

	public static native void gtk_editable_paste_clipboard(int editable);

	public static native void gtk_editable_delete_selection(int editable);

	public static native void gtk_editable_set_position(int editable,
			int position);

	public static native int gtk_editable_get_position(int ditable);

	public static native void gtk_editable_set_editable(int editable,
			boolean is_editable);

	public static native boolean gtk_editable_get_editable(int editable);

	public static native int gtk_spin_button_new_with_range(double  min,double  max,double  step);
	public static native double	gtk_spin_button_get_value(int spin_button);
	public static native int gtk_spin_button_get_value_as_int(int spin_button);
	public static native void gtk_spin_button_set_value(int spin_button,double value);
	/**
	 * 设置小数的精度
	 * @param spin_button
	 * @param digits 精度，最多20
	 */
	public static native void gtk_spin_button_set_digits(int spin_button,byte digits);

	// GTK_STOCK_ start
	public static final String GTK_STOCK_ABOUT = "gtk-about";
	public static final String GTK_STOCK_ADD = "gtk-add";
	public static final String GTK_STOCK_APPLY = "gtk-apply";
	public static final String GTK_STOCK_BOLD = "gtk-bold";
	public static final String GTK_STOCK_CANCEL = "gtk-cancel";
	public static final String GTK_STOCK_CAPS_LOCK_WARNING = "gtk-caps-lock-warning";
	public static final String GTK_STOCK_CDROM = "gtk-cdrom";
	public static final String GTK_STOCK_CLEAR = "gtk-clear";
	public static final String GTK_STOCK_CLOSE = "gtk-close";
	public static final String GTK_STOCK_COLOR_PICKER = "gtk-color-picker";
	public static final String GTK_STOCK_CONNECT = "gtk-connect";
	public static final String GTK_STOCK_CONVERT = "gtk-convert";
	public static final String GTK_STOCK_COPY = "gtk-copy";
	public static final String GTK_STOCK_CUT = "gtk-cut";
	public static final String GTK_STOCK_DELETE = "gtk-delete";
	public static final String GTK_STOCK_DIALOG_AUTHENTICATION = "gtk-dialog-authentication";
	public static final String GTK_STOCK_DIALOG_INFO = "gtk-dialog-info";
	public static final String GTK_STOCK_DIALOG_WARNING = "gtk-dialog-warning";
	public static final String GTK_STOCK_DIALOG_ERROR = "gtk-dialog-error";
	public static final String GTK_STOCK_DIALOG_QUESTION = "gtk-dialog-question";
	public static final String GTK_STOCK_DIRECTORY = "gtk-directory";
	public static final String GTK_STOCK_DISCARD = "gtk-discard";
	public static final String GTK_STOCK_DISCONNECT = "gtk-disconnect";
	public static final String GTK_STOCK_DND = "gtk-dnd";
	public static final String GTK_STOCK_DND_MULTIPLE = "gtk-dnd-multiple";
	public static final String GTK_STOCK_EDIT = "gtk-edit";
	public static final String GTK_STOCK_EXECUTE = "gtk-execute";
	public static final String GTK_STOCK_FILE = "gtk-file";
	public static final String GTK_STOCK_FIND = "gtk-find";
	public static final String GTK_STOCK_FIND_AND_REPLACE = "gtk-find-and-replace";
	public static final String GTK_STOCK_FLOPPY = "gtk-floppy";
	public static final String GTK_STOCK_FULLSCREEN = "gtk-fullscreen";
	public static final String GTK_STOCK_GOTO_BOTTOM = "gtk-goto-bottom";
	public static final String GTK_STOCK_GOTO_FIRST = "gtk-goto-first";
	public static final String GTK_STOCK_GOTO_LAST = "gtk-goto-last";
	public static final String GTK_STOCK_GOTO_TOP = "gtk-goto-top";
	public static final String GTK_STOCK_GO_BACK = "gtk-go-back";
	public static final String GTK_STOCK_GO_DOWN = "gtk-go-down";
	public static final String GTK_STOCK_GO_FORWARD = "gtk-go-forward";
	public static final String GTK_STOCK_GO_UP = "gtk-go-up";
	public static final String GTK_STOCK_HARDDISK = "gtk-harddisk";
	public static final String GTK_STOCK_HELP = "gtk-help";
	public static final String GTK_STOCK_HOME = "gtk-home";
	public static final String GTK_STOCK_INDEX = "gtk-index";
	public static final String GTK_STOCK_INDENT = "gtk-indent";
	public static final String GTK_STOCK_INFO = "gtk-info";
	public static final String GTK_STOCK_ITALIC = "gtk-italic";
	public static final String GTK_STOCK_JUMP_TO = "gtk-jump-to";
	public static final String GTK_STOCK_JUSTIFY_CENTER = "gtk-justify-center";
	public static final String GTK_STOCK_JUSTIFY_FILL = "gtk-justify-fill";
	public static final String GTK_STOCK_JUSTIFY_LEFT = "gtk-justify-left";
	public static final String GTK_STOCK_JUSTIFY_RIGHT = "gtk-justify-right";
	public static final String GTK_STOCK_LEAVE_FULLSCREEN = "gtk-leave-fullscreen";
	public static final String GTK_STOCK_MISSING_IMAGE = "gtk-missing-image";
	public static final String GTK_STOCK_MEDIA_FORWARD = "gtk-media-forward";
	public static final String GTK_STOCK_MEDIA_NEXT = "gtk-media-next";
	public static final String GTK_STOCK_MEDIA_PAUSE = "gtk-media-pause";
	public static final String GTK_STOCK_MEDIA_PLAY = "gtk-media-play";
	public static final String GTK_STOCK_MEDIA_PREVIOUS = "gtk-media-previous";
	public static final String GTK_STOCK_MEDIA_RECORD = "gtk-media-record";
	public static final String GTK_STOCK_MEDIA_REWIND = "gtk-media-rewind";
	public static final String GTK_STOCK_MEDIA_STOP = "gtk-media-stop";
	public static final String GTK_STOCK_NETWORK = "gtk-network";
	public static final String GTK_STOCK_NEW = "gtk-new";
	public static final String GTK_STOCK_NO = "gtk-no";
	public static final String GTK_STOCK_OK = "gtk-ok";
	public static final String GTK_STOCK_OPEN = "gtk-open";
	public static final String GTK_STOCK_ORIENTATION_PORTRAIT = "gtk-orientation-portrait";
	public static final String GTK_STOCK_ORIENTATION_LANDSCAPE = "gtk-orientation-landscape";
	public static final String GTK_STOCK_ORIENTATION_REVERSE_LANDSCAPE = "gtk-orientation-reverse-landscape";
	public static final String GTK_STOCK_ORIENTATION_REVERSE_PORTRAIT = "gtk-orientation-reverse-portrait";
	public static final String GTK_STOCK_PAGE_SETUP = "gtk-page-setup";
	public static final String GTK_STOCK_PASTE = "gtk-paste";
	public static final String GTK_STOCK_PREFERENCES = "gtk-preferences";
	public static final String GTK_STOCK_PRINT = "gtk-print";
	public static final String GTK_STOCK_PRINT_ERROR = "gtk-print-error";
	public static final String GTK_STOCK_PRINT_PAUSED = "gtk-print-paused";
	public static final String GTK_STOCK_PRINT_PREVIEW = "gtk-print-preview";
	public static final String GTK_STOCK_PRINT_REPORT = "gtk-print-report";
	public static final String GTK_STOCK_PRINT_WARNING = "gtk-print-warning";
	public static final String GTK_STOCK_PROPERTIES = "gtk-properties";
	public static final String GTK_STOCK_QUIT = "gtk-quit";
	public static final String GTK_STOCK_REDO = "gtk-redo";
	public static final String GTK_STOCK_REFRESH = "gtk-refresh";
	public static final String GTK_STOCK_REMOVE = "gtk-remove";
	public static final String GTK_STOCK_REVERT_TO_SAVED = "gtk-revert-to-saved";
	public static final String GTK_STOCK_SAVE = "gtk-save";
	public static final String GTK_STOCK_SAVE_AS = "gtk-save-as";
	public static final String GTK_STOCK_SELECT_ALL = "gtk-select-all";
	public static final String GTK_STOCK_SELECT_COLOR = "gtk-select-color";
	public static final String GTK_STOCK_SELECT_FONT = "gtk-select-font";
	public static final String GTK_STOCK_SORT_ASCENDING = "gtk-sort-ascending";
	public static final String GTK_STOCK_SORT_DESCENDING = "gtk-sort-descending";
	public static final String GTK_STOCK_SPELL_CHECK = "gtk-spell-check";
	public static final String GTK_STOCK_STOP = "gtk-stop";
	public static final String GTK_STOCK_STRIKETHROUGH = "gtk-strikethrough";
	public static final String GTK_STOCK_UNDELETE = "gtk-undelete";
	public static final String GTK_STOCK_UNDERLINE = "gtk-underline";
	public static final String GTK_STOCK_UNDO = "gtk-undo";
	public static final String GTK_STOCK_UNINDENT = "gtk-unindent";
	public static final String GTK_STOCK_YES = "gtk-yes";
	public static final String GTK_STOCK_ZOOM_100 = "gtk-zoom-100";
	public static final String GTK_STOCK_ZOOM_FIT = "gtk-zoom-fit";
	public static final String GTK_STOCK_ZOOM_IN = "gtk-zoom-in";
	public static final String GTK_STOCK_ZOOM_OUT = "gtk-zoom-out";

	// GTK_STOCK_ endd

	public static native int gtk_image_new();

	public static native int gtk_image_new_from_file(String filename);

	// GtkIconSize start
	public static final int GTK_ICON_SIZE_INVALID = findConst("GTK_ICON_SIZE_INVALID");
	public static final int GTK_ICON_SIZE_MENU = findConst("GTK_ICON_SIZE_MENU");
	public static final int GTK_ICON_SIZE_SMALL_TOOLBAR = findConst("GTK_ICON_SIZE_SMALL_TOOLBAR");
	public static final int GTK_ICON_SIZE_LARGE_TOOLBAR = findConst("GTK_ICON_SIZE_LARGE_TOOLBAR");
	public static final int GTK_ICON_SIZE_BUTTON = findConst("GTK_ICON_SIZE_BUTTON");
	public static final int GTK_ICON_SIZE_DND = findConst("GTK_ICON_SIZE_DND");
	public static final int GTK_ICON_SIZE_DIALOG = findConst("GTK_ICON_SIZE_DIALOG");

	// GtkIconSize end

	/**
	 * 
	 * @param stock_id
	 *            可选值来自GTK_STOCK_*
	 * @param size
	 *            可选值来自 GTK_ICON_SIZE*
	 * @return
	 */
	public static native int gtk_image_new_from_stock(String stock_id, int size);

	// 资源文件和释放的临时文件的对应关系缓存
	static HashMap<String, String> resourceCache = new HashMap<String, String>();

	
	/**
	 * 把资源resName保存到临时文件夹，返回值为临时文件的全路径
	 * 
	 * @param resName
	 *            com/rupeng/a.mp3这样的格式，注意不以/开头
	 * @return
	 */
	public synchronized static String saveResourceToTemp(String resName)
	{
		// 如果resname之前释放到过临时文件，并且临时文件还存在，则不再重复释放，直接返回之前的文件，以提高性能
		if (resourceCache.containsKey(resName))
		{
			String cachedFileName = resourceCache.get(resName);
			if (new File(cachedFileName).exists())
			{
				return cachedFileName;
			} else
			{
				resourceCache.remove(resName);
			}
		}
		// 获取文件扩展名
		String[] strs = resName.split("\\.");
		String extesion = strs[strs.length - 1];

		InputStream inStream = GTK.class.getClassLoader().getResourceAsStream(resName);//ClassLoader.getSystemResourceAsStream(resName);
		if (inStream == null)
		{
			throw new RuntimeException("找不到资源" + resName);
		}
		try
		{			
			//java 1.6后，运行在Windows下，如果启用了Guest账户，则由于createTempFile调用了SecureRandom 会卡5秒钟
			//因此第一次运行createTempFile会非常慢
			//参考：http://stackoverflow.com/questions/2608763/why-does-first-call-to-java-io-file-createtempfilestring-string-file-take-5-se
			//File tempFile = File.createTempFile("temp", "." + extesion);
			//因此不用createTempFile，改用存到项目的restemp文件夹下
			File resDir = new File(System.getProperty("user.dir"), "gtk/temp");// *.dll放的文件夹
			if (!resDir.exists())
			{
				resDir.mkdirs();
			}
			inStream.mark(Integer.MAX_VALUE);
			String md5 = Utils.getMD5(inStream);//用md5值做文件名
			inStream.reset();//指针复位到mark标记的位置，便于后面保存文件	
			
			// 如果不加扩展名，mci_send_command则无法识别文件类型
			File tempFile = new File(resDir,md5+"."+extesion);
			FileOutputStream outStream = new FileOutputStream(tempFile);
			Utils.copy(inStream,outStream);
			inStream.close();
			outStream.close();
			
			resourceCache.put(resName, tempFile.getAbsolutePath());
			return tempFile.getAbsolutePath();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从java资源中加载资源图片生成image。不是gtk提供的native方法
	 * 
	 * @param resName
	 * @return
	 */
	public static int gtk_image_new_from_resource(String resName)
	{
		return gtk_image_new_from_file(saveResourceToTemp(resName));
	}

	public static native void gtk_image_clear(int image);

	public static native void gtk_image_set_from_stock(int image,
			String stockid, int size);

	public static native void gtk_image_set_from_file(int image, String filename);

	public static void gtk_image_set_from_resource(int image, String resName)
	{
		gtk_image_set_from_file(image, saveResourceToTemp(resName));
	}

	public static native int gtk_radio_button_new(int group);

	public static native int gtk_radio_button_new_with_label(int group,
			String label);

	public static native int gtk_radio_button_new_with_label_from_widget(
			int radio_group_member, String label);

	public static native void gtk_range_set_value(int range, double value);

	public static native double gtk_range_get_value(int range);

	public static native int gtk_scale_new_with_range(int orientation,
			double min, double max, double step);

	public static native boolean gtk_scale_get_draw_value(int scale);

	public static native void gtk_scale_set_draw_value(int scale,
			boolean draw_value);

	public static native int gtk_scrolled_window_new();

	/**
	 *@deprecated gtk_scrolled_window_add_with_viewport has been deprecated since version 3.8 and should not be used in newly-written code.
	 *gtk_container_add() will now automatically add a GtkViewport if the child doesn’t implement GtkScrollable.
	 * @param scrolled_window
	 * @param child
	 */
	@Deprecated 
	public static native void gtk_scrolled_window_add_with_viewport(
			int scrolled_window, int child);

	public static native int gtk_progress_bar_new();

	public static native void gtk_progress_bar_set_fraction(int pbar,
			double fraction);

	public static native double gtk_progress_bar_get_fraction(int pbar);

	public static native void gtk_progress_bar_set_text(int pbar, String text);

	public static native String gtk_progress_bar_get_text(int pbar);

	public static native void gtk_progress_bar_set_show_text(int pbar,
			boolean show_text);

	public static native boolean gtk_progress_bar_get_show_text(int pbar);

	public static native int gtk_notebook_new();

	public static native int gtk_notebook_append_page(int notebook, int child,
			int tab_label);

	// GtkPositionType start
	public static final int GTK_POS_TOP = findConst("GTK_POS_TOP");
	public static final int GTK_POS_BOTTOM = findConst("GTK_POS_BOTTOM");
	public static final int GTK_POS_LEFT = findConst("GTK_POS_LEFT");
	public static final int GTK_POS_RIGHT = findConst("GTK_POS_RIGHT");

	// GtkPositionType end

	public static native void gtk_notebook_set_tab_pos(int notebook,
			int position);

	public static native int gtk_menu_bar_new();

	public static native int gtk_menu_item_new();

	public static native int gtk_menu_item_new_with_label(String label);

	public static native int gtk_menu_item_new_with_mnemonic(String label);

	public static native void gtk_menu_item_set_submenu(int menu_item,
			int submenu);

	public static native int gtk_menu_new();

	public static native void gtk_menu_shell_append(int menu, int child);

	public static native void gtk_menu_attach_to_widget(int menu,
			int attach_widget);

	public static native void gtk_menu_popup(int menu);

	/**
	 * 
	 * @param widget
	 * @param state
	 *            GtkStateType
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static native void gtk_widget_override_background_color(int widget,
			int state, int red, int green, int blue);

	public static native void gtk_widget_override_color(int widget, int state,
			int red, int green, int blue);

	// GtkStateType start
	public static final int GTK_STATE_NORMAL = findConst("GTK_STATE_NORMAL");
	public static final int GTK_STATE_ACTIVE = findConst("GTK_STATE_ACTIVE");
	public static final int GTK_STATE_PRELIGHT = findConst("GTK_STATE_PRELIGHT");
	public static final int GTK_STATE_SELECTED = findConst("GTK_STATE_SELECTED");
	public static final int GTK_STATE_INSENSITIVE = findConst("GTK_STATE_INSENSITIVE");

	// GtkStateType end

	public static native int gtk_tree_iter_new();

	public static native int gtk_tree_iter_free(int iter);

	// GtkListStore实现了GtkTreeModel接口
	public static native boolean gtk_tree_model_get_iter_first(int tree_model,
			int iter);

	public static native boolean gtk_tree_model_iter_previous(int tree_model,
			int iter);

	public static native boolean gtk_tree_model_iter_next(int tree_model,
			int iter);

	public static native String gtk_tree_model_get_value(int tree_model,
			int iter, int columnIndex);

	public static native boolean gtk_list_store_iter_is_valid(int tree_model,
			int iter);

	public static native int gtk_list_store_new(int n_columns);

	public static native void gtk_list_store_append(int list_store, int iter);

	public static native void gtk_list_store_set_value(int list_store,
			int iter, int columnIndex, String value);

	public static native void gtk_list_store_clear(int list_store);

	public static native int gtk_tree_store_new(int n_columns);

	public static native void gtk_tree_store_append(int tree_store, int iter,
			int parentIter);

	public static native void gtk_tree_store_set_value(int tree_store,
			int iter, int column, String value);

	public static native void gtk_tree_store_clear(int list_store);

	public static native int gtk_cell_renderer_text_new();

	public static native int gtk_tree_view_column_new_with_attributes(
			String title, int cellRender, int columnIndex);

	public static native void gtk_tree_view_column_set_visible(int tree_column,
			boolean visible);

	public static native boolean gtk_tree_view_column_get_visible(
			int tree_column);

	public static native void gtk_tree_view_column_set_resizable(
			int tree_column, boolean resizable);

	public static native boolean gtk_tree_view_column_get_resizable(
			int tree_column);

	public static native void gtk_tree_view_column_set_reorderable(
			int tree_column, boolean reorderable);

	public static native boolean gtk_tree_view_column_get_reorderable(
			int tree_column);

	public static native void gtk_tree_view_column_set_sort_column_id(
			int tree_column, int sort_column_id);

	public static native int gtk_tree_view_append_column(int tree_view,
			int column);

	public static native void gtk_tree_view_remove_column(int tree_view,
			int column);

	public static native int gtk_tree_view_get_n_columns(int tree_view);

	public static native int gtk_tree_view_get_column(int tree_view, int n);

	public static native void gtk_tree_view_set_model(int tree_view, int model);

	public static native int gtk_tree_view_new();

	public static native boolean gtk_tree_view_get_headers_visible(int tree_view);

	public static native void gtk_tree_view_set_headers_visible(int tree_view,
			boolean headers_visible);

	public static native int gtk_tree_view_get_selection(int tree_view);

	// GtkSelectionMode start
	public static final int GTK_SELECTION_NONE = findConst("GTK_SELECTION_NONE");
	public static final int GTK_SELECTION_SINGLE = findConst("GTK_SELECTION_SINGLE");
	public static final int GTK_SELECTION_BROWSE = findConst("GTK_SELECTION_BROWSE");
	public static final int GTK_SELECTION_MULTIPLE = findConst("GTK_SELECTION_MULTIPLE");

	// GtkSelectionMode end

	/**
	 * 
	 * @param tree_selection
	 *            来自于gtk_tree_view_get_selection的返回值
	 * @param type
	 *            GtkSelectionMode
	 */
	public static native void gtk_tree_selection_set_mode(int tree_selection,
			int type);

	public static native int gtk_tree_selection_get_mode(int tree_selection);

	public static native int[] gtk_tree_view_get_selection_indices(int tree_view);

	// GtkFileChooserAction start
	public static final int GTK_FILE_CHOOSER_ACTION_OPEN = findConst("GTK_FILE_CHOOSER_ACTION_OPEN");
	public static final int GTK_FILE_CHOOSER_ACTION_SAVE = findConst("GTK_FILE_CHOOSER_ACTION_SAVE");
	public static final int GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER = findConst("GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER");
	public static final int GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER = findConst("GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER");

	// GtkFileChooserAction end

	public static native int gtk_file_chooser_dialog_new(String title,
			int parentWindow, int action, String first_button_text);

	public static native void gtk_file_chooser_set_select_multiple(int chooser,
			boolean select_multiple);

	public static native void gtk_file_chooser_set_current_name(int chooser,
			String name);

	public static native String gtk_file_chooser_get_filename(int chooser);

	public static native String[] gtk_file_chooser_get_filenames(int chooser);

	public static native void gtk_file_chooser_set_do_overwrite_confirmation(
			int chooser, boolean do_overwrite_confirmation);

	public static native void gtk_file_chooser_set_create_folders(int chooser,
			boolean create_folders);

	public static native int gtk_file_filter_new();

	public static native void gtk_file_filter_set_name(int filter, String name);

	public static native void gtk_file_filter_add_pattern(int filter,
			String pattern);

	/**
	 * 设定过滤器
	 * 
	 * @param chooser
	 * @param filter
	 *            使用gtk_file_filter_new创建的
	 */
	public static native void gtk_file_chooser_add_filter(int chooser,
			int filter);

	public static native int gtk_switch_new();

	public static native void gtk_switch_set_active(int sw, boolean is_active);

	public static native boolean gtk_switch_get_active(int sw);

	/**
	 * 创建一个文本迭代器
	 * 
	 * @return
	 */
	public static native int gtk_text_iter_new();

	public static native void gtk_text_iter_free(int iter);

	/**
	 * 移动迭代器到最尾部
	 * 
	 * @param iter
	 */
	public static native void gtk_text_iter_forward_to_end(int iter);

	/**
	 * Returns the line number containing the iterator. Lines in a GtkTextBuffer
	 * are numbered beginning with 0 for the first line in the buffer.
	 * 
	 * @param iter
	 * @return
	 */
	public static native int gtk_text_iter_get_line(int iter);

	/**
	 * 向尾部移动一个字符
	 * 
	 * @param iter
	 */
	public static native void gtk_text_iter_forward_char(int iter);

	/**
	 * 向头部移动一个字符
	 * 
	 * @param iter
	 */
	public static native void gtk_text_iter_backward_char(int iter);

	public static native int gtk_text_view_new();

	/**
	 * 
	 * @param text_view
	 * @param buffer
	 *            gtk_text_buffer_get_text/gtk_text_buffer_set_text
	 */
	// public static native void gtk_text_view_set_buffer(int text_view, int
	// buffer);

	public static native int gtk_text_view_get_buffer(int text_view);

	public static native String gtk_text_buffer_get_text(int buffer);

	public static native void gtk_text_buffer_set_text(int buffer, String text);

	/**
	 * 在迭代器iter位置插入文本text
	 * 
	 * @param buffer
	 * @param iter
	 * @param text
	 */
	public static native void gtk_text_buffer_insert(int buffer, int iter,
			String text);

	/**
	 * 得到在buffer中第line_number行，第byte_index个字节的迭代器，修改反映到iter中
	 * 
	 * @param buffer
	 * @param iter
	 * @param line_number
	 * @param byte_index
	 */
	public static native void gtk_text_buffer_get_iter_at_line_index(
			int buffer, int iter, int line_number, int byte_index);

	/**
	 * 得到在buffer中第char_offset个字符的迭代器
	 * 
	 * @param buffer
	 * @param iter
	 * @param char_offset
	 */
	public static native void gtk_text_buffer_get_iter_at_offset(int buffer,
			int iter, int char_offset);

	public static native void gtk_text_buffer_get_iter_at_line(int buffer,
			int iter, int line_number);

	/**
	 * 得到起始位置的iter
	 * 
	 * @param buffer
	 * @param iter
	 */
	public static native void gtk_text_buffer_get_start_iter(int buffer,
			int iter);

	/**
	 * 得到结束位置的iter
	 * 
	 * @param buffer
	 * @param iter
	 */
	public static native void gtk_text_buffer_get_end_iter(int buffer, int iter);

	public static native void gtk_text_view_set_wrap_mode(int text_view,
			int wrap_mode);

	public static native int gtk_text_view_get_wrap_mode(int text_view);

	// GtkWrapMode start
	public static final int GTK_WRAP_NONE = findConst("GTK_WRAP_NONE");
	public static final int GTK_WRAP_CHAR = findConst("GTK_WRAP_CHAR");
	public static final int GTK_WRAP_WORD = findConst("GTK_WRAP_WORD");
	public static final int GTK_WRAP_WORD_CHAR = findConst("GTK_WRAP_WORD_CHAR");

	// GtkWrapMode end

	public static native void gtk_text_view_set_editable(int text_view,
			boolean setting);

	public static native boolean gtk_text_view_get_editable(int text_view);

	// 当mci_get_status 的 status=MCI_STATUS_MODE的时候，取值为MCI_MODE_* start
	public static final int MCI_MODE_NOT_READY = findConst("MCI_MODE_NOT_READY");
	public static final int MCI_MODE_STOP = findConst("MCI_MODE_STOP");
	public static final int MCI_MODE_PLAY = findConst("MCI_MODE_PLAY");
	public static final int MCI_MODE_RECORD = findConst("MCI_MODE_RECORD");
	public static final int MCI_MODE_SEEK = findConst("MCI_MODE_SEEK");
	public static final int MCI_MODE_PAUSE = findConst("MCI_MODE_PAUSE");
	public static final int MCI_MODE_OPEN = findConst("MCI_MODE_OPEN");
	// end

	public static final int MCI_STATUS_ITEM = findConst("MCI_STATUS_ITEM");
	public static final int MCI_STATUS_START = findConst("MCI_STATUS_START");
	public static final int MCI_STATUS_LENGTH = findConst("MCI_STATUS_LENGTH");// 获取音乐的长度
	public static final int MCI_STATUS_POSITION = findConst("MCI_STATUS_POSITION");// 获取当前播放进度
	public static final int MCI_STATUS_NUMBER_OF_TRACKS = findConst("MCI_STATUS_NUMBER_OF_TRACKS");
	public static final int MCI_STATUS_MODE = findConst("MCI_STATUS_MODE");
	public static final int MCI_STATUS_MEDIA_PRESENT = findConst("MCI_STATUS_MEDIA_PRESENT");
	public static final int MCI_STATUS_TIME_FORMAT = findConst("MCI_STATUS_TIME_FORMAT");
	public static final int MCI_STATUS_READY = findConst("MCI_STATUS_READY");
	public static final int MCI_STATUS_CURRENT_TRACK = findConst("MCI_STATUS_CURRENT_TRACK");

	// 音乐播放API
	public static final native int mci_open(String filename);

	public static final int mci_open_from_resource(String resourceName)
	{
		String tempFileName = saveResourceToTemp(resourceName);
		return mci_open(tempFileName);
	}

	/**
	 * 是否重复
	 * 
	 * @param deviceId
	 *            mci_open的返回值
	 * @param isRepeat
	 */
	public static final native void mci_play(int deviceId, boolean isRepeat);

	public static final native void mci_close(int deviceId);

	public static final native void mci_pause(int deviceId);

	/**
	 * 跳转到toMs毫秒
	 * 
	 * @param deviceId
	 * @param toMS
	 * @return
	 */
	public static final native void mci_seek(int deviceId, long toMS);

	/**
	 * 返回多媒体的状态信息
	 * 
	 * @param deviceId
	 * @param status
	 *            可选值MCI_STATUS_
	 * @return 当status=MCI_STATUS_MODE的时候，取值为MCI_MODE_*
	 */
	public static final native long mci_get_status(int deviceId, int status);

	static native int _g_timeout_add(int interval, int timeoutId);// 内部使用

	static native int _g_idle_add(int idleId);// 内部使用

	/**
	 * 添加定时器
	 * 
	 * @param interval
	 *            每隔多少毫秒执行一次
	 * @param function
	 *            要执行的函数
	 * @param data
	 *            传递给function的最后一个参数
	 * @return
	 */
	public static int g_timeout_add(int interval, IGSourceFunc function,
			Object data)
	{
		return TimeoutManager.Instance.add(interval, function, data);
	}

	/**
	 * 添加空闲执行函数
	 * 
	 * @param function
	 * @param data
	 * @return
	 */
	public static int g_idle_add(IGSourceFunc function, Object data)
	{
		return GIdleManager.Instance.add(function, data);
	}

	// GdkModifierType start
	public static final int GDK_SHIFT_MASK = findConst("GDK_SHIFT_MASK");
	public static final int GDK_CONTROL_MASK = findConst("GDK_CONTROL_MASK");

	// GdkModifierType end

	/**
	 * 
	 * @param event
	 *            GdkEvent
	 * @param state
	 *            GdkModifierType
	 * @return
	 */
	public static final native boolean gdk_event_get_state(int event, int state);

	public static final native double gdk_event_get_coords_x(int event);

	public static final native double gdk_event_get_coords_y(int event);

	// GdkEventType start
	public static final int GDK_NOTHING = findConst("GDK_NOTHING");
	public static final int GDK_DELETE = findConst("GDK_DELETE");
	public static final int GDK_DESTROY = findConst("GDK_DESTROY");
	public static final int GDK_EXPOSE = findConst("GDK_EXPOSE");
	public static final int GDK_MOTION_NOTIFY = findConst("GDK_MOTION_NOTIFY");
	public static final int GDK_BUTTON_PRESS = findConst("GDK_BUTTON_PRESS");
	public static final int GDK_2BUTTON_PRESS = findConst("GDK_2BUTTON_PRESS");
	public static final int GDK_DOUBLE_BUTTON_PRESS = findConst("GDK_DOUBLE_BUTTON_PRESS");
	public static final int GDK_3BUTTON_PRESS = findConst("GDK_3BUTTON_PRESS");
	public static final int GDK_TRIPLE_BUTTON_PRESS = findConst("GDK_TRIPLE_BUTTON_PRESS");
	public static final int GDK_BUTTON_RELEASE = findConst("GDK_BUTTON_RELEASE");
	public static final int GDK_KEY_PRESS = findConst("GDK_KEY_PRESS");
	public static final int GDK_KEY_RELEASE = findConst("GDK_KEY_RELEASE");
	public static final int GDK_ENTER_NOTIFY = findConst("GDK_ENTER_NOTIFY");
	public static final int GDK_LEAVE_NOTIFY = findConst("GDK_LEAVE_NOTIFY");
	public static final int GDK_FOCUS_CHANGE = findConst("GDK_FOCUS_CHANGE");
	public static final int GDK_CONFIGURE = findConst("GDK_CONFIGURE");
	public static final int GDK_MAP = findConst("GDK_MAP");
	public static final int GDK_UNMAP = findConst("GDK_UNMAP");
	public static final int GDK_PROPERTY_NOTIFY = findConst("GDK_PROPERTY_NOTIFY");
	public static final int GDK_SELECTION_CLEAR = findConst("GDK_SELECTION_CLEAR");
	public static final int GDK_SELECTION_REQUEST = findConst("GDK_SELECTION_REQUEST");
	public static final int GDK_SELECTION_NOTIFY = findConst("GDK_SELECTION_NOTIFY");
	public static final int GDK_PROXIMITY_IN = findConst("GDK_PROXIMITY_IN");
	public static final int GDK_PROXIMITY_OUT = findConst("GDK_PROXIMITY_OUT");
	public static final int GDK_DRAG_ENTER = findConst("GDK_DRAG_ENTER");
	public static final int GDK_DRAG_LEAVE = findConst("GDK_DRAG_LEAVE");
	public static final int GDK_DRAG_MOTION = findConst("GDK_DRAG_MOTION");
	public static final int GDK_DRAG_STATUS = findConst("GDK_DRAG_STATUS");
	public static final int GDK_DROP_START = findConst("GDK_DROP_START");
	public static final int GDK_DROP_FINISHED = findConst("GDK_DROP_FINISHED");
	public static final int GDK_CLIENT_EVENT = findConst("GDK_CLIENT_EVENT");
	public static final int GDK_VISIBILITY_NOTIFY = findConst("GDK_VISIBILITY_NOTIFY");
	public static final int GDK_SCROLL = findConst("GDK_SCROLL");
	public static final int GDK_WINDOW_STATE = findConst("GDK_WINDOW_STATE");
	public static final int GDK_SETTING = findConst("GDK_SETTING");
	public static final int GDK_OWNER_CHANGE = findConst("GDK_OWNER_CHANGE");
	public static final int GDK_GRAB_BROKEN = findConst("GDK_GRAB_BROKEN");
	public static final int GDK_DAMAGE = findConst("GDK_DAMAGE");
	public static final int GDK_TOUCH_BEGIN = findConst("GDK_TOUCH_BEGIN");
	public static final int GDK_TOUCH_UPDATE = findConst("GDK_TOUCH_UPDATE");
	public static final int GDK_TOUCH_END = findConst("GDK_TOUCH_END");
	public static final int GDK_TOUCH_CANCEL = findConst("GDK_TOUCH_CANCEL");

	// GdkEventType end

	// 以下是自定义的对事件GtkEvent进行处理的方法
	/**
	 * 
	 * @param event
	 * @return 返回值就是GdkEventType
	 */
	public static final native int gdk_event_get_type(int event);

	public static final native int gdk_event_get_keyval(int event);

	public static final native int gdk_event_get_keycode(int event);

	public static final native int gdk_event_get_button(int event);

	// 工具栏 start
	public static final native int gtk_toolbar_new();

	public static final native void gtk_toolbar_insert(int toolbar, int item,
			int pos);

	public static final native int gtk_tool_item_new();

	public static final native void gtk_tool_item_set_tooltip_text(int toolbar,
			String text);

	public static final native int gtk_tool_button_new(int icon_widget,
			String label);

	public static final native void gtk_tool_button_set_label(int button,
			String label);

	public static final native void gtk_tool_button_set_stock_id(int button,
			String stock_id);

	public static final native void gtk_tool_button_set_icon_widget(int button,
			int widget);

	// 菜单样式的工具栏按钮
	public static final native int gtk_menu_tool_button_new(int icon_widget,
			String label);

	public static final native int gtk_menu_tool_button_new_from_stock(
			String stock_id);

	public static final native void gtk_menu_tool_button_set_menu(int button,
			int menu);

	// 工具栏end

	public static final native int gtk_event_box_new();

	// GdkEventMask start
	public static final int GDK_EXPOSURE_MASK = findConst("GDK_EXPOSURE_MASK");
	public static final int GDK_POINTER_MOTION_MASK = findConst("GDK_POINTER_MOTION_MASK");
	public static final int GDK_POINTER_MOTION_HINT_MASK = findConst("GDK_POINTER_MOTION_HINT_MASK");
	public static final int GDK_BUTTON_MOTION_MASK = findConst("GDK_BUTTON_MOTION_MASK");
	public static final int GDK_BUTTON1_MOTION_MASK = findConst("GDK_BUTTON1_MOTION_MASK");
	public static final int GDK_BUTTON2_MOTION_MASK = findConst("GDK_BUTTON2_MOTION_MASK");
	public static final int GDK_BUTTON3_MOTION_MASK = findConst("GDK_BUTTON3_MOTION_MASK");
	public static final int GDK_BUTTON_PRESS_MASK = findConst("GDK_BUTTON_PRESS_MASK");
	public static final int GDK_BUTTON_RELEASE_MASK = findConst("GDK_BUTTON_RELEASE_MASK");
	public static final int GDK_KEY_PRESS_MASK = findConst("GDK_KEY_PRESS_MASK");
	public static final int GDK_KEY_RELEASE_MASK = findConst("GDK_KEY_RELEASE_MASK");
	public static final int GDK_ENTER_NOTIFY_MASK = findConst("GDK_ENTER_NOTIFY_MASK");
	public static final int GDK_LEAVE_NOTIFY_MASK = findConst("GDK_LEAVE_NOTIFY_MASK");
	public static final int GDK_FOCUS_CHANGE_MASK = findConst("GDK_FOCUS_CHANGE_MASK");
	public static final int GDK_STRUCTURE_MASK = findConst("GDK_STRUCTURE_MASK");
	public static final int GDK_PROPERTY_CHANGE_MASK = findConst("GDK_PROPERTY_CHANGE_MASK");
	public static final int GDK_VISIBILITY_NOTIFY_MASK = findConst("GDK_VISIBILITY_NOTIFY_MASK");
	public static final int GDK_PROXIMITY_IN_MASK = findConst("GDK_PROXIMITY_IN_MASK");
	public static final int GDK_PROXIMITY_OUT_MASK = findConst("GDK_PROXIMITY_OUT_MASK");
	public static final int GDK_SUBSTRUCTURE_MASK = findConst("GDK_SUBSTRUCTURE_MASK");
	public static final int GDK_SCROLL_MASK = findConst("GDK_SCROLL_MASK");
	public static final int GDK_TOUCH_MASK = findConst("GDK_TOUCH_MASK");
	public static final int GDK_SMOOTH_SCROLL_MASK = findConst("GDK_SMOOTH_SCROLL_MASK");
	public static final int GDK_ALL_EVENTS_MASK = findConst("GDK_ALL_EVENTS_MASK");

	// GdkEventMask end

	public static final native void gtk_widget_add_events(int widget, int events);

	/**
	 * 把widget画到cairo_t *上（截图）
	 * 
	 * @param widget
	 * @param cr
	 *            cairo_t *
	 */
	public static final native void gtk_widget_draw(int widget, int cr);

	// 重绘
	public static final native void gtk_widget_queue_draw(int widget);

	public static final native int gtk_widget_get_allocated_width(int widget);

	public static final native int gtk_widget_get_allocated_height(int widget);

	public static final native int gtk_status_icon_new();

	public static final native void gtk_status_icon_set_from_file(
			int status_icon, String filename);

	public static final native void gtk_status_icon_set_from_stock(
			int status_icon, String stock_id);

	public static final void gtk_status_icon_set_from_resource(int status_icon,
			String resName)
	{
		gtk_status_icon_set_from_file(status_icon, saveResourceToTemp(resName));
	}

	public static final native void gtk_status_icon_set_tooltip_text(
			int status_icon, String text);

	public static final native void gtk_status_icon_set_visible(
			int status_icon, boolean visible);

	public static final native boolean gtk_status_icon_get_visible(
			int status_icon);

	public static final native void g_error(String message);

	public static final native int gtk_drawing_area_new();
}
