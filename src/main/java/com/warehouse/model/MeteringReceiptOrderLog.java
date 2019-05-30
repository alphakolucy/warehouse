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
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.MeteringReceiptOrderState;
import com.warehouse.util.enums.TransferState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//计量单日志
@Entity
@Table(name = "td_metering_order_log")
public class MeteringReceiptOrderLog implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7359198918088398924L;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "metering_order_id")
    private MeteringReceiptOrder meteringReceiptOrder;//计量单

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	//@NotNull(message = "订单状态不能为空")
	//@Column(nullable = false, length=10)
	private Integer orderState; //新建 确认 作废

	@NotBlank(message = "操作人不能为空")
	@Column(nullable = false,  length = 200)
	private String createMan;

	@NotBlank(message = "操作人ID不能为空")
	@Column(nullable = false,  length = 32)
	private String createManId;

	//备注
	@Column(length = 1000)
	private String remark;

	@Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

    //返回订单状态对应的枚举Txt
    public String getOrderStateTxt(){
        if(this.getOrderState()!=null){
            return MeteringReceiptOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    //计量单自定义返回
    public JSONObject getMeteringReceiptOrderData() {
        if (this.getMeteringReceiptOrder() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getMeteringReceiptOrder().getId());//id
            jsonObject.put("orderNo", this.getMeteringReceiptOrder().getOrderNo());//计量单单号
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

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MeteringReceiptOrder getMeteringReceiptOrder() {
		return meteringReceiptOrder;
	}

	public void setMeteringReceiptOrder(MeteringReceiptOrder meteringReceiptOrder) {
		this.meteringReceiptOrder = meteringReceiptOrder;
	}


}
