package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the Ws_Int_purchase database table.
 * 
 */
@Entity
@Table(name = "Ws_Int_Purchase")
public class WsIntPurchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "inf_number")
    private String infNumber;    

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inf_date")
    private Calendar infDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inf_deliverydate")
    private Calendar infDeliveryDate;

    @Column(name = "sup_code")
    private String supCode;

    @Column(name = "sup_ponumber")
    private String suppoNumber;


    @Column(name = "user_name")
    private String User_name;

    @Column(name = "payment")
    private String Payment;


    @Column(name = "currency")
    private String Currency;

    @Column(name = "transp_code")
    private String transpCode;


    @Column(name = "transp_freight")
    private String transpFreight;

    @Column(name = "redisp_code")
    private String redispCode;

    @Column(name = "redisp_freight")
    private String redispFreight;

    @Column(name = "tot_valueproduct")
    private BigDecimal totValueproduct;
    
	@Column(name = "tot_freightvalue")
    private BigDecimal tot_FreightValue;

	@Column(name = "tot_discountvalue")
    private BigDecimal tot_DiscountValue;
    
    @Column(name = "tot_discountperc")
    private BigDecimal tot_DiscountPerc;
    
    @Column(name = "tot_value")
    private BigDecimal tot_Value;
    
    @Column(name = "remarks")
    private String remarks;


	// bi-directional many-to-one association to WsIntItemPurchase
    @OneToMany(mappedBy = "wsIntPurchase", cascade = CascadeType.ALL)
    private Collection<WsIntItemPurchase> wsIntItemPurchases;
    
    public WsIntPurchase() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public String getInfNumber() {
		return infNumber;
	}

	public void setInfNumber(String infNumber) {
		this.infNumber = infNumber;
	}
    
	public Calendar getInfDate() {
		return infDate;
	}

	public void setInfDate(Calendar infDate) {
		this.infDate = infDate;
	}

	public Calendar getInfDeliveryDate() {
		return infDeliveryDate;
	}

	public void setInfDeliveryDate(Calendar infDeliveryDate) {
		this.infDeliveryDate = infDeliveryDate;
	}

	public String getSupCode() {
		return supCode;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	public String getSuppoNumber() {
		return suppoNumber;
	}

	public void setSuppoNumber(String suppoNumber) {
		this.suppoNumber = suppoNumber;
	}	

	public String getUser_name() {
		return User_name;
	}

	public void setUser_name(String user_name) {
		User_name = user_name;
	}

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}	

	public String getTranspCode() {
		return transpCode;
	}

	public void setTranspCode(String transpCode) {
		this.transpCode = transpCode;
	}

	public String getTranspFreight() {
		return transpFreight;
	}

	public void setTranspFreight(String transpFreight) {
		this.transpFreight = transpFreight;
	}

	public String getRedispCode() {
		return redispCode;
	}

	public void setRedispCode(String redispCode) {
		this.redispCode = redispCode;
	}

	public String getRedispFreight() {
		return redispFreight;
	}

	public void setRedispFreight(String redispFreight) {
		this.redispFreight = redispFreight;
	}
	
	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public BigDecimal getTotValueproduct() {
		return totValueproduct;
	}

	public void setTotValueproduct(BigDecimal totValueproduct) {
		this.totValueproduct = totValueproduct;
	}

	public BigDecimal getTot_FreightValue() {
		return tot_FreightValue;
	}

	public void setTot_FreightValue(BigDecimal tot_FreightValue) {
		this.tot_FreightValue = tot_FreightValue;
	}

	public BigDecimal getTot_DiscountValue() {
		return tot_DiscountValue;
	}

	public void setTot_DiscountValue(BigDecimal tot_DiscountValue) {
		this.tot_DiscountValue = tot_DiscountValue;
	}

	public BigDecimal getTot_DiscountPerc() {
		return tot_DiscountPerc;
	}

	public void setTot_DiscountPerc(BigDecimal tot_DiscountPerc) {
		this.tot_DiscountPerc = tot_DiscountPerc;
	}

	public BigDecimal getTot_Value() {
		return tot_Value;
	}

	public void setTot_Value(BigDecimal tot_Value) {
		this.tot_Value = tot_Value;
	}

	public Collection<WsIntItemPurchase> getWsIntItemPurchases() {
		return wsIntItemPurchases;
	}

	public void setWsIntItemPurchases(Collection<WsIntItemPurchase> wsIntItemPurchases) {
		this.wsIntItemPurchases = wsIntItemPurchases;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wsIntItemPurchases == null) ? 0 : wsIntItemPurchases.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WsIntPurchase other = (WsIntPurchase) obj;
		if (wsIntItemPurchases == null) {
			if (other.wsIntItemPurchases != null)
				return false;
		} else if (!wsIntItemPurchases.equals(other.wsIntItemPurchases))
			return false;
		return true;
	}

    

    
}
