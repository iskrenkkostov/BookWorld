package com.example.bookworld.configs.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product with given id not found.")
public class ProductNotFoundException extends RuntimeException{
    private long productId;

    public ProductNotFoundException(long productId) {
        super("Product with ID: " + productId + " was not found.");

        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

}
