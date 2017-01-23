package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.microdata.component.persistence.FactoryHibernate;
import dao.CmtPedidoDAO;
import entity.CmtPedido;
import util.PersistenceException;

public class CmtPedidoDAOImpl implements CmtPedidoDAO{

	public CmtPedido executeCmtPedido(String pedido) throws PersistenceException {
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
		    throw new PersistenceMicrodataException(PersistenceException.getCause(e));
		}
		return cmtPedido;
	    }
	

}
