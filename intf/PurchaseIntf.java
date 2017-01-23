package br.com.microdatasistemas.simintegrationws.intf;

import org.apache.axiom.om.OMElement;

import br.com.microdatasistemas.simintegrationws.xml.purchase.PurchaseResultOM;

public interface PurchaseIntf {

    public PurchaseResultOM setPurchaseOrder(OMElement purchase) throws Exception;

}
