package com.atguigu.paymentdemo.service;

import com.atguigu.paymentdemo.entity.OrderInfo;
import com.atguigu.paymentdemo.enums.OrderStatus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo createOrderByProductId(Long producId, String paymentType);

    void saveCodeUrl(String orderNo,String codeUrl);

    List<OrderInfo> listOderByCreateTimeDesc();

    void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus);

    void deleteOrderByOrderNo(String orderNo);

    String getOrderStatus(String orderNo);

    public List<OrderInfo> getNoPayOrderByDuration(int minutes, String paymentType);

    OrderInfo getOrderByOrderNo(String orderNo);
}
