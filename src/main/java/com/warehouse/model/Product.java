package com.warehouse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

//物资
@Entity
@Table(name = "td_product")
public class Product implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2199037843504175654L;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_customer_product", joinColumns = {
            @JoinColumn(name = "customer_id", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "ID")})
	private List<Customer> customers;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id")
    private ProductCates cate;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "物资名称不能为空")
	@Column(nullable = false, length = 200)
	private String productName;

	@NotBlank(message = "单位不能为空")
	@Column(nullable = false, length = 20)
	private String unit;

	@NotBlank(message = "理重不能为空")
	@Column(nullable = false, length = 20)
	private String rationale;

	//@NotBlank(message = "规格不能为空")
	@Column(nullable = false, length = 100)
	private String spec;

	//@NotBlank(message = "型号不能为空")
	@Column(nullable = false, length = 100)
	private String model;

	//@NotBlank(message = "材料不能为空")
	@Column(nullable = false, length = 100)
	private String material;

	@NotBlank(message = "厂家不能为空")
	@Column(nullable = false, length = 200)
	private String maker;

	@Column(columnDefinition = "datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createTime;

    private Boolean isUse; //是否启用

    //物资全称
    public String getFullName() {
    		return this.getProductName() + "/" + this.getSpec() + "/" + this.getModel() + "/" + this.getMaterial() + "/" + this.getMaker();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public ProductCates getCate() {
		return cate;
	}

	public void setCate(ProductCates cate) {
		this.cate = cate;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

}
