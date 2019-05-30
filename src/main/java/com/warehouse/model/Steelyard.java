package com.warehouse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

//秤
@Entity
@Table(name = "td_steelyard")
public class Steelyard implements Serializable {

	@OneToMany(mappedBy = "steelyard")
    private List<Crane> crane; // 部门集合

	/**
	 *
	 */
	private static final long serialVersionUID = -9068022166646186830L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;  //只关联第1级

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	@NotBlank(message = "秤编号不能为空")
	@Column(nullable = false, length = 200)
	private String steelyardNo;

    //维护周期(按天)
    @NotNull(message = "维护周期不能为空")
    private Integer maintainPeriod;

    //投用日期
    private Date putIntoDate;

    //维护日期, 初始值=投用日期+维护周期,后续值=台账维护日期+维护周期
    private Date maintainDate;

    private Boolean isUse; //是否启用

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

	public String getSteelyardNo() {
		return steelyardNo;
	}

	public void setSteelyardNo(String steelyardNo) {
		this.steelyardNo = steelyardNo;
	}

    public Integer getMaintainPeriod() {
        return maintainPeriod;
    }

    public void setMaintainPeriod(Integer maintainPeriod) {
        this.maintainPeriod = maintainPeriod;
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

	public List<Crane> getCrane() {
		return crane;
	}

	public void setCrane(List<Crane> crane) {
		this.crane = crane;
	}


}
