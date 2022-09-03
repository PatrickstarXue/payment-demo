package com.atguigu.paymentdemo.task;

import com.atguigu.paymentdemo.entity.OrderInfo;
import com.atguigu.paymentdemo.enums.PayType;
import com.atguigu.paymentdemo.service.OrderInfoService;
import com.atguigu.paymentdemo.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

@Slf4j
@Component
public class WxPayTask{

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private WxPayService wxPayService;

    /**
     * 秒 分 时 日 月 周
     * *:指定
     * ？:不指定
     * 1-3：1开始3结束
     * 0/3:0开始每个3执行
     * 1，2，3：指定1，2，3执行
     * 日与周互斥
     */
//    @Scheduled(cron = "0/3 * * * * ?")
    public void task1(){
//        log.info("task1 execute");
//        Instant now = Instant.now();
//        log.info("now==>{}",now);
    }

    /**
     * 0s开始每个30s执行查询超过5分钟未支付的订单
     */
    @Scheduled(cron = "0/300 * * * * ?")
    public void orderConfirm() throws Exception {
        log.info("orderConfirm 被执行...");
        List<OrderInfo> orderInfoList = orderInfoService.getNoPayOrderByDuration(1,PayType.WXPAY.getType());

        for (OrderInfo orderInfo : orderInfoList) {
            String orderNo = orderInfo.getOrderNo();
            log.warn("超时订单==={}",orderNo);

            //核实订单状态，嗲偶哦那个微信支付查单接口
            wxPayService.checkOrderStatus(orderNo);
        }
    }
}
