package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "td_customer_warehouse_stock_report_for_month")
public class CustomerWarehouseStockReportForMonth  implements Serializable  {

// 	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_site_id")
    private Warehouse warehouseSite;//仓库

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;//客户

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;//物资

	/**
	 *
	 */
	private static final long serialVersionUID = 7068211459179340538L;

	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String id;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal num;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal tonNum;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal inNum;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal inTonNum;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal outNum;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal outTonNum;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal yesterdayOutNum;

    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal yesterdayOutTonNum;

    @Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

	public Warehouse getWarehouseSite() {
		return warehouseSite;
	}

	public void setWarehouseSite(Warehouse warehouseSite) {
		this.warehouseSite = warehouseSite;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public BigDecimal getTonNum() {
		return tonNum;
	}

	public void setTonNum(BigDecimal tonNum) {
		this.tonNum = tonNum;
	}

	public BigDecimal getInNum() {
		return inNum;
	}

	public void setInNum(BigDecimal inNum) {
		this.inNum = inNum;
	}

	public BigDecimal getInTonNum() {
		return inTonNum;
	}

	public void setInTonNum(BigDecimal inTonNum) {
		this.inTonNum = inTonNum;
	}

	public BigDecimal getOutNum() {
		return outNum;
	}

	public void setOutNum(BigDecimal outNum) {
		this.outNum = outNum;
	}

	public BigDecimal getOutTonNum() {
		return outTonNum;
	}

	public void setOutTonNum(BigDecimal outTonNum) {
		this.outTonNum = outTonNum;
	}

	public BigDecimal getYesterdayOutNum() {
		return yesterdayOutNum;
	}

	public void setYesterdayOutNum(BigDecimal yesterdayOutNum) {
		this.yesterdayOutNum = yesterdayOutNum;
	}

	public BigDecimal getYesterdayOutTonNum() {
		return yesterdayOutTonNum;
	}

	public void setYesterdayOutTonNum(BigDecimal yesterdayOutTonNum) {
		this.yesterdayOutTonNum = yesterdayOutTonNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
