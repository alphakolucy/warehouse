package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.AdjustOrderState;
import com.warehouse.util.enums.InventoryState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//库存调整
@Entity
@Table(name = "td_community_order")
public class Order implements Serializable {

    /**
     *
     */
//    private static final long serialVersionUID = -273666242775479203L;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "community_stock_id")
    private WarehouseStock warehouseStock;  //仓库库存


    @JsonIgnore
    @OneToMany(mappedBy = "adjustOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdjustOrderLog> adjustOrderLogSet;//调整日志集合

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String order_id;

    @NotBlank(message = "单号不能为空")
    @Column(nullable = false, unique = true, length = 200)
    private String orderNo;

    @NotBlank(message = "机构名不能为空")
    @Column(nullable = false, unique = true, length = 200)
    private String organization;

    //进行 完成 取消
    @NotNull(message = "订单状态不能为空")
    @Column(nullable = false, length = 10)
    private Integer orderState;


    @Column(length = 1000)
    private String remark;



    @Column(columnDefinition = "datetime default now()")
    @Generated(GenerationTime.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;


    //返回订单状态对应的枚举Txt
    public String getOrderStateTxt() {
        if (this.getOrderState() != null) {
            return AdjustOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    public String getId() {
        return order_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setId(String id) {
        this.order_id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Set<AdjustOrderLog> getAdjustOrderLogSet() {
        return adjustOrderLogSet;
    }

    public void setAdjustOrderLogSet(Set<AdjustOrderLog> adjustOrderLogSet) {
        this.adjustOrderLogSet = adjustOrderLogSet;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public WarehouseStock getWarehouseStock() {
        return warehouseStock;
    }

    public void setWarehouseStock(WarehouseStock warehouseStock) {
        this.warehouseStock = warehouseStock;
    }


}

