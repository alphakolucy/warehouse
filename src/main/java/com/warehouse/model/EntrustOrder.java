package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.EntrustOrderState;
import com.warehouse.util.enums.EntrustOrderType;
import com.warehouse.util.enums.MeteringReceiptOrderState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//委托单
@Entity
@Table(name = "td_receipt_entrust_order")
public class EntrustOrder implements Serializable  {

	/**
	 *
	 */
	private static final long serialVersionUID = 3503238594994559207L;

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;//仓库（第一级）

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @JsonIgnore
    @OneToMany(mappedBy = "entrustOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EntrustOrderDetail> entrustOrderDetailSet;//委托单明细

    @JsonIgnore
    @OneToMany(mappedBy = "entrustOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EntrustOrderLog> entrustOrderLogSet;//委托单日志集合

    @JsonIgnore
    @OneToMany(mappedBy = "entrustOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MeteringReceiptOrder> meteringReceiptOrderSet;//计量单集合

    @JsonIgnore
    @OneToMany(mappedBy = "entrustOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Receipt> receiptSet;//收发货单集合

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "单号不能为空")
	@Column(nullable = false,  unique = true, length = 200)
	private String orderNo;

	@NotBlank(message = "客户单号不能为空")
	@Column(nullable = false,  length = 200)
	private String customerOrderNo;

	@NotNull(message = "订单状态不能为空")
	@Column(nullable = false, length=10)
	private Integer orderState; //新建 确认  作废  完成

	@NotBlank(message = "到达方式不能为空")
	@Column(nullable = false,  length = 100)
	private String arrivalMode;  //火车 汽车

	@NotNull(message = "订单类型不能为空")
	@Column(nullable = false, length=10)
	private Integer orderType; //收货  提货  对接（对接只生成出库单，但并不减少库存）

	//@NotNull(message = "预收费用不能为空")
	@Column(columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal advanceFee;  //对接委托单时才显示

	//@NotNull(message = "实收费用不能为空") 直接统计计量单费用合计
	//@Column(columnDefinition = "decimal(11,2) default '0.00'")
	//private BigDecimal fee;

	private String consignee;//提货人
	private String tihtype;//提货方式

	@NotBlank(message = "开单人不能为空")
	@Column(nullable = false,  length = 200)
	private String drawer;

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

    //本年第几周(今年第几周,2位表示)
    private String yearWeek;
    //周序号(开始值:1000)
    private String weekNo;

    //费用 fee（合计）
    public BigDecimal getFee() {
        BigDecimal backTotal = BigDecimal.ZERO;
        if (this.getMeteringReceiptOrderSet() != null) {
            Iterator<MeteringReceiptOrder> iterator = this.getMeteringReceiptOrderSet().iterator();
            while (iterator.hasNext()) {
                MeteringReceiptOrder next = iterator.next();
                //确认过的费用
                if (next.getOrderState().equals(MeteringReceiptOrderState.CONFIRM.getValue())
                        && next.getFee() != null) {
                    backTotal = backTotal.add(next.getFee());
                }
            }
            backTotal = backTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return backTotal;
    }

    //返回订单状态对应的枚举Txt
    public String getOrderStateTxt(){
        if(this.getOrderState()!=null){
            return EntrustOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    //返回订单类型对应的枚举Txt
    public String getOrderTypeTxt(){
        if(this.getOrderType()!=null){
            return EntrustOrderType.getTxtByValue(this.getOrderType());
        }
        return null;
    }

    //仓库自定义返回
    public JSONObject getWarehouseData() {
        if (this.getWarehouse() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouse().getId());//id
            jsonObject.put("name", this.getWarehouse().getName());//名称
            return jsonObject;
        }
        return null;
    }

    //客户自定义返回
    public JSONObject getCustomerData() {
        if (this.getCustomer() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getCustomer().getId());//id
            jsonObject.put("name", this.getCustomer().getName());//企业名称
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getCustomerOrderNo() {
		return customerOrderNo;
	}

	public void setCustomerOrderNo(String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	public String getArrivalMode() {
		return arrivalMode;
	}

	public void setArrivalMode(String arrivalMode) {
		this.arrivalMode = arrivalMode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getAdvanceFee() {
		return advanceFee;
	}

	public void setAdvanceFee(BigDecimal advanceFee) {
		this.advanceFee = advanceFee;
	}


    public Set<EntrustOrderDetail> getEntrustOrderDetailSet() {
        return entrustOrderDetailSet;
    }

    public void setEntrustOrderDetailSet(Set<EntrustOrderDetail> entrustOrderDetailSet) {
        this.entrustOrderDetailSet = entrustOrderDetailSet;
    }

    public Set<EntrustOrderLog> getEntrustOrderLogSet() {
        return entrustOrderLogSet;
    }

    public void setEntrustOrderLogSet(Set<EntrustOrderLog> entrustOrderLogSet) {
        this.entrustOrderLogSet = entrustOrderLogSet;
    }

    public Set<MeteringReceiptOrder> getMeteringReceiptOrderSet() {
        return meteringReceiptOrderSet;
    }

    public void setMeteringReceiptOrderSet(Set<MeteringReceiptOrder> meteringReceiptOrderSet) {
        this.meteringReceiptOrderSet = meteringReceiptOrderSet;
    }

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getTihtype() {
		return tihtype;
	}

	public void setTihtype(String tihtype) {
		this.tihtype = tihtype;
	}

    public Set<Receipt> getReceiptSet() {
        return receiptSet;
    }

    public void setReceiptSet(Set<Receipt> receiptSet) {
        this.receiptSet = receiptSet;
    }

    public String getYearWeek() {
        return yearWeek;
    }

    public void setYearWeek(String yearWeek) {
        this.yearWeek = yearWeek;
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }
}
