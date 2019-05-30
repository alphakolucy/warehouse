package com.warehouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.util.enums.AdjustOrderState;
import com.warehouse.util.enums.InventoryState;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//个人表
@Entity
@Table(name = "td_community_person")
public class Person implements Serializable {

    /**
     *
     */
//    private static final long serialVersionUID = -273666242775479203L;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "warehouse_stock_id")
//    private WarehouseStock warehouseStock;  //仓库库存
//
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "adjustOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<AdjustOrderLog> adjustOrderLogSet;//调整日志集合

    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32, unique = true)
    private String person_id;

    @NotBlank(message = "姓名不能为空")
    @Column(nullable = false, unique = true, length = 200)
    private String person_name;

    @NotNull(message = "年龄不能为空")
    @Column(nullable = false)
    private int person_age;

    @NotNull(message = "性别不能为空")
    @Column(nullable = false)
    private String person_gender;


    /**
     * 社区
     */
    @Column(length = 200)
    private String person_community;

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public int getPerson_age() {
        return person_age;
    }

    public void setPerson_age(int person_age) {
        this.person_age = person_age;
    }

    public String getPerson_gender() {
        return person_gender;
    }

    public void setPerson_gender(String person_gender) {
        this.person_gender = person_gender;
    }

    public String getPerson_community() {
        return person_community;
    }

    public void setPerson_community(String person_community) {
        this.person_community = person_community;
    }
}
