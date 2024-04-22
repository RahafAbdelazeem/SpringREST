package com.app.Service;

import com.app.Exception.ProductBadRequest;
import com.app.Exception.ProductNotFoundException;
import com.app.Model.Product;
import com.app.Model.ProductDetails;


import javax.crypto.BadPaddingException;
import java.util.List;

public interface ProductService {
     ProductDetails Add(ProductDetails product) throws  ProductBadRequest;
     Product findById(int Id) throws ProductNotFoundException;
     void deleteById(int id);
     ProductDetails Update(ProductDetails product);
     List<Product> getAllProduct();
     List<Product>findByName(String name);
}
