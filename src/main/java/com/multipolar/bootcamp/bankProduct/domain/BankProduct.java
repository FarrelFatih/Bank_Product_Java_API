package com.multipolar.bootcamp.bankProduct.domain;

import java.io.Serializable;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Document(collection = "productDB")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BankProduct implements Serializable {
    @Id
    private String id;
    @NotEmpty(message = "Product Name is required")
    private String productName;
    private ProductType productType;
    private Double interestRate;
    private Double minimumBalance;
    private Double maximumLoanAmount;
    private String termsAndConditions;
    private LocalDateTime dateOfCreation = LocalDateTime.now();
}
