package com.warehouse.model;

import java.io.Serializable;
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

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//收发货结算支付单
@Entity
@Table(name = "td_receipt_settlement_payment")
public class ReceiptSettlementPayment implements Serializable {

	/**
	 *
//	 */
	private static final long serialVersionUID = -5301655680346335117L;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "settlement_id")
    private ReceiptSettlement receiptSettlement;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "支付单号不能为空")
	@Column(nullable = false,  unique = true, length = 200)
	private String orderNo;

	@NotBlank(message = "支付银行凭证号不能为空")
	@Column(nullable = false,  length = 200)
	private String bankVoucherNo;

	@NotBlank(message = "支付银行不能为空")
	@Column(nullable = false,  length = 200)
	private String bankName;

	@NotBlank(message = "操作人不能为空")
	@Column(nullable = false,  length = 100)
	private String createMan;

	@NotBlank(message = "操作人ID不能为空")
	@Column(nullable = false,  length = 32)
	private String createManId;

	//支付时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date draweeTime;

	@Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

	public ReceiptSettlement getReceiptSettlement() {
		return receiptSettlement;
	}

	public void setReceiptSettlement(ReceiptSettlement receiptSettlement) {
		this.receiptSettlement = receiptSettlement;
	}

	//收发货结算单自定义返回
	public JSONObject getReceiptSettlementData() {
		if (this.getReceiptSettlement() != null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", this.getReceiptSettlement().getId());//id
			jsonObject.put("orderNo", this.getReceiptSettlement().getOrderNo());//结算单号
			jsonObject.put("amount", this.getReceiptSettlement().getAmount());//结算费用
			return jsonObject;
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBankVoucherNo() {
		return bankVoucherNo;
	}

	public void setBankVoucherNo(String bankVoucherNo) {
		this.bankVoucherNo = bankVoucherNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public Date getDraweeTime() {
		return draweeTime;
	}

	public void setDraweeTime(Date draweeTime) {
		this.draweeTime = draweeTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
