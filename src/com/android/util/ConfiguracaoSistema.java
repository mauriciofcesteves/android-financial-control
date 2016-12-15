package com.android.util;

import com.android.bean.Usuario;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Quando a aplicação é executada ou finalizada, esta classe carregará e salvará as configurações
 * da aplicação. 
 */
public class ConfiguracaoSistema {

	/**
	 * Variáveis estáticas utilizadas no controle da flag "Manter Usuário Logado".
	 * Quando a flag está <code>true</code>, então o código do usuário é guardado no
	 * atributo inteiro (idManterUsuarioLogado). Quando a flag está <code>false</code>,
	 * então o atributo idManterUsuarioLogado fica igual a zero.
	 */
	public static final String MANTER_USUARIO_LOGADO = "manterUsuarioLogado";
	public static final String CODIGO_MANTER_USUARIO_LOGADO = "codigoManterUsuarioLogado";

	/**
	 * Este atributo será utilizado para retornar sempre o usuário logado no momento.
	 * Sempre que a aplicação realizar o login, esse campo será preenchido com o
	 * código do usuário logado para consultas posteriores.
	 */
	public static final String CODIGO_USUARIO_LOGADO = "codigoUsuarioLogado";

	//Quando true, a aplicação não pede login/senha ao ser executada.
	//Ela carregará o usuário que entrou por último.
	private boolean manterUsuarioLogado = false;
	private int idManterUsuarioLogado = Usuario.INTEGER_ZERO;
	private int idUsuarioLogado = Usuario.INTEGER_ZERO;

	public ConfiguracaoSistema(Activity principal) {
		carregarConfiguracoes(principal);
	}

	/**
	 * Carrega as configurações do sistema.
	 */
	public void carregarConfiguracoes(Activity principal) {
		SharedPreferences configuracoes = principal.getPreferences(Activity.MODE_PRIVATE);
		this.manterUsuarioLogado = configuracoes.getBoolean(MANTER_USUARIO_LOGADO, this.manterUsuarioLogado);
		this.idManterUsuarioLogado = configuracoes.getInt(CODIGO_MANTER_USUARIO_LOGADO, this.idManterUsuarioLogado);
		this.idUsuarioLogado = configuracoes.getInt(CODIGO_USUARIO_LOGADO, this.idUsuarioLogado);
	}
	
	/**
	 * Salva as configurações do sistema.
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
