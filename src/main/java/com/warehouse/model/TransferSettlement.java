package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
//过户结算
@Entity
@Table(name = "td_transfer_settlement")
public class TransferSettlement implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7543237035768997546L;

    @JsonIgnore
    @OneToMany(mappedBy = "transferSettlement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transfer> transferSet;//过户单集合


    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "结算单号不能为空")
	@Column(nullable = false,  unique = true, length = 200)
	private String orderNo;

	@NotNull(message = "结算费用不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal amount;

	private Boolean isPay;//是否生成过支付单(暂时是1个结算单只付1次)

	@NotBlank(message = "操作人不能为空")
	@Column(nullable = false,  length = 100)
	private String createMan;

	@NotBlank(message = "操作人ID不能为空")
	@Column(nullable = false,  length = 32)
	private String createManId;

	@Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Boolean getIsPay() {
		return isPay;
	}

	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}

    public Set<Transfer> getTransferSet() {
        return transferSet;
    }

    public void setTransferSet(Set<Transfer> transferSet) {
        this.transferSet = transferSet;
    }
}
