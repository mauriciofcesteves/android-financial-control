package com.android.business;

import android.content.Context;

import com.android.bean.ReceitaDespesa;
import com.android.dao.GenericDAO;
import com.android.dao.ReceitaDespesaDAO;

/**
 * Controlador de negócio de ReceitaDespesa.
 */
public class ReceitaDespesaBC extends GenericBC {

	private ReceitaDespesaDAO dao;

	public ReceitaDespesaBC(Context context) {
		dao = (ReceitaDespesaDAO) ReceitaDespesaDAO.getInstance(context);
	}

	@Override
	public GenericDAO getDAO() {
		return dao;
	}

	@Override
	public String getTableName() {
		return ReceitaDespesa.DATABASE_TABLE;
	}
    
}
