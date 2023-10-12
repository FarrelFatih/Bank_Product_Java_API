package com.multipolar.bootcamp.bankProduct.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.multipolar.bootcamp.bankProduct.domain.BankProduct;

public interface ProductRepository extends MongoRepository<BankProduct, String> {
    Optional<BankProduct> findByProductName(String productName);
}
