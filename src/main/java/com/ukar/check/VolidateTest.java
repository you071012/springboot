package com.ukar.check;

import java.math.BigDecimal;

/**
 * @author jia.you
 * @date 2019/02/26
 */
public class VolidateTest {

    public static void main(String[] args) throws Exception {
        DemoReq demoReq = new DemoReq();
        demoReq.setReqDate("20190101");
        demoReq.setSysId("12345678");
        demoReq.setAmt(BigDecimal.ONE);
        demoReq.setCustId("123456789");
        demoReq.setSubAcctId("123456789");
        Validate validate = new Validate();
        validate.validate(demoReq);
    }

}
