package com.warehouse.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//仓库锁定日志(针对库层)
@Entity
@Table(name = "td_warehouse_lock_log")
public class WarehouseLockLog implements Serializable {

    private static final long serialVersionUID = 8279491883748123735L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse; //库层

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "操作人不能为空")
	@Column(nullable = false,  length = 200)
	private String createMan;

	@NotBlank(message = "操作人ID不能为空")
	@Column(nullable = false,  length = 32)
	private String createManId;

	//备注
	@Column(length = 1000)
	private String remark;

	//操作类型(true:锁定;false:解锁)
	private Boolean opType;

	@Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

    //操作类型Txt
    public String getOpTypeTxt() {
        if(this.getOpType() == null){
            return "";
        }
        if(this.getOpType()){
            return "锁定";
        }else {
            return "解锁";
        }
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

    public Boolean getOpType() {
        return opType;
    }

    public void setOpType(Boolean opType) {
        this.opType = opType;
    }
}
