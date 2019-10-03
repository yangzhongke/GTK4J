package com.rupeng.ogtk;

public class GtkRGB
{
	private double red;
	private double green;
	private double blue;

	public GtkRGB()
	{
	}
	
	public GtkRGB(double red,double green,double blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public static final GtkRGB RED = new GtkRGB(1, 0, 0);
	public static final GtkRGB GREEN = new GtkRGB(0, 1, 0);
	public static final GtkRGB BLUE = new GtkRGB(0, 0, 1);
	public static final GtkRGB BLACK = new GtkRGB(0, 0, 0);
	public static final GtkRGB WHITE = new GtkRGB(1, 1, 1);
	public static final GtkRGB PURPLE = new GtkRGB(1,0,1);
	public static final GtkRGB CYAN = new GtkRGB(0,1,1);//青色
	public static final GtkRGB YELLOW = new GtkRGB(1,1,0);//青色

	public double getRed()
	{
		return red;
	}

	public void setRed(double red)
	{
		this.red = red;
	}

	public double getGreen()
	{
		return green;
	}

	public void setGreen(double green)
	{
		this.green = green;
	}

	public double getBlue()
	{
		return blue;
	}

	public void setBlue(double blue)
	{
		this.blue = blue;
	}

}
