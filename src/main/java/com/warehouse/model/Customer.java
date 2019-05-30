package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.SettlementMethod;
import com.warehouse.util.enums.WeightMethod;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "td_customer")
public class Customer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2783789378233083828L;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CustomerLog> customerLog;//委托单日志集合

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_customer_product", joinColumns = {
            @JoinColumn(name = "customer_id", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "ID")})
    private List<Product> products;

    public List<Product> getProducts() {
    		if (this.products != null) {
    			return this.products;
    		}
		return null;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "企业名称不能为空")
	@Column(nullable = false, length = 200)
	private String name;

	@NotNull(message = "结算方式不能为空")
	@Column(nullable = false)
	private Integer settlementMethod; //月结  现结

	@NotNull(message = "计重方式不能为空")
	@Column(nullable = false)
	private Integer weightMethod; //理重  实重

	private Boolean isUnknown; //未知客户

	@Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal detentionTonNum;  //扣押数量

	private Boolean isUse; //是否启用

    @Column(  length = 200)
    private String createMan;

    @Column( length = 32)
    private String createManId;

    //返回结算方式对应的枚举Txt
    public String getSettlementMethodTxt(){
        if(this.getSettlementMethod()!=null){
            return SettlementMethod.getTxtByValue(this.getSettlementMethod());
        }
        return null;
    }

    //返回计重方式对应的枚举Txt
    public String getWeightMethodTxt(){
        if(this.getWeightMethod()!=null){
            return WeightMethod.getTxtByValue(this.getWeightMethod());
        }
        return null;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(Integer settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public Integer getWeightMethod() {
		return weightMethod;
	}

	public void setWeightMethod(Integer weightMethod) {
		this.weightMethod = weightMethod;
	}
	public Boolean getIsUnknown() {
		return isUnknown;
	}
	public void setIsUnknown(Boolean isUnknown) {
		this.isUnknown = isUnknown;
	}

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }
	public BigDecimal getDetentionTonNum() {
		return detentionTonNum;
	}
	public void setDetentionTonNum(BigDecimal detentionTonNum) {
		this.detentionTonNum = detentionTonNum;
	}
	public Set<CustomerLog> getCustomerLog() {
		return customerLog;
	}
	public void setCustomerLog(Set<CustomerLog> customerLog) {
		this.customerLog = customerLog;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public String getCreateManId() {
		return createManId;
	}
	public void setCreateManId(String createManId) {
		this.createManId = createManId;
	}

	public String toString() {
		return String.format("name:%s settlementMethod:%s  weightMethod:%s detentionTonNum:%s isUse:%s", this.name, this.getSettlementMethodTxt(), this.getWeightMethodTxt(), this.getDetentionTonNum(), this.getIsUse().toString());
	}
}
