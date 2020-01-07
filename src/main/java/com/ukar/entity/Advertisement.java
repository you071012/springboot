package com.ukar.entity;

import java.math.BigDecimal;

/**
 * Created by jyou on 2018/5/5.
 */
public class Advertisement {


    private Long id;

    private String userCode;

    private Long createdTime;

    private String digitalAlias;

    private Long digitalCurrencyId;

    private Long isOffShelf;

    private String legalAlias;

    private Long legalTenderType;

    private BigDecimal maxAmount;

    private BigDecimal minAmount;

    private BigDecimal premiumRate;

    private BigDecimal price;

    private Integer status;

    private BigDecimal stockNumber;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getDigitalAlias() {
        return digitalAlias;
    }

    public void setDigitalAlias(String digitalAlias) {
        this.digitalAlias = digitalAlias;
    }

    public Long getDigitalCurrencyId() {
        return digitalCurrencyId;
    }

    public void setDigitalCurrencyId(Long digitalCurrencyId) {
        this.digitalCurrencyId = digitalCurrencyId;
    }

    public Long getIsOffShelf() {
        return isOffShelf;
    }

    public void setIsOffShelf(Long isOffShelf) {
        this.isOffShelf = isOffShelf;
    }

    public String getLegalAlias() {
        return legalAlias;
    }

    public void setLegalAlias(String legalAlias) {
        this.legalAlias = legalAlias;
    }

    public Long getLegalTenderType() {
        return legalTenderType;
    }

    public void setLegalTenderType(Long legalTenderType) {
        this.legalTenderType = legalTenderType;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getPremiumRate() {
        return premiumRate;
    }

    public void setPremiumRate(BigDecimal premiumRate) {
        this.premiumRate = premiumRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(BigDecimal stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
