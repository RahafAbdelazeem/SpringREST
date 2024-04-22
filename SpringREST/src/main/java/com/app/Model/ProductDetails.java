package com.app.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name="product_details")
@Setter
@Getter
@NoArgsConstructor
@ApiModel("this class that describe productdetails")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @ApiModelProperty("this is product details id")
    private int   id;

    @NotBlank(message = "Product name cannot be blank.")
    @Size(max = 64, message = "Product name cannot exceed 64 characters.")
    @Column(name="name")
    @ApiModelProperty("this id product name")
    private String  name;

    @NotNull(message = "date cannot be null")
    @Temporal(TemporalType.DATE)
    @Column(name="expiration_date")
@ApiModelProperty( "this indicte the expiration date of the product")
    private Date expirationDate;

    @Column(name="manufacturer")
    private  String  manufacturer;

    @NotNull(message = "Product price cannot be null.")
    @Min(value = 0, message = "Product price cannot be negative.")
    @Column(name= "price")
    private  Double price;

   @NotNull(message = "available cannot be null")
    @Column(name=" available")
   @ApiModelProperty("this  check for the avalibilty  of the product")
    private  Boolean available;

    @OneToOne(mappedBy = "productDetails" , cascade = CascadeType.ALL)
    private Product product;

    public ProductDetails(String name, Date expirationDate, String manufacturer, Double price, Boolean available) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
        this.price = price;
        this.available = available;
    }
}
