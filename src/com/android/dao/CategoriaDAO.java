package com.android.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.android.bean.Categoria;

/**
 * Controla a persistência de Categoria.
 */
public class CategoriaDAO extends GenericDAO {

	public CategoriaDAO(Context context) {
		super(context, GenericDAO.DATABASE_NAME, Categoria.TABLE_CREATE, Categoria.DATABASE_TABLE, GenericDAO.DATABASE_VERSION);
	}

    public static GenericDAO getInstance(Context ctx){
        instance = new CategoriaDAO(ctx);
        try{
            Log.i(TAG, "Creating or opening the database [ " + GenericDAO.DATABASE_NAME + " ].");
            db = instance.getWritableDatabase();
        }catch(SQLiteException se){
            Log.e(TAG, "Cound not create and/or open the database [ " + GenericDAO.DATABASE_NAME + " ] that will be used for reading and writing.", se);
        }
        return instance;
    }
    
    /**
     * Retorna os registros que possuem o código de usuário igual ao informado. 
     */
    public Cursor listarPorCodigoUsuario(String table, String[] columns, long codigoUsuario){
    	Cursor cursor = null;
    	try {
    		cursor = db.query(true, table, columns, Categoria.COL_ID_USUARIO + "=" + codigoUsuario, null, null, null, null, null);
    		if (cursor != null) {
    			cursor.moveToFirst();
    		}
    	} catch (Exception e) {
    		int version = db.getVersion();
    		onUpgrade(db, version, ++version);
    	}

        return cursor;
    }
}