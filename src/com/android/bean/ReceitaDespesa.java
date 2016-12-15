package com.android.bean;

import java.util.Date;

/**
 * ReceitaDespesa.
 */
public class ReceitaDespesa {

	public static final String TABLE_CREATE =
			"create table receitadespesa (_id integer primary key autoincrement, "
					+ " descricao text not null, valor double not null, data datetime not null, despesa bool not null, " 
					+ " paga bool not null, idCategoria integer not null, idUsuario integer not null, " 
					+ " gastoFixo bool not null );";

    public static final String DATABASE_TABLE = "receitadespesa";
    public static final String COL_DESCRICAO = "descricao";
    public static final String COL_VALOR = "valor";
    public static final String COL_DATA = "data";
    public static final String COL_DESPESA = "despesa";
    public static final String COL_PAGA = "paga";
    public static final String COL_ID_CATEGORIA = "idCategoria";
    public static final String COL_ID_USUARIO = "idUsuario";
    public static final String COL_GASTO_FIXO = "gastoFixo";

    private Integer id;
    private String descricao;
    private Double valor;
    private Date data;
    private Boolean despesa;
    private Boolean paga;
    private Integer idCategoria;
    private Integer idUsuario;
    private Boolean gastoFixo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getDespesa() {
		return despesa;
	}

	public void setDespesa(Boolean despesa) {
		this.despesa = despesa;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean getGastoFixo() {
		return gastoFixo;
	}

	public void setGastoFixo(Boolean gastoFixo) {
		this.gastoFixo = gastoFixo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReceitaDespesa other = (ReceitaDespesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
    
}
