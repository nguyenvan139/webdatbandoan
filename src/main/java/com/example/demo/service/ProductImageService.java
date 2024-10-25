package com.example.demo.service;

import com.example.demo.model.ProductImage;
import com.example.demo.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    public void saveProductImages(List<ProductImage> productImages) {
        productImageRepository.saveAll(productImages);
    }
}
