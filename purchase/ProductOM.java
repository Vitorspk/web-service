package xml.purchase;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
//, namespace = "http://tempuri.org/")
public class ProductOM {
	@XmlElement
    private String code;
    private String description;
    private BigDecimal quantity;
    private String unit;
    private BigDecimal price;
    private BigDecimal discountValue;
    private BigDecimal discountPerc;
    private BigDecimal value;
    
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
