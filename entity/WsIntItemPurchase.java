package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Ws_Int_Item_Purchase database table.
 * 
 */
@Entity
@Table(name = "Ws_Int_Item_Purchase")
public class WsIntItemPurchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    private int item;

    @Column(name = "prod_code")
    private String prodCode;

    @Column(name = "prod_description")
    private String prodDescription;

    @Column(name = "prod_quantity")
    private BigDecimal prodQuantity;

    @Column(name = "prod_unit")
    private String unit;

    @Column(name = "prod_price")
    private BigDecimal price;

    @Column(name = "prod_discountvalue")
    private BigDecimal prodDiscountvalue;

    @Column(name = "prod_discountperc")
    private BigDecimal prodDiscountperc;

    @Column(name = "prod_value")
    private BigDecimal value;

    // bi-directional many-to-one association to WsIntPurchase
    @ManyToOne
    @JoinColumn(name = "id_purchase")
    private WsIntPurchase wsIntPurchase;

    @OneToMany(mappedBy = "itemPurchase", cascade = CascadeType.ALL)
    private Collection<WsIntPurchaseProdAccounting> prodAccounting;

    public WsIntItemPurchase() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getItem() {
	return item;
    }

    public void setItem(int item) {
	this.item = item;
    }

    public String getProdCode() {
	return prodCode;
    }

    public void setProdCode(String prodCode) {
	this.prodCode = prodCode;
    }

    public String getProdDescription() {
	return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
	this.prodDescription = prodDescription;
    }

    public BigDecimal getProdQuantity() {
	return prodQuantity;
    }

    public void setProdQuantity(BigDecimal prodQuantity) {
	this.prodQuantity = prodQuantity;
    }

    public String getUnit() {
	return unit;
    }

    public void setUnit(String unit) {
	this.unit = unit;
    }

    public BigDecimal getValue() {
	return value;
    }

    public void setValue(BigDecimal value) {
	this.value = value;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public Collection<WsIntPurchaseProdAccounting> getProdAccounting() {
	return prodAccounting;
    }

    public void setProdAccounting(Collection<WsIntPurchaseProdAccounting> prodAccounting) {
	this.prodAccounting = prodAccounting;
    }

    public BigDecimal getProdDiscountvalue() {
	return prodDiscountvalue;
    }

    public void setProdDiscountvalue(BigDecimal prodDiscountvalue) {
	this.prodDiscountvalue = prodDiscountvalue;
    }

    public BigDecimal getProdDiscountperc() {
	return prodDiscountperc;
    }

    public void setProdDiscountperc(BigDecimal prodDiscountperc) {
	this.prodDiscountperc = prodDiscountperc;
    }

    public WsIntPurchase getWsIntPurchase() {
	return wsIntPurchase;
    }

    public void setWsIntPurchase(WsIntPurchase wsIntPurchase) {
	this.wsIntPurchase = wsIntPurchase;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((prodCode == null) ? 0 : prodCode.hashCode());
	result = prime * result + ((wsIntPurchase == null) ? 0 : wsIntPurchase.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof WsIntItemPurchase))
	    return false;
	WsIntItemPurchase other = (WsIntItemPurchase) obj;
	if (prodCode == null) {
	    if (other.prodCode != null)
		return false;
	} else if (!prodCode.equals(other.prodCode))
	    return false;
	if (wsIntPurchase == null) {
	    if (other.wsIntPurchase != null)
		return false;
	} else if (!wsIntPurchase.equals(other.wsIntPurchase))
	    return false;
	return true;
    }
}
