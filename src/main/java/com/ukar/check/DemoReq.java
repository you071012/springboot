package com.ukar.check;

import com.ukar.annotation.ValidateAnno;

import java.math.BigDecimal;

/**
 * @author jia.you
 * @date 2019/02/26
 */
public class DemoReq extends BaseRequest {

    @ValidateAnno(maxLength = 9)
    private String custId;

    @ValidateAnno(maxLength = 16)
    private String subAcctId;

    @ValidateAnno(maxLength = 5, nullAble = true)
    private String extInfo;

    private BigDecimal amt;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getSubAcctId() {
        return subAcctId;
    }

    public void setSubAcctId(String subAcctId) {
        this.subAcctId = subAcctId;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}
