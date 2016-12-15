package com.android.dao;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.android.bean.ReceitaDespesa;

/**
 * Controla a persistência de ReceitaDespesa.
 */
public class ReceitaDespesaDAO extends GenericDAO {

	public ReceitaDespesaDAO(Context context) {
		super(context, GenericDAO.DATABASE_NAME, ReceitaDespesa.TABLE_CREATE, ReceitaDespesa.DATABASE_TABLE, GenericDAO.DATABASE_VERSION);
	}

    public static GenericDAO getInstance(Context ctx){
        instance = new ReceitaDespesaDAO(ctx);
        try{
            Log.i(TAG, "Creating or opening the database [ " + GenericDAO.DATABASE_NAME + " ].");
            db = instance.getWritableDatabase();
        }catch(SQLiteException se){
            Log.e(TAG, "Cound not create and/or open the database [ " + GenericDAO.DATABASE_NAME + " ] that will be used for reading and writing.", se);
        }
        return instance;
    }
}