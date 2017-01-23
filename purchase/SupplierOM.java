package br.com.microdatasistemas.simintegrationws.xml.purchase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "supplier")
// , namespace = "http://tempuri.org/")

public class SupplierOM {
	 @XmlElement
	    private String code;
	    private String poNumber;


	    public SupplierOM() {

	    }

	    public String getcode() {
		return code;
	    }

	    public void setcode(String Code) {
		code = Code;
	    }

	    public String getpoNumber() {
		return poNumber;
	    }

	    public void setpoNumber(String poNumber) {
		this.poNumber = poNumber;
	    }
	   
}
