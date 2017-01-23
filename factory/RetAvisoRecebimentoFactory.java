package factory;


import dao.impl.RetAvisoRecebimentoDAOImpl;
import entity.RetAvisoRecebimento;
import util.PersistenceMicrodataException;

public class RetAvisoRecebimentoFactory {
	public RetAvisoRecebimento executeRetAvisoRecebimento(String aviso) throws PersistenceException {
		return new RetAvisoRecebimentoDAOImpl().executeRetAvisoRecebimento(aviso);
	    }

}
