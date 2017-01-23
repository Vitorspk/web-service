package dao;

import entity.CmtPedido;
import util.PersistenceException;

public interface CmtPedidoDAO {
	public CmtPedido executeCmtPedido(String pedido) throws PersistenceException;

}
