package com.warehouse.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.AdjustOrderState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//龙门吊绑秤日志
@Entity
@Table(name = "td_crane_bind_steelyard_log")
public class CraneBindSteelyardLog implements Serializable {
    private static final long serialVersionUID = -2763832789197306495L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "crane_id")
    private Crane crane; //龙门吊

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "steelyard_id")
    private Steelyard steelyard; //秤

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

	//备注(解绑必填)
	@Column(length = 1000)
	private String remark;

	//操作类型(true:绑定;false:解绑)
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
            return "绑定";
        }else {
            return "解绑";
        }
    }

    //秤自定义返回
    public JSONObject getSteelyardData() {
        if (this.getSteelyard() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getSteelyard().getId());//id
            jsonObject.put("steelyardNo", this.getSteelyard().getSteelyardNo());//编号
            return jsonObject;
        }
        return null;
    }

    //龙门吊自定义返回
    public JSONObject getCraneData() {
        if (this.getCrane() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getCrane().getId());//id
            jsonObject.put("craneNo", this.getCrane().getCraneNo());//编号
            return jsonObject;
        }
        return null;
    }

    public Crane getCrane() {
        return crane;
    }

    public void setCrane(Crane crane) {
        this.crane = crane;
    }

    public Steelyard getSteelyard() {
        return steelyard;
    }

    public void setSteelyard(Steelyard steelyard) {
        this.steelyard = steelyard;
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
