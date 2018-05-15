package com.ukar.entity;

import java.math.BigDecimal;

/**
 * Created by jyou on 2018/5/5.
 */
public class UserInfo {

    private String accountCode;

    private String avatar;

    private String nickName;

    private BigDecimal praiseRate;

    private BigDecimal totalTradeNumber;

    private BigDecimal trustedNumber;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public BigDecimal getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(BigDecimal praiseRate) {
        this.praiseRate = praiseRate;
    }

    public BigDecimal getTotalTradeNumber() {
        return totalTradeNumber;
    }

    public void setTotalTradeNumber(BigDecimal totalTradeNumber) {
        this.totalTradeNumber = totalTradeNumber;
    }

    public BigDecimal getTrustedNumber() {
        return trustedNumber;
    }

    public void setTrustedNumber(BigDecimal trustedNumber) {
        this.trustedNumber = trustedNumber;
    }
}
