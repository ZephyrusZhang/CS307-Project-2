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
public class Contract implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String contractNumber;

    private LocalDate contractDate;

    private Integer contractManagerId;

    private String contractType;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }
    public Integer getContractManagerId() {
        return contractManagerId;
    }

    public void setContractManagerId(Integer contractManagerId) {
        this.contractManagerId = contractManagerId;
    }
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Override
    public String toString() {
        return "Contract{" +
            "contractNumber=" + contractNumber +
            ", contractDate=" + contractDate +
            ", contractManagerId=" + contractManagerId +
            ", contractType=" + contractType +
        "}";
    }
}
