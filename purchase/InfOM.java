package xml.purchase;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "inf"/*, namespace = ""*/)
public class InfOM {
	
	@XmlElement
    private String number;
    private Calendar date;
    private Calendar deliveryDate;
    private SupplierOM supplier;
    private String user;
    private String payment;
    private String currency;
    private TransporterOM transporter;
    private TransporterOM redispatch;
    
    public InfOM() {

    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public SupplierOM getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierOM supplier) {
		this.supplier = supplier;
	}
		

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public TransporterOM getTransporter() {
		return transporter;
	}

	public void setTransporter(TransporterOM transporter) {
		this.transporter = transporter;
	}

	public TransporterOM getRedispatch() {
		return redispatch;
	}

	public void setRedispatch(TransporterOM redispatch) {
		this.redispatch = redispatch;
	}

	
    

}
