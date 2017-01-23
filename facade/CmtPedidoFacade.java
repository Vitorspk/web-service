package br.com.microdatasistemas.simintegrationws.facade;


import br.com.microdatasistemas.simintegrationws.entity.CmtPedido;
import br.com.microdatasistemas.simintegrationws.entity.WsIntPurchase;
import br.com.microdatasistemas.simintegrationws.factory.CmtPedidoFactory;

public class CmtPedidoFacade extends AbstractMicrodataFacade {
	
	@SuppressWarnings("unchecked")
	@Override
	public <T, U> T save(T microdataEntity, U wsEntity) throws Exception {
		// Check if the type of method arguments are the desired for this operation and assign to their correct reference.
		CmtPedido purchaseFinal = (CmtPedido) this.checkAndReturnInstance(microdataEntity, CmtPedido.class);
		WsIntPurchase purchaseEspelho = (WsIntPurchase) this.checkAndReturnInstance(wsEntity, WsIntPurchase.class);
		
		purchaseFinal = new CmtPedidoFactory().executeCmtPedido(purchaseEspelho.getInfNumber());
		
		return (T) purchaseFinal;
	}
	
	

}
