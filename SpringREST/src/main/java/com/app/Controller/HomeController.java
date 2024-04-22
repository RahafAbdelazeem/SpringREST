package com.app.Controller;


import com.app.Exception.ProductBadRequest;
import com.app.Exception.ProductNotFoundException;
import com.app.Exception.Responce.ProductErrorResponce;
import com.app.Model.Product;
import com.app.Model.ProductDetails;
import com.app.Service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import java.util.List;

 @RestController
 @Api(tags=" this is the documentation for Product APIs")

public class HomeController {
     @Autowired
     private ProductService productService;


     @GetMapping("/getProducts")
     public ResponseEntity<List<Product>> getAllProduct() {
         return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.ACCEPTED);
     }

     @GetMapping("/Products")
     @ApiOperation(value = "this api will  git the product by id")
     @ApiResponses(value = {
             @ApiResponse(code = 200, message = "this is the successful responce"),
             @ApiResponse(code = 404, message = "the is not found  responce"),
             @ApiResponse(code = 500, message = "this is the responce when there is server error")
     })
     public Product getProductById(@RequestParam(value = "productId", required = false) int id) throws ProductNotFoundException {

              return productService.findById(id);

     }


     @PostMapping("/products")
     @ApiOperation(value = "this api will add product")
     public ProductDetails AddProduct(@RequestBody ProductDetails productDetails) throws ProductBadRequest {
         return productService.Add(productDetails);
     }

     @PutMapping("/products")
     @ApiOperation(value = "this api willupdate the productdetails")
     public ProductDetails updateProduct(@RequestBody ProductDetails productDetails) {
         return productService.Update(productDetails);
     }

     @DeleteMapping("/products")
     @ApiOperation(value = "this api will delete the product using id")
     public void deleteProduct(@RequestParam("productId") int id) {
         productService.deleteById(id);

     }

     @GetMapping("/products/productDetails")
     @ApiOperation(value = "this api willget productdetails by id")
     public ProductDetails getProductDetailsByproductId(@RequestParam("productId") int id) throws ProductNotFoundException {
         Product product = productService.findById(id);
         ProductDetails productDetails = product.getProductDetails();
         return productDetails;
     }


     @GetMapping("/ProductByName")
     @ApiOperation(value = "this api will search foe the product by name")
     public List<Product> getProductByName(@RequestParam("searchName") String searchName) {
         return productService.findByName(searchName);
     }


 }




