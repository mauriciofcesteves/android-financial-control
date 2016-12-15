package com.android.business;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.EditText;

import com.android.dao.GenericDAO;

/**
 * Camada de negócio genérica para as classes que irão necessitar de acesso a base de dados e regras de negócios. 
 */
public abstract class GenericBC {

	/**
	 * Retorna o DAO da classe que herdar do GenericBC.
	 */
    public abstract GenericDAO getDAO();
    
    /**
     * Retorna o nome da tabela que herdar do GenericBC.
     */
    public abstract String getTableName();
    
    /**
     * Insere registros no banco de dados.
     */
    public long inserir(ContentValues values){
        return getDAO().insert(getTableName(), values);
    }
    
    /**
     * Retorna os registros de acordo com as colunas informadas.
     */
    public Cursor listar(String[] columns){
        return getDAO().get(getTableName(), columns);
    }
    
    /**
     * Retorna os registros de acordo com o id e as colunas informadas.
     */
    public Cursor get(String[] columns, long id){
        return getDAO().get(getTableName(), columns, id);
    }
    
    /**
     * Exclui todos os registros da tabela.
     */
    public int excluirTudo() {
        return getDAO().delete(getTableName());
    }
    
    /**
     * Exclui o registro de acordo com o id informado.
     */
    public int excluir(long id) {
        return getDAO().delete(getTableName(), id);
    }

    /**
     * Altera o registro de acordo com o id informado.
     */
    public int alterar(long id, ContentValues values) {
        return getDAO().update(getTableName(), id, values);
    }
    
    /**
     * Fecha o database.
     */
    public void fechar() {
    	getDAO().close();
    }
    
    /**
     * Retorna o caminho do database na aplicação.
     */
    public String getPath() {
    	return getDAO().getPath();
    }

    /**
     * Verifica se o campo está válido, se não estiver, atribue a mensagem internacionalizada ao campo.
     */
    public boolean isCampoValido(EditText campo, String mensagem) {
    	boolean valido = false;
		if (isCampoNulo(campo)) {
			campo.setError(mensagem);
		} else {
			campo.setError(null);
			valido = true;
		}
		
		return valido;
    }
	
	/**
	 * Verifica se o valor já está cadastrado no banco e retorna <code>true</code>.
	 */
	public boolean isCampoJaCadastrado(String campo, String valor) {
		boolean valido = true;
        Cursor cursor = null;
        String[] columns = new String[] {campo};
        cursor = listar(columns);
        int coluna = cursor.getColumnIndex(campo); 
        
        if(cursor != null){
            if(cursor.moveToFirst()){
                int count = cursor.getCount();
                for(int i=0; i<count; i++){
                    String resultado = cursor.getString(coluna); 
                    if (resultado.equalsIgnoreCase(valor.trim())) {
                    	valido = false;
                    	break;
                    }
                    cursor.moveToNext();
                }
            }
        }
		return valido;
	}

    /**
     * Verifica se o campo está nulo e retorna <code>true</code>.
     */
    public boolean isCampoNulo(EditText campo) {
    	return campo.getText() == null
    			|| campo.getText().toString().trim().equalsIgnoreCase("");
    }
}
