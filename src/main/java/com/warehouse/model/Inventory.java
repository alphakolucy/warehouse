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
import com.warehouse.util.enums.InventoryState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//仓库库存盘点
@Entity
@Table(name = "td_warehouse_inventory")
public class Inventory implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1730408501220414504L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_site_id")
    private Warehouse warehouseSite;  //仓库

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_area_id")
    private Warehouse warehouseArea;  //库区

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;  //库位

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_level_id")
    private Warehouse warehouseLevel;  //库层


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String id;

//    @JsonIgnore
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<InventoryDetail> inventoryDetailSet;//库存盘点明细

    @JsonIgnore
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<InventoryLog> inventoryLogSet;//库存盘点日志集合

//    @NotBlank(message = "仓库库存不能为空")
//    @Column(nullable = false, length = 200)
//    private String warehouseStockId;

    @NotBlank(message = "单号不能为空")
    @Column(nullable = false, unique = true, length = 200)
    private String orderNo;

    @NotNull(message = "状态不能为空")
    @Column(nullable = false, length = 10)
    private Integer orderState; //新建 确认 作废

//    @NotNull(message = "库存件数不能为空")
//    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
//    private BigDecimal num;
//
//    @NotNull(message = "库存吨数不能为空")
//    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
//    private BigDecimal tonNum;

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


    //返回订单状态对应的枚举Txt
    public String getOrderStateTxt() {
        if (this.getOrderState() != null) {
            return InventoryState.getTxtByValue(this.getOrderState());
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<InventoryDetail> getInventoryDetailSet() {
        return inventoryDetailSet;
    }

    public void setInventoryDetailSet(Set<InventoryDetail> inventoryDetailSet) {
        this.inventoryDetailSet = inventoryDetailSet;
    }

    public Set<InventoryLog> getInventoryLogSet() {
        return inventoryLogSet;
    }

    public void setInventoryLogSet(Set<InventoryLog> inventoryLogSet) {
        this.inventoryLogSet = inventoryLogSet;
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

//    public BigDecimal getNum() {
//        return num;
//    }
//
//    public void setNum(BigDecimal num) {
//        this.num = num;
//    }
//
//    public BigDecimal getTonNum() {
//        return tonNum;
//    }
//
//    public void setTonNum(BigDecimal tonNum) {
//        this.tonNum = tonNum;
//    }

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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
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

	public Warehouse getWarehouseLevel() {
		return warehouseLevel;
	}

	public void setWarehouseLevel(Warehouse warehouseLevel) {
		this.warehouseLevel = warehouseLevel;
	}

}
