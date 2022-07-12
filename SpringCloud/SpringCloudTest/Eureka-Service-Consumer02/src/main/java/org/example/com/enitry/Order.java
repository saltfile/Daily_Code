package org.example.com.enitry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String OrderNo;
    private String OrderAddress;
    private Double totalPrice;
    private List<Product> productList;
}
