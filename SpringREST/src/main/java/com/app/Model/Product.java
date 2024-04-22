package com.app.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
@ApiModel("this class  is responsible  for product")


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("this is product id")
    private int productId;

    @NotBlank(message = "Product name cannot be blank.")
    @Size(max = 64, message = "Product name cannot exceed 64 characters.")
    @Column(name="name")
    private String productName;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;



    public Product(String productName) {this.productName = productName; }

    public Product(int productId) {this.productId = productId;}
}
