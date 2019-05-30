package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.EntrustOrderState;
import com.warehouse.util.enums.EntrustOrderType;
import com.warehouse.util.enums.TransferState;
import com.warehouse.util.enums.TransferType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
//过户
@Entity
@Table(name = "td_transfer")
public class Transfer implements Serializable  {

	/**
	 *
	 */
	private static final long serialVersionUID = -4341298673528619861L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_customer_id")
    private Customer fromCustomer;  //从

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_customer_id")
    private Customer toCustomer; //到

    //过户结算单(生成过户结算单的时候回写此字段)
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "settlement_id")
    private TransferSettlement transferSettlement;

    @JsonIgnore
    @OneToMany(mappedBy = "transfer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransferLog> transferLogSet;//过户单日志集合

    @OneToMany(mappedBy = "transfer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransferDetail> transferDetailSet;//明细

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "单号不能为空")
	@Column(nullable = false,  unique = true, length = 200)
	private String orderNo;

	//@NotNull(message = "费用不能为空") //明细费用的合计
	@Column(columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal fee;

	@NotNull(message = "过户方式不能为空")
	@Column(nullable = false)
	private Integer transferType;//过户方式  直接过户（直接调整库存）  需要仓库确认（库仓库确认为调整）

	@NotNull(message = "过户状态不能为空")
	@Column(nullable = false)
	private Integer transferState;//过户状态  新建  已过户 作废

	//备注
	@Column(length = 1000)
	private String remark;

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

    private Boolean isSettlement;//是否已结算

    //返回过户状态对应的枚举Txt
    public String getTransferStateTxt(){
        if(this.getTransferState()!=null){
            return TransferState.getTxtByValue(this.getTransferState());
        }
        return null;
    }

    //返回过户类型对应的枚举Txt
    public String getTransferTypeTxt(){
        if(this.getTransferType()!=null){
            return TransferType.getTxtByValue(this.getTransferType());
        }
        return null;
    }

    //从某个客户自定义返回
    public JSONObject getFromCustomerData() {
        if (this.getFromCustomer() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getFromCustomer().getId());//id
            jsonObject.put("name", this.getFromCustomer().getName());//企业名称
            return jsonObject;
        }
        return null;
    }

    //到某个客户自定义返回
    public JSONObject getToCustomerData() {
        if (this.getToCustomer() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getToCustomer().getId());//id
            jsonObject.put("name", this.getToCustomer().getName());//企业名称
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

    public Customer getFromCustomer() {
		return fromCustomer;
	}

	public void setFromCustomer(Customer fromCustomer) {
		this.fromCustomer = fromCustomer;
	}

	public Customer getToCustomer() {
		return toCustomer;
	}

	public void setToCustomer(Customer toCustomer) {
		this.toCustomer = toCustomer;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getTransferType() {
		return transferType;
	}

	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}

	public Integer getTransferState() {
		return transferState;
	}

	public void setTransferState(Integer transferState) {
		this.transferState = transferState;
	}

	public TransferSettlement getTransferSettlement() {
		return transferSettlement;
	}

	public void setTransferSettlement(TransferSettlement transferSettlement) {
		this.transferSettlement = transferSettlement;
	}

    public Set<TransferLog> getTransferLogSet() {
        return transferLogSet;
    }

    public void setTransferLogSet(Set<TransferLog> transferLogSet) {
        this.transferLogSet = transferLogSet;
    }

    public Boolean getIsSettlement() {
        return isSettlement;
    }

    public void setIsSettlement(Boolean isSettlement) {
        this.isSettlement = isSettlement;
    }

    public Set<TransferDetail> getTransferDetailSet() {
        return transferDetailSet;
    }

    public void setTransferDetailSet(Set<TransferDetail> transferDetailSet) {
        this.transferDetailSet = transferDetailSet;
    }
}
