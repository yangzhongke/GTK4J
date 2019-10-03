package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkFileChooserDialog extends GtkDialog
{
	public GtkFileChooserDialog(String title,GtkWindow parentWindow, GtkFileChooserAction action, 
			String first_button_text)
	{
		int id = GTK.gtk_file_chooser_dialog_new(title, parentWindow.getId(),
				action.getValue(), first_button_text);
		setId(id);
		if(action==GtkFileChooserAction.SAVE)
		{
			setDoOverwriteConfirmation(true);
		}
	}
	
	public void setSelectMultiple(boolean select_multiple)
	{
		GTK.gtk_file_chooser_set_select_multiple(getId(), select_multiple);
	}

	public void setCurrentName(String name)
	{
		GTK.gtk_file_chooser_set_current_name(getId(), name);
	}

	public String getFilename()
	{
		return GTK.gtk_file_chooser_get_filename(getId());
	}

	public String[] getFilenames()
	{
		return GTK.gtk_file_chooser_get_filenames(getId());
	}

	public void setDoOverwriteConfirmation(boolean do_overwrite_confirmation)
	{
		GTK.gtk_file_chooser_set_do_overwrite_confirmation(getId(), do_overwrite_confirmation);
	}

	public void setCreateFolders(boolean create_folders)
	{
		GTK.gtk_file_chooser_set_create_folders(getId(), create_folders);
	}
	
	public void addFilter(GtkFileFilter filter)
	{
		GTK.gtk_file_chooser_add_filter(getId(), filter.getId());
	}
}
