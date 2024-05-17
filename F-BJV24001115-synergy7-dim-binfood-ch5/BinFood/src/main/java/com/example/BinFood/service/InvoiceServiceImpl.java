package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.response.InvoiceResponse;
import com.example.BinFood.model.response.OrderDetailResponse;
import com.example.BinFood.repository.OrderDetailRepository;
import com.example.BinFood.repository.OrderRepository;
import com.example.BinFood.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public byte[] generateInvoice(String username) throws FileNotFoundException, JRException {
        List<InvoiceResponse> invoiceResponses = new ArrayList<>();
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        Order userLatestOrder = orderRepository.getLatestOrderOfUser(username);
        final JasperPrint[] invoice = new JasperPrint[1];

        orderDetailRepository.querySelectDetail(userLatestOrder.getId())
                .forEach(orderDetail -> {
                    invoiceResponses.add(InvoiceResponse.builder()
                            .productName(productRepository.getReferenceById(String.valueOf(orderDetail.getProductId().getId())).getProductName())
                            .price("Rp. " + orderDetail.getTotal_price())
                            .quantity((long) orderDetail.getQuantity())
                            .build());

                    orderDetailResponses.add(OrderDetailResponse
                            .builder()
                            .productFinalPrice(orderDetail.getTotal_price())
                            .build());

                    Map<String, Object> parameterMap = new HashMap<>();
                    parameterMap.put("Username", username);
                    parameterMap.put("Final price", Double.toString(orderDetailService.totalPriceInCart(orderDetailResponses)));
                    parameterMap.put("OrderDetail", invoiceResponses);
                    try {
                        invoice[0] = JasperFillManager.fillReport(
                                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:invoice.jrxml").getAbsolutePath()),
                                parameterMap,
                                new JRBeanCollectionDataSource(invoiceResponses)
                        );
                    } catch (JRException | FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        if (invoice[0] != null) {
            return JasperExportManager.exportReportToPdf(invoice[0]);
        } else {
            throw new JRException("Invoice is null");
        }
    }
}
