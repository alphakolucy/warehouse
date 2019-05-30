package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.InventoryState;
import com.warehouse.util.enums.MoveStockOrderState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//库存移库
@Entity
@Table(name = "td_move_stock_order")
public class MoveStockOrder implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2725622463813949322L;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_warehouse_id")
    private Warehouse toWarehouse;  //到 库层

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_stock_id")
    private WarehouseStock warehouseStock;  //仓库库存

    @JsonIgnore
    @OneToMany(mappedBy = "moveStockOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MoveStockOrderLog> moveStockOrderLogSet;//库存移库日志集合

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;//物资(库存冗余方便查询)

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String id;

    @NotBlank(message = "单号不能为空")
    @Column(nullable = false, unique = true, length = 200)
    private String orderNo;

    @NotNull(message = "操作件数不能为空")
    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal num;

    @NotNull(message = "操作吨数不能为空")
    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal tonNum;

    @NotNull(message = "订单状态不能为空")
    @Column(nullable = false, length = 10)
    private Integer orderState; //新建 确认 作废

    //备注
    @Column(length = 1000)
    private String remark;

    @NotBlank(message = "操作人不能为空")
    @Column(nullable = false, length = 200)
    private String createMan;

    @NotBlank(message = "操作人ID不能为空")
    @Column(nullable = false, length = 32)
    private String createManId;

    @Column(columnDefinition = "datetime default now()")
    @Generated(GenerationTime.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    //叉车工(新增)
    private String forkliftMan;

    //库存物资自定义返回
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
            return jsonObject;
        }
        return null;
    }

    //返回订单状态对应的枚举Txt
    public String getOrderStateTxt() {
        if (this.getOrderState() != null) {
            return MoveStockOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    public String getToWarehouseTxt() {
    		if (this.toWarehouse != null) {
    			return this.toWarehouse.getParent().getParent().getName() + " - " + this.toWarehouse.getParent().getName() + " - " + this.toWarehouse.getName();
    		}
    		return null;
    }

    public Warehouse getToWarehouse() {
        return toWarehouse;
    }

    public void setToWarehouse(Warehouse toWarehouse) {
        this.toWarehouse = toWarehouse;
    }

    public WarehouseStock getWarehouseStock() {
        return warehouseStock;
    }

    public void setWarehouseStock(WarehouseStock warehouseStock) {
        this.warehouseStock = warehouseStock;
    }

    public Set<MoveStockOrderLog> getMoveStockOrderLogSet() {
        return moveStockOrderLogSet;
    }

    public void setMoveStockOrderLogSet(Set<MoveStockOrderLog> moveStockOrderLogSet) {
        this.moveStockOrderLogSet = moveStockOrderLogSet;
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

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getForkliftMan() {
        return forkliftMan;
    }

    public void setForkliftMan(String forkliftMan) {
        this.forkliftMan = forkliftMan;
    }
}
