package com.warehouse.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.warehouse.util.enums.DeviceType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//设备维护台账
@Entity
@Table(name = "td_device_maintain_ledger")
public class DeviceMaintainLedger implements Serializable {
    private static final long serialVersionUID = 5583869923274094943L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable=false,length=32, unique = true)
    private String id;

    //设备编号
    @Column(length = 1000)
    @NotBlank(message = "设备编号不能为空")
    private String deviceNo;

    //设备id
    @Column(length = 100)
    @NotBlank(message = "设备Id不能为空")
    private String deviceId;

    //设备类型(枚举：DeviceType)
    @NotNull(message = "设备类型不能为空")
    private Integer deviceType;

    //维护日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private Date maintainDate;

    //维护内容
    @Column(length = 1000)
    private String content;

    //来自排班
    @NotBlank(message = "维护人员不能为空")
    @Column(nullable = false,  length = 200)
    private String maintainMan;

    @NotBlank(message = "创建人不能为空")
    @Column(nullable = false,  length = 200)
    private String createMan;

    @NotBlank(message = "创建人ID不能为空")
    @Column(nullable = false,  length = 32)
    private String createManId;

    //备注
    @Column(length = 1000)
    private String remark;

    @Column(columnDefinition="datetime default now()")
    @Generated(GenerationTime.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    //返回设备类型对应的枚举Txt
    public String getDeviceTypeTxt(){
        if(this.getDeviceType()!=null){
            return DeviceType.getTxtByValue(this.getDeviceType());
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Date getMaintainDate() {
        return maintainDate;
    }

    public void setMaintainDate(Date maintainDate) {
        this.maintainDate = maintainDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMaintainMan() {
        return maintainMan;
    }

    public void setMaintainMan(String maintainMan) {
        this.maintainMan = maintainMan;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
