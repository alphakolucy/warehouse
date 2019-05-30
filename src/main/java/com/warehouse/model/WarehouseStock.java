package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

//仓库库存
@Entity
@Table(name = "td_warehouse_stock")
public class WarehouseStock implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6961667475293422905L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_site_id")
    private Warehouse warehouseSite;//仓库

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_area_id")
    private Warehouse warehouseArea;//库区

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;//库位

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_level_id")
    private Warehouse warehouseLevel;//库层

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;//客户

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;//物资

    @JsonIgnore
    @OneToMany(mappedBy = "warehouseStock", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WarehouseStockLog> warehouseStockLogSet;//仓库库存日志集合

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

    @NotBlank(message = "操作人不能为空")
    @Column(nullable = false, length = 200)
    private String createMan;

    @NotBlank(message = "操作人ID不能为空")
    @Column(nullable = false, length = 32)
    private String createManId;

    @Column(columnDefinition="datetime default now()")
	@Generated(GenerationTime.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;

    //库存客户自定义返回
    public JSONObject getCustomerData() {
        if (this.getCustomer() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getCustomer().getId());//id
            jsonObject.put("name", this.getCustomer().getName());//企业名称
            return jsonObject;
        }
        return null;
    }

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

    //库区自定义返回
    public JSONObject getWarehouseAreaData() {
        if (this.getWarehouseArea() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouseArea().getId());//id
            jsonObject.put("name", this.getWarehouseArea().getName());//名称
            return jsonObject;
        }
        return null;
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
    //库层自定义返回
    public JSONObject getWarehouseLevelData() {
        if (this.getWarehouseLevel() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getWarehouseLevel().getId());//id
            jsonObject.put("name", this.getWarehouseLevel().getName());//名称
            return jsonObject;
        }
        return null;
    }

    //库存物资自定义返回
    public JSONObject getProductData() {
        if (this.getProduct() != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", this.getProduct().getId());//id
            jsonObject.put("productName", this.getProduct().getProductName());//物资名称
            jsonObject.put("unit", this.getProduct().getUnit());//单位
            jsonObject.put("rationale", this.getProduct().getRationale());//理重
            jsonObject.put("spec", this.getProduct().getSpec());//规格
            jsonObject.put("model", this.getProduct().getModel());//型号
            jsonObject.put("material", this.getProduct().getMaterial());//材料
            jsonObject.put("maker", this.getProduct().getMaker());//厂家
            jsonObject.put("productCateName", this.getProduct().getCate().getProductCateName());//分类名
            jsonObject.put("fullName", this.getProduct().getFullName());//全称
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<WarehouseStockLog> getWarehouseStockLogSet() {
        return warehouseStockLogSet;
    }

    public void setWarehouseStockLogSet(List<WarehouseStockLog> warehouseStockLogSet) {
        this.warehouseStockLogSet = warehouseStockLogSet;
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Warehouse getWarehouseSite() {
		return warehouseSite;
	}

	public void setWarehouseSite(Warehouse warehouseSite) {
		this.warehouseSite = warehouseSite;
	}

	public Warehouse getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(Warehouse warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public Warehouse getWarehouseLevel() {
		return warehouseLevel;
	}

	public void setWarehouseLevel(Warehouse warehouseLevel) {
		this.warehouseLevel = warehouseLevel;
	}


}
