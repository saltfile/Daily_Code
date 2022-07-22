package org.example.com.service;

import org.example.com.enity.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServicempl implements ProductService{


    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1,"小米手机",2,5682D),
                new Product(2,"联想电脑",5,5655D),
                new Product(3,"红米电脑",4,4682D),
                new Product(4,"神州电脑",9,6682D),
                new Product(5,"外星人电脑",3,16825D)
        );
    }
}
