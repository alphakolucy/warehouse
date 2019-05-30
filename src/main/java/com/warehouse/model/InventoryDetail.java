package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

//仓库库存盘点明细
@Entity
@Table(name = "td_warehouse_inventory_detail")
public class InventoryDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4658250284777472256L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;  //盘点单ID

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_site_id")
    private Warehouse warehouseSite;  //仓库(每条明细所在仓库)

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_area_id")
    private Warehouse warehouseArea;  //库区(每条明细所在库区)

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;  //库位(每条明细所在库位)

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_level_id")
    private Warehouse warehouseLevel;  //库层(每条明细所在库层)

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;  //物资

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String id;

    @Transient
    private String productId;//物资Id
    @Transient
    private String warehouseLevelId;//库层Id

    @NotNull(message = "库存件数不能为空")
    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal num;

    @NotNull(message = "库存吨数不能为空")
    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal tonNum;

    @Column(columnDefinition = "datetime default now()")
    @Generated(GenerationTime.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    public Warehouse getWarehouseLevel() {
        return warehouseLevel;
    }

    public void setWarehouseLevel(Warehouse warehouseLevel) {
        this.warehouseLevel = warehouseLevel;
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
    //库层自定义返回
    public JSONObject getWarehouseLevelData() {
        if (this.getWarehouseLevel() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouseLevel().getId());//id
            jsonObject.put("name", this.getWarehouseLevel().getName());//名称
            return jsonObject;
        }
        return null;
    }

    //盘点自定义返回
    public JSONObject getInventoryData() {
        if (this.getInventory() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getInventory().getId());//id
            jsonObject.put("orderNo", this.getInventory().getOrderNo());
            jsonObject.put("createMan", this.getInventory().getCreateMan());
            jsonObject.put("createManId", this.getInventory().getCreateManId());
            jsonObject.put("createTime", this.getInventory().getCreateTime());
            jsonObject.put("orderState", this.getInventory().getOrderState());
            jsonObject.put("orderStateTxt", this.getInventory().getOrderStateTxt());
            jsonObject.put("remark", this.getInventory().getRemark());
            return jsonObject;
        }
        return null;
    }

    //物资自定义返回
    public JSONObject getProductData() {
        if (this.getProduct() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productName", this.getProduct().getProductName());//物资名称
            jsonObject.put("id", this.getProduct().getId());//id
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getWarehouseLevelId() {
        return warehouseLevelId;
    }

    public void setWarehouseLevelId(String warehouseLevelId) {
        this.warehouseLevelId = warehouseLevelId;
    }

    public Warehouse getWarehouseSite() {
        return warehouseSite;
    }

    public void setWarehouseSite(Warehouse warehouseSite) {
        this.warehouseSite = warehouseSite;
    }

    public Warehouse getWarehouseArea() {
        return warehouseArea;
    }

    public void setWarehouseArea(Warehouse warehouseArea) {
        this.warehouseArea = warehouseArea;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
