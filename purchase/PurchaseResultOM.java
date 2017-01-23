package xml.purchase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import xml.ResultOM;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "purchaseResult", namespace = "http://tempuri.org/")
public class PurchaseResultOM extends ResultOM {

    @XmlElement
    private Boolean purchaseOrderResponse;
    private String purchaseOrderId;

    public PurchaseResultOM() {
	this.setErrorCode("");
	this.setErrorMessage("");
	this.setRequestId("");
	this.setPurchaseOrderId("");
	this.setPurchaseOrderResponse(Boolean.FALSE);
    }

    public Boolean getPurchaseOrderResponse() {
	return purchaseOrderResponse;
    }

    public void setPurchaseOrderResponse(Boolean purchaseOrderResponse) {
	this.purchaseOrderResponse = purchaseOrderResponse;
    }

    public String getPurchaseOrderId() {
	return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
	this.purchaseOrderId = purchaseOrderId;
    }

}
