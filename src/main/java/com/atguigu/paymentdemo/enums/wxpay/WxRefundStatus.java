package com.atguigu.paymentdemo.enums.wxpay;

import lombok.AllArgsConstructor;
import lombok.Getter;
//商户平台和微信支付平台之间的
@AllArgsConstructor
@Getter
public enum WxRefundStatus {

    /**
     * 退款成功
     */
    SUCCESS("SUCCESS"),

    /**
     * 退款关闭
     */
    CLOSED("CLOSED"),

    /**
     * 退款处理中
     */
    PROCESSING("PROCESSING"),

    /**
     * 退款异常
     */
    ABNORMAL("ABNORMAL");

    /**
     * 类型
     */
    private final String type;
}
