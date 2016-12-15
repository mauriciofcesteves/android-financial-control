package com.android.util;

import java.util.Locale;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Classe auxiliar da aplica��o. 
 */
public class Util {

	public static final String LOCALE_PT_BR = "pt_BR";
	public static final String LOCALE_PT = "pt";

	/**
	 * Faz com que a aplica��o se torne totalmente full screen, retirando o t�tulo na parte superior da aplica��o.
	 */
	public static void aplicarConfiguracoesIniciais(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * Retorna <code>true</code> se o Locale da aplica��o for pt_BR.
	 */
	public static boolean isLocalePtBr() {
		boolean retorno = false;
		if (LOCALE_PT_BR.equals(Locale.getDefault().toString()) || LOCALE_PT.equals(Locale.getDefault().toString())) {
			retorno = true;
		}
		return retorno;
	}

}
