package intf;

import org.apache.axiom.om.OMElement;

import xml.purchase.PurchaseResultOM;

public interface PurchaseIntf {

    public PurchaseResultOM setPurchaseOrder(OMElement purchase) throws Exception;

}
