package com.warehouse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
//龙门吊
@Entity
@Table(name = "td_crane")
public class Crane implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7529142777045511657L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;  //库区,只关联第2级

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "steelyard_id")
    private Steelyard steelyard;  //秤(绑定-解绑-绑定)

    @JsonIgnore
    @OneToMany(mappedBy = "crane", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CraneBindSteelyardLog> craneBindSteelyardLogSet;//绑定日志集合

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "吊车编号不能为空")
	@Column(nullable = false, length = 200)
	private String craneNo;

	//维护周期(按天)
    @NotNull(message = "维护周期不能为空")
    private Integer maintainPeriod;

    //投用日期
    private Date putIntoDate;

    //维护日期, 初始值=投用日期+维护周期,后续值=台账维护日期+维护周期
    private Date maintainDate;

    private Boolean isUse; //是否启用

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

	public String getCraneNo() {
		return craneNo;
	}

	public void setCraneNo(String craneNo) {
		this.craneNo = craneNo;
	}

    public Steelyard getSteelyard() {
        return steelyard;
    }

    public void setSteelyard(Steelyard steelyard) {
        this.steelyard = steelyard;
    }

    public Integer getMaintainPeriod() {
        return maintainPeriod;
    }

    public void setMaintainPeriod(Integer maintainPeriod) {
        this.maintainPeriod = maintainPeriod;
    }

    public Set<CraneBindSteelyardLog> getCraneBindSteelyardLogSet() {
        return craneBindSteelyardLogSet;
    }

    public void setCraneBindSteelyardLogSet(Set<CraneBindSteelyardLog> craneBindSteelyardLogSet) {
        this.craneBindSteelyardLogSet = craneBindSteelyardLogSet;
    }

    public Date getPutIntoDate() {
        return putIntoDate;
    }

    public void setPutIntoDate(Date putIntoDate) {
        this.putIntoDate = putIntoDate;
    }

    public Date getMaintainDate() {
        return maintainDate;
    }

    public void setMaintainDate(Date maintainDate) {
        this.maintainDate = maintainDate;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }
}
