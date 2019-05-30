package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.MeteringReceiptOrderState;
import com.warehouse.util.enums.ReceiptOrderType;
import com.warehouse.util.enums.TransState;
import com.warehouse.util.enums.TransferState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
//计量单
@Entity
@Table(name = "td_metering_receipt_order")
public class MeteringReceiptOrder implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8076464567668182884L;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt; //收发货单(一个收发货多次生成，已确认过的收发货就不能再生成了)

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "crane_id")
    private Crane crane;  //吊车

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "steelyard_id")
    private Steelyard steelyard;  //秤

    @JsonIgnore
    @OneToMany(mappedBy = "meteringReceiptOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MeteringReceiptOrderLog> meteringReceiptOrderLogSet;//计量单日志集合

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entrust_order_id")
    private EntrustOrder entrustOrder;//委托单,方便委托单合计费费用


    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "计量单号不能为空")
	@Column(nullable = false,  unique = true, length = 200)
	private String orderNo;

	@NotNull(message = "实收件数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal num;

	@NotNull(message = "实收吨数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(12,3) default '0.000'")
	private BigDecimal tonNum;

	@NotNull(message = "订单状态不能为空")
	@Column(nullable = false, length=10)
	private Integer orderState; //新建 确认  作废

	private Integer receiptType; //出库  入库

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

	//车号(发货单计量才有)
	@Column(length=20)
	private String vehicleNo;

    //1.如果：entrustOrderDetail是理重,费用不管
    //2.如果：entrustOrderDetail是实重,费用 = 计量单实收吨数 * 委托单明细单价
    //@NotNull(message = "实收费用不能为空")
    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal fee;

    //理货员(新增)
    private String tallyMan;
    //计量员(新增)
    private String meteringMan;
    //行车工(新增)
    private String trafficMan;

    //返回订单状态对应的枚举Txt
    public String getOrderStateTxt(){
        if(this.getOrderState()!=null){
            return MeteringReceiptOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    //返回订单类型对应的枚举Txt
    public String getReceiptTypeTxt(){
        if(this.getReceiptType()!=null){
            return ReceiptOrderType.getTxtByValue(this.getReceiptType());
        }
        return null;
    }

    public String getTransStateTxt() {
    		// TransState
    		if(this.getReceiptType()!=null){
            return TransState.getTxtByValue(this.getReceiptType());
        }
        return null;
    }


    //收发货单自定义返回
    public JSONObject getReceiptData() {
        if (this.getReceipt() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getReceipt().getId());//id
            jsonObject.put("orderNo", this.getReceipt().getOrderNo());//单号
            jsonObject.put("productMaker", this.getReceipt().getProduct().getMaker());//厂家
            jsonObject.put("productName", this.getReceipt().getProduct().getProductName());//物资名称
            jsonObject.put("productMaterial", this.getReceipt().getProduct().getMaterial());//材料
            jsonObject.put("productModel", this.getReceipt().getProduct().getModel());//型号
            jsonObject.put("productSpec", this.getReceipt().getProduct().getSpec());//规格
            jsonObject.put("productRationale", this.getReceipt().getProduct().getRationale());//理重
            jsonObject.put("productUnit", this.getReceipt().getProduct().getUnit());//单位
            jsonObject.put("orderType", this.getReceipt().getOrderType());//收发货单类型
            jsonObject.put("fullName", this.getReceipt().getProduct().getFullName());
            return jsonObject;
        }
        return null;
    }

    //吊车自定义返回
    public JSONObject getCraneData() {
        if (this.getCrane() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getCrane().getId());//id
            jsonObject.put("craneNo", this.getCrane().getCraneNo());//吊车编号
            return jsonObject;
        }
        return null;
    }

    //秤自定义返回
    public JSONObject getSteelyardData() {
        if (this.getSteelyard() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getSteelyard().getId());//id
            jsonObject.put("steelyardNo", this.getSteelyard().getSteelyardNo());//秤编号
            return jsonObject;
        }
        return null;
    }

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
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

	public Crane getCrane() {
		return crane;
	}

	public void setCrane(Crane crane) {
		this.crane = crane;
	}

	public Steelyard getSteelyard() {
		return steelyard;
	}

	public void setSteelyard(Steelyard steelyard) {
		this.steelyard = steelyard;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

    public Set<MeteringReceiptOrderLog> getMeteringReceiptOrderLogSet() {
        return meteringReceiptOrderLogSet;
    }

    public void setMeteringReceiptOrderLogSet(Set<MeteringReceiptOrderLog> meteringReceiptOrderLogSet) {
        this.meteringReceiptOrderLogSet = meteringReceiptOrderLogSet;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public EntrustOrder getEntrustOrder() {
        return entrustOrder;
    }

    public void setEntrustOrder(EntrustOrder entrustOrder) {
        this.entrustOrder = entrustOrder;
    }

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

    public String getTallyMan() {
        return tallyMan;
    }

    public void setTallyMan(String tallyMan) {
        this.tallyMan = tallyMan;
    }

    public String getMeteringMan() {
        return meteringMan;
    }

    public void setMeteringMan(String meteringMan) {
        this.meteringMan = meteringMan;
    }

    public String getTrafficMan() {
        return trafficMan;
    }

    public void setTrafficMan(String trafficMan) {
        this.trafficMan = trafficMan;
    }

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}
}
