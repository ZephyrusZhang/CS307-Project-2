package edu.sustech.cs307.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@TableName("center_record")
public class CenterRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer supplyCenterId;

    private Integer productModelId;

    private Integer staffId;

    private LocalDate date;

    private Integer purchasePrice;

    private Integer quantity;

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
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CenterRecord{" +
            "supplyCenterId=" + supplyCenterId +
            ", productModelId=" + productModelId +
            ", staffId=" + staffId +
            ", date=" + date +
            ", purchasePrice=" + purchasePrice +
            ", quantity=" + quantity +
        "}";
    }
}
