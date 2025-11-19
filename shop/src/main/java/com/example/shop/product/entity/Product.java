package com.example.shop.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(unique = true, nullable = false, name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_stock")
    private Long stock;

    @Column(name = "product_price")
    private Long price;

    @Column(name = "product_status", length = 25)
    private String status;


    public Product(String productCode, String name, Long stock, Long price, String status) {
        this.productCode = productCode;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
    }

    public void updateInfo(String name, Long price, String status) {
        if(name != null) {
            this.name = name;
        }
        if(price != null) {
            this.price = price;
        }
        if(status != null) {
            this.status = status;
        }
    }

    public void discontinue() {
        this.status = "판매 중지";
    }
}
