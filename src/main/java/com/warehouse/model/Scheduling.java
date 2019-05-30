package com.warehouse.model;

import java.io.Serializable;
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

import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

//排班表
@Entity
@Table(name = "td_worker_scheduling")
public class Scheduling implements Serializable {

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_site_id")
    private Warehouse warehouseSite;//所属仓库(从worker取出)

	/**
	 *
	 */
	private static final long serialVersionUID = 2311026284274673946L;

	@NotBlank(message = "名称不能为空")
	@Column(nullable = false, length = 200)
	private String name; //与工作人员一致，方便查询。从worker取出

    @NotBlank(message = "岗位不能为空")
	@Column(nullable = false, length = 100)
	private String job;  //与工作人员岗位一致，方便查询。

    private String groupName; //分组

    private String classesName; //班次

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false,length=32, unique = true)
    private String id;

//	@NotNull(message = "当值时间不能为空")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date schedulingTime;

	@Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

	//删除状态(默认=false,删除=true)
	private Boolean deleteStatus;

    //仓库自定义返回
    public JSONObject getWarehouseSiteData() {
        if (this.getWarehouseSite() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouseSite().getId());//id
            jsonObject.put("name", this.getWarehouseSite().getName());//名称
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

	public Date getSchedulingTime() {
		return schedulingTime;
	}

	public void setSchedulingTime(Date schedulingTime) {
		this.schedulingTime = schedulingTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Warehouse getWarehouseSite() {
        return warehouseSite;
    }

    public void setWarehouseSite(Warehouse warehouseSite) {
        this.warehouseSite = warehouseSite;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}
}
