package com.app.Dao;


import com.app.Model.Product;
import com.app.Model.ProductDetails;

import java.util.List;

public interface ProductDAO {
   Product addProduct(ProductDetails product);
   Product findById(int id);
   Product findByProductDetails(ProductDetails productDetails);
   void deleteById( int id);
   void update (ProductDetails product);
   List<Product> getAllProduct();
   List<Product>findByName(String searchName);

}
