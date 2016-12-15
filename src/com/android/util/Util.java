package com.android.util;

import java.util.Locale;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Classe auxiliar da aplicação. 
 */
public class Util {

	public static final String LOCALE_PT_BR = "pt_BR";
	public static final String LOCALE_PT = "pt";

	/**
	 * Faz com que a aplicação se torne totalmente full screen, retirando o título na parte superior da aplicação.
	 */
	public static void aplicarConfiguracoesIniciais(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * Retorna <code>true</code> se o Locale da aplicação for pt_BR.
	 */
	public static boolean isLocalePtBr() {
		boolean retorno = false;
		if (LOCALE_PT_BR.equals(Locale.getDefault().toString()) || LOCALE_PT.equals(Locale.getDefault().toString())) {
			retorno = true;
		}
		return retorno;
	}

}
