package com.warehouse.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.LockStockOrderState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//锁库
@Entity
@Table(name = "td_lock_stock_order")
public class LockStockOrder implements Serializable {

    private static final long serialVersionUID = 3605574807685800249L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;//仓库（第一级）

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "lockStockOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LockStockOrderLog> lockStockOrderLogSet;//日志集合

    //@JsonIgnore //为了列表展示时同时把明细展示出来
    @OneToMany(mappedBy = "lockStockOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LockStockOrderDetail> lockStockOrderDetailSet;//明细集合

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String id;

    @NotBlank(message = "单号不能为空")
    @Column(nullable = false, unique = true, length = 200)
    private String orderNo;

    @NotNull(message = "状态不能为空")
    @Column(nullable = false, length = 10)
    private Integer orderState; //新建 锁定(确认操作) 解锁(解锁操作) 作废

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

    //返回订单状态对应的枚举Txt
    public String getOrderStateTxt() {
        if (this.getOrderState() != null) {
            return LockStockOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<LockStockOrderLog> getLockStockOrderLogSet() {
        return lockStockOrderLogSet;
    }

    public void setLockStockOrderLogSet(Set<LockStockOrderLog> lockStockOrderLogSet) {
        this.lockStockOrderLogSet = lockStockOrderLogSet;
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

    public Set<LockStockOrderDetail> getLockStockOrderDetailSet() {
        return lockStockOrderDetailSet;
    }

    public void setLockStockOrderDetailSet(Set<LockStockOrderDetail> lockStockOrderDetailSet) {
        this.lockStockOrderDetailSet = lockStockOrderDetailSet;
    }
}

