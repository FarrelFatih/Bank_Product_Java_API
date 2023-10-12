package com.multipolar.bootcamp.bankProduct.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.multipolar.bootcamp.bankProduct.domain.BankProduct;
import com.multipolar.bootcamp.bankProduct.dto.ErrorMessage;
import com.multipolar.bootcamp.bankProduct.service.ProductService;

@RestController
@RequestMapping("/bankProduct")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody BankProduct bankProduct,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorMessage> validationErrors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setCode("VALIDATION_ERROR");
                errorMessage.setMessage(error.getDefaultMessage());
                validationErrors.add(errorMessage);
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }
        BankProduct createProduct = productService.createOrUpdate(bankProduct);
        return ResponseEntity.ok(createProduct);
    }

    @PutMapping("/{id}")
    public BankProduct updateProduct(@PathVariable String id, @RequestBody BankProduct bankProduct) {
        bankProduct.setId(id);
        return productService.createOrUpdate(bankProduct);
    }

    @GetMapping
    public List<BankProduct> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankProduct> getByProductId(@PathVariable String id) {
        Optional<BankProduct> bankProduct = productService.getProductById(id);
        return bankProduct.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}