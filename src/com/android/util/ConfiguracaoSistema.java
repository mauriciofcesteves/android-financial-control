package com.android.util;

import com.android.bean.Usuario;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Quando a aplica��o � executada ou finalizada, esta classe carregar� e salvar� as configura��es
 * da aplica��o. 
 */
public class ConfiguracaoSistema {

	/**
	 * Vari�veis est�ticas utilizadas no controle da flag "Manter Usu�rio Logado".
	 * Quando a flag est� <code>true</code>, ent�o o c�digo do usu�rio � guardado no
	 * atributo inteiro (idManterUsuarioLogado). Quando a flag est� <code>false</code>,
	 * ent�o o atributo idManterUsuarioLogado fica igual a zero.
	 */
	public static final String MANTER_USUARIO_LOGADO = "manterUsuarioLogado";
	public static final String CODIGO_MANTER_USUARIO_LOGADO = "codigoManterUsuarioLogado";

	/**
	 * Este atributo ser� utilizado para retornar sempre o usu�rio logado no momento.
	 * Sempre que a aplica��o realizar o login, esse campo ser� preenchido com o
	 * c�digo do usu�rio logado para consultas posteriores.
	 */
	public static final String CODIGO_USUARIO_LOGADO = "codigoUsuarioLogado";

	//Quando true, a aplica��o n�o pede login/senha ao ser executada.
	//Ela carregar� o usu�rio que entrou por �ltimo.
	private boolean manterUsuarioLogado = false;
	private int idManterUsuarioLogado = Usuario.INTEGER_ZERO;
	private int idUsuarioLogado = Usuario.INTEGER_ZERO;

	public ConfiguracaoSistema(Activity principal) {
		carregarConfiguracoes(principal);
	}

	/**
	 * Carrega as configura��es do sistema.
	 */
	public void carregarConfiguracoes(Activity principal) {
		SharedPreferences configuracoes = principal.getPreferences(Activity.MODE_PRIVATE);
		this.manterUsuarioLogado = configuracoes.getBoolean(MANTER_USUARIO_LOGADO, this.manterUsuarioLogado);
		this.idManterUsuarioLogado = configuracoes.getInt(CODIGO_MANTER_USUARIO_LOGADO, this.idManterUsuarioLogado);
		this.idUsuarioLogado = configuracoes.getInt(CODIGO_USUARIO_LOGADO, this.idUsuarioLogado);
	}
	
	/**
	 * Salva as configura��es do sistema.
	 */
	public void salvarConfiguracoes(Activity principal) {
		SharedPreferences configuracoes = principal.getPreferences(Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = configuracoes.edit();
		editor.putBoolean(MANTER_USUARIO_LOGADO, this.manterUsuarioLogado);
		editor.putInt(CODIGO_MANTER_USUARIO_LOGADO, this.idManterUsuarioLogado);
		editor.putInt(CODIGO_USUARIO_LOGADO, this.idUsuarioLogado);
		editor.commit();
	}

	public boolean isManterUsuarioLogado() {
		return manterUsuarioLogado;
	}

	public void setManterUsuarioLogado(boolean manterUsuarioLogado) {
		this.manterUsuarioLogado = manterUsuarioLogado;
	}

	public int getIdManterUsuarioLogado() {
		return idManterUsuarioLogado;
	}

	public void setIdManterUsuarioLogado(int idManterUsuarioLogado) {
		this.idManterUsuarioLogado = idManterUsuarioLogado;
	}

	public int getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(int idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	
}
