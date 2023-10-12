package com.multipolar.bootcamp.bankProduct.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multipolar.bootcamp.bankProduct.domain.BankProduct;
import com.multipolar.bootcamp.bankProduct.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<BankProduct> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<BankProduct> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Optional<BankProduct> getProductByName(String productName) {
        return productRepository.findByProductName(productName);

    }

    public BankProduct createOrUpdate(BankProduct product) {
        return productRepository.save(product);
    }

    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

}
