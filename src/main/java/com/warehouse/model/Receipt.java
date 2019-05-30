package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//收发货单
@Entity
@Table(name = "td_receipt")
public class Receipt implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 4336595591913885450L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entrust_order_id")
    private EntrustOrder entrustOrder;//委托单(方便查询)

    //委托单明细(目前1对1,生成过的明细就不能再生成了)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entrust_order_detail_id")
    private EntrustOrderDetail entrustOrderDetail;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_site_id")
    private Warehouse warehouseSite;//仓库（第一级）来之委托单

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_area_id")
    private Warehouse warehouseArea;//库区(入库单：第一分库区)

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;//物资来之委托单

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;//客户来之委托单(入库确认时再分客户，修改它和委托里)

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;  //库位(分配的是第三级)

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_level_id")
    private Warehouse warehouseLevel;  //库层(出库单：第一步分库层)

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "settlement_id")
    private ReceiptSettlement receiptSettlement; //结算单(针对月结,生成结算单回写此字段)

    @JsonIgnore
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ReceiptLog> receiptLogSet;//收发货单日志集合

    //@JsonIgnore //不屏蔽让前端列表好做展示
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MeteringReceiptOrder> meteringReceiptOrderSet;//计量单集合

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "单号不能为空")
	@Column(nullable = false,  unique = true, length = 200)
	private String orderNo;

	@NotNull(message = "订单状态不能为空")
	@Column(nullable = false, length=10)
	private Integer orderState; //新建 计量中 已计量 已确认 作废

	@NotNull(message = "订单类型不能为空")
	@Column(nullable = false, length=10)
	private Integer orderType; //入库 出库

	//@NotNull(message = "费用不能为空")
    //收发货单确认时 合计 已确认的计量单费用
	@Column(columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal fee;

	private Boolean isSettlement;//是否已结算(月结生成结算单修改为true,现结等于true)

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

	//委托单里客户带过来的
	//@NotNull(message = "结算方式不能为空")
    private Integer settlementMethod; //月结  现结 (枚举：SettlementMethod)

    //委托单里带过来的(用来判断是需要库存操作)
    private Integer entrustOrderType; //收货  提货  对接（对接只生成出库单，但并不减少库存）

    //计量单是否有一个确认过(只有确认过一个收发货单才能做确认操作)
    private Boolean hasMeteringConfirm;

    //@NotNull(message = "件数不能为空") //收发货单确认时 合计 已确认的计量单件数
    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal num;

    //@NotNull(message = "吨数不能为空") //收发货单确认时 合计 已确认的计量单吨数
    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal tonNum;

    //本年第几周(今年第几周,2位表示)
    private String yearWeek;
    //周序号(开始值:1000)
    private String weekNo;

    //调度员(收货单新增)
    private String yardman;

    //理货员(收货单新增)
    private String tallyMan;

    //装卸工(收、发货单确认)
    private String loadMan;

    //稽核员(收货单确认)
    private String auditMan;

    //单位费用(此物资分类,关联计费方式表fee冗余)
    private BigDecimal unitFee;

    //费用用途(用于理重或实重计算,此物资分类,关联计费方式表冗余)
    private Integer feePurpose;

    //是否分配库区/库层
    private Boolean handWarehouse;

    //委托单明细自定义返回
    public JSONObject getEntrustOrderDetailData() {
        if (this.getEntrustOrderDetail() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getEntrustOrderDetail().getId());//id
            jsonObject.put("vehicleNo", this.getEntrustOrderDetail().getVehicleNo());//车号
            jsonObject.put("num", this.getEntrustOrderDetail().getNum());//件数
            jsonObject.put("tonNum", this.getEntrustOrderDetail().getTonNum());//吨数
            jsonObject.put("vehicleNo", this.getEntrustOrderDetail().getVehicleNo());//吨数
            jsonObject.put("entrustorderNo", this.getEntrustOrderDetail().getEntrustOrder().getOrderNo());
            jsonObject.put("entrustorderId", this.getEntrustOrderDetail().getEntrustOrder().getId());
            jsonObject.put("customerId", this.getEntrustOrderDetail().getEntrustOrder().getCustomer().getId());
            jsonObject.put("ywNo", this.getEntrustOrderDetail().getEntrustOrder().getYearWeek() + this.getEntrustOrderDetail().getEntrustOrder().getWeekNo());
            return jsonObject;
        }
        return null;
    }

    //计划数量、计算吨数
    public JSONObject getPlanData() {
        JSONObject jsonObject = new JSONObject();
        BigDecimal planNum = BigDecimal.ZERO;
        BigDecimal planTonNum = BigDecimal.ZERO;
        if (this.getEntrustOrderDetail() != null) {
            planNum = this.getEntrustOrderDetail().getNum();
            planTonNum = this.getEntrustOrderDetail().getTonNum();//吨数
        }
        jsonObject.put("planNum", planNum);//件数
        jsonObject.put("planTonNum", planTonNum);//吨数
        return jsonObject;
    }

    //已计量 件数、吨数
    public JSONObject getMeterData(){
        JSONObject jsonObject = new JSONObject();
        BigDecimal meterNum = BigDecimal.ZERO;
        BigDecimal meterTonNum = BigDecimal.ZERO;
        if(this.getMeteringReceiptOrderSet() != null){
            Iterator<MeteringReceiptOrder> iterator = this.getMeteringReceiptOrderSet().iterator();
            while(iterator.hasNext()){
                MeteringReceiptOrder next = iterator.next();
                //不等于作废的都算
                if(!MeteringReceiptOrderState.INVALID.getValue().equals(next.getOrderState())){
                    meterNum = meterNum.add(next.getNum());
                    meterTonNum = meterTonNum.add(next.getTonNum());
                }
            }
        }
        jsonObject.put("meterNum", meterNum);//件数
        jsonObject.put("meterTonNum", meterTonNum);//吨数
        return jsonObject;
    }

    //未计量 数量、吨数
    public JSONObject getNoMeterData(){
        JSONObject jsonObject = new JSONObject();
        BigDecimal noMeterNum = this.getPlanData().getBigDecimal("planNum")
                .subtract(this.getMeterData().getBigDecimal("meterNum"));
        BigDecimal noMeterTonNum = this.getPlanData().getBigDecimal("planTonNum")
                .subtract(this.getMeterData().getBigDecimal("meterTonNum"));

        jsonObject.put("noMeterNum", noMeterNum);//件数
        jsonObject.put("noMeterTonNum", noMeterTonNum);//吨数
        return jsonObject;
    }

    //物资自定义返回
    public JSONObject getProductData() {
        if (this.getProduct() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getProduct().getId());//id
            jsonObject.put("productName", this.getProduct().getProductName());//物资名称
            jsonObject.put("unit", this.getProduct().getUnit());//单位
            jsonObject.put("rationale", this.getProduct().getRationale());//理重
            jsonObject.put("spec", this.getProduct().getSpec());//规格
            jsonObject.put("model", this.getProduct().getModel());//型号
            jsonObject.put("material", this.getProduct().getMaterial());//材料
            jsonObject.put("maker", this.getProduct().getMaker());//厂家
            jsonObject.put("productCateName", this.getProduct().getCate().getProductCateName());//分类名
            jsonObject.put("fullName", this.getProduct().getFullName());//分类名
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
            jsonObject.put("isUnknown", this.getCustomer().getIsUnknown());//是否未知
            return jsonObject;
        }
        return null;
    }

    //仓库自定义返回
    public JSONObject getWarehouseSiteData() {
        if (this.getWarehouseSite() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouseSite().getId());//id
            jsonObject.put("name", this.getWarehouseSite().getName());//名称
            return jsonObject;
        }
        return null;
    }

    //库区自定义返回
    public JSONObject getWarehouseAreaData() {
        if (this.getWarehouseArea() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouseArea().getId());//id
            jsonObject.put("name", this.getWarehouseArea().getName());//名称
            return jsonObject;
        }
        return null;
    }

    //库位自定义返回
    public JSONObject getWarehouseData() {
        if (this.getWarehouse() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouse().getId());//id
            jsonObject.put("name", this.getWarehouse().getName());//名称
            return jsonObject;
        }
        return null;
    }

	//返回订单状态对应的枚举Txt
    public String getOrderStateTxt(){
        if(this.getOrderState()!=null){
            return ReceiptOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    //返回订单类型对应的枚举Txt
    public String getOrderTypeTxt(){
        if(this.getOrderType()!=null){
            return ReceiptOrderType.getTxtByValue(this.getOrderType());
        }
        return null;
    }


    public String getTransStateTxt() {
		// TransState
		if(this.getOrderState()!=null){
	        return TransState.getTxtByValue(this.getOrderState());
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public ReceiptSettlement getReceiptSettlement() {
		return receiptSettlement;
	}

	public void setReceiptSettlement(ReceiptSettlement receiptSettlement) {
		this.receiptSettlement = receiptSettlement;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Boolean getIsSettlement() {
		return isSettlement;
	}

	public void setIsSettlement(Boolean isSettlement) {
		this.isSettlement = isSettlement;
	}

    public EntrustOrderDetail getEntrustOrderDetail() {
        return entrustOrderDetail;
    }

    public void setEntrustOrderDetail(EntrustOrderDetail entrustOrderDetail) {
        this.entrustOrderDetail = entrustOrderDetail;
    }

    public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

    public Set<ReceiptLog> getReceiptLogSet() {
        return receiptLogSet;
    }

    public void setReceiptLogSet(Set<ReceiptLog> receiptLogSet) {
        this.receiptLogSet = receiptLogSet;
    }

    public Integer getSettlementMethod() {
        return settlementMethod;
    }

    public void setSettlementMethod(Integer settlementMethod) {
        this.settlementMethod = settlementMethod;
    }

    public Integer getEntrustOrderType() {
        return entrustOrderType;
    }

    public void setEntrustOrderType(Integer entrustOrderType) {
        this.entrustOrderType = entrustOrderType;
    }

    public Warehouse getWarehouseSite() {
        return warehouseSite;
    }

    public void setWarehouseSite(Warehouse warehouseSite) {
        this.warehouseSite = warehouseSite;
    }

    public Boolean getHasMeteringConfirm() {
        return hasMeteringConfirm;
    }

    public void setHasMeteringConfirm(Boolean hasMeteringConfirm) {
        this.hasMeteringConfirm = hasMeteringConfirm;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Warehouse getWarehouseArea() {
        return warehouseArea;
    }

    public void setWarehouseArea(Warehouse warehouseArea) {
        this.warehouseArea = warehouseArea;
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

    public Set<MeteringReceiptOrder> getMeteringReceiptOrderSet() {
        return meteringReceiptOrderSet;
    }

    public void setMeteringReceiptOrderSet(Set<MeteringReceiptOrder> meteringReceiptOrderSet) {
        this.meteringReceiptOrderSet = meteringReceiptOrderSet;
    }

	public Warehouse getWarehouseLevel() {
		return warehouseLevel;
	}

	public void setWarehouseLevel(Warehouse warehouseLevel) {
		this.warehouseLevel = warehouseLevel;
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

    public String getYardman() {
        return yardman;
    }

    public void setYardman(String yardman) {
        this.yardman = yardman;
    }

    public String getLoadMan() {
        return loadMan;
    }

    public void setLoadMan(String loadMan) {
        this.loadMan = loadMan;
    }

    public String getAuditMan() {
        return auditMan;
    }

    public void setAuditMan(String auditMan) {
        this.auditMan = auditMan;
    }

    public String getTallyMan() {
        return tallyMan;
    }

    public void setTallyMan(String tallyMan) {
        this.tallyMan = tallyMan;
    }

    public BigDecimal getUnitFee() {
        return unitFee;
    }

    public void setUnitFee(BigDecimal unitFee) {
        this.unitFee = unitFee;
    }

    public Integer getFeePurpose() {
        return feePurpose;
    }

    public void setFeePurpose(Integer feePurpose) {
        this.feePurpose = feePurpose;
    }

    public EntrustOrder getEntrustOrder() {
        return entrustOrder;
    }

    public void setEntrustOrder(EntrustOrder entrustOrder) {
        this.entrustOrder = entrustOrder;
    }

    public Boolean getHandWarehouse() {
        return handWarehouse;
    }

    public void setHandWarehouse(Boolean handWarehouse) {
        this.handWarehouse = handWarehouse;
    }

    public JSONObject gettimeZone() {
        try
        {

        	 	Calendar cal = Calendar.getInstance();
        	 	cal.setTime(this.createTime);//设置起时间
        	 	cal.add(Calendar.HOUR_OF_DAY, 3);//增加小时


        		long diff = cal.getTime().getTime() - (new Date()).getTime();
        		long days = diff / (1000 * 60 * 60 * 24);

        		long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
        		long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);


        		JSONObject jsonObject = new JSONObject();
            jsonObject.put("days", days);
            jsonObject.put("hours", hours);
            jsonObject.put("minutes", minutes);
            return jsonObject;
        }
        catch (Exception e)
        {
        		return null;
        }
    }

}
