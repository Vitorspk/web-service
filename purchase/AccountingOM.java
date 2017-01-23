package xml.purchase;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "accounting")
public class AccountingOM {		
	
	@XmlElement(name = "costCenter")
    private String costCenter;
	
	@XmlElement(name = "subCostCenter")
    private String subCostCenter;
	
	@XmlElement(name = "costType")
    private String costType;
	
	@XmlElement(name = "value")
    private BigDecimal value;
	


	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getSubCostCenter() {
		return subCostCenter;
	}

	public void setSubCostCenter(String subCostCenter) {
		this.subCostCenter = subCostCenter;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
		
}
