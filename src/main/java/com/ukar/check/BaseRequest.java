package com.ukar.check;

import com.ukar.annotation.ValidateAnno;

/**
 * @author jia.you
 * @date 2019/02/26
 */
public class BaseRequest {

    @ValidateAnno(maxLength = 8)
    private String reqDate;

    @ValidateAnno(maxLength = 8)
    private String sysId;

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }
}
