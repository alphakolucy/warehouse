package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
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

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

//仓库(仓库＞库区＞库位＞库层)
@Entity
@Table(name = "td_warehouse")
public class Warehouse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 107070153579356233L;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<WarehouseLockLog> warehouseLockLogSet;//锁定日志集合

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

	/**父组织*/
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="parent_id")
    private Warehouse parent; //parent_id为null为库区  最后一级为库位

    /**子组织*/
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Set<Warehouse> child = new HashSet<>();

    @NotBlank(message = "名称不能为空")
	@Column(nullable = false, length = 200)
	private String name;

	//@NotBlank(message = "省不能为空")
	@Column(length = 300)
	private String province;

	//@NotBlank(message = "市不能为空")
	@Column(length = 300)
	private String city;

	//@NotBlank(message = "区不能为空")
	@Column(length = 300)
	private String area;

	//地区编号
    @Column(length = 100)
    private String regionCode;

    //件数最大容量(目前只对库位这一级)
    private BigDecimal numMaxCapacity;

    //吨数最大容量(目前只对库位这一级)
    private BigDecimal tonNumMaxCapacity;

    //锁定状态(目前只对库层这一级)，同时记录日志
    //true:已锁定;false:未锁定(默认值)
    private Boolean lockState;

    //层级顺序(1,2,3,4)
    private Integer levelOrder;

    private Boolean isUse; //是否启用

    //父级自定义返回
    public JSONObject getParentData() {
        if (this.getParent() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getParent().getId());//id
            jsonObject.put("name", this.getParent().getName());//名称
            return jsonObject;
        }
        return null;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Warehouse getParent() {
		return parent;
	}

	public void setParent(Warehouse parent) {
		this.parent = parent;
	}

	public Set<Warehouse> getChild() {
		return child;
	}

	public void setChild(Set<Warehouse> child) {
		this.child = child;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public BigDecimal getNumMaxCapacity() {
        return numMaxCapacity;
    }

    public void setNumMaxCapacity(BigDecimal numMaxCapacity) {
        this.numMaxCapacity = numMaxCapacity;
    }

    public BigDecimal getTonNumMaxCapacity() {
        return tonNumMaxCapacity;
    }

    public void setTonNumMaxCapacity(BigDecimal tonNumMaxCapacity) {
        this.tonNumMaxCapacity = tonNumMaxCapacity;
    }

    public Boolean getLockState() {
        return lockState;
    }

    public void setLockState(Boolean lockState) {
        this.lockState = lockState;
    }

    public Set<WarehouseLockLog> getWarehouseLockLogSet() {
        return warehouseLockLogSet;
    }

    public void setWarehouseLockLogSet(Set<WarehouseLockLog> warehouseLockLogSet) {
        this.warehouseLockLogSet = warehouseLockLogSet;
    }

    public Integer getLevelOrder() {
        return levelOrder;
    }

    public void setLevelOrder(Integer levelOrder) {
        this.levelOrder = levelOrder;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }
}
