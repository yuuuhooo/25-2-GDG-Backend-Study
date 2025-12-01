package com.example.shop.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor
public class Product {

    // 등록순으로 부여되는 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    // unique한 제품 코드.
    @Column(unique = true, nullable = false, name = "product_code")
    private String productCode;

    // 제품명
    @Column(name = "product_name", length = 40)
    private String name;

    // 재고
    @Column(name = "product_stock")
    private Long stock;

    // 판매가
    @Column(name = "product_price")
    private Long price;

    // 상태
    @Column(name = "product_status", length = 25)
    private String status;


    // 생성자
    public Product(String productCode, String name, Long stock, Long price, String status) {
        this.productCode = productCode;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
    }

    // 제품 정보 수정
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

    // 판매 중지
    public void discontinue() {

        this.status = "판매 중지";
    }
}
