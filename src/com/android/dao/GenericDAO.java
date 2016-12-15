package com.android.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DAO Genérico para as classes que irão necessitar de uma base de dados.
 */
public abstract class GenericDAO extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "controleFinanceiro";
	public static final int DATABASE_VERSION = 1;

    protected static final String TAG = "GenericDAO";
    private static String dName;
    private static String tName;
    private static String sql;
    public static final String KEY_ID = "_id";

    protected static GenericDAO instance;
    protected static SQLiteDatabase db;
    
    protected GenericDAO(Context ctx, String dbName, String sql, String tableName, int ver){
        super(ctx, dbName, null, ver);
        Log.i(TAG, "Criando ou abrindo o database [ " + dbName + " ].");
        GenericDAO.sql = sql;
        dName = dbName;
        tName = tableName;
    }

    public void close(){
        if(instance != null){
            Log.i(TAG, "Fechando o database [ " + dName + " ].");
            db.close();
            instance = null;
        }
    }
    
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "Tentando criar a tabela no database se ela ainda não existir [ " + sql + " ].");
        try{
            db.execSQL(sql);
        }catch(SQLException se){
            Log.e(TAG, "Não foi possível criar a tabela no database de acordo com a sql informada: [ " + sql + " ].", se);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
       Log.i(TAG, "Atualizando database da versão" + oldVersion + " para " + newVersion + ", todos os dados anteriores serão perdidos.");
       try{
           db.execSQL("DROP TABLE SE EXISTIR " + tName);
       }catch(SQLException se){
            Log.e(TAG, "Não foi possível dropar a tabela do database: [ " + tName + " ].", se);
        }
       onCreate(db);
    }

    /**
     * Insere o registro na tabela. 
     */
    public long insert(String table, ContentValues values){
        return db.insert(table, null, values);
    }
    
    /**
     * Retorna os registros da tabela de acordo com as colunas informadas. 
     */
    public Cursor get(String table, String[] columns){
    	Cursor c = null;
    	try {
    		c = db.query(table, columns, null, null, null, null, null);
    	} catch (Exception e) {
    		int version = db.getVersion();
    		onUpgrade(db, version, ++version);
    	}
        return c;
    }
    
    /**
     * Retorna os registros da tabela de acordo com as colunas e o id informados. 
     */
    public Cursor get(String table, String[] columns, long id){
        Cursor cursor =db.query(true, table, columns, KEY_ID + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    
    /**
     * Deleta todos os registros da tabela informada.
     */
    public int delete(String table) {
        return db.delete(table, "1", null);
    }
    
    /**
     * Deleta da tabela o registro com o id informado.
     */
    public int delete(String table, long id) {
        return db.delete(table, KEY_ID + "=" + id, null);
    }
    
    /**
     * Altera o registro de acordo com o id informado.
     */
    public int update(String table, long id, ContentValues values) {
        return db.update(table, values, KEY_ID + "=" + id, null);
    }
    
    /**
     * Retorna o caminho do database na aplicação.
     */
    public String getPath() {
    	return db.getPath();
    }
}