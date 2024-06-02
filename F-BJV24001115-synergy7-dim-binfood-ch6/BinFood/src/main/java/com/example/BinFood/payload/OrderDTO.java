package com.example.BinFood.payload;

import com.example.BinFood.model.accounts.User;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private String orderId;
    private String userName;
    private String destinationAddress;
    private List<OrderDetailDTO> orderDetailList;

    public void setUser(User user) {
        this.userName = user.getUsername();
    }

    public void setId(UUID id) {
        this.orderId = id.toString();
    }

}
