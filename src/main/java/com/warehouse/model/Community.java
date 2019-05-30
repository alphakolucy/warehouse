package com.warehouse.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class Community {


    //仓库(仓库＞库区＞库位＞库层)
    @Entity
    @Table(name = "community")
    public class Warehouse implements Serializable {

        /**
         *
         */
//        private static final long serialVersionUID = 107070153579356233L;

//        @JsonIgnore
//        @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//        private Set<WarehouseLockLog> warehouseLockLogSet;//锁定日志集合

        @Id
        @Column(nullable = false, unique = true)
        private int id;


        @NotBlank(message = "名称不能为空")
        @Column(nullable = false, length = 200)
        private String name;


        @NotNull(message = "年龄不能为空")
        @Column(nullable = false)
        private int age;


        @NotBlank(message = "性别不能为空")
        @Column(nullable = false)
        private String gender;



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Warehouse() {
        }

        public Warehouse(String name, int age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

    }


}
