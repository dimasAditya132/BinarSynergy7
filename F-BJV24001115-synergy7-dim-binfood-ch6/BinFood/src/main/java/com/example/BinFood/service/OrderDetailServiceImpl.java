package com.example.BinFood.service;

import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.repository.OrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> createBatchesOrder(List<OrderDetail> orderDetailList) {
        orderDetailList = orderDetailRepository.saveAll(orderDetailList);
        log.info("Order detail data succesfully created");
        return orderDetailList;
    }

    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.fetchAllOrderDetail();
    }

    @Override
    public List<OrderDetail> getAllOrdersDetailPageable(int pageNumber, int pageAmount) {
        Pageable pageable = PageRequest.of(pageNumber, pageAmount);
        Page<OrderDetail> orderDetailPage = orderDetailRepository.findAll(pageable);
        return orderDetailPage.toList();
    }
}
