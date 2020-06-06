package com.lilanz.printer.entity;

import lombok.Data;

import java.util.List;

@Data
public class Courier {
    private String key;

    private String templateid;

    private String title;

    private String storeName;

    private String orderid;

    private String orderTime;

    private String memberName;

    private String transactionNumber;

    private String receiver;

    private String contactNumber;

    private String receiverAddress;

    private List<Order> orderList;

    private String afterSales;

    private List<String> reason;
}
