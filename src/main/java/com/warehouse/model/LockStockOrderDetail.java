package com.warehouse.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

//锁库单明细
@Entity
@Table(name = "td_lock_order_detail")
public class LockStockOrderDetail implements Serializable {

    private static final long serialVersionUID = -8842990666430389812L;
    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lock_stock_order_id")
    private LockStockOrder lockStockOrder;

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;


	@NotNull(message = "件数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal num;

	@NotNull(message = "吨数不能为空")
	@Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
	private BigDecimal tonNum;



    //锁库单自定义返回
    public JSONObject getLockStockOrderData() {
        if (this.getLockStockOrder() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getLockStockOrder().getId());//id
            jsonObject.put("orderNo", this.getLockStockOrder().getOrderNo());//单号
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
            return jsonObject;
        }
        return null;
    }

    public LockStockOrder getLockStockOrder() {
        return lockStockOrder;
    }

    public void setLockStockOrder(LockStockOrder lockStockOrder) {
        this.lockStockOrder = lockStockOrder;
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

}
