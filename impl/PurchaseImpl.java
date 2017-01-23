package br.com.microdatasistemas.simintegrationws.impl;

import org.apache.axiom.om.OMElement;

import br.com.microdatasistemas.simintegrationws.entity.CmtPedido;
import br.com.microdatasistemas.simintegrationws.entity.WsIntPurchase;
import br.com.microdatasistemas.simintegrationws.entity.WsIntRequest;
import br.com.microdatasistemas.simintegrationws.facade.CmtPedidoFacade;
import br.com.microdatasistemas.simintegrationws.facade.PurchaseFacade;
import br.com.microdatasistemas.simintegrationws.facade.RequestFacade;
import br.com.microdatasistemas.simintegrationws.intf.PurchaseIntf;
import br.com.microdatasistemas.simintegrationws.xml.purchase.PurchaseOM;
import br.com.microdatasistemas.simintegrationws.xml.purchase.PurchaseResultOM;

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