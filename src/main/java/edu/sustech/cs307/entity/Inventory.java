package edu.sustech.cs307.entity;

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
public class Inventory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer supplyCenterId;

    private Integer productModelId;

    private Integer count;

    public Integer getSupplyCenterId() {
        return supplyCenterId;
    }

    public void setSupplyCenterId(Integer supplyCenterId) {
        this.supplyCenterId = supplyCenterId;
    }
    public Integer getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Integer productModelId) {
        this.productModelId = productModelId;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Inventory{" +
            "supplyCenterId=" + supplyCenterId +
            ", productModelId=" + productModelId +
            ", count=" + count +
        "}";
    }
}
