package edu.sustech.cs307.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
public class Staff implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private String gender;

    private String number;

    private String mobileNumber;

    private String type;

    private Integer supplyCenterId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getSupplyCenterId() {
        return supplyCenterId;
    }

    public void setSupplyCenterId(Integer supplyCenterId) {
        this.supplyCenterId = supplyCenterId;
    }

    @Override
    public String toString() {
        return "Staff{" +
            "id=" + id +
            ", name=" + name +
            ", age=" + age +
            ", gender=" + gender +
            ", number=" + number +
            ", mobileNumber=" + mobileNumber +
            ", type=" + type +
            ", supplyCenterId=" + supplyCenterId +
        "}";
    }
}
