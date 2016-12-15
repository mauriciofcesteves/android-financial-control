package com.android.business;

import android.content.Context;
import android.database.Cursor;

import com.android.bean.Categoria;
import com.android.dao.CategoriaDAO;
import com.android.dao.GenericDAO;

/**
 * Controlador de negócio de Categoria.
 */
public class CategoriaBC extends GenericBC {

	private CategoriaDAO dao;

	public CategoriaBC(Context context) {
		dao = (CategoriaDAO) CategoriaDAO.getInstance(context);
	}

	@Override
	public GenericDAO getDAO() {
		return dao;
	}

	@Override
	public String getTableName() {
		return Categoria.DATABASE_TABLE;
	}
    
    /**
     * Retorna os registros que possuem o código de usuário igual ao informado. 
     */
    public Cursor listarPorCodigoUsuario(String[] colunas, long codigoUsuario){
    	return dao.listarPorCodigoUsuario(getTableName(), colunas, codigoUsuario);
    }
}
