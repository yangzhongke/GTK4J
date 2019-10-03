package com.rupeng.ogtk;

public class GtkImageButton extends GtkButton
{
	private GtkImage img ;
	public GtkImageButton()
	{
		img = new GtkImage();
		img.show();		
		setImage(img);
	}
	
	public void setImageFromFile(String filename)
	{
		img.setFromFile(filename);
	}
	
	public void setImageFromStock(String stock_id,GtkIconSize iconsize)
	{
		img.setFromStock(stock_id, iconsize);
	}
	
	public void setImageFromResource(String resName)
	{
		img.setFromResource(resName);
	}
}
