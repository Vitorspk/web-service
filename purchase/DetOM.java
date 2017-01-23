package xml.purchase;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "det")
// , namespace = "http://tempuri.org/")
public class DetOM {

	@XmlAttribute(name = "item")
	private int item;
	
	@XmlElement(name = "product")
	private ProductOM product;
	
	@XmlElement(name = "accounting")
	private List<AccountingOM> accounting;

	
	public DetOM() {

	}

	public int getItem() {
		return item;
	}


	public void setItem(int item) {
		this.item = item;
	}


	public ProductOM getProduct() {
		return product;
	}


	public void setProduct(ProductOM product) {
		this.product = product;
	}


	public List<AccountingOM> getAccounting() {
		return accounting;
	}


	public void setAccounting(List<AccountingOM> accounting) {
		this.accounting = accounting;
	}
			
}
