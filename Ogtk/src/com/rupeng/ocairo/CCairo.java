package com.rupeng.ocairo;

import com.rupeng.gtk4j.Cairo;
import com.rupeng.ogtk.GtkRGB;

public class CCairo
{

	private int cr;
	
	public CCairo(int cr)
	{
		this.cr = cr;
	}

	public void setSourceRGB(GtkRGB rgb)
	{
		Cairo.cairo_set_source_rgb(cr, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
	}
	
	public void setLineWidth(double width)
	{
		Cairo.cairo_set_line_width(cr, width);
	}
	
	public void moveTo(double x, double y)
	{
		Cairo.cairo_move_to(cr, x, y);
	}
	
	public void lineTo(double x, double y)
	{
		Cairo.cairo_line_to(cr, x, y);
	}
	
	public void arc(double xc, double yc,
			double radius, double angle1, double angle2)
	{
		Cairo.cairo_arc(cr, xc, yc, radius, angle1, angle2);
	}
	
	public void circle(double centerX,double centerY,double radius)
	{
		arc(centerX,centerY,radius,0,2*Math.PI);
	}
	
	public void rectangle(double x, double y,
			double width, double height)
	{
		Cairo.cairo_rectangle(cr, x, y, width, height);
	}
	
	public void stroke()
	{
		Cairo.cairo_stroke(cr);
	}
	
	public void fill()
	{
		Cairo.cairo_fill(cr);
	}
	
	public void selectFontFace(String family,CFontSlant slant, boolean isBold)
	{
		Cairo.cairo_select_font_face(cr, family, slant.getValue(), 
				isBold?Cairo.CAIRO_FONT_WEIGHT_BOLD:Cairo.CAIRO_FONT_WEIGHT_NORMAL);
	}

	public void setFontSize(double size)
	{
		Cairo.cairo_set_font_size(cr, size);
	}

	public void showText(String utf8)
	{
		Cairo.cairo_show_text(cr, utf8);
	}
}
