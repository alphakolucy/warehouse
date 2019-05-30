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

import com.warehouse.util.enums.InventoryState;
import com.warehouse.util.enums.WarehouseStockLogOpType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//仓库库存日志
@Entity
@Table(name = "td_warehouse_stock_log")
public class WarehouseStockLog implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8877264436146782366L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_sotck_id")
    private WarehouseStock warehouseStock;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "操作单号不能为空")
	@Column(nullable = false,  length = 32)
	private String opOrderNo;

	@NotBlank(message = "操作单号ID不能为空")
	@Column(nullable = false,  length = 32)
	private String opOrderId;

	@NotNull(message = "操作单类型不能为空")
	@Column(nullable = false)
	private Integer opType;//收货 提货 调整 盘库 移库 过户

	@NotNull(message = "操作件数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal opNum;

	@NotNull(message = "操作吨数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal opTonNum;

	@NotNull(message = "结余件数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal blaNum;

	@NotNull(message = "结余吨数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal blaTonNum;

	@NotBlank(message = "操作人不能为空")
	@Column(nullable = false,  length = 200)
	private String createMan;

	@NotBlank(message = "操作人ID不能为空")
	@Column(nullable = false,  length = 32)
	private String createManId;

	@Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

	//返回订单状态对应的枚举Txt
	public String getOpTypeyTxt() {
		if (this.getOpType() != null) {
			return WarehouseStockLogOpType.getTxtByValue(this.getOpType());
		}
		return null;
	}

	public WarehouseStock getWarehouseStock() {
		return warehouseStock;
	}

	public void setWarehouseStock(WarehouseStock warehouseStock) {
		this.warehouseStock = warehouseStock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOpOrderNo() {
		return opOrderNo;
	}

	public void setOpOrderNo(String opOrderNo) {
		this.opOrderNo = opOrderNo;
	}

	public String getOpOrderId() {
		return opOrderId;
	}

	public void setOpOrderId(String opOrderId) {
		this.opOrderId = opOrderId;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public BigDecimal getOpNum() {
		return opNum;
	}

	public void setOpNum(BigDecimal opNum) {
		this.opNum = opNum;
	}

	public BigDecimal getOpTonNum() {
		return opTonNum;
	}

	public void setOpTonNum(BigDecimal opTonNum) {
		this.opTonNum = opTonNum;
	}

	public BigDecimal getBlaNum() {
		return blaNum;
	}

	public void setBlaNum(BigDecimal blaNum) {
		this.blaNum = blaNum;
	}

	public BigDecimal getBlaTonNum() {
		return blaTonNum;
	}

	public void setBlaTonNum(BigDecimal blaTonNum) {
		this.blaTonNum = blaTonNum;
	}



}
