package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CmtPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "Empresa")
    private String empresa;	
	
	@Id
	@Column(name = "Pedido")
    private String pedido;
	
	public String getPedido() {
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
