package com.rupeng.ocairo;

import com.rupeng.gtk4j.Cairo;

public enum CFontSlant
{
	NORMAL(Cairo.CAIRO_FONT_SLANT_NORMAL),
	ITALIC(Cairo.CAIRO_FONT_SLANT_ITALIC),
	OBLIQUE(Cairo.CAIRO_FONT_SLANT_OBLIQUE);
	private int value;
	
	public int getValue()
	{
		return value;
	}
	
	private CFontSlant(int value)
	{
		this.value = value;
	}
}
