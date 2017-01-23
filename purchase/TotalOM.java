package br.com.microdatasistemas.simintegrationws.xml.purchase;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "total")
//, namespace = "http://tempuri.org/")
public class TotalOM {
	
	@XmlElement
	private BigDecimal valueProduct;	
	private BigDecimal freightValue;
	private BigDecimal discountValue;
	private BigDecimal discountPerc;
	private BigDecimal value;
	
	
	
	public BigDecimal getValueProduct() {
		return valueProduct;
	}
	public void setValueProduct(BigDecimal valueProduct) {
		this.valueProduct = valueProduct;
	}
	public BigDecimal getFreightValue() {
		return freightValue;
	}
	public void setFreightValue(BigDecimal freightValue) {
		this.freightValue = freightValue;
	}
	public BigDecimal getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}
	public BigDecimal getDiscountPerc() {
		return discountPerc;
	}
	public void setDiscountPerc(BigDecimal discountPerc) {
		this.discountPerc = discountPerc;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	
	
}
