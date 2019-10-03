package com.bumimang.gtk4j.provselect;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class ProvinceSelect {

	static int cmbProv;
	static int cmbCity;
	
	public static void main(String[] args) {
		
		GTK.gtk_init();
		
		int window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "省市选择");
		GTK.gtk_widget_set_size_request(window, 0, 20);
		GTK.g_signal_connect(window, "destroy", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object) {
				GTK.gtk_main_quit();				
			}
		}, null);
		GTK.gtk_widget_show(window);
		
		int hbox = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		GTK.gtk_container_add(window, hbox);
		
		GTK.gtk_widget_show(hbox);
		
		cmbProv = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(cmbProv, "河南");
		GTK.gtk_combo_box_text_append_text(cmbProv, "河北");
		GTK.gtk_combo_box_text_append_text(cmbProv, "山东");		
		GTK.gtk_box_pack_start(hbox, cmbProv, false, false, 0);
		GTK.gtk_widget_show(cmbProv);
		GTK.g_signal_connect(cmbProv, "changed", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object) {
				String city = GTK.gtk_combo_box_text_get_active_text(cmbProv);
				GTK.gtk_combo_box_text_remove_all(cmbCity);
				if(city.equals("河南"))
				{
					GTK.gtk_combo_box_text_append_text(cmbCity, "郑州");
					GTK.gtk_combo_box_text_append_text(cmbCity, "洛阳");
					GTK.gtk_combo_box_text_append_text(cmbCity, "平顶山");
				}
				else if(city.equals("河北"))
				{
					GTK.gtk_combo_box_text_append_text(cmbCity, "石家庄");
					GTK.gtk_combo_box_text_append_text(cmbCity, "秦皇岛");
					GTK.gtk_combo_box_text_append_text(cmbCity, "邯郸");
				}
				else if(city.equals("山东"))
				{
					GTK.gtk_combo_box_text_append_text(cmbCity, "济南");
					GTK.gtk_combo_box_text_append_text(cmbCity, "青岛");
					GTK.gtk_combo_box_text_append_text(cmbCity, "潍坊");
				}
			}
		}, null);
		
		
		cmbCity = GTK.gtk_combo_box_text_new();
		GTK.gtk_box_pack_start(hbox, cmbCity, false, false, 0);
		GTK.gtk_widget_show(cmbCity);
		
		GTK.gtk_main();
	}

}
