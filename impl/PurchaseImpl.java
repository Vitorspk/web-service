package impl;

import org.apache.axiom.om.OMElement;

import entity.CmtPedido;
import entity.WsIntPurchase;
import facade.CmtPedidoFacade;
import facade.PurchaseFacade;
import facade.RequestFacade;
import intf.PurchaseIntf;
import xml.purchase.PurchaseOM;
import xml.purchase.PurchaseResultOM;

public class PurchaseImpl extends AbstractImpl implements PurchaseIntf {

    @Override
    public PurchaseResultOM setPurchaseOrder(OMElement purchase) throws Exception {
	PurchaseResultOM purchaseResultOM = new PurchaseResultOM();
	PurchaseOM purchaseOM = (PurchaseOM) this.unmarshalOM(purchase, PurchaseOM.class);
	PurchaseFacade purchaseFacade = new PurchaseFacade();

	try {
	    // Validate XML
	    purchaseFacade.validatePurchase(purchaseOM, purchaseResultOM);

	    // Persist Purchase
	    WsIntPurchase wsIntPurchase = purchaseFacade.persistPurchase(purchaseOM, purchaseResultOM);

	    // Persist Cmt_Pedido
	    CmtPedido cmtPedido = new CmtPedidoFacade().save(new CmtPedido(), wsIntPurchase);

	    // Set the ResultId
	    purchaseResultOM.setPurchaseOrderId(cmtPedido.getPedido());
	} catch (Exception e) {
	    purchaseResultOM.setErrorCode("900");
	    purchaseResultOM.setErrorMessage(e.getMessage());
	    e.printStackTrace();
	} finally {
	    // Persist XML Request
	    new RequestFacade().saveRequest(purchaseOM, purchaseResultOM, new WsIntRequest());
	}
	return purchaseResultOM;

    }
}
