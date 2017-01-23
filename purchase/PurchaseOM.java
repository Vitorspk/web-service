package br.com.microdatasistemas.simintegrationws.xml.purchase;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.microdatasistemas.simintegrationws.xml.purchase.DetOM;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "purchase"/* , namespace = "" */)
public class PurchaseOM {

	@XmlElement
	private InfOM inf;
	@XmlElement(name = "det")
	private List<DetOM> detail;
	private TotalOM total;
	private String remarks;
		
	
	public PurchaseOM() {
		this.setDetail(new ArrayList<DetOM>());
	}

	public InfOM getInf() {
		return inf;
	}

	public void setInf(InfOM inf) {
		this.inf = inf;
	}

	public List<DetOM> getDetail() {
		return detail;
	}

	public void setDetail(List<DetOM> detail) {
		this.detail = detail;
	}

	public TotalOM getTotal() {
		return total;
	}

	public void setTotal(TotalOM total) {
		this.total = total;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
