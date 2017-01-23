package br.com.microdatasistemas.simintegrationws.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the Ws_Int_Purchase_ProdAgent database table.
 * 
 */
@Entity
@Table(name = "Ws_Int_Purchase_ProdAccounting")
public class WsIntPurchaseProdAccounting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@Column(name = "Acc_costCenter")
    private String costCenter;
	
	@Column(name = "Acc_subCostCenter")
    private String subCostCenter;
	
	@Column(name = "Acc_costType")
    private String costType;
	
	@Column(name = "Acc_value")
    private BigDecimal value;
	
	@ManyToOne
    @JoinColumn(name = "id_item_purchase")
    private WsIntItemPurchase itemPurchase;
	
	       

    public WsIntPurchaseProdAccounting() {
    }    
    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



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

	//get item from Purchase
	public WsIntItemPurchase getItemPurchase() {
		return itemPurchase;
	}
	//set item from Purchase
	public void setItemPurchase(WsIntItemPurchase itemPurchase) {
		this.itemPurchase = itemPurchase;
	}


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((costCenter == null) ? 0 : costCenter.hashCode());
	result = prime * result + ((itemPurchase == null) ? 0 : itemPurchase.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof WsIntPurchaseProdAccounting))
	    return false;
	WsIntPurchaseProdAccounting other = (WsIntPurchaseProdAccounting) obj;
	if (costCenter == null) {
	    if (other.costCenter != null)
		return false;
	} else if (!costCenter.equals(other.costCenter))
	    return false;
	if (itemPurchase == null) {
	    if (other.itemPurchase != null)
		return false;
	} else if (!itemPurchase.equals(other.itemPurchase))
	    return false;
	return true;
    }
}