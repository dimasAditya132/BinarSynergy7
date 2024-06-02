package com.example.BinFood.service;

public interface InvoiceService {
    byte[] generateInvoice(String UserId, String orderId);
}
