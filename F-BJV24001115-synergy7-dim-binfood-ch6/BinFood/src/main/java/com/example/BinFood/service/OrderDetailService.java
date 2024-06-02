package com.example.BinFood.service;

import com.example.BinFood.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> createBatchesOrder(List<OrderDetail> orderDetailList);

    List<OrderDetail> getAllOrderDetail();

    List<OrderDetail> getAllOrdersDetailPageable(int pageNumber, int pageAmount);

}
