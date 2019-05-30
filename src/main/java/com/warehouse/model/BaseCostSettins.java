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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.CostSettinsChargeUnit;
import com.warehouse.util.enums.CostSettinsFeePurpose;
import com.warehouse.util.enums.CostSettinsOpType;


//基础计费方式
@Entity
@Table(name = "td_base_cost_settins")
public class BaseCostSettins implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7294910192035945961L;

	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id")
    private ProductCates cate;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable=false,length=32, unique = true)
    private String id;

    @NotNull(message = "收费类型不能为空")
    @Column(nullable = false, length=10)
    private Integer opType; // 入库、出库、加工、移库、对接、过户、托盘、仓储

    @NotNull(message = "费用不能为空")
    @Column(nullable = false, columnDefinition = "decimal(11,2) default '0.00'")
    private BigDecimal fee;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date endTime;

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

    //是否使用
    private Boolean isUse;

    //计费单位(按吨或车),暂时没用，默认设为吨
    private Integer chargeUnit;

    //费用用途(用于理重或实重计算)
    //理重:对应委托单的计算规则;实重:对应计量单的计算规则
    private Integer feePurpose;


    //物资分类自定义返回
    public JSONObject getCateData() {
        if (this.getCate() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getCate().getId());//id
            jsonObject.put("productCateName", this.getCate().getProductCateName());//物资分类名称
            return jsonObject;
        }
        return null;
    }

    //返回收费类型对应的枚举Txt
    public String getOpTypeTxt(){
        if(this.getOpType()!=null){
            return CostSettinsOpType.getTxtByValue(this.getOpType());
        }
        return null;
    }

    //返回计费单位对应的枚举Txt
    public String getChargeUnitTxt(){
        if(this.getChargeUnit()!=null){
            return CostSettinsChargeUnit.getTxtByValue(this.getChargeUnit());
        }
        return null;
    }

    //返回费用用途对应的枚举Txt
    public String getFeePurposeTxt(){
        if(this.getFeePurpose()!=null){
            return CostSettinsFeePurpose.getTxtByValue(this.getFeePurpose());
        }
        return null;
    }

    public ProductCates getCate() {
        return cate;
    }

    public void setCate(ProductCates cate) {
        this.cate = cate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(Integer chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public Integer getFeePurpose() {
        return feePurpose;
    }

    public void setFeePurpose(Integer feePurpose) {
        this.feePurpose = feePurpose;
    }
}
