package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

//委托单明细
@Entity
@Table(name = "td_entrust_order_detail")
public class EntrustOrderDetail implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6216171609243196118L;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entrust_order_id")
    private EntrustOrder entrustOrder;

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	//车号
	@NotBlank(message = "车号不能为空")
	@Column(length=20)
	private String vehicleNo;

	@NotNull(message = "件数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal num;

	@NotNull(message = "吨数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(12,3) default '0.000'")
	private BigDecimal tonNum;

	//是否生成过收发货单(目前1对1,生成过的明细就不能再生成了)
	private Boolean isReceipt;

    //委托单自定义返回
    public JSONObject getEntrustOrderData() {
        if (this.getEntrustOrder() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getEntrustOrder().getId());//id
            jsonObject.put("orderNo", this.getEntrustOrder().getOrderNo());//委托单单号
            jsonObject.put("warehouseData", this.getEntrustOrder().getWarehouseData());//委托单仓库信息
            jsonObject.put("ywNo", this.getEntrustOrder().getYearWeek() + this.getEntrustOrder().getWeekNo());//委托单顺序号
            return jsonObject;
        }
        return null;
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
            jsonObject.put("fullName", this.getProduct().getFullName());//全称
            return jsonObject;
        }
        return null;
    }

	public EntrustOrder getEntrustOrder() {
		return entrustOrder;
	}

	public void setEntrustOrder(EntrustOrder entrustOrder) {
		this.entrustOrder = entrustOrder;
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

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

    public Boolean getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(Boolean isReceipt) {
        this.isReceipt = isReceipt;
    }
}
