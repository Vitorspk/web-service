package br.com.microdatasistemas.simintegrationws.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.microdata.component.persistence.FactoryHibernate;
import br.com.microdatasistemas.simintegrationws.dao.CmtPedidoDAO;
import br.com.microdatasistemas.simintegrationws.entity.CmtPedido;
import br.com.microdatasistemas.simintegrationws.util.PersistenceMicrodataException;

public class CmtPedidoDAOImpl implements CmtPedidoDAO{

	public CmtPedido executeCmtPedido(String pedido) throws PersistenceMicrodataException {
		Session session = (Session) FactoryHibernate.getInstance().getEntityManager().getDelegate();
		String queryString = "exec SP_Ws_Int_Gera_Cmt_Pedido :pedido";
		Transaction t = null;
		CmtPedido cmtPedido = null;
		
		try {
		    t = session.beginTransaction();

		    Query query = session.createSQLQuery(queryString).addEntity(CmtPedido.class);
		    query.setString("pedido", pedido);
		    cmtPedido = (CmtPedido) query.uniqueResult();
		    
		    t.commit();
		}
		catch(Exception e) {
		    e.printStackTrace();
		    if(t != null) {
			t.rollback();
		    }
		    throw new PersistenceMicrodataException(PersistenceMicrodataException.getCause(e));
		}
		return cmtPedido;
	    }
	

}
