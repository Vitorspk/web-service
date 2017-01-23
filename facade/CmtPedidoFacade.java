package facade;


import entity.CmtPedido;
import entity.WsIntPurchase;
import factory.CmtPedidoFactory;

public class CmtPedidoFacade extends AbstractFacade {
	
	@SuppressWarnings("unchecked")
	@Override
	public <T, U> T save(T Entity, U wsEntity) throws Exception {
		// Check if the type of method arguments are the desired for this operation and assign to their correct reference.
		CmtPedido purchaseFinal = (CmtPedido) this.checkAndReturnInstance(Entity, CmtPedido.class);
		WsIntPurchase purchaseEspelho = (WsIntPurchase) this.checkAndReturnInstance(wsEntity, WsIntPurchase.class);
		
		purchaseFinal = new CmtPedidoFactory().executeCmtPedido(purchaseEspelho.getInfNumber());
		
		return (T) purchaseFinal;
	}
	
	

}
