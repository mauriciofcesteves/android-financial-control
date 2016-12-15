package com.android.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.database.Cursor;

import com.android.bean.Usuario;
import com.android.dao.GenericDAO;
import com.android.dao.UsuarioDAO;

/**
 * Controlador de negócio de Usuário.
 */
public class UsuarioBC extends GenericBC {

	private UsuarioDAO dao;

	public UsuarioBC(Context context) {
		dao = (UsuarioDAO) UsuarioDAO.getInstance(context);
	}

	@Override
	public GenericDAO getDAO() {
		return dao;
	}

	@Override
	public String getTableName() {
		return Usuario.DATABASE_TABLE;
	}
	
	/**
	 * Verifica se a senha está com o tamanho necessário e se está igual com a confirmação de senha.
	 */
	public boolean isSenhaValida(String senha, String confirmacaoSenha) {
		boolean valido = true;
		if (!senha.equals(confirmacaoSenha) || senha.length() < 6) {
			valido = false;
		}
		return valido;
	}

	/**
	 * Validação de email. Retorna <code>true</code> se email válido.
	 */
	public boolean isEmailValido(String email) {
		boolean valido = true;
		//Set the email pattern string  
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");  
  
		//Match the given string with the pattern  
		Matcher m = p.matcher(email);  
  
		//check whether match is found   
		boolean matchFound = m.matches();  
  
		if (!matchFound) {
			valido = false;
		}
		return valido;
	}
	
	/**
	 * Autentica o usuário.
	 */
	public Usuario autenticar(Usuario usuario) {
		Usuario autenticado = null;
        Cursor cursor = null;
        String[] columns = new String[] {GenericDAO.KEY_ID, Usuario.COL_LOGIN, Usuario.COL_SENHA, Usuario.COL_EMAIL};
        cursor = listar(columns);

        int id = cursor.getColumnIndex(GenericDAO.KEY_ID); 
        int login = cursor.getColumnIndex(Usuario.COL_LOGIN); 
        int senha = cursor.getColumnIndex(Usuario.COL_SENHA); 
        int email = cursor.getColumnIndex(Usuario.COL_EMAIL); 

        if(cursor != null){
            if(cursor.moveToFirst()){
                int count = cursor.getCount();
                for(int i=0; i<count; i++){

                	String loginUsuario = cursor.getString(login);
                	String senhaUsuario = cursor.getString(senha);
                	if (loginUsuario.equalsIgnoreCase(usuario.getLogin().trim()) && senhaUsuario.equals(usuario.getSenha())) {
                		int codigoUsuario = cursor.getInt(id);
                		String emailUsuario = cursor.getString(email);
                		
                		autenticado = new Usuario();
                		autenticado.setId(codigoUsuario);
                		autenticado.setLogin(loginUsuario);
                		autenticado.setSenha(senhaUsuario);
                		autenticado.setEmail(emailUsuario);
                		break;
                	}
                	
                    cursor.moveToNext();
                }
            }
        }
		return autenticado;
	}
	
	/**
	 * Consulta por id de usuário.
	 */
	public Usuario consultarPorCodigo(int codigo) {
		Usuario usuario = null;
		Cursor cursor = null;
		String[] columns = new String[] {GenericDAO.KEY_ID, Usuario.COL_LOGIN, Usuario.COL_SENHA, Usuario.COL_EMAIL};
		cursor = listar(columns);
		
		int id = cursor.getColumnIndex(GenericDAO.KEY_ID); 
		int login = cursor.getColumnIndex(Usuario.COL_LOGIN); 
		int senha = cursor.getColumnIndex(Usuario.COL_SENHA); 
		int email = cursor.getColumnIndex(Usuario.COL_EMAIL); 
		
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				for(int i=0; i<count; i++){
					
					int idUsuario = cursor.getInt(id);
					if (codigo > Usuario.INTEGER_ZERO && codigo == idUsuario) {
						String emailUsuario = cursor.getString(email);
						String loginUsuario = cursor.getString(login);
						String senhaUsuario = cursor.getString(senha);

						usuario = new Usuario();
						usuario.setId(codigo);
						usuario.setLogin(loginUsuario);
						usuario.setSenha(senhaUsuario);
						usuario.setEmail(emailUsuario);
						break;
					}
					
					cursor.moveToNext();
				}
			}
		}
		return usuario;
	}
}
