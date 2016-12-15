package com.android.controlefinanceiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.bean.Usuario;

/**
 * Tela exibida logo após a autenticação do usuário. 
 */
public class ControleFinanceiroView extends Activity {

	private Usuario usuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controle_financeiro);
    }

    /**
     * Menu para inserir/excluir Categorias. 
     */
    public void clicarBotaoCategoria(View view) {
    	Intent intent = new Intent(ControleFinanceiroView.this, CategoriaView.class);
		startActivity(intent);
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}
