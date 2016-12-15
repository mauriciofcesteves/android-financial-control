package com.android.controlefinanceiro;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.bean.Usuario;
import com.android.business.UsuarioBC;
import com.android.controlefinanceiro.R;

/**
 * Responsável pela criação de uma nova conta de usuário.
 */
public class NovaContaView extends Activity {

	private Usuario usuario;
	private EditText editor;
	private String confirmacaoSenha;
	private UsuarioBC usuarioBC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	usuario = new Usuario();
    	usuarioBC = new UsuarioBC(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_conta);
    }

    /**
     * Salva o usuário de acordo com os dados informados.
     */
    public void clicarBotaoSalvar(View view) {
    	boolean valido = validarUsuario();
    	if (!valido) {
    		return;
    	}

		ContentValues values = new ContentValues();
		values.put(Usuario.COL_LOGIN, usuario.getLogin());
		values.put(Usuario.COL_SENHA, usuario.getSenha());
		values.put(Usuario.COL_EMAIL, usuario.getEmail());
		usuarioBC.inserir(values);

		Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.registration_completed), Toast.LENGTH_SHORT);
		toast.show();
    }

    /**
     * Valida os campos obrigatórios, senha e confirmação de senha e se login e emails já cadastrados.
     */
    private boolean validarUsuario() {
    	boolean valido = false;
    	editor = (EditText) findViewById(R.id.nomeUsuario);
    	valido = usuarioBC.isCampoValido(editor, getString(R.string.invalid_blank_field));
    	if (!valido) {
    		return valido;
    	}
    	
    	usuario.setLogin(editor.getText().toString());

    	editor = (EditText) findViewById(R.id.senhaUsuario);
    	valido = usuarioBC.isCampoValido(editor, getString(R.string.invalid_blank_field));
    	if (!valido) {
    		return valido;
    	}
    	
    	usuario.setSenha(editor.getText().toString());
    	
    	editor = (EditText) findViewById(R.id.confirmarSenhaUsuario);
    	valido = usuarioBC.isCampoValido(editor, getString(R.string.invalid_blank_field));
    	if (!valido) {
    		return valido;
    	}

    	confirmacaoSenha = editor.getText().toString();

    	editor = (EditText) findViewById(R.id.emailUsuario);
    	valido = usuarioBC.isCampoValido(editor, getString(R.string.invalid_blank_field));
    	if (!valido) {
    		return valido;
    	}

    	usuario.setEmail(editor.getText().toString());

    	valido = usuarioBC.isSenhaValida(usuario.getSenha(), confirmacaoSenha);
    	EditText campo = (EditText) findViewById(R.id.senhaUsuario);
    	if (!valido) {
    		campo = (EditText) findViewById(R.id.senhaUsuario);
    		campo.setError(getString(R.string.invalid_password));
    		return valido;
    	} else {
    		campo.setError(null);
    	}
    	
    	valido = usuarioBC.isCampoJaCadastrado(Usuario.COL_LOGIN, usuario.getLogin());
    	if (!valido) {
    		campo = (EditText) findViewById(R.id.nomeUsuario);
    		campo.setError(getString(R.string.login_already_exist));
    		return valido;
    	} else {
    		campo.setError(null);
    	}
    	
    	valido = usuarioBC.isEmailValido(usuario.getEmail());
    	if (!valido) {
    		campo = (EditText) findViewById(R.id.emailUsuario);
    		campo.setError(getString(R.string.invalid_email));
    		return valido;
    	} else {
    		campo.setError(null);
    	}
    	
    	valido = usuarioBC.isCampoJaCadastrado(Usuario.COL_EMAIL, usuario.getEmail());
    	if (!valido) {
    		campo = (EditText) findViewById(R.id.emailUsuario);
    		campo.setError(getString(R.string.email_already_exist));
    		return valido;
    	} else {
    		campo.setError(null);
    	}
    	return valido;
    }

}
