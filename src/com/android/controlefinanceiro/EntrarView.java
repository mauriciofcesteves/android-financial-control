package com.android.controlefinanceiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.bean.Usuario;
import com.android.business.UsuarioBC;
import com.android.controlefinanceiro.R;

/**
 * Classe para autentica��o do usu�rio.
 */
public class EntrarView extends Activity {

	private Usuario usuario;
	private UsuarioBC usuarioBC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	usuario = new Usuario();
    	usuarioBC = new UsuarioBC(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrar);
    }

    /**
     * A��o realizada no click do bot�o Entrar.
     * Nesse momento, a aplica��o realizar� o login do usu�rio e enviar� para o controle financeiro.
     */
    public void clicarBotaoEntrar(View view) {
    	boolean valido = isUsuarioValido();
    	if (!valido) {
    		return;
    	}

    	PrincipalView.getConfiguracoesDoSistema().setIdUsuarioLogado(usuario.getId());
    	if (PrincipalView.getConfiguracoesDoSistema().isManterUsuarioLogado()) {
    		PrincipalView.getConfiguracoesDoSistema().setIdManterUsuarioLogado(usuario.getId());
    		PrincipalView.getConfiguracoesDoSistema().salvarConfiguracoes(this);
    	}

		Intent intent = new Intent(EntrarView.this, ControleFinanceiroView.class);
		startActivity(intent);
    }

    /**
     * Verifica se os campos obrigat�rios est�o preenchidos e se o usu�rio est� v�lido/cadastrado. 
     */
    private boolean isUsuarioValido() {
    	boolean valido = true;
    	EditText editor = (EditText) findViewById(R.id.nomeParaAutenticar);
    	valido = usuarioBC.isCampoValido(editor, getString(R.string.invalid_blank_field));
    	if (!valido) {
    		return valido;
    	}
    	
    	usuario.setLogin(editor.getText().toString());

    	editor = (EditText) findViewById(R.id.senhaParaAutenticar);
    	valido = usuarioBC.isCampoValido(editor, getString(R.string.invalid_blank_field));
    	if (!valido) {
    		return valido;
    	}
    	
    	usuario.setSenha(editor.getText().toString());
    	
    	Usuario autenticado = usuarioBC.autenticar(usuario);
    	if (autenticado == null) {
    		valido = false;
    		Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.user_password_invalid), Toast.LENGTH_SHORT);
    		toast.show();
    		return valido;
    	} else {
    		usuario = autenticado;
    	}
    	return valido;
    }
}
