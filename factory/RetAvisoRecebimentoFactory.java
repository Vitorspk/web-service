package br.com.microdatasistemas.simintegrationws.factory;


import br.com.microdatasistemas.simintegrationws.dao.impl.RetAvisoRecebimentoDAOImpl;
import br.com.microdatasistemas.simintegrationws.entity.RetAvisoRecebimento;
import br.com.microdatasistemas.simintegrationws.util.PersistenceMicrodataException;

public class RetAvisoRecebimentoFactory {
	public RetAvisoRecebimento executeRetAvisoRecebimento(String aviso) throws PersistenceMicrodataException {
		return new RetAvisoRecebimentoDAOImpl().executeRetAvisoRecebimento(aviso);
	    }

}
