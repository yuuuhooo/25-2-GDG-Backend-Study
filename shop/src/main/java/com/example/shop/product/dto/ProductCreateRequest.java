package com.example.shop.product.dto;

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    @NotNull(message = ErrorMessage.PRODUCTCODE_NOT_NULL)
    private String productCode;

    private String productName;
    private Long stock;
    private Long price;
    private String status;

    public ProductCreateRequest(String productCode, String productName, Long stock, Long price, String status) {
        this.productCode = productCode;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        this.status = status;
    }
}
