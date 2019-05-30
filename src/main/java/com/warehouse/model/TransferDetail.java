package com.warehouse.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//过户明细
@Entity
@Table(name = "td_transfer_detail")
public class TransferDetail implements Serializable {

    private static final long serialVersionUID = -854190843017874642L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "transfer_id")
    private Transfer transfer;  //过户单

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_stock_id")
    private WarehouseStock warehouseStock;  //仓库库存

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String id;


    @NotNull(message = "库存件数不能为空")
    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal num;

    @NotNull(message = "库存吨数不能为空")
    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal tonNum;

    //@NotNull(message = "费用不能为空")
    @Column(columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal fee;

    @Column(columnDefinition = "datetime default now()")
    @Generated(GenerationTime.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    //过户单自定义返回
    public JSONObject getTransferData() {
        if (this.getTransfer() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getTransfer().getId());//id
            jsonObject.put("orderNo", this.getTransfer().getOrderNo());
            return jsonObject;
        }
        return null;
    }

    //仓库自定义返回
    public JSONObject getWarehouseStockData() {
        if (this.getWarehouseStock() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouseStock().getId());//id
            jsonObject.put("createTime", this.getWarehouseStock().getCreateTime());//创建时间
            jsonObject.put("num", this.getWarehouseStock().getNum());
            jsonObject.put("tonNum", this.getWarehouseStock().getTonNum());

            JSONObject jsonObject1 = new JSONObject();
            if (this.getWarehouseStock().getProduct() != null) {
                jsonObject1.put("productName", this.getWarehouseStock().getProduct().getProductName());//物资名称
                jsonObject1.put("id", this.getWarehouseStock().getProduct().getId());//id
                jsonObject1.put("unit", this.getWarehouseStock().getProduct().getUnit());//单位
                jsonObject1.put("rationale", this.getWarehouseStock().getProduct().getRationale());//理重
                jsonObject1.put("spec", this.getWarehouseStock().getProduct().getSpec());//规格
                jsonObject1.put("model", this.getWarehouseStock().getProduct().getModel());//型号
                jsonObject1.put("material", this.getWarehouseStock().getProduct().getMaterial());//材料
                jsonObject1.put("maker", this.getWarehouseStock().getProduct().getMaker());//厂家
                jsonObject1.put("productCateName", this.getWarehouseStock().getProduct().getCate().getProductCateName());//分类名
            }
            jsonObject.put("productData", jsonObject1);


            JSONObject jsonObject2 = new JSONObject();
            if (this.getWarehouseStock().getCustomer() != null) {
                jsonObject2.put("name", this.getWarehouseStock().getCustomer().getName());//客户名称
                jsonObject2.put("id", this.getWarehouseStock().getCustomer().getId());//id
            }
            jsonObject.put("customerData", jsonObject2);

            JSONObject jsonObject3 = new JSONObject();
            if (this.getWarehouseStock().getWarehouseSite() != null) {
                jsonObject3.put("name", this.getWarehouseStock().getWarehouseSite().getName());//仓库名称
                jsonObject3.put("id", this.getWarehouseStock().getWarehouseSite().getId());//id
            }
            jsonObject.put("warehouseSiteData", jsonObject3);

            JSONObject jsonObject4 = new JSONObject();
            if (this.getWarehouseStock().getWarehouseSite() != null) {
                jsonObject4.put("name", this.getWarehouseStock().getWarehouseArea().getName());//库区名称
                jsonObject4.put("id", this.getWarehouseStock().getWarehouseArea().getId());//id
            }
            jsonObject.put("warehouseAreaData", jsonObject4);


            JSONObject jsonObject5 = new JSONObject();
            if (this.getWarehouseStock().getWarehouseSite() != null) {
                jsonObject5.put("name", this.getWarehouseStock().getWarehouse().getName());//库位名称
                jsonObject5.put("id", this.getWarehouseStock().getWarehouse().getId());//id
            }
            jsonObject.put("warehouseData", jsonObject5);

            JSONObject jsonObject6 = new JSONObject();
            if (this.getWarehouseStock().getWarehouseLevel() != null) {
                jsonObject5.put("name", this.getWarehouseStock().getWarehouseLevel().getName());//库位名称
                jsonObject5.put("id", this.getWarehouseStock().getWarehouseLevel().getId());//id
            }
            jsonObject.put("warehouseLevelData", jsonObject6);


            return jsonObject;
        }
        return null;
    }


    public WarehouseStock getWarehouseStock() {
        return warehouseStock;
    }

    public void setWarehouseStock(WarehouseStock warehouseStock) {
        this.warehouseStock = warehouseStock;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
