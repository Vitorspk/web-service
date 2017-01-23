package dao;

import br.com.microdatasistemas.simintegrationws.entity.CmtPedido;
import br.com.microdatasistemas.simintegrationws.util.PersistenceMicrodataException;

public interface CmtPedidoDAO {
	public CmtPedido executeCmtPedido(String pedido) throws PersistenceMicrodataException;

}
