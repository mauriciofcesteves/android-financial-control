package com.android.controlefinanceiro;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.bean.Categoria;
import com.android.business.CategoriaBC;
import com.android.dao.GenericDAO;

/**
 * Menu para inserir/excluir Categorias.
 */
public class CategoriaView extends Activity implements OnItemSelectedListener {
	
	private Categoria categoria;
	private CategoriaBC categoriaBC;
	private EditText editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	categoria = new Categoria();
    	categoriaBC = new CategoriaBC(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria);

        inicializarListaCategorias();
    }

    /**
     * Método que popula o spinner (combo box) de categorias.
     */
    @SuppressWarnings("deprecation")
	public void inicializarListaCategorias() {
    	Spinner spinner = (Spinner) findViewById(R.id.descricoesCategoria);
    	spinner.setOnItemSelectedListener(this);

    	String[] colunas = new String[] {GenericDAO.KEY_ID, Categoria.COL_DESCRICAO, Categoria.COL_ID_USUARIO};
    	Cursor cursor = categoriaBC.listarPorCodigoUsuario(colunas, PrincipalView.getConfiguracoesDoSistema().getIdUsuarioLogado());
    	startManagingCursor(cursor);
    	String[] de = new String[] {Categoria.COL_DESCRICAO};
    	int[] para = new int[] {android.R.id.text1};
    	
    	SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, de, para);
    	spinner.setAdapter(adapter);
    }

    /**
     * Salvar categoria.
     */
    public void clicarBotaoSalvar(View view) {
    	boolean valido = validarCategoria();
    	if (!valido) {
    		return;
    	}

		ContentValues values = new ContentValues();
		values.put(Categoria.COL_DESCRICAO, categoria.getDescricao());
		values.put(Categoria.COL_ID_USUARIO, PrincipalView.getConfiguracoesDoSistema().getIdUsuarioLogado());
		categoriaBC.inserir(values);

		editor = (EditText) findViewById(R.id.descricaoCategoria);
		editor.setText(null);

		inicializarListaCategorias();
		Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.registration_completed), Toast.LENGTH_SHORT);
		toast.show();
    }

    /**
     * Remove a categoria selecionada.
     */
    public void clicarBotaoRemover(View view) {
    	if (categoria != null && categoria.getId() != null) {
    		categoriaBC.excluir(categoria.getId());
    		inicializarListaCategorias();

    		Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.category_removed), Toast.LENGTH_SHORT);
    		toast.show();
    	} else {
    		Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.select_a_category), Toast.LENGTH_SHORT);
    		toast.show();
    	}
    }

    /**
     * Valida os campos obrigatórios da categoria e valida categorias com mesma descrição já cadastrada.
     */
    private boolean validarCategoria() {
       	boolean valido = false;
    	editor = (EditText) findViewById(R.id.descricaoCategoria);
    	valido = categoriaBC.isCampoValido(editor, getString(R.string.invalid_blank_field));
    	if (!valido) {
    		return valido;
    	}

    	categoria.setDescricao(editor.getText().toString());
    	valido = categoriaBC.isCampoJaCadastrado(Categoria.COL_DESCRICAO, categoria.getDescricao());
    	if (!valido) {
    		editor = (EditText) findViewById(R.id.descricaoCategoria);
    		editor.setError(getString(R.string.category_already_exist));
    		return valido;
    	} else {
    		editor.setError(null);
    	}

    	return valido;
    }

    /**
     * Executado ao selecionar um item da lista de descrições de categorias.
     */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		Cursor cursor = (Cursor) parent.getItemAtPosition(pos);
		if (cursor != null && cursor.moveToFirst()) {
			int idColuna = cursor.getColumnIndex(GenericDAO.KEY_ID);
			int total = cursor.getCount();
			
			for (int i=0; i<total; i++) {
				
				if (pos == i) {
					categoria.setId(cursor.getInt(idColuna));
				}
				
				cursor.moveToNext();
			}
		}
	}

	/**
	 * Executado quando nada é selecionada na lista de descrições de categorias.
	 */
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}

}
