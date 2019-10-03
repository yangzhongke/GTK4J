package com.rupeng.ogtk;

import com.rupeng.ocairo.CCairo;

public interface IDrawCallBack
{
	public void execute(int instance,CCairo cairo,Object userdata);
}
