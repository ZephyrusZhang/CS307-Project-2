package edu.sustech.cs307.entity;

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
public class Orders implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String contractNumber;

    private Integer enterpriseId;

    private Integer productModelId;

    private Integer quantity;

    private LocalDate estimatedDeliveryDate;

    private LocalDate lodgementDate;

    private Integer salesmanId;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public Integer getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Integer productModelId) {
        this.productModelId = productModelId;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }
    public LocalDate getLodgementDate() {
        return lodgementDate;
    }

    public void setLodgementDate(LocalDate lodgementDate) {
        this.lodgementDate = lodgementDate;
    }
    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    @Override
    public String toString() {
        return "Orders{" +
            "contractNumber=" + contractNumber +
            ", enterpriseId=" + enterpriseId +
            ", productModelId=" + productModelId +
            ", quantity=" + quantity +
            ", estimatedDeliveryDate=" + estimatedDeliveryDate +
            ", lodgementDate=" + lodgementDate +
            ", salesmanId=" + salesmanId +
        "}";
    }
}
