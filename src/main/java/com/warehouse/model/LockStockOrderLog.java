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
import java.io.Serializable;
import java.util.Date;

//锁库单日志
@Entity
@Table(name = "td_lock_stock_order_log")
public class LockStockOrderLog implements Serializable {

    private static final long serialVersionUID = -902498895358177740L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lock_order_id")
    private LockStockOrder lockStockOrder;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String id;

    //@NotNull(message = "订单状态不能为空")
    @Column(length = 10)
    private Integer orderState; //新建 锁定 解锁 作废

    @NotBlank(message = "操作人不能为空")
    @Column(nullable = false, length = 200)
    private String createMan;

    @NotBlank(message = "操作人ID不能为空")
    @Column(nullable = false, length = 32)
    private String createManId;

    //备注
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
            return LockStockOrderState.getTxtByValue(this.getOrderState());
        }
        return null;
    }

    //锁库自定义返回
    public JSONObject getLockStockOrderData(){
        if (this.getLockStockOrder() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getLockStockOrder().getId());//id
            jsonObject.put("orderNo", this.getLockStockOrder().getOrderNo());//单号
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}
