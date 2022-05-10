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
public class Enterprise implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String country;

    private String city;

    private Integer supplyCenterId;

    private String industry;

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
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public Integer getSupplyCenterId() {
        return supplyCenterId;
    }

    public void setSupplyCenterId(Integer supplyCenterId) {
        this.supplyCenterId = supplyCenterId;
    }
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
            "id=" + id +
            ", name=" + name +
            ", country=" + country +
            ", city=" + city +
            ", supplyCenterId=" + supplyCenterId +
            ", industry=" + industry +
        "}";
    }
}
