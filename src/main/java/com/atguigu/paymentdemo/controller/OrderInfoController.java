package com.atguigu.paymentdemo.controller;


import com.atguigu.paymentdemo.entity.OrderInfo;
import com.atguigu.paymentdemo.enums.OrderStatus;
import com.atguigu.paymentdemo.service.OrderInfoService;
import com.atguigu.paymentdemo.vo.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin //开放前端的跨域访问
@Api(tags = "订单管理")
@RestController
@RequestMapping("/api/order-info")
public class OrderInfoController {




    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/list")
    public R list(){

        List<OrderInfo> list = orderInfoService.listOderByCreateTimeDesc();
        return R.ok().data("list",list);

    }

    /**
     * 查询订单状态
     * @param orderNo
     * @return
     */
    @GetMapping("query-order-status/{orderNo}")
    public R queryOrderStatus(@PathVariable String orderNo){

        String orderStatus = orderInfoService.getOrderStatus(orderNo);
        if(OrderStatus.SUCCESS.getType().equals(orderStatus)){
            return R.ok().setMessage("支付成功");//支付成功
        }

        return R.ok().setCode(101).setMessage("支付中....");
    }

    /**
     * 根据订单号删除订单
     * @param orderNo
     * @return
     * @throws Exception
     */
    @PostMapping("/delete/{orderNo}")
    public R delete (@PathVariable String orderNo) throws Exception{
        orderInfoService.deleteOrderByOrderNo(orderNo);
        return R.ok().setMessage("删除成功");
    }
}
