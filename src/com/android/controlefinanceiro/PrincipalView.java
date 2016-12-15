package com.android.controlefinanceiro;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

import com.android.bean.Usuario;
import com.android.business.UsuarioBC;
import com.android.controlefinanceiro.R;
import com.android.dao.GenericDAO;
import com.android.util.ConfiguracaoSistema;

/**
 * Classe principal que irá carregar o menu da aplicação.
 */
public class PrincipalView extends Activity {

	private static ConfiguracaoSistema configuracoesDoSistema;
	private CheckBox checkBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	configuracoesDoSistema = new ConfiguracaoSistema(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        
        carregarCheckBox();
        System.out.println("REGISTROS: "+testarInserirUsuario());
    }

	/**
     * Controla o status do checkbox.
     */
    private void carregarCheckBox() {
    	checkBox = (CheckBox) findViewById(R.id.chManterUsuarioLogado);
    	if (configuracoesDoSistema.isManterUsuarioLogado()) {
    		checkBox.setChecked(true);
    	} else {
    		checkBox.setChecked(false);
    	}
    }
    
    public static ConfiguracaoSistema getConfiguracoesDoSistema() {
    	return configuracoesDoSistema;
    }

    /**
     * Salvando as configurações "Manter Usuário Logado". 
     */
    public void clicarChManterUsuarioLogado(View view) {
    	if (checkBox.isChecked()) {
    		configuracoesDoSistema.setManterUsuarioLogado(true);
    	} else {
    		configuracoesDoSistema.setIdManterUsuarioLogado(Usuario.INTEGER_ZERO);
    		configuracoesDoSistema.setManterUsuarioLogado(false);
    	}
    	salvarConfiguracoes();
    }

    /**
     * Ação realizada no click do botão Entrar.
     * Nesse momento, a aplicação executará o menu que realiza o Login.
     */
    public void clicarBotaoEntrar(View view) {
    	if (configuracoesDoSistema.isManterUsuarioLogado()
    			&& checkBox.isChecked()
    			&& configuracoesDoSistema.getIdManterUsuarioLogado() > Usuario.INTEGER_ZERO) {
    		configuracoesDoSistema.setIdUsuarioLogado(configuracoesDoSistema.getIdManterUsuarioLogado());
    		Intent intent = new Intent(PrincipalView.this, ControleFinanceiroView.class);
    		startActivity(intent);
    	} else {
			//abrindo o menu de acordo com o botão pressionado
			Intent intent = new Intent(PrincipalView.this, EntrarView.class);
			startActivity(intent);
    	}
    }

    /**
     * Este método é chamado quando o Menu Principal deixar de ser executado.
     */
    @Override
    public void onPause() {
    	super.onPause();
    	salvarConfiguracoes();
    }

    /**
     * Salva as configurações do sistema.
     */
    private void salvarConfiguracoes() {
    	if (configuracoesDoSistema != null) {
    		configuracoesDoSistema.salvarConfiguracoes(this);
    	}
    }

    /**
     * Ação realizada no click do botão Nova Conta.
     * Nesse momento, a aplicação executará o menu que cadastra uma nova conta.
     */
    public void clicarBotaoNovaConta(View view) {
    	//abrindo o menu de acordo com o botão pressionado
		Intent intent = new Intent(PrincipalView.this, NovaContaView.class);
		startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }

    private String testarInserirUsuario(){
        
        String result="";
        Cursor cursor = null;
        String[] columns = new String[] {GenericDAO.KEY_ID, Usuario.COL_SENHA};
        
        UsuarioBC usuarioBC = new UsuarioBC(this);

        if(usuarioBC != null){
            
//            ContentValues values = new ContentValues();
//            values.put(Usuario.COL_SENHA, "111");
//            usuarioBC.inserir(values);
//            
//            values = new ContentValues();
//            values.put(Usuario.COL_SENHA, "222");
//            usuarioBC.inserir(values);
            
            cursor = usuarioBC.listar(columns);
            
            int idColumn = cursor.getColumnIndex(GenericDAO.KEY_ID); 
            int senhaColumn = cursor.getColumnIndex(Usuario.COL_SENHA); 
            
            if(cursor != null){
                if(cursor.moveToFirst()){
                    
                    int count = cursor.getCount();
                    result = "there are " + count + " records.";
                    List<Usuario> list = new ArrayList<Usuario>();
                    
                    for(int i=0; i<count; i++){
                        
                        int id = cursor.getInt(idColumn); 
                        String senha = cursor.getString(senhaColumn); 
                        
                        Usuario usuario = new Usuario();
                        usuario.setId(id);
                        usuario.setSenha(senha);
                        
                        list.add(usuario);
                        
                        result += " " + i + ": " + "id=" + id + ", senha=" + senha+ ";";
                        
                        cursor.moveToNext();
                    }
                }
            }
//           System.out.println("CAMINHO DO DATABASE: "+usuarioBC.getPath());
//            result += " now update the second record.";
//            
//            values = new ContentValues();
//            values.put(Usuario.COL_SENHA, "333");
//            usuarioBC.alterar(2, values);
//            
//            cursor.requery();
//            cursor.close();
//
//            result += " now delete first record.";
//            
//            usuarioBC.excluir(1);
//            
//            result += " now delete all records.";
//            
//            usuarioBC.excluirTudo();
            
            usuarioBC.fechar();
        }
        
        return result;
    }

}
